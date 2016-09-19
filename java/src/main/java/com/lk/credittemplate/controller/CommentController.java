package com.lk.credittemplate.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.beans.ApprovalBean;
import com.lk.credittemplate.beans.AssigneeUserBean;
import com.lk.credittemplate.beans.CommentsBean;
import com.lk.credittemplate.beans.StatusListBean;
import com.lk.credittemplate.beans.UnitCommentsBean;
import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.common.util.LKUtils;
import com.lk.credittemplate.dao.StatusTrackerDao;
import com.lk.credittemplate.domain.SendMail;
import com.lk.credittemplate.model.AsigneeApps;
import com.lk.credittemplate.model.Audit;
import com.lk.credittemplate.model.LoanComments;
import com.lk.credittemplate.model.LoanCommentsCat;
import com.lk.credittemplate.model.LoanCommentsCibil;
import com.lk.credittemplate.model.StatusTracker;
import com.lk.credittemplate.model.UserMaster;
import com.lk.credittemplate.service.ApprovalService;
import com.lk.credittemplate.service.DataService;
import com.lk.credittemplate.service.ExcelService;
import com.lk.credittemplate.service.NotificationService;
import com.lk.credittemplate.service.ParseExcel;

@Controller
public class CommentController {

	@Autowired
	ExcelService excelService;

	@Autowired
	ParseExcel parseExcel;
	
	@Autowired
	DataService dataService;
	
	@Autowired
	ApprovalService approvalService;
	
	@Autowired 
	NotificationService notificationService;
	
	@Autowired
	StatusTrackerDao trackerDao;
	

	private static final Logger logger = Logger.getLogger(CommentController.class);
	
	
	
	static Map<String,Integer> roleMap = new HashMap<String,Integer>();
	static Map<Integer, String> userMap = new HashMap<Integer, String>();
	static Map<Integer, String> statusMap = new HashMap<Integer, String>();
	
	
	static{
		
		roleMap.put("Credit Manager",2);
		roleMap.put("AVP",3);
		roleMap.put("COO",4);
		roleMap.put("Credit Analyst",1);

		userMap.put(2, "ed7835b5-ad6d-11e5-8af7-0290fb34887d");//Credit Manager
		userMap.put(3, "7feaca8d-ad6e-11e5-8af7-0290fb34887d");//AVP
		userMap.put(4, "10821c59-ba82-11e5-8af7-0290fb34887d");//COO
		
		//Substatuses
		statusMap.put(1,"1f974f0c-62b3-11e5-988d-d6b2fad01013"); //CAT in progress
		statusMap.put(2,"1f974f0c-62b3-11e5-988d-d6b2fad01016");
		statusMap.put(3,"1f974f0c-62b3-11e5-988d-d6b2fad01017");
		statusMap.put(4,"1f974f0c-62b3-11e5-988d-d6b2fad01018");
	}
	
	
	@RequestMapping(value = "/showCibilComments/{loanid}/{directorid}", method = RequestMethod.GET)
	public @ResponseBody String getCibilComments(@PathVariable("loanid") String appId
			,@PathVariable("directorid") String dirId) {

		
		List<UnitCommentsBean> commentsBeanList = new ArrayList<UnitCommentsBean>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		DirectorMaster directorMaster = dataService.getByDirectorId(dirId); 

		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<LoanCommentsCibil> commentsList = approvalService.getCibilCommentsByLoanIdAndDirId(appId,directorMaster);
		try{
		for (LoanCommentsCibil comment : commentsList) {
			
			UnitCommentsBean commentsBean = new UnitCommentsBean();
			commentsBean.setLastModifiedDate(df.format(comment.getCreatedDate()));
			commentsBean.setUserId(comment.getUserMaster().getName());
			commentsBean.setRemarks(comment.getRemarks());
			commentsBeanList.add(commentsBean);
			}
		
			return gson.toJson(commentsBeanList);
		}
		catch(Exception e){
			return gson.toJson("Error");
		}

	}

	@RequestMapping(value = "/saveCibilComments", method = RequestMethod.POST)
	public @ResponseBody String getCommentsApproval(@RequestBody UnitCommentsBean cibilCommentsBean) {
		
		LoanCommentsCibil cibilComments = new LoanCommentsCibil();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = "";
		
		try{
			DirectorMaster directorMaster = dataService.getByDirectorId(cibilCommentsBean.getDirId()); 
			UserMaster userMaster = dataService.getByUserId(cibilCommentsBean.getUserId());
			
			cibilComments.setAppId(cibilCommentsBean.getAppId());
			cibilComments.setCreatedDate(Calendar.getInstance().getTime());
			cibilComments.setDirectorMaster(directorMaster);
			cibilComments.setUserMaster(userMaster);
			cibilComments.setRemarks(cibilCommentsBean.getRemarks());
			cibilComments.setUpdatedDate(Calendar.getInstance().getTime());
			
			approvalService.saveCibilComments(cibilComments);
			
			json = "Saved Successfully";
		}
		catch(Exception e){
			//e.printStackTrace();
			json = "Couldnt Save !Error";
		}
		
		return gson.toJson(json);
	}
	
