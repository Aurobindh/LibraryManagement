package pakages;
import java.sql.*;
import java.time.LocalDate;

public class Bookreturn {
	
	String id;
	String statusr;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getStatusr() {
		return statusr;
	}
	public void setStatusr(String statusr) {
		this.statusr = statusr;
	}
	
	
	public String execute() {
		
		int i=0;
		
		String update="Update booking set status=?,date_returned=?,days_used=?,fine=? where bookingid=?";
		String fetchtaken="Select * from booking where bookingid=?";
		String updatebook="Update book set number=number+1 where name=?";
		
		try {
			//connecting to Database
			Connection con=DBConnection.createConnection();
			
			//fetching datetaken, status and bookname in the Booking table with the id parameter
			PreparedStatement ft =con.prepareStatement(fetchtaken);
			ft.setString(1,getId());
			ResultSet rs=ft.executeQuery();
			rs.next();
			Date datetaken=rs.getDate("date_taken");
			String status=rs.getString("status");
			String bookname=rs.getString("book");
			
			//calculating no. of days the book is used and fine amount.
			Date returned=Date.valueOf(LocalDate.now());
			int days=returned.compareTo(datetaken);
			int fine=0;
			if(days>30) {
				days-=30;
				fine=days*100;
			}
			
			//updating the status in the booking table and incrementing the book count in booktable.
			if(status.equals("Book Taken")) {
				PreparedStatement updatebooking=con.prepareStatement(update);
				updatebooking.setString(1, "Book returned");
				updatebooking.setDate(2, returned);
				updatebooking.setInt(3,days);
				updatebooking.setInt(4, fine);
				updatebooking.setString(5, getId());
				
				PreparedStatement updateboook=con.prepareStatement(updatebook);
				updateboook.setString(1, bookname);
				
				updateboook.executeUpdate();
				i=updatebooking.executeUpdate();
				
				if(i>0) {
					return "submissionok";
				}
				else {
					statusr="Error's in submission! ☹️ check and resubmit";
					return "response";
				}
			}
			else {
				return "submissionok";
			}
		}
		catch(Exception e){
			e.printStackTrace();
			statusr="Error's in submission! ☹️ check and resubmit";
			return "response";
			
		}
		
		
	}
}
