package pakages;
import java.sql.*;
public class AddBook{
	String bookname;
	String Authorname;
	String bookcnt;
	String statusr;
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthorname() {
		return Authorname;
	}
	public void setAuthorname(String authorname) {
		Authorname = authorname;
	}
	public String getBookcnt() {
		return bookcnt;
	}
	public void setBookcnt(String bookcnt) {
		this.bookcnt = bookcnt;
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
			//making connection to database
			Connection con=DBConnection.createConnection();
			
			//inserting into book table
			String query="INSERT INTO book(name,author,number) VALUES(?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, getBookname());
			ps.setString(2, getAuthorname());
			ps.setString(3, getBookcnt());
			i = ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(i>0) {
			statusr="Submission is OK 🙂";
			return "response";
		}
		else {
			statusr="Submission Already Exist's 🙁";
			return "response";
		}
	}	
	// public static boolean tryParse(String s) {
	// 	try{
	// 		return Integer.parseInt(s) > 0 ? true : false;
	// 	}
	// 	catch(Exception e) {
	// 		return false;
	// 	}
	// }
	
	// public static boolean isAlpha(String s) {
	// 	return s != null && s.matches("^[a-zA-Z\\s-, ]+$");
	// }	
}
