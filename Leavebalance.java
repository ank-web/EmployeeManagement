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
	public int getEmpId() {
		return emp_id;
	}
	public int getLeaveBalance() {
		return leave_balance;
	}
	public int getLeaveUsed() {
		return leave_used;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date endDate() {
		return endDate;
	}
	public String getStatus() {
		return status;
	}
	private void setLeaveBalance(int leavebalance) {
		this.leave_balance=leavebalance;
	}
	private void setStatus(String string) {
		this.status=string;
		
	}
	private void setLeaveUsed(int i) {
		this.leave_used+=i;
		
	}
	
	//methods 
	public void approveRejectLeave(int daysRequested) {
	    int balance = getLeaveBalance();
	    int used = getLeaveUsed();

	    if (balance >= daysRequested && ( daysRequested <= 2)) {
	           setLeaveBalance(balance - daysRequested);
	           setLeaveUsed(used + daysRequested);
	           setStatus("Approved");
	    } else {
	        setStatus("Insufficient balance or leave duration is not approved");
	    }
	}

	
}
