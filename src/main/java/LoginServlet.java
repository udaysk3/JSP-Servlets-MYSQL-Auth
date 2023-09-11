
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String password = request.getParameter("password");
		String email = request.getParameter("email");
		try {
			Connection con = MYSQLConnection.getMySQLConnection();
			String sql = "SELECT * FROM STUDENT WHERE EMAIL=? AND PASSWORD=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("name",rs.getString("name"));
				response.sendRedirect("/JSPCRUD/index.jsp");
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
