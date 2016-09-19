package com.lk.credittemplate.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
//import java.text.DateFormat;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.credittemplate.beans.MasterBean;
import com.lk.credittemplate.beans.PDSocialPresenceBean;
import com.lk.credittemplate.beans.PersonalCompanyBean;
import com.lk.credittemplate.beans.PersonalDetailsApplicantBean;
import com.lk.credittemplate.beans.PersonalDetailsApplicantInfoBean;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.lk.doctorapp.beans.JsonResponse;
import com.lk.credittemplate.beans.PersonalDetailsBean;
import com.lk.credittemplate.beans.PersonalDetailsBusinessBean;
import com.lk.credittemplate.beans.PersonalDetailsCalculationBean;
import com.lk.credittemplate.beans.PersonalDetailsMarketBean;
import com.lk.credittemplate.model.PersonalDetailsSocialPresence;
import com.lk.credittemplate.model.SellingOnMaster;
import com.mysql.fabric.xmlrpc.base.Array;
import com.lk.credittemplate.beans.PersonalKYCBean;
import com.lk.credittemplate.cibildao.ApplicantDao;
import com.lk.credittemplate.cibildao.DirectorMasterDao;
import com.lk.credittemplate.cibildao.LoanMasterDao;
import com.lk.credittemplate.cibilmodel.DirectorMaster;
import com.lk.credittemplate.cibilmodel.LoanMaster;
import com.lk.credittemplate.common.util.LKUtils;
import com.lk.credittemplate.common.util.StringUtil;
import com.lk.credittemplate.dao.AnalysisDao;
import com.lk.credittemplate.dao.ExcelTableDataDao;
import com.lk.credittemplate.dao.LoanEnquiriesDao;
import com.lk.credittemplate.dao.LoanSellingOnDao;
import com.lk.credittemplate.dao.PersonalDetailsApplicantInfoDao;
import com.lk.credittemplate.dao.PersonalDetailsBusinessDao;
import com.lk.credittemplate.dao.PersonalDetailsCalculationDao;
import com.lk.credittemplate.dao.PersonalDetailsCompanyDao;
import com.lk.credittemplate.dao.PersonalDetailsEycDao;
import com.lk.credittemplate.dao.PersonalDetailsMarketDao;
import com.lk.credittemplate.dao.PersonalDetailsSocialApplicantDao;
import com.lk.credittemplate.dao.PersonalDetailsSocialPresenceDao;
import com.lk.credittemplate.dao.SellingOnMasterDao;
import com.lk.credittemplate.model.Analysis;
import com.lk.credittemplate.model.Applicant;
import com.lk.credittemplate.model.Cibil;
import com.lk.credittemplate.model.LoanSellingOn;
import com.lk.credittemplate.model.PersonalDetailsApplicantDetails;
import com.lk.credittemplate.model.PersonalDetailsBusiness;
import com.lk.credittemplate.model.PersonalDetailsCalculation;
import com.lk.credittemplate.model.PersonalDetailsCompany;
import com.lk.credittemplate.model.PersonalDetailsEYC;
import com.lk.credittemplate.model.PersonalDetailsInfo;
import com.lk.credittemplate.model.PersonalDetailsMarket;
import com.lk.credittemplate.cibildao.LoanMasterDao;

/**
 * @author Pooja Desai
 *
 */
/**
 * @author Pooja Desai
 *
 */
@Service
@Transactional
public class PersonalDetailsService {

	@Autowired
	AnalysisDao analysisDao;

	@Autowired
	LoanMasterDao loanMasterDao;

	@Autowired
	PersonalDetailsCompanyDao personalDetailsCompanyDao;

	@Autowired
	ApplicantDao applicantDao;

	@Autowired
	PersonalDetailsEycDao personalDetailsEycDao;

	@Autowired
	DirectorMasterDao directorMasterDao;

	@Autowired
	PersonalDetailsSocialPresenceDao personalDetailsSocialPresenceDao;

	@Autowired
	PersonalDetailsSocialApplicantDao personalDetailsSocialApplicantDao;

	@Autowired
	PersonalDetailsBusinessDao personalDetailsBusinessDao;

	@Autowired
	PersonalDetailsApplicantInfoDao personalDetailsApplicantInfoDao;

	@Autowired
	PersonalDetailsCalculationDao personalDetailsCalculationDao;

	@Autowired
	PersonalDetailsMarketDao personalDetailsMarketDao;

	@Autowired
	LoanSellingOnDao loanSellingOnDao;

	@Autowired
	SellingOnMasterDao sellingOnMasterDao;
	
 
   
	
	
	// START OF GET METHOD FOR BATCH 1

	public PersonalDetailsBean getPersonalDetails(String appId) {

		Analysis analysis = analysisDao.getAnalysisByAppId(appId);

		if (analysis != null) {
			PersonalDetailsBean detailsBean = new PersonalDetailsBean();
			detailsBean = toPersonalDetailsBean(analysis, detailsBean);
			return detailsBean;
		} else {

			LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(appId);
			Analysis analysisNew = new Analysis();
			if (loanMaster != null) {

				analysisNew.setApplicationID(loanMaster.getApplicationId());
				analysisNew.setClsId(loanMaster.getClsApplicationId());
				analysisNew.setCreatedDate(loanMaster.getCreated());
				analysisNew.setAnalystName(loanMaster.getAssignedTo());
				analysisNew.setLeadSource(loanMaster.getLeadSource());

			}

			PersonalDetailsBean detailsBean = new PersonalDetailsBean();
			detailsBean = toPersonalDetailsBean(analysisNew, detailsBean);

			return detailsBean;
		}

	}
	
	//BATCH 2

	public List<PersonalDetailsCalculationBean> getPersonalCalDetails(String appId) {

		List<PersonalDetailsCalculationBean> personalDetailsCalculation = personalDetailsCalculationDao.getCalculationsByAppId(appId);
		//LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(appId);
		if (personalDetailsCalculation != null) {
			return personalDetailsCalculation;
		} else {
			// personalDetailsCalculation.setCalappId(loanMaster.getApplicationId());
			List<PersonalDetailsCalculationBean> empty = new ArrayList<PersonalDetailsCalculationBean>();
			PersonalDetailsCalculationBean detailsBean = new PersonalDetailsCalculationBean();
			empty.add(detailsBean);
			return empty;
		}
	}
	
	public Map<String,List<PersonalDetailsCalculationBean> > savePersonalCalDetails(List<PersonalDetailsCalculationBean> loanCycle,
			String loanId) {
		
		Map<String,List<PersonalDetailsCalculationBean>> finalResponse = new HashMap<String,List<PersonalDetailsCalculationBean>>();
		//personalDetailsCalculationDao.saveCalculationsByAppId(loanId,loanCycle);
		//List<PersonalDetailsCalculation> personalDetailsCalculation = new ArrayList<PersonalDetailsCalculation>();
		List<PersonalDetailsCalculation> personalDetailsCalculation = new ArrayList<PersonalDetailsCalculation>();
		for(PersonalDetailsCalculationBean tmp: loanCycle){
			PersonalDetailsCalculation response = new PersonalDetailsCalculation();
			response.setBankAccountNum(tmp.getDisbursementAccNum());
			response.setCalappId(tmp.getAppId());
			response.setCalId(tmp.getCalId());
			response.setDisbursementDate(tmp.getDisbursementDate());
			response.setDuration(tmp.getDuration());
			response.seteCollection(tmp.iseCollection());
			response.setInstallmentAmount(tmp.getInstallmentAmount());
			response.setInstallmentDelays(tmp.getInstallmentWithDelay());
			response.setInstallmentEndDate(tmp.getInstallmentEndDate());
			response.setInstallmentFrequency(tmp.getInstallmentFrequency());
			response.setInterestType(tmp.getInterestType());
			response.setLatePaymentCharges(tmp.getLatePaymentChargesDue());
			response.setLatePaymentDays(tmp.getLatePaymentInDays());
			response.setMaxDelayDays(tmp.getMaxDelayDays());
			response.setNachActivation(tmp.isNachActivation());
			response.setOutstandingAmount(tmp.getOutstandingAmount());
			response.setRateOfInterest(tmp.getRateOfInterest());
			response.setSanctionedAmount(tmp.getSanctionedAmount());
			personalDetailsCalculation.add(response);
			
		}

		if(personalDetailsCalculationDao.deletByAppId(loanId)){
			personalDetailsCalculationDao.batchSave(personalDetailsCalculation);
			finalResponse.put("success",loanCycle );
		}else{
			finalResponse.put("error",loanCycle );
		}
		
		return finalResponse;
	}

	
	

	// START OF GET METHOD FOR BATCH 3

	public PersonalCompanyBean getCompanyDetails(String appId) {

		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(appId);
		PersonalDetailsCompany personalDetailsCompany = personalDetailsCompanyDao.getCompanyDetailsByAppId(appId);
		List<DirectorMaster> directorMaster = directorMasterDao.findDirectorMasterDataByAppID(appId);
		Applicant applicant = applicantDao.getMinCibil(loanMaster.getLoanMastertId());

		if (personalDetailsCompany != null) {
			PersonalCompanyBean detailsBean = new PersonalCompanyBean();
			detailsBean = toPersonalCompanyBean(personalDetailsCompany, detailsBean);
			return detailsBean;
		}
		// if(personalDetailsCompany!=null)
		// {
		// PersonalDetailsBean detailsBean = new PersonalDetailsBean();
		// detailsBean =
		// toPersonalCompanyBean(personalDetailsCompany,detailsBean);
		// //return detailsBean;
		// }
		else {
			PersonalDetailsCompany personalDetailsCompanyNew = new PersonalDetailsCompany();
			if (loanMaster != null) {

				personalDetailsCompanyNew.setAppID(loanMaster.getApplicationId());
				if (StringUtil.notEmpty(loanMaster.getCompanyName())) {
					personalDetailsCompanyNew.setCompanyName(loanMaster.getCompanyName());
				}
				personalDetailsCompanyNew.setContactDetails(loanMaster.getContactNo());
				personalDetailsCompanyNew.setEmailAdress1(loanMaster.getEmail());
				personalDetailsCompanyNew.setConstitution(loanMaster.getBusinessRunBy());
				personalDetailsCompanyNew.setOnlineStartDate(loanMaster.getSellOnline());
				personalDetailsCompanyNew.setOfflineStartDate(loanMaster.getSellOffline());

			}
			if (directorMaster != null) {
				personalDetailsCompanyNew.setClientName(String.valueOf(directorMaster.get(0).getdName()));
				personalDetailsCompanyNew.setContactPerson1(String.valueOf(directorMaster.get(0).getdName()));

			}

			if (applicant != null) {
				personalDetailsCompanyNew.setCibilScore(applicant.getCibilscore());
			}

			PersonalCompanyBean detailsBean = new PersonalCompanyBean();
			detailsBean = toPersonalCompanyBean(personalDetailsCompanyNew, detailsBean);
			// detailsBean=toPersonalCompanyBean(personalDetailsCompany,
			// detailsBean);
			personalDetailsCompanyDao.merge(personalDetailsCompanyNew);
			return detailsBean;
		}

	}

