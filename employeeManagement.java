package employeeManagement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeManagement {
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
	private static final String USER = "CISADM";
	private static final String PASS = "CISADM";

	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		Connection conn;
		
		
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add new employee");
            System.out.println("2. Get indivdual employee attributes");
            System.out.println("3. Update indivdual employee attributes");
            System.out.println("4. Add leave balance");
            System.out.println("5. Add timesheet");
            System.out.println("6. Calculate salary");
            System.out.println("7. Generate report");
            
            System.out.println("8. Exit");
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
                    int phoneNo = scanner.nextInt();
                    System.out.print("Enter employee department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter employee joining date (yyyy-mm-dd):");
                    LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter employee status:");
                    String status = scanner.nextLine();
                    System.out.print("Enter employee jobTitle:");
                    String jobTitle = scanner.nextLine();
                    System.out.print("Enter employee Salary:");
                    Double salary = scanner.nextDouble();
                    Employee employee = new Employee(emp_id,first_name,last_name,
                    		email, address, phoneNo,joiningDate,status,
                    		jobTitle,department,salary );
                    employee.addEmployee(conn,employee);
                    break;
                case 2:    
                	int subChoice1 = 0;
                    do {
                        System.out.println("Get employee attributes:");
                        
                        System.out.println("1. getFirstName");
                        System.out.println("2. getLastName");
                        System.out.println("3. getEmail");
                        System.out.println("4. getAddress");
                        System.out.println("5. getPhoneNumber");
                        System.out.println("6. getJoiningDate");
                        System.out.println("7. getStatus");
                        System.out.println("8. getJobTitle");
                        System.out.println("9. getDepartment");
                        System.out.println("10. getSalary");
                        System.out.println("11. Exit to main menu");
                        System.out.print("Enter your choice: ");           
                        subChoice1 = scanner.nextInt();
                        System.out.print("Enter employee id: ");
                        emp_id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline character
                        switch (subChoice1) {
	                        
	                        case 1:
	                            
	                            String firstName = Employee.getFirstName(emp_id,conn);
	                            if (firstName != null) {
	                                System.out.println("First Name: " + firstName);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 2:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            String lastName = Employee.getLastName(emp_id,conn);
	                            if (lastName != null) {
	                                System.out.println("Last Name: " + lastName);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 3:
	                            email = Employee.getEmail(emp_id, conn);
	                            if (email != null) {
	                                System.out.println("Email: " + email);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 4:
	                            address = Employee.getAddress(emp_id,conn);
	                            if (address != null) {
	                                System.out.println("Address: " + address);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 5:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            int phoneNumber = Employee.getPhone(emp_id,conn);
	                            if (phoneNumber != 0) {
	                                System.out.println("Phone Number: " + phoneNumber);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 6:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            joiningDate = Employee.getJoiningDate(emp_id,conn);
	                            if (joiningDate != null) {
	                                System.out.println("Joining Date: " + joiningDate);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 7:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                        	status = Employee.getStatus(emp_id,conn);
	                            if (status != null) {
	                                System.out.println("Status: " + status);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 8:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                        	jobTitle = Employee.getJobTitle(emp_id,conn);
	                            if (jobTitle != null) {
	                                System.out.println("Job Title: " + jobTitle);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 9:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            String dep = Employee.getDepartment(emp_id,conn);
	                            if (dep != null) {
	                                System.out.println("Department: " + dep);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 10:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            salary = Employee.getSalary(emp_id,conn);
	                            System.out.println("Salary: " + salary);
	                            break;
	                        case 11:
	                            System.out.println("Exiting...");
	                            break;
	                        default:
	                            System.out.println("Invalid choice, please try again.");
	                            break;
                        }
                    }
                        while(subChoice1!=11);
                    
                        
                case 3:    
                	int subChoice2 = 0;
                    do {
                        System.out.println("Set employee attributes:");
                        
                        System.out.println("1. setFirstName");
                        System.out.println("2. setLastName");
                        System.out.println("3. setEmail");
                        System.out.println("4. setAddress");
                        System.out.println("5. setPhoneNumber");
                        System.out.println("6. setJoiningDate");
                        System.out.println("7. setStatus");
                        System.out.println("8. setJobTitle");
                        System.out.println("9. setDepartment");
                        System.out.println("10. setSalary");
                        System.out.println("11. Exit to main menu");
                        System.out.print("Enter your choice: ");           
                        subChoice1 = scanner.nextInt();
                        System.out.print("Enter employee id: ");
                        emp_id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline character
                        switch (subChoice2) {
	                        
	                        case 1:	                        
	                        	System.out.print("Enter new first_name: ");
	                            String newFirst = scanner.nextLine();
	                            Employee.setFirstName(emp_id,newFirst,conn);
	                            break;
	                        case 2:
//	                            System.out.print("Enter new Second name: ");
	                            String newLast= scanner.nextLine();
	                            Employee.setLastName(emp_id,newLast,conn);
	                            break;
	                        case 3:
	                            System.out.print("Enter new email: ");
	                            String newEmails = scanner.nextLine();
	                            Employee.setEmail(emp_id,newEmails,conn);
	                            break;
	                        case 4:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            address = Employee.getAddress(emp_id,conn);
	                            if (address != null) {
	                                System.out.println("Address: " + address);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 5:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            int phoneNumber = Employee.getPhone(emp_id,conn);
	                            if (phoneNumber != 0) {
	                                System.out.println("Phone Number: " + phoneNumber);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 6:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            joiningDate = Employee.getJoiningDate(emp_id,conn);
	                            if (joiningDate != null) {
	                                System.out.println("Joining Date: " + joiningDate);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 7:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                        	status = Employee.getStatus(emp_id,conn);
	                            if (status != null) {
	                                System.out.println("Status: " + status);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 8:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                        	jobTitle = Employee.getJobTitle(emp_id,conn);
	                            if (jobTitle != null) {
	                                System.out.println("Job Title: " + jobTitle);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 9:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            String dep = Employee.getDepartment(emp_id,conn);
	                            if (dep != null) {
	                                System.out.println("Department: " + dep);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 10:
//	                            System.out.print("Enter employee id: ");
//	                            emp_id = scanner.nextInt();
	                            salary = Employee.getSalary(emp_id,conn);
	                            System.out.println("Salary: " + salary);
	                            break;
	                        case 11:
	                            System.out.println("Exiting...");
	                            break;
	                        default:
	                            System.out.println("Invalid choice, please try again.");
	                            break;
                        }
                    }
                        while(subChoice2!=11);
                    
                case 4:
                    System.out.print("Enter employee id: ");
                    emp_id = scanner.nextInt();
                    System.out.print("Enter the number of reuested leaves (in Days): ");
                    int daysRequested = scanner.nextInt();
                    Leavebalance lb = new Leavebalance();
                    lb.approveRejectLeave(emp_id,daysRequested);
                    break;
                case 5:
                    System.out.print("Enter timesheet file name: ");
                    String fileName = scanner.nextLine();
                    Timesheet ts = new Timesheet();
                    ts.updateTimeSheet();
                    break;
                case 6:
                    System.out.print("Enter employee id: ");
                    emp_id = scanner.nextInt();
                    System.out.print("Enter leave balance: ");
                    int lbalance = scanner.nextInt();
                    System.out.print("Enter basic salary: ");
                    double basicSalary = scanner.nextDouble();
                    Salary sal = new Salary(emp_id, basicSalary,lbalance);
                    double calculatedSalary = sal.calculateSalary(emp_id);
                    System.out.println("Calculated salary: " + calculatedSalary);
                    break;
                case 7:
                    System.out.print("Enter report start date (yyyy-mm-dd): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter report end date (yyyy-mm-dd): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());
                    //Report report = new Report(startDate, endDate);
                    //report.generateReport();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 8);
        scanner.close();
	}
	
}