package exceptions;
import rocketDomain.RateDomainModel;
public class RateException extends Exception {

	RateDomainModel rate;

	public RateException(RateDomainModel d)
	{
		this.rate = d;
	}

	public RateDomainModel getRateDomainModel()
	{
		return this.rate;
	}
}
