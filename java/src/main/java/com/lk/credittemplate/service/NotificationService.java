package com.lk.credittemplate.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.emailNotification.LoginResultBean;
import com.lk.credittemplate.emailNotification.MailDetailsBean;
import com.lk.credittemplate.emailNotification.MailStatus;
import com.lk.credittemplate.emailNotification.UserEmail;
import com.lk.credittemplate.model.Audit;

@Service
public class NotificationService {

	public static String emailApiUrl="http://ap2.lendingkart.com:8880/lkping";
	
	public static final String APPROVED_ANALYSIS_INTERNAL_MAIL_FOR_LKSCORE =  "Analysis_Approved_For_Lkscore_Internal"; 
	public static final String FINAL_APPROVAL_MAIL="Final_Approval_Internal_Mail";
	public static final String NO_REPLY_MAIL_ID="noreply@lendingkart.com";
	

	public LoginResultBean getAuthToken() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		headers.add("User-Agent", "Mozilla");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		RestTemplate restTemplate = new RestTemplate();
		String url = emailApiUrl + "/login";
		UserEmail userEmail = new UserEmail();
		userEmail.setUserName("lkping");
		userEmail.setPassWord("pingpong");
		String json = gson.toJson(userEmail);
		HttpEntity<String> httpEntity = new HttpEntity<String>(json, headers);
		LoginResultBean out = restTemplate.postForObject(url, httpEntity, LoginResultBean.class);
		return out;
}
	
	public MailStatus sendInternalMail(String userRegistractionMail, String noReplyEmail,
            Map<String, String> variables, LoginResultBean loginResultBean, Set<String> tomail,
            boolean mailRetryEnabled, String waitTime, String url) {
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
     MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
     headers.add("Content-Type", "application/json");
     headers.add("User-Agent", "Mozilla");
     headers.add("Auth-Token", loginResultBean.getSession_auth_token());
     RestTemplate restTemplate = new RestTemplate();
     MailDetailsBean detailsBean = new MailDetailsBean();
     detailsBean.setTemplateId(userRegistractionMail);
     detailsBean.setMailFrom(noReplyEmail);
     detailsBean.setMailTo(tomail);
     detailsBean.setVariables(variables);
     detailsBean.setMailRetryEnabled(mailRetryEnabled);
     detailsBean.setWaitTime(waitTime);
     String json = gson.toJson(detailsBean);
     HttpEntity<String> httpEntity = new HttpEntity<String>(json, headers);
     MailStatus mailStatus = restTemplate.postForObject(url, httpEntity, MailStatus.class);
     return mailStatus;
}
	
	 public void sendDataScienceMail(LoanMaster loanForMail, String fromMail) {
      try {
             LoginResultBean loginResultBean = getAuthToken();
             Map<String, String> variables = new HashMap<String, String>();
             if (loginResultBean != null && loginResultBean.isStatus()) {
                   variables.put("userName", loanForMail.getCompanyName());
                   variables.put("applicationId", loanForMail.getApplicationId());
                   if (StringUtils.isNotEmpty(loanForMail.getClsApplicationId())) {
                          variables.put("clsApplicationId", loanForMail.getClsApplicationId());
                   } else {
                          variables.put("clsApplicationId", "");
                   }
                   Set<String> tomail = new HashSet<String>();
                 tomail.add("lkdatascience@lendingkart.com");
                 tomail.add("credit@lendingkart.com");
                   String url = emailApiUrl + "/sendtemplatemail";
                   MailStatus mailStatus = sendInternalMail(APPROVED_ANALYSIS_INTERNAL_MAIL_FOR_LKSCORE, fromMail, variables, loginResultBean,
                                 tomail, false, "5", url);
                   if (mailStatus != null && mailStatus.isSent_status()) {
                   } else {
                   }
             }
      } catch (Exception e) {
             e.printStackTrace();
      }
}
	 public void sendInternalMailforFinalApproval(LoanMaster loanforMail,Audit audit) {
			try {
				LoginResultBean loginResultBean = getAuthToken();
				if (loginResultBean != null && loginResultBean.isStatus()) {
					// HashMap<String,String> variables=new
					// HashMap<String,String>();
					Map<String, String> variables = new HashMap<String, String>();
					variables.put("userName", loanforMail.getCompanyName());
					variables.put("applicationId", loanforMail.getApplicationId());
//					variables.put("accountId", exists.getLoanId());
					if (StringUtils.isNotEmpty(loanforMail.getClsApplicationId())) {
						variables.put("clsApplicationId", loanforMail.getClsApplicationId());
					} else {
						variables.put("clsApplicationId", "");
					}
					if (StringUtils.isNotEmpty(loanforMail.getAssignedTo())) {
						variables.put("AssignTo", loanforMail.getAssignedTo());
					} else {
						variables.put("AssignTo", "");
					}
					Set<String> tomail = new HashSet<String>();
					tomail.add("credit@lendingkart.com");
					tomail.add(audit.getUserMaster().getEmail());
					String url = emailApiUrl + "/sendtemplatemail";
					MailStatus mailStatus = sendInternalMail(FINAL_APPROVAL_MAIL, NO_REPLY_MAIL_ID, variables,
							loginResultBean, tomail, false, "5", url);
					if (mailStatus != null && mailStatus.isSent_status()) {
					} else {
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

}
