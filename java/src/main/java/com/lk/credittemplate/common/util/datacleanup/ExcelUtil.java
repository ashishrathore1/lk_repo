/**
 * 
 */
package com.lk.credittemplate.common.util.datacleanup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lk.credittemplate.cibildao.CibilDao;
import com.lk.credittemplate.model.Applicant;

/**
 * @author ramamohan
 *
 */
public class ExcelUtil {
	
	 @Autowired
	 private CibilDao cibilDao;
	 
	 static ExcelUtil excelUtil;
	 
	public static void main(String[] args) throws IOException {
		excelUtil = new ExcelUtil();
	    new ApplicationContextLoader().load(excelUtil, "classpath*:**/applicationContext.xml");
	    System.out.println("Start");
	    Map<String, Date> duplicateApplicants = excelUtil.cibilDao.getDuplicateApplicants();
	    System.out.println("duplicateApplicants :: "+duplicateApplicants);
	    System.out.println("duplicateApplicants size :: "+duplicateApplicants.size());
	    deleteDuplicates(duplicateApplicants);
	    System.out.println("Test");
	}
	
	/**
	 * deleteDuplicates
	 * 
	 * @param duplicateApplicants
	 */
	public static void deleteDuplicates(Map<String, Date> duplicateApplicants){
		List<Applicant> duplicates = new ArrayList<Applicant>();
		int total = 0;
		List<Long> notDuplicateApplicants = new ArrayList<>();
		List<Long> duplicateApplicantIds = new ArrayList<>();
		for (Entry<String, Date> entry : duplicateApplicants.entrySet()) {
			String[] split = entry.getKey().split("_");
			String name = split[0];
			String loanId = split[1];
			List<Applicant> applicants = excelUtil.cibilDao.getApplicantByNameAndLoan(name,loanId);
			//System.out.println("applicants :: "+applicants.size());
			total += applicants.size();
			long applicantId = 0;
			for (Applicant applicant : applicants) {
				if (applicant.getCreatedDate().before(entry.getValue())) {
					duplicates.add(applicant);
				} else {
					long id = applicant.getId();
					duplicateApplicantIds.add(id);
					if (applicantId<id) {
						applicantId = id;
					}
				}
			}
			notDuplicateApplicants.add(applicantId);
		}
		System.out.println("Total : "+total);
		System.out.println("duplicates : "+duplicates.size());
		System.out.println("not duplicates : "+(total-duplicates.size()));
		for (Applicant applicant : duplicates) {
			long id = applicant.getId();
			System.out.println("Deleted record Id : "+id);
			excelUtil.cibilDao.deleteCibilByApplicant(id);
			excelUtil.cibilDao.deleteLoanEnquiriesByApplicant(id);
			excelUtil.cibilDao.deleteApplicantByApplicant(id);
			
		}
		
		System.out.println("Total : "+duplicateApplicantIds.size());
		System.out.println("duplicates : "+(duplicateApplicantIds.size()-notDuplicateApplicants.size()));
		System.out.println("not duplicates : "+notDuplicateApplicants.size());
		System.out.println("not duplicates : "+notDuplicateApplicants);
		System.out.println("duplicates : "+duplicateApplicantIds);
		
		/*for (Long applicantId : duplicateApplicantIds) {
			if (notDuplicateApplicants.contains(applicantId)) {
				continue;
			}
			System.out.println("Deleted record Id : "+applicantId);
			excelUtil.cibilDao.deleteCibilByApplicant(applicantId);
			excelUtil.cibilDao.deleteLoanEnquiriesByApplicant(applicantId);
			excelUtil.cibilDao.deleteApplicantByApplicant(applicantId);
			
		}*/
	}
	
}


class ApplicationContextLoader {

    protected ConfigurableApplicationContext applicationContext;

    public ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Loads application context. Override this method to change how the
     * application context is loaded.
     * 
     * @param configLocations
     *            configuration file locations
     */
    protected void loadApplicationContext(String... configLocations) {
        applicationContext = new ClassPathXmlApplicationContext(
                configLocations);
        applicationContext.registerShutdownHook();
    }

    /**
     * Injects dependencies into the object. Override this method if you need
     * full control over how dependencies are injected.
     * 
     * @param main
     *            object to inject dependencies into
     */
    protected void injectDependencies(Object main) {
        getApplicationContext().getBeanFactory().autowireBeanProperties(
                main, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
    }

    /**
     * Loads application context, then injects dependencies into the object.
     * 
     * @param main
     *            object to inject dependencies into
     * @param configLocations
     *            configuration file locations
     */
    public void load(Object main, String... configLocations) {
        loadApplicationContext(configLocations);
        injectDependencies(main);
    }
    
}
