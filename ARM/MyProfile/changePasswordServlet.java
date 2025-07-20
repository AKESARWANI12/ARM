package controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String newPassword = request.getParameter("newPassword");
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");

            String sql = "UPDATE Users SET Password = ? WHERE UserID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("password", newPassword); // update session password
                request.setAttribute("message", "Change password successfully");
            } else {
                request.setAttribute("message", "Failed to change password");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error occurred while updating password");
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        request.getRequestDispatcher("user-profile.jsp").forward(request, response);
    }
}