	// START OF GET METHOD FOR BATCH 4

	public PersonalKYCBean getKycDetails(String appId) {

		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(appId);
		PersonalDetailsEYC personalDetailsEYC = personalDetailsEycDao.getKYCDetailsByAppId(appId);
		if (personalDetailsEYC != null) {
			PersonalKYCBean detailsBean = new PersonalKYCBean();
			detailsBean = toPersonalEycBean(personalDetailsEYC, detailsBean);
			return detailsBean;
		} else {
			PersonalDetailsEYC personalDetailsEYCtmp = new PersonalDetailsEYC();
			if (loanMaster != null) {
				personalDetailsEYCtmp.setAppId(loanMaster.getApplicationId());
				personalDetailsEYCtmp.setCompanyPan(loanMaster.getPanNumber());
				personalDetailsEYCtmp.setRegisteredAddress(loanMaster.getAddress());
				// personalDetailsEYCtmp.setOwnership(loanMaster.getBusinessAddress());
				personalDetailsEYCtmp.setPinCode(loanMaster.getPincode());
				personalDetailsEYCtmp.setCity(loanMaster.getCity());
				personalDetailsEYCtmp.setState(loanMaster.getState());
				personalDetailsEYCtmp.setCommunicationAddress(loanMaster.getAddress());
				personalDetailsEYCtmp.setWarehouseAddress(loanMaster.getWarehouseAddress());
				// personalDetailsEYCtmp.setWhownership(loanMaster.getWhAddress());
				personalDetailsEYCtmp.setWhpinCode(loanMaster.getWarehousePincode());
				personalDetailsEYCtmp.setWhcity(loanMaster.getWarehouseCity());
				personalDetailsEYCtmp.setWhstate(loanMaster.getWarehouseState());
				personalDetailsEYCtmp.setVatNum(loanMaster.getTin());
				personalDetailsEYCtmp.setServiceTax(loanMaster.getServiceTax());
			}
			personalDetailsEycDao.merge(personalDetailsEYCtmp);
			PersonalKYCBean detailsBean = new PersonalKYCBean();
			detailsBean = toPersonalEycBean(personalDetailsEYCtmp, detailsBean);
			// detailsBean=toPersonalCompanyBean(personalDetailsCompany,
			// detailsBean);
			return detailsBean;
		}

	}

	// start of get of batch 5

	public PersonalDetailsBusinessBean getBusinessDetails(String appId) {

		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(appId);
		PersonalDetailsBusiness personalDetailsBusiness = personalDetailsBusinessDao.getBusinessByAppId(appId);
		if (personalDetailsBusiness != null) {
			PersonalDetailsBusinessBean detailsBean = new PersonalDetailsBusinessBean();
			detailsBean = toPersonalBusinessDetailsBean(personalDetailsBusiness, detailsBean);
			return detailsBean;
		} else {
			PersonalDetailsBusiness personalDetailsBusinesstmp = new PersonalDetailsBusiness();
			if (loanMaster != null) {

				personalDetailsBusinesstmp.setNumOfEmployees(String.valueOf(loanMaster.getNoOfEmployees()));
			}
			personalDetailsBusinessDao.merge(personalDetailsBusinesstmp);
			PersonalDetailsBusinessBean detailsBean = new PersonalDetailsBusinessBean();

			detailsBean = toPersonalBusinessDetailsBean(personalDetailsBusinesstmp, detailsBean);
			// detailsBean=toPersonalCompanyBean(personalDetailsCompany,
			// detailsBean);
			return detailsBean;
		}

	}

	public List<PersonalDetailsMarketBean> getMarketDetails(String appId) {

		String masterId = loanMasterDao.findLoanMasterIdByAppId(appId);
		List<PersonalDetailsMarket> personalDetailsMarkets = personalDetailsMarketDao.getMarketByAppId(appId);

		List<LoanSellingOn> sellingList = loanSellingOnDao.getDataByMasterId(masterId);
		List<PersonalDetailsMarket> updateList = new ArrayList<PersonalDetailsMarket>();
		List<String> deletePersonalList = new ArrayList<String>();
		if (sellingList.size() >= personalDetailsMarkets.size()) {
			for (LoanSellingOn loanSellingOn : sellingList) {
				SellingOnMaster sellingOnMaster = sellingOnMasterDao
						.getDataBySellingOnId(loanSellingOn.getLsoSellingId());
				PersonalDetailsMarket marketDetails = new PersonalDetailsMarket();
				if (sellingOnMaster != null) {
					marketDetails.setMarketGuid(sellingOnMaster.getpGuid());
					if (personalDetailsMarkets != null && personalDetailsMarkets.size() > 0) {
						for (PersonalDetailsMarket personalDetailsMarket : personalDetailsMarkets) {
							if (!sellingOnMaster.getpGuid().equals(personalDetailsMarket.getMarketGuid())) {
								marketDetails.setMarketPlaces(sellingOnMaster.getpName());
								// marketDetails.setMarketGuid(sell);
								marketDetails.setMarketId(appId);
								updateList.add(marketDetails);

							} else {
								marketDetails.setMarketPlaces(sellingOnMaster.getpName());
								marketDetails.setMarketId(appId);
								updateList.add(marketDetails);
							}
						}

					} else {
						marketDetails.setMarketPlaces(sellingOnMaster.getpName());
						marketDetails.setMarketId(appId);
						updateList.add(marketDetails);
					}

					List<PersonalDetailsMarket> details = new ArrayList<PersonalDetailsMarket>();
					details.addAll(updateList);
					personalDetailsMarketDao.batchSaveUpdate(details);

				}
			}

		}

		else {
			for (PersonalDetailsMarket details : personalDetailsMarkets) {
				deletePersonalList.add(details.getMarketGuid());
			}
			for (LoanSellingOn SellingOn : sellingList) {
				deletePersonalList.remove(SellingOn.getLsoSellingId());
			}

			deletePersonalDetailsMarketDetailsList(deletePersonalList);

		}
		//System.out.println(deletePersonalList.toString());
		List<PersonalDetailsMarketBean> applicantBeans = new ArrayList<PersonalDetailsMarketBean>();
		List<PersonalDetailsMarket> personalDetailsMarketNew = personalDetailsMarketDao.getMarketByAppId(appId);

		for (PersonalDetailsMarket marketDetails : personalDetailsMarketNew) {
			PersonalDetailsMarketBean detailsBean = new PersonalDetailsMarketBean();
			detailsBean = toMarketApplicantBean(marketDetails, detailsBean);
			applicantBeans.add(detailsBean);

		}
		return applicantBeans;
	}

	public void deletePersonalDetailsMarketDetailsList(List<String> deletePersonalList) {
		for (String marketId : deletePersonalList) {
			personalDetailsMarketDao.deleteByID(marketId);
		}

	}
	// batch 6

	public List<PersonalDetailsApplicantInfoBean> getInfoApplicanDetails(String appId) {
		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(appId);
		List<PersonalDetailsInfo> personalDetailsInfoList = personalDetailsApplicantInfoDao
				.getApplicantInfoAppId(appId);
		List<DirectorMaster> directorMastersList = directorMasterDao
				.getDirectorMasterDataByAppID(loanMaster.getLoanMastertId());

		List<PersonalDetailsInfo> updateList = new ArrayList<PersonalDetailsInfo>();
		List<String> deletePersonalList = new ArrayList<String>();
		if (directorMastersList != null && directorMastersList.size() > 0) {

			if (directorMastersList.size() >= personalDetailsInfoList.size()) {
				for (DirectorMaster directorMaster : directorMastersList) {
					PersonalDetailsInfo applicantDetails = new PersonalDetailsInfo();
					applicantDetails.setDirId(directorMaster.getdGuid());
					Applicant applicant = applicantDao.getApplicantByLoanAndDirectorId(directorMaster.getdGuid(),
							directorMaster.getLoanMaster().getLoanMastertId());
					if (personalDetailsInfoList != null && personalDetailsInfoList.size() > 0) {

						for (PersonalDetailsInfo personalDetailsInfo : personalDetailsInfoList) {

							if (!directorMaster.getdGuid().equals(personalDetailsInfo.getDirId())) {
								applicantDetails.setInfoAppId(appId);
								applicantDetails.setDirTabName(directorMaster.getDTabName());
								applicantDetails.setName(directorMaster.getFirstName() + ""
										+ directorMaster.getMiddleName() + "" + directorMaster.getLastName());
								applicantDetails.setDateOfBirth(directorMaster.getdDateOfBirth());
								applicantDetails.setPanNum(directorMaster.getdPan());
								applicantDetails.setHouseOwnerShip(directorMaster.getdResidenceAddress());
								if (applicant != null) {
									applicantDetails.setCibilScore(applicant.getCibilscore());
									applicantDetails.setPersonalScore(applicant.getPersonalscore());
								}
								updateList.add(applicantDetails);

								// personalDetailsSocialApplicantDao.merge(applicantDetails);
							} else {
								applicantDetails.setInfoAppId(appId);
								applicantDetails.setDirTabName(directorMaster.getDTabName());
								applicantDetails.setName(directorMaster.getFirstName() + ""
										+ directorMaster.getMiddleName() + "" + directorMaster.getLastName());
								applicantDetails.setDateOfBirth(directorMaster.getdDateOfBirth());
								applicantDetails.setPanNum(directorMaster.getdPan());
								applicantDetails.setHouseOwnerShip(directorMaster.getdResidenceAddress());
								if (applicant != null) {
									applicantDetails.setCibilScore(applicant.getCibilscore());
									applicantDetails.setPersonalScore(applicant.getPersonalscore());
								}
								updateList.add(applicantDetails);

							}
						}

					} else {
						applicantDetails.setInfoAppId(appId);
						applicantDetails.setDirTabName(directorMaster.getDTabName());
						applicantDetails.setName(directorMaster.getFirstName() + "" + directorMaster.getMiddleName()
								+ "" + directorMaster.getLastName());
						applicantDetails.setDateOfBirth(directorMaster.getdDateOfBirth());
						applicantDetails.setPanNum(directorMaster.getdPan());
						applicantDetails.setHouseOwnerShip(directorMaster.getdResidenceAddress());
						if (applicant != null) {
							applicantDetails.setCibilScore(applicant.getCibilscore());
							applicantDetails.setPersonalScore(applicant.getPersonalscore());
						}
						updateList.add(applicantDetails);
					}
				}
				List<PersonalDetailsInfo> details = new ArrayList<PersonalDetailsInfo>();
				details.addAll(updateList);
				personalDetailsApplicantInfoDao.batchSaveUpdate(details);

			} else {
				for (PersonalDetailsInfo personalDetails : personalDetailsInfoList) {
					deletePersonalList.add(personalDetails.getDirId());
				}
				for (DirectorMaster directorMaster : directorMastersList) {
					deletePersonalList.remove(directorMaster.getdGuid());
				}

				deletePersonalDetailsApplicantInfoList(deletePersonalList);

			}
		}
		//System.out.println(deletePersonalList.toString());
		List<PersonalDetailsApplicantInfoBean> applicantBeans = new ArrayList<PersonalDetailsApplicantInfoBean>();
		for (PersonalDetailsInfo details : personalDetailsInfoList) {
			PersonalDetailsApplicantInfoBean detailsBean = new PersonalDetailsApplicantInfoBean();
			detailsBean = toPersonalApplicantInfoBean(details, detailsBean);
			applicantBeans.add(detailsBean);

		}
		return applicantBeans;
	}

