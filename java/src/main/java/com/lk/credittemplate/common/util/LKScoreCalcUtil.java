package com.lk.credittemplate.common.util;

import java.util.List;

import org.apache.commons.beanutils.MethodUtils;

import com.lk.credittemplate.common.constants.LKConstants;
import com.lk.credittemplate.model.Variables;
import com.lk.credittemplate.model.ds.LoanScoreModel;
import com.lk.credittemplate.model.ds.LoanVarScoreModel;
import com.lk.credittemplate.model.ds.VarScoreModel;


/**
 * 
 * @author ramamohan
 *
 */
public class LKScoreCalcUtil {

	/**
	 * getSalesg12Score
	 * 
	 * @param val
	 * @return
	 */
	public static double getSalesg12Score(String val) {
		double score = 0;
		/*
		 * if () {
		 * 
		 * }
		 */
		return score;
	}

	/**
	 * getSalesg23Score
	 * 
	 * @param val
	 * @return
	 */
	public static double getSalesg23Score(String val) {
		double score = 0;
		/*
		 * if () {
		 * 
		 * }
		 */
		return score;
	}

	/**
	 * getSalesavg3m
	 * 
	 * @param val
	 * @return
	 */
	public static double getSalesavg3mScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value > 0 && value <= 150) {
			score = 0.67 * value;
		} else if (value > 150) {
			score = 100;
		}
		return score;
	}

	/**
	 * getVATnScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getVATnScore(String val) {
		double score = 0;
		if (val.equalsIgnoreCase("NA")) {
			score = 60;
		} else {
			score = min((getDoubleVal(val) / 4) * 100, 100);
		}
		return score;
	}

	/**
	 * getCIBILnScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getCIBILnScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value == -1) {
			score = 40;
		} else if (value >= 1 && value <= 5) {
			score = ((value - 1) / 5) * 100;
		} else if (value >= 300) {
			score = ((value - 500) / 500) * 100;
		}
		return score;
	}

	/**
	 * getBusNatuScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getBusNatuScore(String val) {
		double score = 0;
		if (val.equalsIgnoreCase("Manufacturing & Selling: Online & Offline")) {
			score = 100;
		} else if (val.equalsIgnoreCase("TE")) {
			score = 80;
		} else if (val.equalsIgnoreCase("TO")) {
			score = 60;
		}
		return score;
	}

	/**
	 * getVintoffScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getVintoffScore(String val) {
		return min((getDoubleVal(val) / 48) * 100, 100);
	}

	/**
	 * getVintonScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getVintonScore(String val) {
		return min((getDoubleVal(val) / 18) * 100, 100);
	}

	/**
	 * getEmpnScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getEmpnScore(String val) {
		double score = 0;
		int value = getIntVal(val);
		if (value > 4) {
			switch (value) {
			case 5:
				score = 1;
				break;
			case 6:
				score = 2;
				break;
			case 7:
				score = 3;
				break;
			case 8:
				score = 4;
				break;
			case 9:
				score = 5;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
				score = 60;
				break;
			case 14:
			case 15:
			case 16:
			case 17:
				score = 70;
				break;
			case 18:
			case 19:
			case 20:
			case 21:
				score = 80;
				break;
			case 22:
			case 23:
			case 24:
			case 25:
				score = 90;
				break;
			default:
				score = 100;
				break;
			}
		}
		return score;
	}

	/**
	 * getInvtSalesRScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getInvtSalesRScore(String val) {
		return getDoubleVal(val);
	}

	/**
	 * getBustypScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getBustypScore(String val) {
		double score = 0;
		switch (val.toUpperCase()) {
		case "PARTNERSHIP":
			score = 50;
			break;
		case "LLP":
			score = 70;
			break;
		case "PRIVATELIMITED":
			score = 80;
			break;
		case "PROPRIETORSHIP":
			score = 100;
			break;
		default:
			break;
		}
		return score;
	}

	/**
	 * getRefScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getRefScore(String val) {
		double score = 0;
		switch (val.toUpperCase()) {
		case "FAMILY":
			score = 75;
			break;
		case "FRIEND":
			score = 60;
			break;
		case "VENDOR/SUPPLIER":
			score = 100;
			break;
		default:
			break;
		}
		return score;
	}

	/**
	 * getFbfrndnScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getFbfrndnScore(String val) {
		return min(((getDoubleVal(val) - 50) / 450) * 100, 100);
	}

	/**
	 * getLinkednScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getLinkednScore(String val) {
		return min(((getDoubleVal(val) - 50) / 450) * 100, 100);
	}

	/**
	 * getPastJobScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getPastJobScore(String val) {
		double score = 0;
		if (val.equalsIgnoreCase("Y")) {
			score = 100;
		}
		return score;
	}

	/**
	 * getOwnhouseScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getOwnhouseScore(String val) {
		double score = 0;
		switch (val.toUpperCase()) {
		case "OWNED":
			score = 100;
			break;
		case "OWNEDPARENTS":
			score = 50;
			break;
		case "RENTED":
			score = 10;
			break;
		default:
			break;
		}
		return score;
	}

	/**
	 * getEduqualScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getEduqualScore(String val) {
		double score = 0;
		switch (val.toUpperCase()) {
		case "GRADUATE":
			score = 80;
			break;
		case "POSTGRADUATE":
			score = 100;
			break;
		case "UNDERGRADUATE":
			score = 30;
			break;
		default:
			break;
		}
		return score;
	}

	/**
	 * getSpouseEmpScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getSpouseEmpScore(String val) {
		double score = 0;
		switch (val.toUpperCase()) {
		case "NOTWORKING":
			break;
		case "SALARIED":
			score = 100;
			break;
		case "SELFEMPLOYEE":
			score = 50;
			break;
		case "WORKING":
			score = 80;
			break;
		default:
			break;
		}
		return score;
	}

	/**
	 * getChildScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getChildScore(String val) {
		double score = 0;
		switch (getIntVal(val)) {
		case 0:
		case 2:
			score = 75;
			break;
		case 1:
			score = 100;
			break;
		default:
			score = 0;
			break;
		}
		return score;
	}

	/**
	 * getParentdpScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getParentdpScore(String val) {
		double score = 0;
		switch (val.toUpperCase()) {
		case "DEPENDENT":
			score = 50;
			break;
		case "INDEPENDENT":
			score = 100;
			break;
		default:
			break;
		}
		return score;
	}

	/**
	 * getAvgchqrtnScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getAvgchqrtnScore(String val) {
		double score = 0;
		if (getDoubleVal(val) < 2) {
			score = 100;
		}
		return score;
	}

	/**
	 * getMarketavgScScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getMarketavgScScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value >= 2.5) {
			score = ((value - 2.5) / (2.5)) * 100;
		}
		return score;
	}

	/**
	 * getTotalLiabilityScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getTotalLiabilityScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value >= 0 && value <= 100) {
			score = 100 - value;
		}
		return score;
	}

	/**
	 * getSOACountScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getSOACountScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value >= 0 && value <= 12) {
			score = 8.4 * value;
		} else if (value > 12) {
			score = 100;
		}
		return score;
	}

	/**
	 * getCrDrRScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getCrDrRScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value >= 1.1) {
			score = 100;
		} else if (value > 1 && value < 1.1) {
			score = 1000 * (value - 1);
		}
		return score;
	}

	/**
	 * getDrRatioNScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getDrRatioNScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value >= 1.1) {
			score = 100;
		} else if (value > 1 && value < 1.1) {
			score = 1000 * (value - 1);
		}
		return score;
	}

	/**
	 * getCashDepositScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getCashDepositScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value == 0) {
			score = 100;
		} else if (value > 0 && value < 20) {
			score = 100 - 5 * value;
		}
		return score;
	}

	/**
	 * getInventoryScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getInventoryScore(String val, String loanAmount) {
		double score = 0;
		if (getDoubleVal(val) > getDoubleVal(loanAmount)) {
			score = 100;
		}
		return score;
	}

	/**
	 * getLoanAmountScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getLoanAmountScore(String val) {
		return Math.abs(getDoubleVal(val));
	}

	/**
	 * getProdcatScore
	 * 
	 * @param val
	 * @return
	 */
	public static double getProdcatScore(String val) {
		double score = 0;
		double value = getDoubleVal(val);
		if (value <= 10) {
			score = 100;
		} else if (value > 10 && value < 40) {
			score = (-10 / 3) * (value - 40);
		}
		return score;
	}

	/**
	 * calculateLoanScores
	 * 
	 * @param loanVarScoreModels
	 * @param loanScoreModel
	 */
	public static void calculateLoanScores(List<LoanVarScoreModel> loanVarScoreModels, LoanScoreModel loanScoreModel) {
		double financialHealthScore = 0;
		double marketplacePerformanceScore = 0;
		double socialReliabiltyScore = 0;
		double statutoryComplianceScore = 0;
		double score = 0;
		for (LoanVarScoreModel loanVarScoreModel : loanVarScoreModels) {
			String variable = loanVarScoreModel.getVarScoreModel().getVarName();
			double varScore = loanVarScoreModel.getVarScore();
			score += varScore;
			if (LKConstants.FINANCIAL_HEALTH_SCORE_VARS.contains(variable)) {
				financialHealthScore += varScore;
			} else if (LKConstants.MARKETPLACE_PERFORMANCE_SCORE_VARS.contains(variable)) {
				marketplacePerformanceScore += varScore;
			} else if (LKConstants.SOCIAL_RELIABILITY_SCORE_VARS.contains(variable)) {
				socialReliabiltyScore += varScore;
			} else if (LKConstants.STATUTORY_COMPLIANCE_VARS.contains(variable)) {
				statutoryComplianceScore += varScore;
			}
		}
		loanScoreModel.setScore(score + "");
		loanScoreModel.setFinancialHealthScore(financialHealthScore + "");
		loanScoreModel.setMarketplacePerformanceScore(marketplacePerformanceScore + "");
		loanScoreModel.setSocialReliabiltyScore(socialReliabiltyScore + "");
		loanScoreModel.setStatutoryCompliance(statutoryComplianceScore + "");
	}

	/**
	 * getdoubleVal
	 * 
	 * @param str
	 * @return
	 */
	public static double getDoubleVal(String str) {
		double value = 0;
		try {
			value = Double.valueOf(str);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * getIntVals
	 * 
	 * @param str
	 * @return
	 */
	public static int getIntVal(String str) {
		int value = 0;
		try {
			value = Integer.valueOf(str);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * min
	 * 
	 * @param val1
	 * @param val2
	 * @return
	 */
	public static double min(double val1, double val2) {
		return Math.min(val1, val2);
	}

	/**
	 * getLinearDecrease
	 * 
	 * @return
	 */
	public static double getLinearDecrease() {
		return 0;
	}

	/**
	 * getLinearIncrease
	 * 
	 * @return
	 */
	public static double getLinearIncrease() {
		return 0;
	}

	/**
	 * getVarScore
	 * 
	 * @param variable
	 * @param varScoreModel
	 * @return
	 */
	public static double getVarScore(Variables variable, VarScoreModel varScoreModel) {
		double score = 0;
		try {
			String methodName = LKConstants.VARS_MAP.get(variable.getKey());
			System.out.println("********** methodName :: " + methodName);
			score = (double) MethodUtils.invokeStaticMethod(LKScoreCalcUtil.class, methodName,
					new Object[] { variable.getVal() }, new Class<?>[] { String.class });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LKUtils.doubleRound(score * varScoreModel.getWeight());
	}
}
