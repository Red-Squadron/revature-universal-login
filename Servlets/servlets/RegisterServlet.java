package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet {
	private static final long serialVersionUID = 1L;
    
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pstmt;
	static PrintWriter pw;
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
			
			pw = response.getWriter();
			response.setContentType("text/html");
			
			String email = request.getParameter("userEmail");
			String fname = request.getParameter("firstname");
			String mname = request.getParameter("middlename");
			String lname = request.getParameter("lastname");
			String phone = request.getParameter("phoneNumber");
			String pass = request.getParameter("password");
			
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", database, password);
			String SQL = "call database.add_user(?, ?, ?, ?, ?, ?)";
			cstmt = conn.prepareCall(SQL);
			cstmt.setString(1, email);
			cstmt.setString(2, fname);
			cstmt.setString(3, mname);
			cstmt.setString(4, lname);
			cstmt.setString(5, phone);
			cstmt.setString(6, pass);
			cstmt.execute();
			
			//rs.close();
			//stmt.close();
			cstmt.close();
			conn.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.sendRedirect("FIndAddCustomers.html");
		
	}
}