	public void deletePersonalDetailsApplicantInfoList(List<String> deletePersonalList) {
		for (String directorId : deletePersonalList) {
			personalDetailsApplicantInfoDao.deleteByID(directorId);
		}
	}
	// START OF GET METHOD FOR BATCH 7

	public List<PDSocialPresenceBean> getSocialDetails(String appId) {

		// LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(appId);
		List<PersonalDetailsSocialPresence> personalDetailsSocialPresence =  personalDetailsSocialPresenceDao
				.getSocialPresenceByAppId(appId);
		List<PDSocialPresenceBean>applicantBeans=new ArrayList<PDSocialPresenceBean>();
		if(personalDetailsSocialPresence != null && personalDetailsSocialPresence.size()>0)
		{
		for(PersonalDetailsSocialPresence presence : personalDetailsSocialPresence)
		{
			PDSocialPresenceBean detailsBean = new PDSocialPresenceBean();
			detailsBean = toSocialPresencebean(presence, detailsBean);
			applicantBeans.add(detailsBean);
		}
	}

		else
		{
			HashSet<String>names=new HashSet<String>();
			names.add("Social Presence");
			names.add("OwnWebsite");
			names.add("Num Of Comments on Website");
			names.add("JustDial");
			names.add("Page on FaceBook");
			names.add("Page on Linkedin");
			names.add("Related to any political Party");
			

			
			for(String name :names)
			{
				PDSocialPresenceBean detailsBean = new PDSocialPresenceBean();
				//detailsBean.setAppId(appId);
				detailsBean = toSocialPresencebeanNew(name, detailsBean);
				applicantBeans.add(detailsBean);
			}
			
		}
		return applicantBeans;
	}

	// batch 8
	public List<PersonalDetailsApplicantBean> getPersonalDetailsApplicant(String appId) {

		String masterId = loanMasterDao.findLoanMasterIdByAppId(appId);
		List<PersonalDetailsApplicantDetails> personalDetailsApplicantDetails = personalDetailsSocialApplicantDao
				.getPersonalDetailsApplicantById(appId);

		List<DirectorMaster> directorMastersList = directorMasterDao.getDirectorMasterDataByAppID(masterId);
		List<PersonalDetailsApplicantDetails> updateList = new ArrayList<PersonalDetailsApplicantDetails>();
		List<String> deletePersonalList = new ArrayList<String>();
		if (directorMastersList != null && directorMastersList.size() > 0) {

			if (directorMastersList.size() >= personalDetailsApplicantDetails.size()) {
				for (DirectorMaster directorMaster : directorMastersList) {
					PersonalDetailsApplicantDetails applicantDetails = new PersonalDetailsApplicantDetails();
					applicantDetails.setDirectorId(directorMaster.getdGuid());
					if (personalDetailsApplicantDetails != null && personalDetailsApplicantDetails.size() > 0) {
						for (PersonalDetailsApplicantDetails personalDetailsApplicant : personalDetailsApplicantDetails) {
							if (!directorMaster.getdGuid().equals(personalDetailsApplicant.getDirectorId())) {
								applicantDetails.setSocialAppId(appId);
								// applicantDetails.setDirectorId(directorMaster.getdGuid());
								applicantDetails.setDirTabName(directorMaster.getDTabName());
								applicantDetails.setCreatedDate(Calendar.getInstance().getTime());
								updateList.add(applicantDetails);

								// personalDetailsSocialApplicantDao.merge(applicantDetails);
							} else {
								applicantDetails.setSocialAppId(appId);
								// applicantDetails.setDirectorId(directorMaster.getdGuid());
								applicantDetails.setDirTabName(directorMaster.getDTabName());
								applicantDetails.setCreatedDate(Calendar.getInstance().getTime());

								updateList.add(applicantDetails);
							}
						}

					} else {
						applicantDetails.setSocialAppId(appId);
						// applicantDetails.setDirectorId(directorMaster.getdGuid());
						applicantDetails.setDirTabName(directorMaster.getDTabName());
						applicantDetails.setCreatedDate(Calendar.getInstance().getTime());
						updateList.add(applicantDetails);
					}
				}
				List<PersonalDetailsApplicantDetails> details = new ArrayList<PersonalDetailsApplicantDetails>();
				details.addAll(updateList);
				personalDetailsSocialApplicantDao.batchSaveUpdate(details);

			} else {
				for (PersonalDetailsApplicantDetails personalDetails : personalDetailsApplicantDetails) {
					deletePersonalList.add(personalDetails.getDirectorId());
				}
				for (DirectorMaster directorMaster : directorMastersList) {
					deletePersonalList.remove(directorMaster.getdGuid());
				}

				deletePersonalDetailsApplicantDetailsList(deletePersonalList);

			}
		}
		//System.out.println(deletePersonalList.toString());
		List<PersonalDetailsApplicantBean> applicantBeans = new ArrayList<PersonalDetailsApplicantBean>();
		for (PersonalDetailsApplicantDetails details : personalDetailsApplicantDetails) {
			PersonalDetailsApplicantBean detailsBean = new PersonalDetailsApplicantBean();
			detailsBean = toSocialApplicantBean(details, detailsBean);
			applicantBeans.add(detailsBean);

		}
		return applicantBeans;
	}

	public void deletePersonalDetailsApplicantDetailsList(List<String> deletePersonalList) {
		for (String directorId : deletePersonalList) {
			personalDetailsSocialApplicantDao.deleteByID(directorId);
		}
	}

	private PersonalDetailsApplicantBean toSocialApplicantBean(PersonalDetailsApplicantDetails applicantDetails,
			PersonalDetailsApplicantBean detailsBean) {
		// PersonalDetailsApplicantBean detailsBean = new
		// PersonalDetailsApplicantBean();

		detailsBean.setDirId(applicantDetails.getDirectorId());
		detailsBean.setDirTabName(applicantDetails.getDirTabName());
		if (applicantDetails.getFbUrl() != null) {
			detailsBean.setFbUrl(applicantDetails.getFbUrl());
		}
		if (applicantDetails.getLinkedinConnections() != null) {
			detailsBean.setLinkedinConnections(applicantDetails.getLinkedinConnections());
		}
		if (applicantDetails.getLinkedinUrl() != null) {
			detailsBean.setLinkedinUrl(applicantDetails.getLinkedinUrl());
		}
		if (detailsBean.getNumOfFriends() != null) {
			detailsBean.setNumOfFriends(applicantDetails.getNumOfFriends());
		}
		return detailsBean;
	}

	private PersonalDetailsMarketBean toMarketApplicantBean(PersonalDetailsMarket personalDetailsMarket,
			PersonalDetailsMarketBean detailsBean) {
		// PersonalDetailsMarketBean detailsBean = new
		// PersonalDetailsMarketBean();

		detailsBean.setMarketId(personalDetailsMarket.getMarketGuid());
		if (personalDetailsMarket.getCommission() != null) {
			detailsBean.setCommission(personalDetailsMarket.getCommission());
		}
		if (personalDetailsMarket.getMarketPlaces() != null) {
			detailsBean.setMarketplaces(personalDetailsMarket.getMarketPlaces());
		}
		if (personalDetailsMarket.getNumOfRating() != null) {
			detailsBean.setNumOfRating(Integer.parseInt(personalDetailsMarket.getNumOfRating()));
		}
		if (personalDetailsMarket.getPrevNumReviews() != null) {
			detailsBean.setPreviousNoOfReviews(Integer.parseInt(personalDetailsMarket.getPrevNumReviews()));
		}
		if (personalDetailsMarket.getPreviousRating() != null) {
			detailsBean.setPreviousRating(Integer.parseInt(personalDetailsMarket.getPreviousRating()));
		}
		if (personalDetailsMarket.getRating() != null) {
			detailsBean.setRatingScore(personalDetailsMarket.getRating());
		}
		if (personalDetailsMarket.getUrl() != null) {
			detailsBean.setUrl(personalDetailsMarket.getUrl());
		}
		return detailsBean;
	}

