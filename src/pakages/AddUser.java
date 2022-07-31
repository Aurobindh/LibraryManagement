package pakages;
import java.sql.*;

public class AddUser {
	String username;
	String dob;
	String email;
	String address;
	String phone;
	String statusr;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getStatusr() {
		return statusr;
	}
	public void setStatusr(String statusr) {
		this.statusr = statusr;
	}
	public String execute() {
		int i=0;
		try {
			//making connection to the Database.
			Connection con=DBConnection.createConnection();
			
			//inserting into user table
			String query="INSERT INTO user(name,email,dob,address,phone) VALUES(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, getUsername());
			ps.setString(2, getEmail());
			ps.setString(3, getDob());
			ps.setString(4, getAddress());
			ps.setString(5, getPhone());
			i=ps.executeUpdate();
		}
		catch(SQLException e) {
			statusr="Email already exits! ☹️ check and resubmit";
			return "response";
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(i>0) {
			statusr="Submission is OK 🙂";
			return "response";
		}
		else {
			statusr="Error's in submission! ☹️ check and resubmit";
			return "response";
		}
	}
	// public static boolean isAlpha(String s) {
	// 	return s != null && s.matches("^[a-zA-Z\\s-, ]+$");
	// }
}