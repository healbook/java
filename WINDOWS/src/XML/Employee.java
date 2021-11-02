package XML;
public class Employee {
	private String id;
	private String name;
	private String phone;
	private String location;
	private float salary;
	
	public Employee() {
		super();
	}

	public Employee(String id,String name,String phone,String location,float salary) {
		super();
		this.id = id;
		this.name = name;
		this.phone=phone;
		this.location=location;
		this.salary=salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	
}