	private PersonalDetailsBean toPersonalDetailsBean(Analysis analysis, PersonalDetailsBean detailsBean) {
		// TODO Auto-generated method stub

		detailsBean.setAdminId(analysis.getApplicationID());
		detailsBean.setAnalystName(analysis.getAnalystName());
		detailsBean.setAppCreatedDate(LKUtils.getDateStrOnly(analysis.getCreatedDate()));
		detailsBean.setCaseType(analysis.getType());
		detailsBean.setClContractId(analysis.getClContractId());
		if (analysis.getClsId() != null) {
			detailsBean.setClsAppID(analysis.getClsId());
		}
		detailsBean.setCycle(analysis.getCycle());
		if (analysis.getDateAssignedAnalyst() != null) {
			detailsBean.setDateAssignToAnalyst(LKUtils.getDateStrOnly(analysis.getDateAssignedAnalyst()));
		}
		if (analysis.getDateAnalysis() != null) {
			detailsBean.setDateOfAnalysis(LKUtils.getDateStrOnly(analysis.getDateAnalysis()));
		}
		detailsBean.setLeadSource(analysis.getLeadSource());
		detailsBean.setLoanApplied(String.valueOf(analysis.getLoanApplied()));
		detailsBean.setLoanEligible(String.valueOf(analysis.getLoanEligible()));
		detailsBean.setLoanPurpose(analysis.getLoanPurpose());
		detailsBean.setOthers(analysis.getLeadSourceOthers());
		detailsBean.setProduct(analysis.getProduct());
		detailsBean.setType(analysis.getType());

		return detailsBean;
	}

	private PersonalCompanyBean toPersonalCompanyBean(PersonalDetailsCompany personalDetailsCompany,
			PersonalCompanyBean detailsBean) {
		// TODO Auto-generated method stub

		detailsBean.setCompanyName(personalDetailsCompany.getCompanyName());
		if (personalDetailsCompany.getContactPerson1() != null) {
			detailsBean.setContactPerson1(personalDetailsCompany.getContactPerson1());
		}
		detailsBean.setClientFullName(personalDetailsCompany.getClientName());
		detailsBean.setContactDetails1(String.valueOf(personalDetailsCompany.getContactDetails()));
		detailsBean.setEmailAddress1(personalDetailsCompany.getEmailAdress1());
		if (personalDetailsCompany.getRelationship() != null) {
			detailsBean.setRelationship1(personalDetailsCompany.getRelationship());
		}
		detailsBean.setContactPerson2(personalDetailsCompany.getContactPerson2());
		if (personalDetailsCompany.getConstitution() != null) {
			detailsBean.setConstitution(personalDetailsCompany.getConstitution());
		}
		// else
		// {
		// detailsBean.setConstitution("");
		// }
		detailsBean.setContactDetails2(String.valueOf(personalDetailsCompany.getContactDetails2()));
		detailsBean.setEmailAddress2(personalDetailsCompany.getEmailAddress2());
		detailsBean.setRelationship2(personalDetailsCompany.getRelationship2());
		// detailsBean.setConstitution(personalDetailsCompany.getConstitution());
		detailsBean.setNatureOfBusiness(personalDetailsCompany.getNatureOfBusiness());
		detailsBean.setCibilScoreMin(String.valueOf(personalDetailsCompany.getCibilScore()));
		detailsBean.setOfflineStartDate(LKUtils.getDateStrOnly(personalDetailsCompany.getOfflineStartDate()));
		detailsBean.setOnlineStartDate(LKUtils.getDateStrOnly(personalDetailsCompany.getOnlineStartDate()));
		detailsBean.setIncorporationDate(LKUtils.getDateStrOnly(personalDetailsCompany.getIncorporationDate()));
		detailsBean.setOfflineVintage(String.valueOf(personalDetailsCompany.getOfflineVintage()));
		detailsBean.setOnlineVintage(String.valueOf(personalDetailsCompany.getOnlineVintage()));
		detailsBean.setIndustrySelected(personalDetailsCompany.getIndustrySelected());
		detailsBean.setNumOfProductCategories(String.valueOf(personalDetailsCompany.getNoOfProductCategories()));
		detailsBean.setPrimaryCategory(personalDetailsCompany.getPrimaryCategory());
		detailsBean.setSecondaryCategory(personalDetailsCompany.getSecondaryCategory());
		detailsBean.setNumOfBankAccounts(String.valueOf(personalDetailsCompany.getNoOfBankAccounts()));
		detailsBean.setPrimaryBank(personalDetailsCompany.getPrimaryBank());
		detailsBean.setSecondaryBank(personalDetailsCompany.getSecondaryBank());
		if (personalDetailsCompany.getOtherBanks() != null) {
			detailsBean.setOtherBanks(personalDetailsCompany.getOtherBanks());
		}
		detailsBean.setTotalMonthsBankStmt(String.valueOf(personalDetailsCompany.getTotalBankStatements()));
		detailsBean.setTotalMonthsOfRevenue(String.valueOf(personalDetailsCompany.getTotalMonthsRevenue()));
		return detailsBean;
	}

	private PersonalKYCBean toPersonalEycBean(PersonalDetailsEYC personalDetailsEYC, PersonalKYCBean detailsBean) {
		// TODO Auto-generated method stub

		detailsBean.setCompanyPan(personalDetailsEYC.getCompanyPan());
		detailsBean.setCity(personalDetailsEYC.getCity());
		detailsBean.setCommunicationAddress(personalDetailsEYC.getCommunicationAddress());
		detailsBean.setNumOfServiceTax(String.valueOf(personalDetailsEYC.getNumOfServiceTax()));
		detailsBean.setNumOfVat(String.valueOf(personalDetailsEYC.getNumOfVatMonthly()));
		detailsBean.setOwnership(personalDetailsEYC.isOwnership());
		detailsBean.setPinCode(personalDetailsEYC.getPinCode());
		detailsBean.setRegisteredAddress(personalDetailsEYC.getPinCode());
		detailsBean.setServiceTax(String.valueOf(personalDetailsEYC.getServiceTax()));
		detailsBean.setState(personalDetailsEYC.getState());
		detailsBean.setVatNum(personalDetailsEYC.getVatNum());
		detailsBean.setWarehouseAddress(personalDetailsEYC.getWarehouseAddress());
		detailsBean.setWhCity(personalDetailsEYC.getWhcity());
		detailsBean.setWhOwnership(personalDetailsEYC.isWhownership());
		detailsBean.setWhState(personalDetailsEYC.getWhstate());
		detailsBean.setWhPincode(personalDetailsEYC.getWhpinCode());
		detailsBean.setWhCity(personalDetailsEYC.getWhcity());

		return detailsBean;
	}

	private PersonalDetailsBusinessBean toPersonalBusinessDetailsBean(PersonalDetailsBusiness personalDetailsBusiness,
			PersonalDetailsBusinessBean detailsBean) {
		// TODO Auto-generated method stub

		detailsBean.setAwardsAndRecognition(personalDetailsBusiness.isAwardsRecognition());
		detailsBean.setCashConversionCycleDays(String.valueOf(personalDetailsBusiness.getCashConversionCycle()));
		detailsBean.setCashConversionCycleLakhs(String.valueOf(personalDetailsBusiness.getCashConversionCycleLakhs()));
		detailsBean.setCashDiscount(personalDetailsBusiness.getCashDiscount());
		detailsBean.setCreditorDays(String.valueOf(personalDetailsBusiness.getCreditorDays()));
		detailsBean.setCurrentAccountsPayable(String.valueOf(personalDetailsBusiness.getCurrAccPayable()));
		detailsBean.setCurrentInventoryAmount(String.valueOf(personalDetailsBusiness.getCurrentInventoryAmount()));
		detailsBean.setDebtorDays(String.valueOf(personalDetailsBusiness.getDebitorDays()));
		detailsBean.setEligible(personalDetailsBusiness.isEligible());
		detailsBean.setEndorsementReceived(personalDetailsBusiness.isEndorsementReceived());
		detailsBean.setGoogleSearch(personalDetailsBusiness.isGoogleSearch());
		detailsBean.setInventoryDays(String.valueOf(personalDetailsBusiness.getInventoryDays()));
		detailsBean.setInventoryFulfillment(String.valueOf(personalDetailsBusiness.getInventoryFulfilment()));
		detailsBean.setNumOfEmployees(personalDetailsBusiness.getNumOfEmployees());
		detailsBean.setOfflineBusiness(String.valueOf(personalDetailsBusiness.getOfflineBusiness()));
		detailsBean.setOnlineBusiness(String.valueOf(personalDetailsBusiness.getOnlineBusiness()));
		detailsBean.setOtherCompany(personalDetailsBusiness.isOtherCompany());
		detailsBean.setOwnedCapital(String.valueOf(personalDetailsBusiness.getOwnedCapital()));
		detailsBean.setProductRejection(String.valueOf(personalDetailsBusiness.getProdRejectionRate()));
		detailsBean.setProfitMargin(String.valueOf(personalDetailsBusiness.getProfitMargin()));
		detailsBean.setRbiDefaulter(personalDetailsBusiness.isRbiDefaulter());
		detailsBean.setReference(personalDetailsBusiness.getReference());
		detailsBean.setRegisteredFulfillment(personalDetailsBusiness.isRegisteredFulfilment());
		detailsBean.setEndorsementReceived(personalDetailsBusiness.isEndorsementReceived());

		return detailsBean;
	}

	/*private PersonalDetailsCalculationBean toPersonalDetailsCalBean(
			PersonalDetailsCalculation personalDetailsCalculation, PersonalDetailsCalculationBean detailsBean) {
		// TODO Auto-generated method stub

		detailsBean.setBankAccNum(personalDetailsCalculation.getBankAccountNum());
		detailsBean.setDisbursementDate(LKUtils.getDateStrOnly(personalDetailsCalculation.getDisbursementDate()));
		detailsBean.setDuration(personalDetailsCalculation.getDuration());
		detailsBean.seteCollection(personalDetailsCalculation.iseCollection());
		detailsBean.setInstallmentAmount(personalDetailsCalculation.getInterestAmount());
		detailsBean.setInstallmentDelay(personalDetailsCalculation.getInstallmentDelays());
		detailsBean.setInstallmentEndDate(LKUtils.getDateStrOnly(personalDetailsCalculation.getInstallmentEndDate()));
		detailsBean.setInstallmentFrequency(personalDetailsCalculation.getInterestFrequency());
		detailsBean.setInterestType(personalDetailsCalculation.getInterestType());
		detailsBean.setLatePayment(personalDetailsCalculation.getLatePaymentCharges());
		detailsBean.setMaxDelayDays(personalDetailsCalculation.getMaxDelayDays());
		detailsBean.setNachActivation(personalDetailsCalculation.isNachActivation());
		detailsBean.setOutstandingAmount(personalDetailsCalculation.getOutstandingAmount());
		detailsBean.setRateOfInterest(personalDetailsCalculation.getRateOfInterest());
		detailsBean.setSanctionedAmount(personalDetailsCalculation.getSanctionedAmount());
		detailsBean.setTotalOutstanding(personalDetailsCalculation.getTotalOutstanding());

		return detailsBean;
	}*/