	@RequestMapping(value = "/api/insertComments", method = RequestMethod.GET)
	public void submitForApproval(HttpServletRequest request) {
		
		try{
		UserMaster userMaster = dataService.getByUserId("000dcf38-ce21-11e5-a29c-06acb01be40f"); 
		
		LoanCommentsCibil loan_Comments_Cibil = new LoanCommentsCibil();
		loan_Comments_Cibil.setCreatedDate(Calendar.getInstance().getTime());
		loan_Comments_Cibil.setRemarks("wshsdkhakdh");
		loan_Comments_Cibil.setUpdatedDate(Calendar.getInstance().getTime());
		loan_Comments_Cibil.setUserMaster(userMaster);
		loan_Comments_Cibil.setAppId("sajhdjahdjkh");
		
		approvalService.saveCibilComments(loan_Comments_Cibil);;
		
		}
		catch(Exception e){
			//e.printStackTrace();
		}
	}

	@RequestMapping(value = "/api/insertCatComments", method = RequestMethod.GET)
	public void submitForNhp(HttpServletRequest request) {
		
		try{
		UserMaster userMaster = dataService.getByUserId("000dcf38-ce21-11e5-a29c-06acb01be40f"); 
		
		LoanCommentsCat loanCommentsCat  = new LoanCommentsCat();
		
		loanCommentsCat.setCreatedDate(Calendar.getInstance().getTime());
		loanCommentsCat.setRemarks("wshsdkhakdh");
		loanCommentsCat.setUpdatedDate(Calendar.getInstance().getTime());
		loanCommentsCat.setUserMaster(userMaster);
		loanCommentsCat.setAppId("sajhdjahdjkh");
		loanCommentsCat.setStatus("GG");
		
		approvalService.saveCatComments(loanCommentsCat);
		
		}
		catch(Exception e){
			//e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/getComments/{loanid}", method = RequestMethod.GET)
	public @ResponseBody String getCommentsApproval(@PathVariable("loanid") String appId) {
		
		CommentsBean commentsBean = new CommentsBean();
		List<UnitCommentsBean> commentsBeanList = new ArrayList<UnitCommentsBean>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		
		try{
			
			LoanMaster loanMaster = dataService.findLoanMasterId(appId);
			
			commentsBean.setRejectionReason("");
			if(loanMaster.getStatusId().equals("280e5bda-62b3-11e5-988d-d6b2fad02237")){
			commentsBean.setRejectionReason(loanMaster.getRejectionReason());
		}
		
			List<LoanComments> commentsList = approvalService.getCatCommentsByLoanId(appId);	
		for (LoanComments comment : commentsList) {
			
			cal.setTime(comment.getCreatedDate());
			cal.add(Calendar.HOUR_OF_DAY, 5);
			cal.add(Calendar.MINUTE, 30);
		
			UnitCommentsBean cibilCommentsBean = new UnitCommentsBean();
			if(comment instanceof LoanCommentsCibil){
				
				LoanCommentsCibil newObj = (LoanCommentsCibil)comment;
				
				cibilCommentsBean.setLastModifiedDate(df.format(cal.getTime()));
				cibilCommentsBean.setUserId(((LoanCommentsCibil)comment).getUserMaster().getName());
				cibilCommentsBean.setRemarks(comment.getRemarks());
				cibilCommentsBean.setDirId(newObj.getDirectorMaster().getdName());
				cibilCommentsBean.setTag(comment.getType());
			}
			else{
				LoanCommentsCat newObj = (LoanCommentsCat)comment;
				
				cibilCommentsBean.setLastModifiedDate(df.format(cal.getTime()));
				cibilCommentsBean.setUserId(comment.getUserMaster().getName());
				cibilCommentsBean.setRemarks(comment.getRemarks());
				cibilCommentsBean.setStatus(newObj.getStatus());
				cibilCommentsBean.setTag(comment.getType());
			}
			
			
			commentsBeanList.add(cibilCommentsBean);
			}
		
		commentsBean.setUnitCommentsList(commentsBeanList);
		
		return gson.toJson(commentsBean);
		
		}catch(Exception e){
			//e.printStackTrace();
			return gson.toJson("Error");

		
		
		}

	}
	
	@RequestMapping(value = "/submitTemplate", method = RequestMethod.POST)
	public @ResponseBody String submitForApproval(@RequestBody ApprovalBean approvalBean, HttpServletRequest request) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = "";
		String emailMsg;
		
		LoanCommentsCat loanCommentsCat = new LoanCommentsCat();
		loanCommentsCat.setCreatedDate(Calendar.getInstance().getTime());
		loanCommentsCat.setAppId(approvalBean.getAppId());
		loanCommentsCat.setRemarks(approvalBean.getRemarks());
		loanCommentsCat.setStatus(approvalBean.getStatus());
		loanCommentsCat.setUpdatedDate(Calendar.getInstance().getTime());
		

		try {
			
			UserMaster analystUser = dataService.getByUserId(approvalBean.getUserId());
			
			loanCommentsCat.setUserMaster(analystUser);
			
			Audit audit = excelService.findSubmit(approvalBean.getAppId());
			
			audit.setSubmitFlag(true);
			
			AsigneeApps asigneeApps = approvalService.findAssignedAppById(approvalBean.getAppId());

			if (asigneeApps == null) {

				asigneeApps = new AsigneeApps();
				asigneeApps.setCreated(Calendar.getInstance().getTime());
				asigneeApps.setLoanId(approvalBean.getAppId());
				asigneeApps.setStatus("OnGoing");
				asigneeApps.setType("Normal");
			}
			
			LoanMaster loanMaster  = dataService.findLoanMasterId(approvalBean.getAppId());
	
			UserMaster userMaster = dataService.getByUserId(approvalBean.getAssignedId());
			asigneeApps.setUserMaster(userMaster);
		    emailMsg = "<br><br><br> An application has been <b>assigned</b> to you.";
			
		    loanMaster.setStatusId("1f974f0c-62b3-11e5-988d-d6b2fad01015");
		    loanMaster.setSubStatus("1f974f0c-62b3-11e5-988d-d6b2fad01016");
		    loanMaster.setStatusNotificationFlag(LKUtils.getStatusNoficationFlag(loanMaster));
		    
			approvalService.commitSubmitForApproval(asigneeApps,audit,loanCommentsCat,loanMaster);
			
			String link = "http://app.lendingkart.com/CreditTemplate/"+loanMaster.getApplicationId()+"?role="+asigneeApps.getUserMaster().getUserId();
			
			String msg = "Hello "+ asigneeApps.getUserMaster().getName() +","+emailMsg+"<br><br> Please find the link below: <br>";
			
			sendMail(asigneeApps.getUserMaster().getEmail(), msg,link,loanMaster.getApplicationId());
			
			json = gson.toJson("Successfully Submitted");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed",e);
			json = gson.toJson("Couldnt Save");
		}

		return json;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	@RequestMapping(value = "/saveComments", method = RequestMethod.POST)
	public @ResponseBody String saveCommentsApproval(@RequestBody ApprovalBean approvalBean) {

		int flag = 0;
		String msg;
		String emailMsg = "";
		boolean dataScienceMail = false;
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		LoanCommentsCat loanCommentsCat = new LoanCommentsCat();
		loanCommentsCat.setCreatedDate(Calendar.getInstance().getTime());
		loanCommentsCat.setAppId(approvalBean.getAppId());
		loanCommentsCat.setRemarks(approvalBean.getRemarks());
		loanCommentsCat.setStatus(approvalBean.getStatus());
		loanCommentsCat.setUpdatedDate(Calendar.getInstance().getTime());
		
		
	  LoanMaster loanMaster = dataService.findLoanMasterId(approvalBean.getAppId());
	  String loanId = loanMaster.getLoanMastertId();
	  Audit audit = excelService.findSubmitByLoanId(loanId);
		try {
			
			
			 StatusTracker statusTracker=trackerDao.getStatusTrackerByAppId(loanMaster.getApplicationId());
			
	
			if(statusTracker ==null)
			{
				 statusTracker= new StatusTracker();
				 statusTracker.setCreatedDate(new Date());
				 statusTracker.setApplicationId(loanMaster.getApplicationId());
				 statusTracker.setStatusChangeDate(new Date());
			}
			
				statusTracker.setFlag(false);
				statusTracker.setUserID(approvalBean.getUserId());
				statusTracker.setStatus(loanMaster.getStatusId());
				statusTracker.setStatusChangeDate(new Date());
			
			UserMaster currentUser = dataService.getByUserId(approvalBean.getUserId());
			loanCommentsCat.setUserMaster(currentUser);
			
			int curPerm = currentUser.getRoleMaster().getLevel();
			
			AsigneeApps asigneeApps = approvalService.findAssignedAppById(approvalBean.getAppId());
			
			if (asigneeApps == null) {
				asigneeApps = new AsigneeApps();
				asigneeApps.setType("Normal");
				asigneeApps.setLoanId(approvalBean.getAppId());
				asigneeApps.setCreated((Calendar.getInstance().getTime()));
				
			}
			
			asigneeApps.setStatus(approvalBean.getStatus());
			
			if (approvalBean.getStatus().equals("Final Approval") || approvalBean.getStatus().equals("Rejected")) {
				
				asigneeApps.setUserMaster(null);
				asigneeApps.setType("Normal");
				loanMaster.setModified(Calendar.getInstance().getTime());

				if (approvalBean.getStatus().equals("Final Approval")){
					loanMaster.setStatusId("deea51d2-65ed-11e5-97cd-da43beef7680");
					loanMaster.setSubStatus(null);
					notificationService.sendInternalMailforFinalApproval(loanMaster,audit);
					
				}
				else{
					
					String concatReasons = "";
					for(String s : approvalBean.getRejectionReasons()){
						
						concatReasons += s+";";
					}
					
					loanMaster.setStatusId("280e5bda-62b3-11e5-988d-d6b2fad02237");
					loanMaster.setSubStatus("1f974f0c-62b3-11e5-988d-d6b2fad01033");
					loanMaster.setRejectionReason(concatReasons.substring(0, concatReasons.length()-1));
				}
				
			} else if (approvalBean.getStatus().equals("Assign To")) {
				
				asigneeApps.setType("ReAssigned");
				UserMaster reAssigned = dataService.getByUserId(approvalBean.getAssignedId());
				asigneeApps.setUserMaster(reAssigned);
				loanMaster.setSubStatus(statusMap.get(reAssigned.getRoleMaster().getLevel()));
				if(reAssigned.getRoleMaster().getLevel() == 1){
					loanMaster.setStatusId("dd1affa8-62c0-11e5-988d-d6b2fad02237");
				}
				
				emailMsg = "<br><br><br> An application has been <b>Re-assigned</b> to you.";

			} else if (approvalBean.getStatus().equals("Approved")) {
				
				emailMsg = "<br><br><br> An application has been <b>assigned</b> to you.";
				asigneeApps.setType("Normal");
				UserMaster userMaster = dataService.getByUserId(userMap.get(curPerm + 1));
				asigneeApps.setUserMaster(userMaster);
				loanMaster.setSubStatus(statusMap.get(userMaster.getRoleMaster().getLevel()));
				if(curPerm == 2){
					loanMaster.setDsFlag((short)1);
					dataScienceMail = true;
				}
							
			} else {
				flag = 1;
			}

			if (flag == 0) {
				
				loanMaster.setModified(Calendar.getInstance().getTime());
				loanMaster.setStatusNotificationFlag(LKUtils.getStatusNoficationFlag(loanMaster));
				approvalService.commitCommentTrans(loanCommentsCat,asigneeApps,loanMaster,statusTracker);
				msg = "Saved Successfully";
				
				if(asigneeApps.getUserMaster() != null){
				String link = "http://app.lendingkart.com/CreditTemplate/"+approvalBean.getApplicationId()+"?role="+asigneeApps.getUserMaster().getUserId();
				emailMsg = "Hello "+ asigneeApps.getUserMaster().getName() +","+emailMsg+"<br><br> Please find the link below: <br>";
				sendMail(asigneeApps.getUserMaster().getEmail(),emailMsg,link,approvalBean.getApplicationId());
				
				}
				
			} else {
				msg = "Wrong Status Sent";
			}
			
			if(dataScienceMail){
				
				notificationService.sendDataScienceMail(loanMaster,currentUser.getEmail());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed",e);
			msg = "Could Not Save Error!!!";
		}
		
		return gson.toJson(msg);

	}

	@RequestMapping(value = "/getStatusList/{roleName}", method = RequestMethod.GET)
	public @ResponseBody String getStatusListByRole(@PathVariable("roleName") String rName) {

		List<StatusListBean> statusList = new ArrayList<StatusListBean>();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		StatusListBean listBeanObj = new StatusListBean();

		if (rName.equals("Credit Manager")) {

			listBeanObj.setName("Approved");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(false);
			statusList.add(listBeanObj);

			listBeanObj = new StatusListBean();

			listBeanObj.setName("Rejected");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(false);
			statusList.add(listBeanObj);

			listBeanObj = new StatusListBean();

			listBeanObj.setName("Assign To");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(true);
			statusList.add(listBeanObj);

		} else if (rName.equals("AVP")) {

			listBeanObj.setName("Approved");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(false);
			statusList.add(listBeanObj);

			listBeanObj = new StatusListBean();

			listBeanObj.setName("Rejected");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(false);
			statusList.add(listBeanObj);

			listBeanObj = new StatusListBean();

			listBeanObj.setName("Assign To");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(true);
			statusList.add(listBeanObj);

			listBeanObj = new StatusListBean();

			listBeanObj.setName("Final Approval");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(false);
			statusList.add(listBeanObj);
		} else if (rName.equals("COO")) {

			listBeanObj.setName("Rejected");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(false);
			statusList.add(listBeanObj);

			listBeanObj = new StatusListBean();

			listBeanObj.setName("Assign To");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(true);
			statusList.add(listBeanObj);

			listBeanObj = new StatusListBean();

			listBeanObj.setName("Final Approval");
			listBeanObj.setValue(false);
			listBeanObj.setDropdown(false);
			statusList.add(listBeanObj);

		} else {

		}

		return gson.toJson(statusList);

	}
	
	public void sendMail(String userEmail,String msg,String link,String appId) throws MessagingException {

		String host = "outlook.office365.com";//smtp.outlook.com
        String port = "587";
        String mailFrom = "noreply@lendingkart.com";
        String password = "Buna8755";
 
        // outgoing message information
        
        LoanMaster loanMaster = dataService.getApplicationByAppId(appId);
        
        String mailTo = userEmail;
        String subject = loanMaster.getCompanyName()+"-"+appId+":Credit Decisioning";
 
         SendMail mailer = new SendMail();
 
        try {
            mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo,
                    subject, "<html><body><p>"+msg+"<a href = "+link+">Credit Template View</a></p><p>Lendingkart Technologies Pvt Ltd</p><br><img style='margin-left:-2%' src=http://lendingkart.com/img/LENDINGKART_LOGO.png>");
            logger.info("Email sent");
 
            
        } catch (Exception ex) {
            logger.info("Failed to sent email.");
        }
	}
	
	
	public void getRejectionReasons(){
		
		
		
	}
	
	
	@RequestMapping(value = "/getAssigneeList/{roleName}", method = RequestMethod.GET)
	public @ResponseBody String getAssigneeList(@PathVariable("roleName") String rName) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try{
		int curLevel = roleMap.get(rName);
		List<UserMaster> userMasterList = dataService.getUsersByLevels(curLevel);
		List<AssigneeUserBean> assigneeBeanList = new ArrayList<AssigneeUserBean>() ;
		
		for(UserMaster user :userMasterList){
			AssigneeUserBean assignee = new AssigneeUserBean();
			assignee.setName(user.getName());
			assignee.setId(user.getUserId());
			
			assigneeBeanList.add(assignee);
		}
		return gson.toJson(assigneeBeanList);
		}
		catch(Exception e){
		return gson.toJson("Invalid Role Name");
			
		}
		 
	}
	
	@RequestMapping(value = "/getManagerList", method = RequestMethod.GET)
	public @ResponseBody String getCreditManagerList() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		List<AssigneeUserBean> managerList = new ArrayList<AssigneeUserBean>();
		
		List<UserMaster> userList = dataService.getRoleList("Credit Manager");

		for(UserMaster user : userList){
		
		AssigneeUserBean assigneeUserBean = new AssigneeUserBean();
		assigneeUserBean.setId(user.getUserId());
		assigneeUserBean.setName(user.getName());
		managerList.add(assigneeUserBean);
		
		}
		
		return gson.toJson(managerList);
	}
	
	public void sendDataScienceEmail(String userId,String appId)
	{
	    final String uri = "https://app.lendingkart.com/lkart/datascience/mail?userId="+userId+"&applicationId="+appId;
	    
	    System.out.println(uri);
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);	     
	    System.out.println(result);
	}
	
//	@RequestMapping(value = "/getRejectionReasons", method = RequestMethod.GET)
//	private static List<String> getRejectionReasonList(){
//	
//	  //String rReas[]= {''};
//	  
//	  return null;
//		
//	}
	

	/*@RequestMapping(value = "/saveRejectionReason", method = RequestMethod.POST)
	public void getRejectionReasons(RejectionReasonBean rejectionReasonBean){
		
		
		
	}*/

	
}