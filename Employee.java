package employeeManagement;

//package employeeManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;



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
	
  
  public void addEmployee(Connection conn, Employee employee) throws SQLException {
  	
      // Add a new employee to the database
      String sql = "INSERT INTO employee (emp_id, first_name, last_name, email, phone, address, joiningdate, statusid, jobtitle, department, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, employee.getEmpId());
      stmt.setString(2, employee.getFirstName());
      stmt.setString(3, employee.getLastName());
      stmt.setString(4, employee.getEmail());
      stmt.setInt(5, employee.getPhone());
      stmt.setString(6, employee.getAddress());
      stmt.setDate(7, java.sql.Date.valueOf(employee.getJoiningDate()));
      stmt.setString(8, employee.getStatus());
      stmt.setString(9, employee.getJobTitle());
      stmt.setString(10, employee.getDepartment());
      stmt.setDouble(11, employee.getSalary());
      stmt.executeUpdate();
  }

	public void updateEmployee(Connection conn, Employee employee) throws SQLException {
  	
      // Update an existing employee in the database
      String sql = "UPDATE employee SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, joiningdate = ?, status = ?, jobtitle = ?, department = ?, salary = ? WHERE emp_id = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, employee.getFirstName());
      stmt.setString(2, employee.getLastName());
      stmt.setString(3, employee.getEmail());
      stmt.setInt(4, employee.getPhone());
      stmt.setString(5, employee.getAddress());
      stmt.setDate(6, java.sql.Date.valueOf(employee.getJoiningDate()));
      stmt.setString(7, employee.getStatus());
      stmt.setString(8, employee.getJobTitle());
      stmt.setString(9, employee.getDepartment());
      stmt.setDouble(10, employee.getSalary());
      stmt.setInt(11, employee.getEmpId());
      stmt.executeUpdate();
  }
	
	
	//getters functions
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
	public static double getSalary(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT salary FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getDouble("salary");
	    } else {
	        throw new SQLException("Employee with emp_id " + emp_id + " not found");
	    }
	}

	public static String getStatus(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT status FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getString("status");
	    } else {
	        throw new SQLException("Employee with emp_id " + emp_id + " not found");
	    }
	}

	public static int getPhone(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT phone FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("phone");
	    } else {
	        throw new SQLException("Employee with emp_id " + emp_id + " not found");
	    }
	}

	public static String getFirstName(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT first_name FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getString("first_name");
	    } else {
	        throw new SQLException("Employee with emp_id " + emp_id + " not found");
	    }
	}

	public static String getLastName(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT last_name FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getString("last_name");
	    } else {
	        throw new SQLException("Employee with emp_id " + emp_id + " not found");
	    }
	}

	public static String getEmail(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT email FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getString("email");
	    } else {
	        throw new SQLException("Employee with emp_id " + emp_id + " not found");
	    }
	}

	public static String getJobTitle(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT jobtitle FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getString("jobtitle");
	    } else {
	        throw new SQLException("Employee with emp_id " + emp_id + " not found");
	    }
	}

	public static String getDepartment(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT department FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getString("department");
	    } else {
	        return null;
	    }
	}
	public static LocalDate getJoiningDate(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT joiningdate FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
	    	java.sql.Date joiningdate = rs.getDate("joiningdate");
	        return joiningdate.toLocalDate();
	    }

	    return null;
	}
	public static String getAddress(int emp_id, Connection conn) throws SQLException {
	    String sql = "SELECT address FROM employee WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, emp_id);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getString("address");
	    } else {
	        throw new SQLException("No employee found with emp_id: " + emp_id);
	    }
	}
	
	
	//setter functions 
	public static void setFirstName(int emp_id, String firstName, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET first_name = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, firstName);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setLastName(int emp_id, String lastName, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET last_name = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, lastName);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setEmail(int emp_id, String email, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET email = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, email);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setPhone(int emp_id, int phone, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET phone = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, phone);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setAddress(int emp_id, String address, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET address = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, address);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setJoiningDate(int emp_id, LocalDate joiningDate, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET joiningdate = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setDate(1, java.sql.Date.valueOf(joiningDate));
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setDepartment(int emp_id, String department, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET department = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, department);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setJobTitle(int emp_id, String jobTitle, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET jobtitle = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, jobTitle);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}

	public static void setSalary(int emp_id, double salary, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET salary = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setDouble(1, salary);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}
	public static void setStatus(int emp_id, String newStatus, Connection conn) throws SQLException {
	    String sql = "UPDATE employee SET status = ? WHERE emp_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, newStatus);
	    stmt.setInt(2, emp_id);
	    stmt.executeUpdate();
	}



}