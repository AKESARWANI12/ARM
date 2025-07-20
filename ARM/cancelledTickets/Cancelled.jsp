<%@ page import="java.util.*, model.CancelledTrip" %>
<%@ page session="true" %>
<%
    List<CancelledTrip> trips = (List<CancelledTrip>) request.getAttribute("trips");
    String userName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Cancelled Trips</title>
  <style>
    /* Reset */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #22304a; /* lighter dark blue */
  color: #ffffff;
}

/* Navigation Bar */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #2e3e5c;
  padding: 15px 30px;
  box-shadow: 0 2px 5px rgba(0, 0, 50, 0.3);
}

.navbar a {
  color: #ffffff;
  text-decoration: none;
  margin: 0 20px;
  font-weight: 500;
  font-size: 16px;
  transition: color 0.3s ease;
}

.navbar a:hover {
  color: #ffffff;
}

/* Welcome Text */
.welcome {
  text-align: right;
  margin: 15px 30px;
  font-size: 18px;
  color: #ffffff;
}

/* Title */
.title {
  text-align: center;
  font-size: 32px;
  font-weight: bold;
  margin: 20px 0;
  color: #ffffff;
}

/* Form Container */
.search-form {
  background-color: #2f3f5a;
  margin: 30px auto;
  padding: 80px 40px;
  width: 50%;
  border: 2px solid #41597a;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 51, 102, 0.3);
}

.line{
    padding: 50px, 20px;
}
/* Dropdown Container */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (hidden by default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #2e3e5c;
  min-width: 160px;
  box-shadow: 0px 8px 16px rgba(0,0,0,0.2);
  z-index: 1;
  border-radius: 4px;
}

/* Dropdown Links */
.dropdown-content a {
  color: white;
  padding: 12px 16px;
  display: block;
  text-decoration: none;
  font-size: 14px;
}

/* Hover Effects */
.dropdown-content a:hover {
  background-color: #3f4f70;
}

.dropdown:hover .dropdown-content {
  display: block;
}

  </style>
</head>
<body>
  <div class="navbar">
    <div class="left">
      <a href="home.jsp">Home</a>
      <a href="profile.jsp">My Profile</a>
      <div class="dropdown">
        <a href="#">My Trips</a>
        <div class="dropdown-content">
          <a href="upcoming-trips">Upcoming</a>
          <a href="cancelled-bookings">Cancelled</a>
          <a href="completed-trips">Completed</a>
        </div>
      </div>
    </div>
    <div class="right">
      <a href="logout.jsp">Logout</a>
    </div>
  </div>

  <div class="welcome">Welcome, <b><%= userName %></b></div>
  <div class="title">Cancelled Trips</div>

  <div class="search-form">
     <% if(trips != null && !trips.isEmpty()){
     %>
        <table style="width:100%; text-align: left; color: white; border-spacing: 0 15px;">
            <tr>
                <th>Flight Name</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Travel Date</th>
                <th>NoOfTickets</th>
                <th>Seat Category</th>
                <th>Booked Price</th>
                <th>Refunded Amount</th>
            </tr>
      
            <% for (CancelledTrip trip : trips) { %>
                <tr>
                <td><%= trip.getFlightName() %></td>
                <td><%= trip.getOrigin() %></td>
                <td><%= trip.getDestination() %></td>
                <td><%= trip.getTravelDate() %></td>
                <td><%= trip.getNoOfTickets() %></td>
                <td><%= trip.getSeatCategory() %></td>
                <td>₹<%= trip.getBookedAmount() %></td>
                <td>₹<%= trip.getRefundedAmount() %></td>
                </tr>
            <%   
                }
            %>
        </table>
     <%
     }
     else{
     %>
        <div style="text-align: center; color: red; font-weight: bold;">No Cancelled Flight Tickets</div>
     <%
     }
     %>
  </div>
</body>
</html>
