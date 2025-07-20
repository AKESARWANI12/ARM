<%@ page import="java.util.*" %>
<%@ page session="true" %>
<%
    String name = (String) session.getAttribute("name");
    String lastName = (String) session.getAttribute("lastName");
    String username = (String) session.getAttribute("username");
    String phone = (String) session.getAttribute("phone");
    String gmail = (String) session.getAttribute("gmail");
    String dob = (String) session.getAttribute("dob");
    String password = (String) session.getAttribute("password");
    Integer userId = (Integer) session.getAttribute("userId");

    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f4f4f4;
            padding: 30px;
        }
        .info-row {
            margin-bottom: 10px;
        }
        .label {
            display: inline-block;
            width: 150px;
            font-weight: bold;
        }
        .password-row {
            display: flex;
            align-items: center;
        }
        #changePasswordSection {
            margin-top: 10px;
            display: none;
        }
        #successMessage {
            color: green;
            margin-top: 10px;
        }
    </style>
    <script>
        function showPasswordChange() {
            document.getElementById("changePasswordSection").style.display = "block";
            document.getElementById("changeBtn").style.display = "none";
        }
    </script>
</head>
<body>
    <h2>User Profile</h2>

    <div class="info-row"><span class="label">Name:</span> <%= name %></div>
    <div class="info-row"><span class="label">Last Name:</span> <%= lastName %></div>
    <div class="info-row"><span class="label">Username:</span> <%= username %></div>
    <div class="info-row"><span class="label">Phone:</span> <%= phone %></div>
    <div class="info-row"><span class="label">Gmail:</span> <%= gmail %></div>
    <div class="info-row"><span class="label">Date of Birth:</span> <%= dob %></div>

    <div class="info-row password-row">
        <span class="label">Password:</span> <%= password %>
        <button id="changeBtn" onclick="showPasswordChange()" style="margin-left: 20px;">Change Password</button>
    </div>

    <div id="changePasswordSection">
        <form action="change-password" method="post">
            <input type="hidden" name="userId" value="<%= userId %>">
            <input type="password" name="newPassword" placeholder="Enter new password" required>
            <br><br>
            <button type="submit">Save</button>
        </form>
    </div>

    <% if (message != null) { %>
        <div id="successMessage"><%= message %></div>
    <% } %>
</body>
</html>