	private PersonalDetailsApplicantInfoBean toPersonalApplicantInfoBean(PersonalDetailsInfo personalDetailsInfo,
			PersonalDetailsApplicantInfoBean detailsBean) {
		// TODO Auto-generated method stub
		detailsBean.setDirId(personalDetailsInfo.getDirId());
		detailsBean.setDirName(personalDetailsInfo.getDirTabName());
		detailsBean.setAge(personalDetailsInfo.getAge());
		if (personalDetailsInfo.getGender() != null) {
			detailsBean.setGender(personalDetailsInfo.getGender());
		}
		detailsBean.setPersonalCibilScore(personalDetailsInfo.getPersonalScore());
		detailsBean.setCibilScore(personalDetailsInfo.getCibilScore());
		detailsBean.setDateOfBirth(LKUtils.getDateStrOnly(personalDetailsInfo.getDateOfBirth()));
		if (personalDetailsInfo.getEducation() != null) {
			detailsBean.setEducation(personalDetailsInfo.getEducation());
		}
		if (personalDetailsInfo.getExperience() != 0) {
			detailsBean.setExperienceMonths(personalDetailsInfo.getExperience());
		}
		detailsBean.setHouseOwnership(personalDetailsInfo.getHouseOwnerShip());
		if (personalDetailsInfo.getLifeCycle() != null) {
			detailsBean.setLifeCycle(personalDetailsInfo.getLifeCycle());
		}
		if (personalDetailsInfo.getMaritalStatus() != null) {
			detailsBean.setMartialStatus(personalDetailsInfo.getMaritalStatus());
		}
		detailsBean.setName(personalDetailsInfo.getName());
		detailsBean.setNoOfChildren(personalDetailsInfo.getNumOfChildren());
		detailsBean.setPanNo(personalDetailsInfo.getPanNum());
		detailsBean.setParents(personalDetailsInfo.isParentDependent());
		detailsBean.setPastJob(personalDetailsInfo.isPastJob());
		detailsBean.setPersonalCibilScore(personalDetailsInfo.getPersonalScore());
		if (personalDetailsInfo.getRelationship() != null) {
			detailsBean.setRelationShip(personalDetailsInfo.getRelationship());
		}
		if (personalDetailsInfo.getSpouseEmployment() != null) {
			detailsBean.setSpouseEmployment(personalDetailsInfo.getSpouseEmployment());
		}
		return detailsBean;
	}

	private PDSocialPresenceBean toSocialPresencebean(PersonalDetailsSocialPresence personalDetailsSocialPresence,
			PDSocialPresenceBean detailsBean) {
		
		//detailsBean.setAppId(personalDetailsSocialPresence.getLoanAppId());
		detailsBean.setTabName(personalDetailsSocialPresence.getSocialTab());
		detailsBean.setValues(personalDetailsSocialPresence.getValues());
		detailsBean.setRemarks(personalDetailsSocialPresence.getRemarks());
        //detailsBean.setAppId(personalDetailsSocialPresence.getLoanAppId());
		// TODO Auto-generated method stub

		


		return detailsBean;
	}
	
	private PDSocialPresenceBean toSocialPresencebeanNew(String name,
			PDSocialPresenceBean detailsBean) {
		
		PersonalDetailsSocialPresence personalDetailsSocialPresence=new PersonalDetailsSocialPresence();
		
		//detailsBean.setAppId(personalDetailsSocialPresence.getLoanAppId());
		detailsBean.setTabName(name);
		if(personalDetailsSocialPresence.getSocialTab() != null)
		{
		detailsBean.setSocialTab(personalDetailsSocialPresence.getSocialTab());
		}
		if(personalDetailsSocialPresence.getValues() != null)
		{
		detailsBean.setValues(personalDetailsSocialPresence.getValues());
		}
		if(personalDetailsSocialPresence.getRemarks() != null)
		{
		detailsBean.setRemarks(personalDetailsSocialPresence.getRemarks());
		}

		// TODO Auto-generated method stub

		


		return detailsBean;
	}

//	private PersonalDetailsApplicantBean toSocialApplicantBean(
//			List<PersonalDetailsApplicantDetails> personalDetailsApplicantDetails,
//			PersonalDetailsApplicantBean detailsBean) {
//
//		// TODO Auto-generated method stub
//
//		// detailsBean=createDetailsBean()
//
//		return detailsBean;
//	}

	public PersonalDetailsBean saveLoanDetails(PersonalDetailsBean personalDetailsBean, String loanId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Analysis analysis = analysisDao.findApplication(loanId);
		if (analysis != null) {
			analysis = toPersonalDetails(analysis, personalDetailsBean, loanId);
		} else {
			analysis = new Analysis();
			analysis = toPersonalDetails(analysis, personalDetailsBean, loanId);
		}
		analysis = analysisDao.merge(analysis);
		String json = gson.toJson(analysis);
		System.out.println(json);
		personalDetailsBean = toPersonalDetailsBean(analysis, new PersonalDetailsBean());
		return personalDetailsBean;

	}

	public PersonalCompanyBean saveCompanyDetails(PersonalCompanyBean personalCompanyBean, String loanId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		PersonalDetailsCompany personalDetailsCompany = personalDetailsCompanyDao.getCompanyDetailsByAppId(loanId);
		if (personalDetailsCompany != null) {
			personalDetailsCompany = toCompanyDetails(personalDetailsCompany, personalCompanyBean, loanId);
		} else {
			personalDetailsCompany = new PersonalDetailsCompany();
			personalDetailsCompany = toCompanyDetails(personalDetailsCompany, personalCompanyBean, loanId);
		}
		personalDetailsCompany = personalDetailsCompanyDao.merge(personalDetailsCompany);
		String json = gson.toJson(personalDetailsCompany);
		System.out.println(json);
		personalCompanyBean = toPersonalCompanyBean(personalDetailsCompany, new PersonalCompanyBean());
		return personalCompanyBean;

	}

	public PersonalKYCBean saveKYCDetails(PersonalKYCBean personalKYCBean, String loanId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// LoanMaster loanMaster=loanMasterDao.getApplicationByAppId(appId);
		// Analysis analysis =
		// analysisDao.findApplication(personalDetailsBean.getAdminid());
		PersonalDetailsEYC personalDetailsEYC = personalDetailsEycDao.getKYCDetailsByAppId(loanId);
		if (personalDetailsEYC != null) {
			personalDetailsEYC = toPersonalKYCDetails(personalDetailsEYC, personalKYCBean, loanId);
		} else {
			personalDetailsEYC = new PersonalDetailsEYC();
			personalDetailsEYC = toPersonalKYCDetails(personalDetailsEYC, personalKYCBean, loanId);
		}
		personalDetailsEYC = personalDetailsCompanyDao.merge(personalDetailsEYC);
		String json = gson.toJson(personalDetailsEYC);
		System.out.println(json);
		personalKYCBean = toPersonalEycBean(personalDetailsEYC, new PersonalKYCBean());
		return personalKYCBean;

	}

	public PersonalDetailsBusinessBean saveBusinessDetails(PersonalDetailsBusinessBean personalDetailsBusinessBean,
			String loanId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// LoanMaster loanMaster=loanMasterDao.getApplicationByAppId(appId);
		// Analysis analysis =
		// analysisDao.findApplication(personalDetailsBean.getAdminid());
		PersonalDetailsBusiness personalDetailsBusiness = personalDetailsBusinessDao.getBusinessByAppId(loanId);
		if (personalDetailsBusiness != null) {
			personalDetailsBusiness = toPersonalBusinessDetails(personalDetailsBusiness, personalDetailsBusinessBean,
					loanId);
		} else {
			personalDetailsBusiness = new PersonalDetailsBusiness();
			personalDetailsBusiness = toPersonalBusinessDetails(personalDetailsBusiness, personalDetailsBusinessBean,
					loanId);
		}
		personalDetailsBusiness = personalDetailsBusinessDao.merge(personalDetailsBusiness);
		String json = gson.toJson(personalDetailsBusiness);
		System.out.println(json);
		personalDetailsBusinessBean = toPersonalBusinessDetailsBean(personalDetailsBusiness,
				new PersonalDetailsBusinessBean());
		return personalDetailsBusinessBean;
	}

	public List<PersonalDetailsMarketBean> saveMarketDetails(List<PersonalDetailsMarketBean> personalDetailsMarketBeans,
			String loanId) {
		// String masterId = loanMasterDao.findLoanMasterIdByAppId(loanId);
		// List<DirectorMaster> directorMastersList =
		// directorMasterDao.getDirectorMasterDataByAppID(masterId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<PersonalDetailsMarket> personalDetailsMarkets = new ArrayList<PersonalDetailsMarket>();
		for (PersonalDetailsMarketBean infoBean : personalDetailsMarketBeans) {
			PersonalDetailsMarket applicantDetails = personalDetailsMarketDao
					.getDataBySellingId(infoBean.getMarketId());
			if (applicantDetails != null) {
				applicantDetails.setCommission(infoBean.getCommission());
				applicantDetails.setMarketGuid(infoBean.getMarketId());
				applicantDetails.setMarketPlaces(infoBean.getMarketplaces());
				applicantDetails.setNumOfRating(String.valueOf(infoBean.getNumOfRating()));
				applicantDetails.setPreviousRating(String.valueOf(infoBean.getPreviousRating()));
				applicantDetails.setPrevNumReviews(String.valueOf(infoBean.getPreviousNoOfReviews()));
				applicantDetails.setRating(infoBean.getRatingScore());
				applicantDetails.setUrl(infoBean.getUrl());
				personalDetailsMarkets.add(applicantDetails);
			} else {
				PersonalDetailsMarket applicantNew = new PersonalDetailsMarket();
				applicantNew.setCommission(infoBean.getCommission());
				applicantNew.setMarketGuid(infoBean.getMarketId());
				applicantNew.setMarketPlaces(infoBean.getMarketplaces());
				applicantNew.setNumOfRating(String.valueOf(infoBean.getNumOfRating()));
				applicantNew.setPreviousRating(String.valueOf(infoBean.getPreviousRating()));
				applicantNew.setPrevNumReviews(String.valueOf(infoBean.getPreviousNoOfReviews()));
				applicantNew.setRating(infoBean.getRatingScore());
				applicantNew.setUrl(infoBean.getUrl());
				personalDetailsMarkets.add(applicantNew);

			}
		}

		List<PersonalDetailsMarketBean> infoBeans = toMarketBean(personalDetailsMarkets);
		personalDetailsMarkets = personalDetailsMarketDao.batchSaveUpdate(personalDetailsMarkets);
		String json = gson.toJson(personalDetailsMarkets);
		System.out.println(infoBeans);
		System.out.println(json);
		return infoBeans;

	}

