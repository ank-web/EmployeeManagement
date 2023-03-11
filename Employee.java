package employeeManagement;
import java.util.Date;

enum EmployeeStatus {
    INTERN,
    FULL_TIME,
    CONTRACTOR
}

class Employee{
	
	private int emp_id;
	private String first_name, last_name, email, jobTitle, department, address;
	private Double phoneNumber, salary;
	private Date hireDate;
	private EmployeeStatus status;
	
	public Employee(int emp_id,String first_name, String last_name, 
			String email, String jobTitle, String department,String address,
			Double phoneNumber, Double salary, Date hireDate, EmployeeStatus status) {
		this.emp_id = emp_id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.jobTitle=jobTitle;
		this.department=department;
		this.address=address;
		this.phoneNumber=phoneNumber;
		this.salary = salary;
		this.hireDate=hireDate;
		this.status=status;
	}
	public int getEmpId() {
		return emp_id;
	}
	public String getFirstName() {
		return first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public String getEmail() {
		return email;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public String getDepartment() {
		return department;
	}
	public String getAddress() {
		return address;
	}
	
}
