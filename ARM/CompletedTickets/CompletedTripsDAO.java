package model;

import java.sql.*;
import java.util.*;

public class CompletedTripsDAO {
    public static List<CompletedTrip> getCompletedTrips(int userId) {
        List<CompletedTrip> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT C.CarrierName || ' ' || F.FlightID AS FlightName, " +
                           "F.Origin, F.Destination, B.DateOfTravel, B.NoOfSeats, B.SeatCategory, B.BookingAmount " +
                           "FROM FlightBooking B " +
                           "JOIN Flight F ON B.FlightID = F.FlightID " +
                           "JOIN Carrier C ON F.CarrierID = C.CarrierID " +
                           "WHERE B.UserID = ? AND B.BookingStatus = 'Booked' AND B.DateOfTravel < CURRENT_DATE";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CompletedTrip trip = new CompletedTrip();
                trip.setFlightName(rs.getString("FlightName"));
                trip.setOrigin(rs.getString("Origin"));
                trip.setDestination(rs.getString("Destination"));
                trip.setTravelDate(rs.getDate("DateOfTravel"));
                trip.setNoOfTickets(rs.getInt("NoOfSeats"));
                trip.setSeatCategory(rs.getString("SeatCategory"));
                trip.setBookedPrice(rs.getInt("BookingAmount"));
                list.add(trip);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
