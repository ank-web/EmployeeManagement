package employeeManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class employeeManagement {
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
	private static final String USER = "CISADM";
	private static final String PASS = "CISADM";

	public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException,NoSuchElementException {
		Connection conn;
		Report report = new Report(); 
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		Scanner scanner = new Scanner(System.in);
        String choice = "0";
        int emp_id;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add new employee");
            System.out.println("2. Get indivdual employee attributes");
            System.out.println("3. Update indivdual employee attributes");
            System.out.println("4. Calculate salary");
            System.out.println("5. Get Number Of Employees");
            System.out.println("6. Get Average Salary");
            System.out.println("7. calculate Total Hours Worked By Employee ");
            System.out.println("8. calculate Employees By JobTitle");
            System.out.println("9. calculate Employees By Department");
            System.out.println("10. calculate Average Salary By Department");
            System.out.println("11. generate report based on the status of the employee");
            System.out.println("12. Update Timesheet");
            System.out.println("13. Exit");
            
            System.out.print("Enter your choice: ");

            choice = scanner.nextLine();
//        	scanner.nextLine(); 
        	
            switch (choice) {
            
                case "1":
                	System.out.print("Enter employee id: ");
                    emp_id= scanner.nextInt();
                	scanner.nextLine();
                    System.out.print("Enter employee first name: ");
                    String first_name = scanner.nextLine();
                    System.out.print("Enter employee last name: ");
                    String last_name = scanner.nextLine();
                    System.out.print("Enter employee email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter employee address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter employee Phone Number: ");
                    Double phonenumber = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter employee department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter employee joining date (yyyy-mm-dd): ");
                    LocalDate joiningDate = LocalDate.parse(scanner.nextLine());
                    scanner.nextLine();
                    System.out.print("Enter employee status:");
                    String status = scanner.nextLine();
                    System.out.print("Enter employee jobTitle:");
                    String jobTitle = scanner.nextLine();
                    System.out.print("Enter employee Salary:");
                    Double salary = scanner.nextDouble();
                    scanner.nextLine();
                    Employee Employee = new Employee(emp_id,first_name,last_name,
                    		email, address, phonenumber,joiningDate,status,
                    		jobTitle,department,salary);
                    Employee.addEmployee(conn,Employee);
                    break;
//                case 2:
//                    System.out.print("Enter employee id: ");
//                    emp_id = scanner.nextInt();
//                    System.out.print("Enter the number of reuested leaves (in Days): ");
//                    int daysRequested = scanner.nextInt();
//                    Leavebalance lb = new Leavebalance();
//                    lb.approveRejectLeave(emp_id,daysRequested);
//                    break;
                case "2":    
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
                        
                        scanner.nextLine(); // consume the newline character
                        Employee employee = new Employee();
                        switch (subChoice1) {
	                        case 1:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine(); 
	                            String firstName = employee.getFirstName(emp_id,conn);
	                            if (firstName != null) {
	                                System.out.println("First Name: " + firstName);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 2:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine(); 
	                            String lastName = employee.getLastName(emp_id,conn);
	                            if (lastName != null) {
	                                System.out.println("Last Name: " + lastName);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 3:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine(); 
	                            email = employee.getEmail(emp_id, conn);
	                            if (email != null) {
	                                System.out.println("Email: " + email);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 4:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine(); 
	                            address = employee.getAddress(emp_id,conn);
	                            if (address != null) {
	                                System.out.println("Address: " + address);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 5:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine();
	                            Double phoneNumber = employee.getPhone(emp_id,conn);
	                            Double num = Double.valueOf(phoneNumber);
	                            if (num != 0) {
	                                System.out.println("Phone Number: " + num);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 6:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine();
	                            joiningDate = employee.getJoiningDate(emp_id,conn);
	                            if (joiningDate != null) {
	                                System.out.println("Joining Date: " + joiningDate);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 7:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine();
	                        	status = employee.getStatus(emp_id,conn);
	                            if (status != null) {
	                                System.out.println("Status: " + status);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 8:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine();
	                        	jobTitle = employee.getJobTitle(emp_id,conn);
	                            if (jobTitle != null) {
	                                System.out.println("Job Title: " + jobTitle);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 9:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine();
	                            String dep = employee.getDepartment(emp_id,conn);
	                            if (dep != null) {
	                                System.out.println("Department: " + dep);
	                            } else {
	                                System.out.println("Employee with ID " + emp_id + " does not exist in database.");
	                            }
	                            break;
	                        case 10:
	                        	System.out.print("Enter employee id: ");
	                            emp_id = scanner.nextInt();
	                            scanner.nextLine(); 
	                            
	                            salary = employee.getSalary(emp_id,conn);
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
                
                case "3": 
                	Employee employee = new Employee();
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
                        subChoice2 = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (subChoice2) {
                        
                        case 1:  
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new first_name: ");
                            String newFirst = scanner.nextLine();
                            employee.setFirstName(emp_id,newFirst,conn);
                            break;
                        case 2:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new Last name: ");
                            String newLast= scanner.nextLine();
                            employee.setLastName(emp_id,newLast,conn);
                            break;
                        case 3:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new email: ");
                            String newEmails = scanner.nextLine();
                            employee.setEmail(emp_id,newEmails,conn);
                            break;
                        case 4:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new address: ");
                            String newAddress = scanner.nextLine();
                            employee.setAddress(emp_id,newAddress,conn );
                            break;
                        case 5:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new phone number: ");
                            Double newPhoneNo = scanner.nextDouble();
                            scanner.nextLine();
                            employee.setPhone(emp_id, newPhoneNo,conn);
                            break;
                        case 6:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new joining date (yyyy-mm-dd): ");
                            LocalDate newJoiningDate = LocalDate.parse(scanner.nextLine());
                            employee.setJoiningDate(emp_id, newJoiningDate,conn);
                            break;
                        case 7:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new status: ");
                            String newStatus = scanner.nextLine();
                            employee.setStatus(emp_id,newStatus,conn);
                            break;
                        case 8:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new job title: ");
                            String newJobTitle = scanner.nextLine();
                            employee.setJobTitle(emp_id,newJobTitle,conn);
                            break;
                        case 9:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new department: ");
                            String newDepartment = scanner.nextLine();
                            employee.setDepartment(emp_id,newDepartment,conn);
                            break;
                        case 10:
                        	System.out.print("Enter employee id: ");
                            emp_id =scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new salary: ");
                            Double newSalary = scanner.nextDouble();
                            employee.setSalary(emp_id,newSalary,conn);
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
                case "4":
                    System.out.print("Enter employee id: ");
                    emp_id = scanner.nextInt();
                    
                    Salary sal = new Salary();
                    double calculatedSalary = sal.calculateSalary(emp_id);
                    System.out.println("Calculated salary: " + calculatedSalary);
                    break;
                case "5":
                	System.out.println("Get Number of Employees");
                	System.out.println("Total Number Of Employees: "+report.getNumberOfEmployees());
                	break;
                case "6": 
                	System.out.println("AverageSalary: "+report.getAverageSalary());
                	break;
                case "7":
                	System.out.println(report.calculateTotalHoursWorkedByEmployee());
                	break;
                case "8":
                	System.out.println(report.calculateEmployeesByJobTitle());
                	break;
                case "9":
                	System.out.println(report.calculateEmployeesByDepartment());
                	break;
                case "10":
                	System.out.println("department:");
                	department = scanner.nextLine();
                	System.out.println(report.calculateAverageSalaryByDepartment(department));
                	break;
                case "11":
                	System.out.println("status:");
            		String s = scanner.nextLine();
                	report.generate_monthly_report(s);
                	break;
                case "12":
                    //System.out.print("Enter timesheet file name: ");
                    //String fileName = scanner.nextLine();
                    Timesheet ts = new Timesheet();
                    ts.updateTimeSheet();
                    break;
                case "13":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != "13");
        scanner.close();
	}
}