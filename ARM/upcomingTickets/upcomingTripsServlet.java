@WebServlet("/upcoming-trips")
public class UpcomingTripsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        List<UpcomingTrip> trips = new BookingDAO().getUpcomingTrips(userId);

        request.setAttribute("trips", trips);
        request.getRequestDispatcher("UpcomingTrips.jsp").forward(request, response);
    }
}
