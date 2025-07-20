@WebServlet("/cancel-booking")
public class CancelBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        int flightId = Integer.parseInt(request.getParameter("flightId"));
        String seatCategory = request.getParameter("seatCategory");
        Date travelDate = Date.valueOf(request.getParameter("dateOfTravel"));
        int noOfSeats = Integer.parseInt(request.getParameter("noOfSeats"));
        int refundAmount = Integer.parseInt(request.getParameter("refundAmount"));
        String flightName = request.getParameter("flightName");

        try (Connection con = DBUtil.getConnection()) {
            con.setAutoCommit(false);

            // 1. Update Booking Status and Refund Amount
            String updateBooking = "UPDATE FlightBooking SET BookingStatus='Cancelled', refundedAmount=? WHERE BookingID=?";
            try (PreparedStatement ps = con.prepareStatement(updateBooking)) {
                ps.setInt(1, refundAmount);
                ps.setInt(2, bookingId);
                ps.executeUpdate();
            }

            // 2. Update FlightSchedule count
            String column = seatCategory + "ClassBookedCount";
            String updateSchedule = "UPDATE FlightSchedule SET " + column + " = " + column + " - ? " +
                                    "WHERE FlightID = ? AND DateOfTravel = ?";
            try (PreparedStatement ps = con.prepareStatement(updateSchedule)) {
                ps.setInt(1, noOfSeats);
                ps.setInt(2, flightId);
                ps.setDate(3, travelDate);
                ps.executeUpdate();
            }

            con.commit();
            request.getSession().setAttribute("cancelMessage", flightName + " is cancelled");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("cancelMessage", "Error cancelling flight.");
        }

        response.sendRedirect("upcoming-trips");
    }
}
