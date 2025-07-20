public class BookingDAO {
    public List<UpcomingTrip> getUpcomingTrips(int userId) {
        List<UpcomingTrip> list = new ArrayList<>();
        String query = "SELECT fb.BookingID, fb.FlightID, fb.DateOfTravel, fb.BookingAmount, fb.SeatCategory, fb.NoOfSeats, " +
                "f.Origin, f.Destination, c.CarrierName, c.RefundPercentageForCancellation2DaysBefore, " +
                "c.RefundPercentageForCancellation10DaysBefore, c.RefundPercentageForCancellation20DaysBefore " +
                "FROM FlightBooking fb " +
                "JOIN Flight f ON fb.FlightID = f.FlightID " +
                "JOIN Carrier c ON f.CarrierID = c.CarrierID " +
                "WHERE fb.BookingStatus = 'Booked' AND fb.UserID = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UpcomingTrip trip = new UpcomingTrip();
                trip.setBookingId(rs.getInt("BookingID"));
                trip.setFlightId(rs.getInt("FlightID"));
                trip.setDateOfTravel(rs.getDate("DateOfTravel"));
                trip.setBookingAmount(rs.getInt("BookingAmount"));
                trip.setNoOfSeats(rs.getInt("NoOfSeats"));
                trip.setSeatCategory(rs.getString("SeatCategory"));
                trip.setOrigin(rs.getString("Origin"));
                trip.setDestination(rs.getString("Destination"));
                String flightName = rs.getString("CarrierName") + " " + rs.getInt("FlightID");
                trip.setFlightName(flightName);

                // Refund logic
                long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), trip.getDateOfTravel().toLocalDate());
                int refundPercent;
                if (daysLeft >= 20) refundPercent = rs.getInt("RefundPercentageForCancellation20DaysBefore");
                else if (daysLeft >= 10) refundPercent = rs.getInt("RefundPercentageForCancellation10DaysBefore");
                else refundPercent = rs.getInt("RefundPercentageForCancellation2DaysBefore");

                int refundAmount = (trip.getBookingAmount() * refundPercent) / 100;
                trip.setRefundAmount(refundAmount);
                list.add(trip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
