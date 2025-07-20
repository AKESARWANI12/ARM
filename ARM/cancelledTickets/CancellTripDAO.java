package model;
import java.sql.*;
import java.util.*;

public class CancelledTripDAO {
    public static List<CancelledTrip> getCancelledTripsByUserId(int userId) {
        List<CancelledTrip> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection(); // You must have a DBConnection.java file
            String sql = "SELECT c.CarrierName, f.FlightID, f.Origin, f.Destination, b.DateOfTravel, " +
                         "b.NoOfSeats, b.SeatCategory, b.BookingAmount, b.RefundedAmount " +
                         "FROM FlightBooking b " +
                         "JOIN Flight f ON b.FlightID = f.FlightID " +
                         "JOIN Carrier c ON f.CarrierID = c.CarrierID " +
                         "WHERE b.UserID = ? AND b.BookingStatus = 'Cancelled'";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CancelledTrip trip = new CancelledTrip();
                trip.setFlightName(rs.getString("CarrierName") + " " + rs.getInt("FlightID"));
                trip.setOrigin(rs.getString("Origin"));
                trip.setDestination(rs.getString("Destination"));
                trip.setTravelDate(rs.getDate("DateOfTravel").toString());
                trip.setNoOfTickets(rs.getInt("NoOfSeats"));
                trip.setSeatCategory(rs.getString("SeatCategory"));
                trip.setBookedAmount(rs.getInt("BookingAmount"));
                trip.setRefundedAmount(rs.getInt("RefundedAmount"));
                list.add(trip);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
