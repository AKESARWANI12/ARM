package controller;

import model.CompletedTripsDAO;
import model.CompletedTrip;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CompletedTripsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        List<CompletedTrip> trips = CompletedTripsDAO.getCompletedTrips(userId);
        request.setAttribute("trips", trips);
        request.getRequestDispatcher("completed.jsp").forward(request, response);
    }
}
