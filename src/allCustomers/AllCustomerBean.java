package allCustomers;

public class AllCustomerBean {
	String MilkmanID;
	String Mobile;
	String CustomerName;
	String Address;
	String Area;
	String City;
	String CQ;
	String CP;
	String BQ;
	String BP;
	String Date;
	public AllCustomerBean(String MilkmanID, String Mobile, String CustomerName, String Address, String Area, String City, String CQ, String CP, String BQ, String BP, String Date) {
		super();
		this.MilkmanID = MilkmanID;
		this.Mobile = Mobile;
		this.CustomerName = CustomerName;
		this.Address = Address;
		this.Area = Area;
		this.City = City;
		this.CQ = CQ;
		this.CP = CP;
		this.BQ = BQ;
		this.BP = BP;
		this.Date = Date;
	}
	public AllCustomerBean() {
	
	}
	public String getMilkmanID() {
		return MilkmanID;
	}
	public void setMilkmanID(String milkmanID) {
		MilkmanID = milkmanID;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCQ() {
		return CQ;
	}
	public void setCQ(String cQ) {
		CQ = cQ;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getBQ() {
		return BQ;
	}
	public void setBQ(String bQ) {
		BQ = bQ;
	}
	public String getBP() {
		return BP;
	}
	public void setBP(String bP) {
		BP = bP;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
}
