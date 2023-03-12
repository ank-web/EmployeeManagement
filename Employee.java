package employeeManagement;
//package employeeManagement;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//enum EmployeeStatus {
//    INTERN,
//    FULL_TIME,
//    CONTRACTOR
//}

class Employee{
	
	private int emp_id,phoneNumber;
	private String first_name, last_name, email, jobTitle, department, address;
	private Double salary;
	private LocalDate joiningDate;
	private String status;
	
	public Employee(int emp_id,String first_name, String last_name, 
			String email ,String address,
			int phoneNumber, LocalDate joiningDate,
			String status, String jobTitle, String department, Double salary) {
		this.emp_id = emp_id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.jobTitle=jobTitle;
		this.department=department;
		this.address=address;
		this.phoneNumber=phoneNumber;
		this.salary = salary;
		this.joiningDate=joiningDate;
		this.status=status;
	}
	

	
    public List<Employee> getEmployees(Connection conn,int emp_id) throws SQLException {
        // Retrieve all employees from the database
        List<Employee> employees = new ArrayList<>();
//        String sql = "SELECT e.* FROM employee e JOIN ";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Employee employee = new Employee(
                rs.getInt("emp_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getInt("phone"),
                rs.getDate("hire_date"),
                EmployeeStatus.valueOf(rs.getString("status_name")),
                rs.getString("job_title"),
                rs.getString("department"),
                rs.getDouble("salary")
            );
            employees.add(employee);
        }
        return employees;
    }
    
    public void addEmployee(Connection conn, Employee employee) throws SQLException {
    	
        // Add a new employee to the database
        String sql = "INSERT INTO employee (emp_id, first_name, last_name, email, phone, address, hire_date, status_id, job_title, department, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, employee.getEmpId());
        stmt.setString(2, employee.getFirstName());
        stmt.setString(3, employee.getLastName());
        stmt.setString(4, employee.getEmail());
        stmt.setInt(5, employee.getPhone());
        stmt.setString(6, employee.getAddress());
        stmt.setDate(7, new java.sql.LocalDate(employee.getHireDate().getTime()));
        stmt.setInt(8, employee.getStatus().ordinal());
        stmt.setString(9, employee.getJobTitle());
        stmt.setString(10, employee.getDepartment());
        stmt.setDouble(11, employee.getSalary());
        stmt.executeUpdate();
    }
    
    

	public void updateEmployee(Connection conn, Employee employee) throws SQLException {
    	
        // Update an existing employee in the database
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, hire_date = ?, status_id = ?, job_title = ?, department = ?, salary = ? WHERE emp_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, employee.getFirstName());
        stmt.setString(2, employee.getLastName());
        stmt.setString(3, employee.getEmail());
        stmt.setInt(4, employee.getPhone());
        stmt.setString(5, employee.getAddress());
        stmt.setDate(6, new java.sql.LocalDate(employee.getHireDate().getTime()));
        stmt.setInt(7, employee.getStatus().ordinal());
        stmt.setString(8, employee.getJobTitle());
        stmt.setString(9, employee.getDepartment());
        stmt.setDouble(10, employee.getSalary());
        stmt.setInt(11, employee.getEmpId());
        stmt.executeUpdate();
    }
	
	public double getSalary() {
		return salary;
	}

	public String getStatus() {
		return status;
	}

	public int getPhone() {
		return phoneNumber;
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

	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	
//	public static List<Employee> readEmployeesFromFile(String filename) {
//  List<Employee> employees = new ArrayList<>();
//  try {
//      Scanner scanner = new Scanner(new File(filename));
//      while (scanner.hasNextLine()) {
//          String line = scanner.nextLine();
//          String[] parts = line.split(",");
//          int id = Integer.parseInt(parts[0].trim());
//          String firstName = parts[1].trim();
//          String lastName = parts[2].trim();
//          String email = parts[3].trim();
//          String phone = parts[4].trim();
//          String address = parts[5].trim();
//          Date hireDate = new LocalDate(parts[6].trim());
//          String status = parts[7].trim();
//          String jobTitle = parts[8].trim();
//          String department = parts[9].trim();
//          Double salary=Double.parseDouble(parts[10].trim());
//          Employee employee = new Employee(id, firstName, lastName, email, phone, address, hireDate, status,jobTitle,department,salary);
//          employees.add(employee);
//      }
//      scanner.close();
//  } catch (FileNotFoundException e) {
//      System.err.println("File not found: " + filename);
//  }
//  return employees;
//}
	
}
