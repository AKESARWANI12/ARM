package controller;

import model.CancelledTrip;
import model.CancelledTripDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/cancelled-bookings")
public class CancelledBookingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        List<CancelledTrip> trips = CancelledTripDAO.getCancelledTripsByUserId(userId);

        session.setAttribute("userName", session.getAttribute("userName")); // Optional
        request.setAttribute("trips", trips);
        RequestDispatcher rd = request.getRequestDispatcher("Cancelled.jsp");
        rd.forward(request, response);
    }
}
