package employeeManagement;

import java.sql.SQLException;

public class employeeManagement {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Timesheet timesheet = new Timesheet();
		timesheet.updateTimeSheet();
		System.out.println("this is working");
	}
}
