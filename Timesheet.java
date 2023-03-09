package employeeManagement;

import java.util.Date;

public class Timesheet {
	private int id, emp_id;
	private Date date;
	private int hoursWorked;
	public Timesheet(int id,int emp_id,Date date,int hoursWorked){
		this.id=id;
		this.emp_id=emp_id;
		this.date=date;
		this.hoursWorked=hoursWorked;
	}
	public int getId() {
		return id;
	}
	public int getEmpId() {
		return emp_id;
	}
	public Date getDate() {
		return date;
	}
	public int getHoursWorked() {
		return hoursWorked;
	}
}
