import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String branch = request.getParameter("branch");
		try {
			Connection con = MYSQLConnection.getMySQLConnection();
			String sql = "INSERT INTO STUDENT(NAME,PASSWORD,BIRTHDAY,GENDER, EMAIL,PHONE,BRANCH)  VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,birthday);
			ps.setString(4,gender);
			ps.setString(5,email);
			ps.setString(6,phone);
			ps.setString(7,branch);
			int rowCount = ps.executeUpdate();
			if(rowCount > 0) {
				response.sendRedirect("/JSPCRUD/Login");
			}
			else {
				response.sendRedirect("/JSPCRUD/Register");
			}
			}
		catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("SQL Exception");
			e.printStackTrace();
		}
	}

}
