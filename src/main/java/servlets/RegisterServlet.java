package servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet {
	private static final long serialVersionUID = 1L;
    
	static Connection conn;
	static PreparedStatement pstmt;
	static CallableStatement cstmt;
	static String database;
	static String password;
	
    public RegisterServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		try {		
			/*
			 * parse json to variables
			 * replace the get parameters with the variables
			 * */
			// init
			
				// set database variables
				database = null;
				password = null;
			
				
			
			// make connection
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", database, password);
			
			// make callable statement
				String SQL = "{call add_new_user(?, ?, ?, ?, ?, ?)}";
				cstmt = conn.prepareCall(SQL);
				
				// set vars
				cstmt.setString(1, email);
				cstmt.setString(2, fname);
				cstmt.setString(3, mname);
				cstmt.setString(4, lname);
				cstmt.setInt(5, phone);
				cstmt.setString(6, pass);

				// execute
				cstmt.execute();
			
			// only need one
				//cstmt.close();
				conn.close();
				
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//response.sendRedirect("FIndAddCustomers.html");
		
	}
}
