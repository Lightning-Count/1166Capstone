package itemList;

public class Pkg {

	private String trackingNumber;
	private String accountName;
	private String dateShipped;
	private String dateArrival;
	private String status;
	
	private final static String EMPTYDATE = "0000-00-00";
	
	public Pkg(String trackingNumber, String accountName,
			String dateShipped, String dateArrival, String status)
	{
		this.trackingNumber = trackingNumber;
		this.accountName = accountName;
		if(dateShipped.equals(EMPTYDATE))
		{
			this.dateShipped = "Not Shipped Yet";
		}
		else
		{
			this.dateShipped = dateShipped;		
		}
		if(dateArrival.equals(EMPTYDATE))
		{
			this.dateArrival = "No Date Calculated";
		}
		else
		{
			this.dateArrival = dateArrival;			
		}
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		return trackingNumber + " || " + accountName + " || " + dateShipped + " || "
				+ dateArrival + " || " + status;
	}



	//Getters and Setters
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(String dateShipped) {
		this.dateShipped = dateShipped;
	}

	public String getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(String dateArrival) {
		this.dateArrival = dateArrival;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}