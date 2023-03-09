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
	
	public void calaculateSalary() {};
}
