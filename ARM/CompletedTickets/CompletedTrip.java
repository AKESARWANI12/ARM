package model;

import java.util.Date;

public class CompletedTrip {
    private String flightName;
    private String origin;
    private String destination;
    private Date travelDate;
    private int noOfTickets;
    private String seatCategory;
    private int bookedPrice;

    // Getters and setters
    public String getFlightName() {
        return flightName;
    }
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Date getTravelDate() {
        return travelDate;
    }
    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }
    public int getNoOfTickets() {
        return noOfTickets;
    }
    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
    public String getSeatCategory() {
        return seatCategory;
    }
    public void setSeatCategory(String seatCategory) {
        this.seatCategory = seatCategory;
    }
    public int getBookedPrice() {
        return bookedPrice;
    }
    public void setBookedPrice(int bookedPrice) {
        this.bookedPrice = bookedPrice;
    }
}
