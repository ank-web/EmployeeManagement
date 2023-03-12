package employeeManagement;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class employeeManagement {
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
	private static final String USER = "CISADM";
	private static final String PASS = "CISADM";

	public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
		Connection conn;
		
		
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add new employee");
            System.out.println("2. Add leave balance");
            System.out.println("3. Add timesheet");
            System.out.println("4. Calculate salary");
            System.out.println("5. Get Number Of Employees");
            System.out.println("6. Get Average Salary");
            System.out.println("7. calculate Total Hours Worked By Employee ");
            System.out.println("8. calculate Employees By JobTitle");
            System.out.println("9. calculate Employees By Department");
            System.out.println("10. calculate Average Salary By Department");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            
            // consume the newline character  
            int emp_id;
            switch (choice) {
            
                case 1:
                	System.out.print("Enter employee id: ");
                	emp_id= scanner.nextInt();
                    System.out.print("Enter employee first name: ");
                    String first_name = scanner.nextLine();
                    System.out.print("Enter employee last name: ");
                    String last_name = scanner.nextLine();
                    System.out.print("Enter employee email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter employee address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter employee Phone Number: ");
                    String phoneNo = scanner.nextLine();
                    System.out.print("Enter employee department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter employee joining date (yyyy-mm-dd): ");
                    LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter employee status:");
                    String status = scanner.nextLine();
                    System.out.print("Enter employee jobTitle:");
                    String jobTitle = scanner.nextLine();
                    System.out.print("Enter employee Salary:");
                    Double salary = scanner.nextDouble();
                    Employee employee = new Employee(emp_id,first_name,last_name,
                    		email, address, phoneNo,joiningDate,status,
                    		jobTitle,department,salary);
                    employee.addEmployee(conn,employee);
                    break;
                case 2:
                    System.out.print("Enter employee id: ");
                    emp_id = scanner.nextInt();
                    System.out.print("Enter the number of reuested leaves (in Days): ");
                    int daysRequested = scanner.nextInt();
                    Leavebalance lb = new Leavebalance();
                    lb.approveRejectLeave(emp_id,daysRequested);
                    break;
                case 3:
                    //System.out.print("Enter timesheet file name: ");
                    //String fileName = scanner.nextLine();
                    Timesheet ts = new Timesheet();
                    ts.updateTimeSheet();
                    break;
                case 4:
                    System.out.print("Enter employee id: ");
                    emp_id = scanner.nextInt();
//                    System.out.print("Enter leave balance: ");
//                    int lbalance = scanner.nextInt();
//                    System.out.print("Enter basic salary: ");
//                    double basicSalary = scanner.nextDouble();
                    Salary sal = new Salary();
                    double calculatedSalary = sal.calculateSalary(emp_id);
                    System.out.println("Calculated salary: " + calculatedSalary);
                    break;
                case 5:
                	System.out.println("Get Number of Employees");
                	Report report = new Report();
                	System.out.println(report.getNumberOfEmployees());
                	break;
                case 6:
                	report = new Report(); 
                	System.out.println(report.getAverageSalary());
                	break;
                case 7:
                	report = new Report(); 
                	System.out.println(report.calculateTotalHoursWorkedByEmployee());
                	break;
                case 8:
                	report = new Report(); 
                	System.out.println(report.calculateEmployeesByJobTitle());
                	break;
                case 9:
                	report = new Report(); 
                	System.out.println(report.calculateEmployeesByDepartment());
                	break;
                case 10:
                	System.out.println("department:");
                	department = scanner.nextLine();
                	report = new Report(); 
                	System.out.println(report.calculateAverageSalaryByDepartment(department));
                	break;
                case 11:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 11);
        scanner.close();
	}
}