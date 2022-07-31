package pakages;
import java.sql.*;
import java.time.LocalDate;

//allocation bo
public class Allocatebook {
	String email;
	String book;
	String statusr;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getStatusr() {
		return statusr;
	}
	public void setStatusr(String statusr) {
		this.statusr = statusr;
	}
	public String execute() {
		
		int i=0;
		
		String Insertquery="INSERT INTO booking(username,email,book,date_taken,due_date,fine,status) VALUES(?,?,?,?,?,?,?)";
		String fetchusername="Select * from user where email= ? ";
		String fetchbookcnt="select * from book where name=?";
		String bookcount="UPDATE book SET number = number-1 WHERE (name = ?);";
		
		try {
			//Connecting to Database
			Connection con=DBConnection.createConnection();
			
			//fetching the email id as matching record.
			PreparedStatement ps=con.prepareStatement(fetchusername);
			ps.setString(1, getEmail());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String name=rs.getString("name");
			
			//fetch the bookcount in the book table
			PreparedStatement bc=con.prepareStatement(fetchbookcnt);
			bc.setString(1, getBook());
			ResultSet rc=bc.executeQuery();
			rc.next();
			int bookcnt=rc.getInt("number");
			
			//calculating date taken, due date, fine amount and status.
			Date date_taken=Date.valueOf(LocalDate.now());
			Date due_date = Date.valueOf(LocalDate.now().plusDays(31));
			int fine=0;
			String status="Book Taken";

			if(bookcnt>0) {
				//inserting the record in BookAllocation Table.
				PreparedStatement insertbooking=con.prepareStatement(Insertquery);
				insertbooking.setString(1, name);
				insertbooking.setString(2, getEmail());
				insertbooking.setString(3, getBook());
				insertbooking.setDate(4, date_taken);
				insertbooking.setDate(5, due_date);
				insertbooking.setInt(6,fine);
				insertbooking.setString(7, status);	
				i=insertbooking.executeUpdate();
				
				//updating the book count in Book Table.
				PreparedStatement bookquery=con.prepareStatement(bookcount);
				bookquery.setString(1, getBook());
				bookquery.executeUpdate();
			}
			else {
				statusr="No. of books is zero! ☹️ check and resubmit";
				return "response";
			}
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
}
