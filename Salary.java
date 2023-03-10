package employeeManagement;

public class Salary {
	private int id;
	private int emp_id;
	private double salary;
	private static int leaveBalance;
	
	public Salary(int id,int emp_id,double salary,int leaveBalance) {
		this.id=id;
		this.emp_id=emp_id;
		this.salary=salary;
		this.leaveBalance=leaveBalance;
	}
	
	//methods
	public double getSalary() {return 0.0;};
	
	public double calaculateSalary() {
		if (leaveBalance < 4) {
	        this.salary -= Math.abs(4-this.leaveBalance)*(salary / 30); // Assuming a month has 30 days
	    }
		return this.salary;
	};
}
