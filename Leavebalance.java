package employeeManagement;

import java.util.Date;

public class Leavebalance {
	private int emp_id, leave_balance,leave_used;
	private Date startDate,endDate;
	private String status;
	public Leavebalance(int emp_id,int leave_balance,int leave_used
			,Date startDate,Date endDate,String status) {
		this.emp_id=emp_id;
		this.leave_balance=leave_balance;
		this.leave_used=leave_used;
		this.startDate=startDate;
		this.endDate=endDate;
		this.status=status;
	}
	
}
