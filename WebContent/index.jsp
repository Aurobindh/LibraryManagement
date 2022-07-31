<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.io.*" %> 

<%@page import = "java.sql.DriverManager"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "java.sql.Statement"%>
<%@page import = "java.sql.Connection"%>

<%
String id = request.getParameter("id");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "library";
String userid = "root";
String password = "";
try {
	Class.forName(driver);
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Library Management System</title>
	<link rel="stylesheet" href="styles.css">
	<script src="./js/main.js" type="module" ></script>

</head>
<body>
	<div class="sidenav">	
		<a id="addbok" >Add Book</a>
		<a id="addusr" >Add Users</a>
		<a id="alctbook">Allocate Book</a>
		<a id="bookdta">Book Records</a>
		<a id="userdta">User Records</a>
		<a id="allocationdta">Book Allocation Records</a>
	  </div>
	  <div class="load">
	       <h1>Library Management Applicaiton</h1>
	  </div>
	  <div class="mainpage">

		<div class="addbok">
			<h1>ADD BOOK</h1>
			<form action="AddBook" id="addbook">
			<table>
				<tr>
					<td><strong>Book Name:</strong></td>
					<td><input name="bookname"></td>
				</tr>
				<tr>
					<td><strong>Author Name:</strong></td>
					<td><input name="authorname"></td>
				</tr>
				<tr>
					<td><strong> No. of Books:</strong></td>
					<td><input name="bookcnt"></td>
				</tr>
				<tr>
					<td><input type="reset"  value="reset"></td>
					<td><input type="submit" value="Submit"></td>
				</tr>
			</table>		
			</form>
		</div>
			
		<div class="addusr">
			<h1>ADD USER</h1>
			<form action="Adduser" id="adduser">
				<table>
					<tr>
						<td><strong>User Name:</strong></td>
						<td><input name="username"></td>
					</tr>
					<tr>
						<td><strong>DOB:</strong></td>
						<td><input name="dob" type="date" ></td>
					</tr>
					<tr>
						<td><strong>Email:</strong></td>
						<td><input name="email" type="email"></td>
					</tr>
					<tr>
						<td><strong>Address:</strong></td>
						<td><textarea name="address" rows="4" ></textarea></td>
					</tr>
					<tr>
						<td><strong>Phone:</strong></td>
						<td><input name="phone" pattern="[0-9]{10}"type="tel"></td>
					</tr>
					<tr>
						<td><input type="reset"  value="reset"></td>
						<td><input type="submit" value="submit"></td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="alctbook">
			<h1>Book Allocation</h1>
			<form class="form" action="Allocatebook">
				<table >
					<tr>
						<td><strong>User ID:</strong></td>
						<td>
							<select name="email" required >
								<option></option>
								<%
									try{
										connection = DriverManager.getConnection(connectionUrl+database,userid,password);
										statement = connection.createStatement();
										String query = "Select * from User";
										resultSet = statement.executeQuery(query);
										while(resultSet.next()){
											%>
											<option><%=resultSet.getString("email") %></option>
											<%
										}
										connection.close();
									}
									catch(Exception e){
									
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td><strong>Book :</strong></td>
						<td>
							<select name="book" required>
								<option></option>
								<%
									try{
										connection = DriverManager.getConnection(connectionUrl+database,userid,password);
										statement = connection.createStatement();
										String query = "Select * from Book";
										resultSet = statement.executeQuery(query);
										while(resultSet.next()){
											%>
											<option><%=resultSet.getString("name") %></option>
											<%
										}
										connection.close();
									}
									catch(Exception e){
									
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td><strong>No. of Days:</strong></td>
						<td><input type="Number" name="daycount" value=30 readonly></td>
					</tr>
					<tr>
						<td> </td>
						<td>By default the no. of days is 30 after that rs. 100 is 
						charged for each day.</td>
					</tr>
					<tr>
						<td><strong>No. of Books:</strong></td>
						<td><input type="Number" name="bookcount" value=1 readonly></td>
					</tr>
					<tr>
						<td><input type="reset"  value="reset"></td>
						<td><input type="submit" value="submit"></td>
					</tr>
				</table>
			</form>
		</div>

		<div class="bookdta">
			<h1>BOOK  DATA</h1>
			<table border="1">
				<tr>
				<td><strong>Book name</strong></td>
				<td><strong>Author Name</strong></td>
				<td><strong>No. of Books</strong></td>
				</tr>
				
				<%
				try{
				connection = DriverManager.getConnection(connectionUrl+database, userid, password);
				statement = connection.createStatement();
				String sql ="select * from book";
				resultSet = statement.executeQuery(sql);
				while(resultSet.next()){
				%>
				<tr>
				<td><%=resultSet.getString("name") %></td>
				<td><%=resultSet.getString("author") %></td>
				<td><%=resultSet.getString("number") %></td>
				</tr>
				<%
				}
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				%>
			</table>
		</div>
		
		<div class="userdta">
			<h1>USER DATA</h1>
			<table border="1">
			<tr>
			<td><strong>User Name</strong></td>
			<td><strong>Email</strong></td>
			<td><strong>DOB</strong></td>
			<td><strong>Address</strong></td>
			<td><strong>Phone</strong></td>
			</tr>
			
			<%
			try{
			connection = DriverManager.getConnection(connectionUrl+database, userid, password);
			statement = connection.createStatement();
			String sql ="select * from user";
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
			%>
			<tr>
			<td><%=resultSet.getString("name") %></td>
			<td><%=resultSet.getString("email") %></td>
			<td><%=resultSet.getString("dob") %></td>
			<td><%=resultSet.getString("address") %></td>
			<td><%=resultSet.getString("phone") %></td>
			</tr>
			<%
			}
			connection.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
			%>
			</table>
			<br><br>
		</div>
		
		<div class="allocationdta">
			<h1>BOOK ALLOCATION DATA</h1>
			<table border="1">
			<tr>
			<td><strong>Booking id</strong></td>
			<td><strong>User Name</strong></td>
			<td><strong>Email</strong></td>
			<td><strong>Book Name</strong></td>
			<td><strong>Date Taken</strong></td>
			<td><strong>Due Date</strong></td>
			<td><strong>Date returned</strong></td>
			<td><strong>Days Used</strong></td>
			<td><strong>Fine Amount</strong></td>
			<td><strong>Status</strong></td>
			<td><strong>Update</strong></td>
			</tr>
			
			<%
			try{
				connection = DriverManager.getConnection(connectionUrl+database, userid, password);
				statement = connection.createStatement();
				String sql ="select * from booking";
				resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
			%>
			<tr>
			<td><%=resultSet.getString("bookingid") %></td>
			<td><%=resultSet.getString("username") %></td>
			<td><%=resultSet.getString("email") %></td>
			<td><%=resultSet.getString("book") %></td>
			<td><%=resultSet.getString("date_taken") %></td>
			<td><%=resultSet.getString("due_date") %></td>
			<td><%=resultSet.getString("date_returned") %></td>
			<td><%=resultSet.getString("days_used") %></td>
			<td><%=resultSet.getString("fine") %></td>
			<td><%=resultSet.getString("status") %></td>
			<s:url action="Bookreturn" var="urlTag" >
			    <s:param name="id"><%=resultSet.getString("bookingid") %></s:param>
			</s:url>
			<td><a href="<s:property value="#urlTag" />" >Book Returned</a></td>
			</tr>
			<%
			}
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			%>
			</table>
		</div>
	</div>

</body>
</html>