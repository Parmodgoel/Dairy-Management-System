package allMilkmen;

public class MilkmanBean {
	String MilkmanID;
	String MilkmanName;
	String Mobile;
	String Address;
	String Salary;
	
	public MilkmanBean(String MilkmanID, String MilkmanName, String Mobile, String Address, String Salary) {
		super();
		this.MilkmanID = MilkmanID;
		this.MilkmanName = MilkmanName;
		this.Mobile = Mobile;
		this.Address = Address;
		this.Salary = Salary;
	}

	public MilkmanBean() {
		
	}
	
	public String getMilkmanID() {
		return MilkmanID;
	}

	public void setMilkmanID(String milkmanID) {
		MilkmanID = milkmanID;
	}

	public String getMilkmanName() {
		return MilkmanName;
	}

	public void setMilkmanName(String milkmanName) {
		MilkmanName = milkmanName;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}
}