	public List<PersonalDetailsApplicantInfoBean> saveApplicantInfo(
			List<PersonalDetailsApplicantInfoBean> personalDetailsApplicantInfoBean, String loanId) {
		// String masterId = loanMasterDao.findLoanMasterIdByAppId(loanId);
		// List<DirectorMaster> directorMastersList =
		// directorMasterDao.getDirectorMasterDataByAppID(masterId);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<PersonalDetailsInfo> personalDetailsInfos = new ArrayList<PersonalDetailsInfo>();
		for (PersonalDetailsApplicantInfoBean infoBean : personalDetailsApplicantInfoBean) {
			PersonalDetailsInfo applicantDetails = personalDetailsApplicantInfoDao
					.getApplicantInfoDirId(infoBean.getDirId());
			if (applicantDetails != null) {
				applicantDetails.setRelationship(infoBean.getRelationShip());
				applicantDetails.setNumOfChildren(infoBean.getNoOfChildren());
				applicantDetails.setParentDependent(infoBean.isParents());
				applicantDetails.setPastJob(infoBean.isPastJob());
				applicantDetails.setEducation(infoBean.getEducation());
				applicantDetails.setExperience(infoBean.getExperienceMonths());
				applicantDetails.setLifeCycle(infoBean.getLifeCycle());
				applicantDetails.setSpouseEmployment(infoBean.getSpouseEmployment());
				personalDetailsInfos.add(applicantDetails);
			} else {
				PersonalDetailsInfo applicantInfotmp = new PersonalDetailsInfo();
				applicantInfotmp.setRelationship(infoBean.getRelationShip());
				applicantInfotmp.setNumOfChildren(infoBean.getNoOfChildren());
				applicantInfotmp.setParentDependent(infoBean.isParents());
				applicantInfotmp.setPastJob(infoBean.isPastJob());
				applicantInfotmp.setEducation(infoBean.getEducation());
				applicantInfotmp.setExperience(infoBean.getExperienceMonths());
				applicantInfotmp.setLifeCycle(infoBean.getLifeCycle());
				applicantInfotmp.setSpouseEmployment(infoBean.getSpouseEmployment());
				personalDetailsInfos.add(applicantInfotmp);

			}
		}

		List<PersonalDetailsApplicantInfoBean> infoBeans = toPersonalDetailsInfoBean(personalDetailsInfos);
		personalDetailsInfos = personalDetailsApplicantInfoDao.batchSaveUpdate(personalDetailsInfos);
		String json = gson.toJson(personalDetailsInfos);
		System.out.println(json);
		return infoBeans;

	}

	public List<PDSocialPresenceBean> saveSocialDetails(List<PDSocialPresenceBean> loan, String loanId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<PersonalDetailsSocialPresence> personalDetailsSocialDetails = new ArrayList<PersonalDetailsSocialPresence>();
		for(PDSocialPresenceBean bean :loan){
			PersonalDetailsSocialPresence socialPresence=personalDetailsSocialPresenceDao.findApplication(loanId);
			if(socialPresence != null)
			{
				personalDetailsSocialPresenceDao.deleteByID(loanId);
				PersonalDetailsSocialPresence detailsSocialPresence= new PersonalDetailsSocialPresence();
			
				detailsSocialPresence.setLoanAppId(loanId);
				detailsSocialPresence.setRemarks(bean.getRemarks());
				detailsSocialPresence.setSocialTab(bean.getTabName());
				detailsSocialPresence.setValues(bean.getValues());
				personalDetailsSocialDetails.add(detailsSocialPresence);
			}
			
			
			else{
			PersonalDetailsSocialPresence socialPresenceNew=new PersonalDetailsSocialPresence();
			socialPresenceNew.setLoanAppId(loanId);
			socialPresenceNew.setRemarks(bean.getRemarks());
			socialPresenceNew.setSocialTab(bean.getTabName());
			socialPresenceNew.setValues(bean.getValues());
			personalDetailsSocialDetails.add(socialPresenceNew);
		}
		}
		
			List<PDSocialPresenceBean> applicantBeans = toPersonalDetailsSocialBean(
					personalDetailsSocialDetails);
			personalDetailsSocialPresenceDao.batchSaveUpdate(personalDetailsSocialDetails);
			String json = gson.toJson(personalDetailsSocialDetails);
			System.out.println(json);
			// personalDetailsApplicantDetails =
			// toSocialApplicantBean(personalDetailsApplicantDetails);
			return applicantBeans;

	}

	public List<PersonalDetailsApplicantBean> saveSocialApplicantDetails(List<PersonalDetailsApplicantBean> loan,
			String loanId) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<PersonalDetailsApplicantDetails> personalDetailsApplicantDetails = new ArrayList<PersonalDetailsApplicantDetails>();
		for (PersonalDetailsApplicantBean personalDetailsApplicantBean : loan) {
			PersonalDetailsApplicantDetails applicantDetails = personalDetailsSocialApplicantDao
					.getPersonalDirectorDetailsById(personalDetailsApplicantBean.getDirId());
			if (applicantDetails != null) {
				applicantDetails.setFbUrl(personalDetailsApplicantBean.getFbUrl());
				applicantDetails.setLinkedinConnections(personalDetailsApplicantBean.getLinkedinConnections());
				applicantDetails.setLinkedinUrl(personalDetailsApplicantBean.getLinkedinUrl());
				applicantDetails.setNumOfFriends(personalDetailsApplicantBean.getNumOfFriends());
				personalDetailsApplicantDetails.add(applicantDetails);
			} else {
				PersonalDetailsApplicantDetails applicantDetailstmp = new PersonalDetailsApplicantDetails();
				applicantDetailstmp.setDirectorId(personalDetailsApplicantBean.getDirId());
				applicantDetailstmp.setDirTabName(personalDetailsApplicantBean.getDirTabName());
				applicantDetailstmp.setFbUrl(personalDetailsApplicantBean.getFbUrl());
				applicantDetailstmp.setLinkedinConnections(personalDetailsApplicantBean.getLinkedinConnections());
				applicantDetailstmp.setLinkedinUrl(personalDetailsApplicantBean.getLinkedinUrl());
				applicantDetailstmp.setNumOfFriends(personalDetailsApplicantBean.getNumOfFriends());
				// applicantDetails = new PersonalDetailsApplicantDetails();
				personalDetailsApplicantDetails.add(applicantDetailstmp);

			}
		}

