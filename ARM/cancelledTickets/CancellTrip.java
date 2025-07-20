package model;

public class CancelledTrip {
    private String flightName;
    private String origin;
    private String destination;
    private String travelDate;
    private int noOfTickets;
    private String seatCategory;
    private int bookedAmount;
    private int refundedAmount;

    // Getters and Setters
    public String getFlightName() { return flightName; }
    public void setFlightName(String flightName) { this.flightName = flightName; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getTravelDate() { return travelDate; }
    public void setTravelDate(String travelDate) { this.travelDate = travelDate; }

    public int getNoOfTickets() { return noOfTickets; }
    public void setNoOfTickets(int noOfTickets) { this.noOfTickets = noOfTickets; }

    public String getSeatCategory() { return seatCategory; }
    public void setSeatCategory(String seatCategory) { this.seatCategory = seatCategory; }

    public int getBookedAmount() { return bookedAmount; }
    public void setBookedAmount(int bookedAmount) { this.bookedAmount = bookedAmount; }

    public int getRefundedAmount() { return refundedAmount; }
    public void setRefundedAmount(int refundedAmount) { this.refundedAmount = refundedAmount; }
}
