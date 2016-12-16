package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;


public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		double returnRate = 0;
		boolean found = false;
		
		for(int i = 0; i < rates.size(); i++)
		{
			if(GivenCreditScore >= rates.get(i).getiMinCreditScore() && found == false)
			{
				returnRate = rates.get(i).getdInterestRate();
				found = true;
			}
		}
		
		if(returnRate == 0)
		{
			
			throw new RateException(rates.get(rates.size() - 1));
		}
		else 
		{
			return returnRate;
		}

	}
		
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