		List<PersonalDetailsApplicantBean> applicantBeans = toPersonalDetailsApplicantBean(
				personalDetailsApplicantDetails);
		personalDetailsApplicantDetails = personalDetailsSocialPresenceDao
				.batchSaveUpdate(personalDetailsApplicantDetails);
		String json = gson.toJson(personalDetailsApplicantDetails);
		System.out.println(json);
		// personalDetailsApplicantDetails =
		// toSocialApplicantBean(personalDetailsApplicantDetails);
		return applicantBeans;

	}

	/**
	 * 
	 * @param personalDetailsApplicantDetails
	 * @return
	 */
	private List<PersonalDetailsApplicantBean> toPersonalDetailsApplicantBean(
			List<PersonalDetailsApplicantDetails> personalDetailsApplicantDetails) {
		List<PersonalDetailsApplicantBean> applicantBeansList = new ArrayList<PersonalDetailsApplicantBean>();
		// PersonalDetailsApplicantBean detailsBean =new
		// PersonalDetailsApplicantBean();
		for (PersonalDetailsApplicantDetails applicantDetails : personalDetailsApplicantDetails) {
			PersonalDetailsApplicantBean detailsBean = new PersonalDetailsApplicantBean();
			PersonalDetailsApplicantBean bean = toSocialApplicantBean(applicantDetails, detailsBean);
			applicantBeansList.add(bean);
		}
		return applicantBeansList;
	}

	private List<PersonalDetailsApplicantInfoBean> toPersonalDetailsInfoBean(
			List<PersonalDetailsInfo> personalDetailsInfos) {
		List<PersonalDetailsApplicantInfoBean> applicantBeansList = new ArrayList<PersonalDetailsApplicantInfoBean>();
		for (PersonalDetailsInfo applicantDetails : personalDetailsInfos) {
			PersonalDetailsApplicantInfoBean detailsBean = new PersonalDetailsApplicantInfoBean();
			PersonalDetailsApplicantInfoBean bean = toPersonalApplicantInfoBean(applicantDetails, detailsBean);
			applicantBeansList.add(bean);
		}
		return applicantBeansList;
	}

	private List<PersonalDetailsMarketBean> toMarketBean(List<PersonalDetailsMarket> personalDetailsMarkets) {
		List<PersonalDetailsMarketBean> applicantBeansList = new ArrayList<PersonalDetailsMarketBean>();
		// PersonalDetailsMarketBean detailsBean =new
		// PersonalDetailsMarketBean();
		for (PersonalDetailsMarket applicantDetails : personalDetailsMarkets) {
			PersonalDetailsMarketBean detailsBean = new PersonalDetailsMarketBean();
			PersonalDetailsMarketBean bean = toMarketApplicantBean(applicantDetails, detailsBean);
			applicantBeansList.add(bean);
		}
		return applicantBeansList;
	}
	
	private List<PDSocialPresenceBean> toPersonalDetailsSocialBean(
			List<PersonalDetailsSocialPresence> personalDetailsSocialDetails) {
		List<PDSocialPresenceBean> applicantBeansList = new ArrayList<PDSocialPresenceBean>();
		// PersonalDetailsApplicantBean detailsBean =new
		// PersonalDetailsApplicantBean();
		for (PersonalDetailsSocialPresence applicantDetails : personalDetailsSocialDetails) {
			PDSocialPresenceBean detailsBean = new PDSocialPresenceBean();
			PDSocialPresenceBean bean = toSocialPresencebean(applicantDetails, detailsBean);
			applicantBeansList.add(bean);
		}
		return applicantBeansList;
	}

	private Analysis toPersonalDetails(Analysis analysis, PersonalDetailsBean personalDetailsBean, String loanId) {

		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(loanId);
		// List<DirectorMaster>
		// directorMaster=directorMasterDao.findDirectorMasterDataByAppID(personalDetailsBean.getAdminid());
		Boolean flag = true;
		if (loanMaster != null) {
			analysis.setApplicationID(loanMaster.getApplicationId());
			analysis.setClsId(loanMaster.getClsApplicationId());
			analysis.setCreatedDate(loanMaster.getCreated());
			analysis.setAnalystName(loanMaster.getAssignedTo());
			analysis.setLeadSource(loanMaster.getLeadSource());

		} else {
			flag = false;
		}
		if (flag) {
			if (loanMaster.getAssignedTo() != personalDetailsBean.getAnalystName()) {
				loanMaster.setAssignedTo(personalDetailsBean.getAnalystName());
				analysis.setAnalystName(personalDetailsBean.getAnalystName());
			}

			loanMasterDao.merge(loanMaster);
			analysis.setClContractId(personalDetailsBean.getClContractId());
			analysis.setCycle(personalDetailsBean.getCycle());
			analysis.setDateAnalysis(LKUtils.getDateOnly(personalDetailsBean.getDateOfAnalysis()));
			analysis.setDateAssignedAnalyst(LKUtils.getDateOnly(personalDetailsBean.getDateAssignToAnalyst()));
			analysis.setLeadSourceOthers(personalDetailsBean.getOthers());
			double loanApplied = LKUtils.parseDouble(personalDetailsBean.getLoanApplied());

			analysis.setLoanApplied(loanApplied);

			if (StringUtil.notEmpty(personalDetailsBean.getLoanEligible())) {
				analysis.setLoanEligible(Double.parseDouble(personalDetailsBean.getLoanEligible()));
			} else {

			}
			analysis.setLoanPurpose(personalDetailsBean.getLoanPurpose());
			analysis.setProduct(personalDetailsBean.getProduct());
			analysis.setType(personalDetailsBean.getType());

			return analysis;
		} else {
			return analysis;
		}
	}

	private PersonalDetailsCompany toCompanyDetails(PersonalDetailsCompany personalDetailsCompany,
			PersonalCompanyBean personalCompanyBean, String loanId) {

		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(loanId);
		List<DirectorMaster> directorMaster = directorMasterDao
				.getDirectorMasterDataByAppID(loanMaster.getLoanMastertId());
		// Analysis analysis = analysisDao.getAnalysisByAppId(loanId);
		// if(directorMaster.size()>0)
		// {
		// personalDetailsCompany.setClientName(directorMaster.get(0).getFirstName()+
		// " " + directorMaster.get(0).getMiddleName()+ " "
		// +directorMaster.get(0).getLastName());
		// personalDetailsCompany.setContactPerson1(directorMaster.get(0).getFirstName()+
		// " " + directorMaster.get(0).getMiddleName()+ " "
		// +directorMaster.get(0).getLastName());
		// personalDetailsCompany.setContactDetails(directorMaster.get(0).getdMobile());
		// personalDetailsCompany.setEmailAdress1(directorMaster.get(0).getdEmail());
		// }
		Applicant applicant = applicantDao.getMinCibil(loanMaster.getLoanMastertId());

		Boolean flag = true;
		if (flag) {
			if (loanMaster != null) {

				personalDetailsCompany.setAppID(loanMaster.getApplicationId());
				if (StringUtil.notEmpty(loanMaster.getCompanyName())) {
					personalDetailsCompany.setCompanyName(loanMaster.getCompanyName());
				}
				personalDetailsCompany.setConstitution(loanMaster.getBusinessRunBy());
				personalDetailsCompany.setOnlineStartDate(loanMaster.getSellOnline());
				personalDetailsCompany.setOfflineStartDate(loanMaster.getSellOffline());
			}
			if (applicant != null) {
				personalDetailsCompany.setCibilScore(applicant.getCibilscore());
			}

			if (directorMaster.size() > 0) {
				personalDetailsCompany.setClientName(directorMaster.get(0).getFirstName() + " "
						+ directorMaster.get(0).getMiddleName() + " " + directorMaster.get(0).getLastName());
				personalDetailsCompany.setContactPerson1(directorMaster.get(0).getFirstName() + " "
						+ directorMaster.get(0).getMiddleName() + " " + directorMaster.get(0).getLastName());

				personalDetailsCompany.setContactDetails(directorMaster.get(0).getdMobile());
				personalDetailsCompany.setEmailAdress1(directorMaster.get(0).getdEmail());
			}

		} else {
			flag = false;
		}

		if (flag) {
			if (loanMaster.getCompanyName() != personalCompanyBean.getCompanyName()) {
				loanMaster.setCompanyName(personalCompanyBean.getCompanyName());
				personalDetailsCompany.setCompanyName(personalCompanyBean.getCompanyName());
			}
			if (loanMaster.getContactNo() != personalCompanyBean.getContactDetails1()) {
				loanMaster.setContactNo(personalCompanyBean.getContactDetails1());
				personalDetailsCompany.setContactDetails(personalCompanyBean.getContactDetails1());
			}
			if (loanMaster.getEmail() != personalCompanyBean.getEmailAddress1()) {
				loanMaster.setEmail(personalCompanyBean.getEmailAddress1());
				personalDetailsCompany.setEmailAdress1(personalCompanyBean.getContactDetails1());
			}

			if (String.valueOf(directorMaster.get(0).getFirstName() + " " + directorMaster.get(0).getMiddleName() + " "
					+ directorMaster.get(0).getLastName()) != personalCompanyBean.getClientFullName()) {
				directorMaster.get(0).setFirstName(personalCompanyBean.getClientFullName());
			}

			loanMasterDao.merge(loanMaster);
			directorMasterDao.batchSave(directorMaster);
			// Analysis analysis
			// =analysisDao.getAnalysisByAppId(personalDetailsBean.getAdminid());
			// Date onlineDate=personalDetailsCompany.getOnlineStartDate();

			// Date dateOfAnalysis=analysis.getDateAnalysis();

			// Date offlineDate=personalDetailsCompany.getOfflineStartDate();

			personalDetailsCompany.setRelationship(personalCompanyBean.getRelationship1());
			personalDetailsCompany.setRelationship2(personalCompanyBean.getRelationship1());
			personalDetailsCompany.setContactPerson2(personalCompanyBean.getContactPerson1());
			personalDetailsCompany.setContactDetails2(personalCompanyBean.getContactDetails2());
			personalDetailsCompany.setEmailAddress2(personalCompanyBean.getEmailAddress2());
			personalDetailsCompany.setNatureOfBusiness(personalCompanyBean.getNatureOfBusiness());
			personalDetailsCompany.setIncorporationDate(LKUtils.Min(personalDetailsCompany.getOfflineStartDate(),
					personalDetailsCompany.getOnlineStartDate()));
			// personalDetailsCompany.setOnlineVintage(getDiffInMonths(dateOfAnalysis,
			// onlineDate));
			// personalDetailsCompany.setOfflineVintage(getDiffInMonths(dateOfAnalysis,
			// offlineDate));
			personalDetailsCompany.setIndustrySelected(personalCompanyBean.getIndustrySelected());
			personalDetailsCompany
					.setNoOfProductCategories(Integer.valueOf(personalCompanyBean.getNumOfProductCategories()));
			personalDetailsCompany.setPrimaryCategory(personalCompanyBean.getPrimaryCategory());
			personalDetailsCompany.setSecondaryCategory(personalCompanyBean.getSecondaryCategory());
			personalDetailsCompany.setOtherCategory(personalCompanyBean.getOtherCategories());
			personalDetailsCompany.setNoOfBankAccounts(personalCompanyBean.getNumOfBankAccounts());
			personalDetailsCompany.setPrimaryBank(personalCompanyBean.getPrimaryBank());
			personalDetailsCompany.setSecondaryBank(personalCompanyBean.getSecondaryBank());
			personalDetailsCompany.setTotalBankStatements(personalCompanyBean.getTotalMonthsBankStmt());
			personalDetailsCompany.setTotalMonthsRevenue(personalCompanyBean.getTotalMonthsOfRevenue());
			return personalDetailsCompany;
		}

		else {
			return personalDetailsCompany;
		}
	}

	private PersonalDetailsEYC toPersonalKYCDetails(PersonalDetailsEYC personalDetailsEYC,
			PersonalKYCBean personalKYCBean, String loanId) {

		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(loanId);
		// List<DirectorMaster>
		// directorMaster=directorMasterDao.findDirectorMasterDataByAppID(personalDetailsBean.getAdminid());
		Boolean flag = true;
		if (loanMaster != null) {
			personalDetailsEYC.setAppId(loanMaster.getApplicationId());
			personalDetailsEYC.setCompanyPan(loanMaster.getPanNumber());
			personalDetailsEYC.setRegisteredAddress(loanMaster.getAddress());
			// personalDetailsEYC.setOwnership(loanMaster.getBusinessAddress());
			personalDetailsEYC.setPinCode(loanMaster.getPincode());
			personalDetailsEYC.setCity(loanMaster.getCity());
			personalDetailsEYC.setState(loanMaster.getState());
			personalDetailsEYC.setCommunicationAddress(loanMaster.getAddress());
			personalDetailsEYC.setWarehouseAddress(loanMaster.getWarehouseAddress());
			personalDetailsEYC.setWarehouseAddress(loanMaster.getWhAddress());
			personalDetailsEYC.setWhpinCode(loanMaster.getWarehousePincode());
			personalDetailsEYC.setWhcity(loanMaster.getWarehouseCity());
			personalDetailsEYC.setWhstate(loanMaster.getWarehouseState());
			personalDetailsEYC.setVatNum(loanMaster.getTin());
			personalDetailsEYC.setServiceTax(loanMaster.getServiceTax());

		} else {
			flag = false;
		}
		if (flag) {
			if (loanMaster.getAddress() != personalKYCBean.getRegisteredAddress()) {
				loanMaster.setAddress(personalKYCBean.getRegisteredAddress());
				personalDetailsEYC.setRegisteredAddress(personalKYCBean.getRegisteredAddress());
				personalDetailsEYC.setCommunicationAddress(personalKYCBean.getCommunicationAddress());
			}

			if (loanMaster.getPincode() != personalKYCBean.getPinCode()) {
				loanMaster.setPincode(personalKYCBean.getPinCode());
				personalDetailsEYC.setPinCode(personalKYCBean.getPinCode());
			}

			if (loanMaster.getCity() != personalKYCBean.getCity()) {
				loanMaster.setCity(personalKYCBean.getCity());
				personalDetailsEYC.setCity(personalKYCBean.getCity());
			}
			if (loanMaster.getPincode() != personalKYCBean.getPinCode()) {
				loanMaster.setPincode(personalKYCBean.getPinCode());
				personalDetailsEYC.setPinCode(personalKYCBean.getPinCode());
			}
			if (loanMaster.getState() != personalKYCBean.getState()) {
				loanMaster.setState(personalKYCBean.getState());
				personalDetailsEYC.setState(personalKYCBean.getState());
			}
			if (loanMaster.getWarehouseAddress() != personalKYCBean.getWarehouseAddress()) {
				loanMaster.setWarehouseAddress(personalKYCBean.getWarehouseAddress());
				personalDetailsEYC.setWarehouseAddress(personalKYCBean.getWarehouseAddress());
			}

			if (loanMaster.getWarehouseCity() != personalKYCBean.getWhCity()) {
				loanMaster.setWarehouseCity(personalKYCBean.getWhCity());
				personalDetailsEYC.setWhcity(personalKYCBean.getWhCity());
			}

			if (loanMaster.getWarehousePincode() != personalKYCBean.getWhPincode()) {
				loanMaster.setWarehousePincode(personalKYCBean.getWhPincode());
				personalDetailsEYC.setWhpinCode(personalKYCBean.getWhPincode());
			}

			if (loanMaster.getWarehouseState() != personalKYCBean.getWhState()) {
				loanMaster.setWarehouseState(personalKYCBean.getWhState());
				personalDetailsEYC.setWhstate(personalKYCBean.getWhState());
			}

			if (loanMaster.getTin() != personalKYCBean.getVatNum()) {
				loanMaster.setTin(personalKYCBean.getVatNum());
				personalDetailsEYC.setVatNum(personalKYCBean.getVatNum());
			}

			loanMasterDao.merge(loanMaster);
			personalDetailsEYC.setOwnership(personalKYCBean.isOwnership());
			personalDetailsEYC.setWhownership(personalKYCBean.isWhOwnership());
			personalDetailsEYC.setServiceTax(personalKYCBean.getServiceTax());
			personalDetailsEYC.setNumOfVatMonthly(personalKYCBean.getNumOfVat());
			personalDetailsEYC.setNumOfServiceTax(personalKYCBean.getNumOfServiceTax());

			return personalDetailsEYC;
		} else {
			return personalDetailsEYC;
		}
	}

	private PersonalDetailsBusiness toPersonalBusinessDetails(PersonalDetailsBusiness personalDetailsBusiness,
			PersonalDetailsBusinessBean personalDetailsBusinessBean, String loanId) {

		LoanMaster loanMaster = loanMasterDao.getApplicationByAppId(loanId);
		// List<DirectorMaster>
		// directorMaster=directorMasterDao.findDirectorMasterDataByAppID(personalDetailsBean.getAdminid());
		Boolean flag = true;
		if (loanMaster != null) {
			personalDetailsBusiness.setBusAppId(loanMaster.getApplicationId());
			personalDetailsBusiness.setNumOfEmployees(loanMaster.getNoOfEmployees());

		} else {
			flag = false;
		}
		if (flag) {
			if (loanMaster.getNoOfEmployees() != personalDetailsBusinessBean.getNumOfEmployees()) {
				loanMaster.setNoOfEmployees(personalDetailsBusinessBean.getNumOfEmployees());
				personalDetailsBusiness.setNumOfEmployees(personalDetailsBusinessBean.getNumOfEmployees());

			}
			loanMasterDao.merge(loanMaster);
			int inventoryDays = Integer.parseInt(personalDetailsBusinessBean.getInventoryDays());
			int DebitorDays = Integer.parseInt(personalDetailsBusinessBean.getDebtorDays());
			int CreditorDays = Integer.parseInt(personalDetailsBusinessBean.getCreditorDays());
			double inventoryLakhs = Double.parseDouble(personalDetailsBusinessBean.getCurrentInventoryAmount());
			double accountReceivable = Double.parseDouble(personalDetailsBusinessBean.getCurrentAccountsReceived());
			double accountsPayable = Double.parseDouble(personalDetailsBusinessBean.getCurrentAccountsPayable());
			personalDetailsBusiness.setAwardsRecognition(personalDetailsBusinessBean.isAwardsAndRecognition());
			personalDetailsBusiness.setCashConversionCycle(LKUtils.cycleDays(DebitorDays, inventoryDays, CreditorDays));
			personalDetailsBusiness.setCashConversionCycleLakhs(
					LKUtils.cycleDaysLakhs(accountReceivable, inventoryLakhs, accountsPayable));
			personalDetailsBusiness.setCashDiscount(personalDetailsBusinessBean.getCashDiscount());
			personalDetailsBusiness.setCreditorDays(Integer.parseInt(personalDetailsBusinessBean.getCreditorDays()));
			personalDetailsBusiness
					.setCurAccReceivable(Double.parseDouble(personalDetailsBusinessBean.getCurrentAccountsReceived()));
			personalDetailsBusiness
					.setCurrAccPayable(Integer.parseInt(personalDetailsBusinessBean.getCurrentAccountsPayable()));
			personalDetailsBusiness.setCurrentInventoryAmount(
					Integer.parseInt(personalDetailsBusinessBean.getCurrentInventoryAmount()));
			personalDetailsBusiness.setDebitorDays(Integer.parseInt(personalDetailsBusinessBean.getDebtorDays()));
			personalDetailsBusiness.setEligible(personalDetailsBusinessBean.isEligible());
			personalDetailsBusiness.setEndorsementReceived(personalDetailsBusinessBean.isEndorsementReceived());
			personalDetailsBusiness.setGoogleSearch(personalDetailsBusinessBean.isGoogleSearch());
			personalDetailsBusiness.setInventoryDays(Integer.parseInt(personalDetailsBusinessBean.getInventoryDays()));
			personalDetailsBusiness
					.setInventoryFulfilment(Double.parseDouble(personalDetailsBusinessBean.getInventoryFulfillment()));
			personalDetailsBusiness.setNumOfEmployees(personalDetailsBusinessBean.getNumOfEmployees());
			personalDetailsBusiness
					.setOfflineBusiness(Double.parseDouble(personalDetailsBusinessBean.getOfflineBusiness()));
			personalDetailsBusiness
					.setOnlineBusiness(Double.parseDouble(personalDetailsBusinessBean.getOnlineBusiness()));
			personalDetailsBusiness.setOtherCompany(personalDetailsBusinessBean.isOtherCompany());
			personalDetailsBusiness.setOwnedCapital(Double.parseDouble(personalDetailsBusinessBean.getOwnedCapital()));
			personalDetailsBusiness
					.setProdRejectionRate(Integer.parseInt(personalDetailsBusinessBean.getProductRejection()));
			personalDetailsBusiness.setProfitMargin(Integer.parseInt(personalDetailsBusinessBean.getProfitMargin()));
			personalDetailsBusiness.setRbiDefaulter(personalDetailsBusinessBean.isRbiDefaulter());
			personalDetailsBusiness.setReference(personalDetailsBusinessBean.getReference());
			personalDetailsBusiness.setRegisteredFulfilment(personalDetailsBusinessBean.isRegisteredFulfillment());

			return personalDetailsBusiness;
		} else {
			return personalDetailsBusiness;
		}
	}

	private PersonalDetailsSocialPresence toSocialPresence(PersonalDetailsSocialPresence personalDetailsSocialPresence,
			PDSocialPresenceBean pdSocialPresenceBean, String loanId) {

		personalDetailsSocialPresence.setLoanAppId(loanId);
        personalDetailsSocialPresence.setRemarks(pdSocialPresenceBean.getRemarks());
        personalDetailsSocialPresence.setSocialTab(pdSocialPresenceBean.getTabName());
        personalDetailsSocialPresence.setValues(pdSocialPresenceBean.getValues());
        

		return personalDetailsSocialPresence;
	}

	public MasterBean getCatDetails(String appId) {
		MasterBean masterBean = new MasterBean();
		masterBean.setPersonalDetailsBean(getPersonalDetails(appId));// batch1
//		masterBean.setPersonalDetailsCalculationBean(getPersonalCalDetails(appId));// batch2
		masterBean.setPersonalCompanyBean(getCompanyDetails(appId));// batch3
		masterBean.setPersonalKYCBean(getKycDetails(appId));// batch4
		masterBean.setPersonalDetailsBusinessBean(getBusinessDetails(appId));// batch 5a
		masterBean.setPersonalDetailsMarketBean(getMarketDetails(appId));// batch5b
		masterBean.setPersonalDetailsApplicantInfoBean(getInfoApplicanDetails(appId));// batch6
		masterBean.setPdSocialPresenceBean(getSocialDetails(appId));// batch7
		masterBean.setPersonalDetailsApplicantBean(getPersonalDetailsApplicant(appId));// batch8

		
		
		// try {
		// try {
		// nullAwareBeanCopy(personalDetailsBean,personalDetailsBean1);
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return masterBean;
	}

	
	//
	//
	// return personalDetailsApplicantDetails;
	// }
	// public int getDiffInMonths(Date d1, Date d2) {
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Calendar a = Calendar.getInstance();
	// a.setTime(d1);
	// int m1 = a.get(Calendar.MONTH);
	// Calendar b = Calendar.getInstance();
	// b.setTime(d2);
	// int m2 = b.get(Calendar.MONTH);
	// int diff = m1 - m2;
	// System.out.println(diff);
	// return diff;
	// }

	// public static int getAgeFromDateOfBirth(Date dob) {
	// Period period = null;
	// try {
	// Calendar cal = Calendar.getInstance();
	// cal.setTime(dob);
	// int year = cal.get(Calendar.YEAR);
	// int month = cal.get(Calendar.MONTH) + 1;
	// int day = cal.get(Calendar.DATE);
	// LocalDate birthdate = new LocalDate(year, month, day);
	// LocalDate now = new LocalDate();
	// period = new Period(birthdate, now, PeriodType.yearMonthDay());
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// LOGGER.error("Exception occurred : ", e);
	// }
	// int age = period.getYears();
	// return age;
	// }

	// private Consumer<? super DirectorMaster>
	// saveDirector(List<DirectorMaster> directorMaster)
	// {
	//
	// PersonalDetailsApplicantDetails personalDetailsApplicantDetails =new
	// PersonalDetailsApplicantDetails();
	// personalDetailsApplicantDetails.setDirectorId(directorMaster.get(index));
	// return null;
	//
	// }

}
