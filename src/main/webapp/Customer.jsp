<%@ page import="com.example.customerapi.CustomerList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customerlist</title>
</head>
<body bgcolor=”#FF00FF">
<h1 style="color: #f1d27c;" style="font-size:4vw">Welcome to the Customerlist</h1>
<br>
<p> Here is Some java code on the website !</p>
<p><%
    CustomerList customerList = new CustomerList();
    out.print(customerList.getCustomerArrayList());
%></p>
<br>
<p>Here is some music, you're welcome</p>
<iframe width="420" height="315"
        src="https://www.youtube.com/embed/tgbNymZ7vqY?autoplay=1">
</iframe>
<br>
<p> Here is the site that solves all your problems!</p>
<a href="https://www.stackoverflow.com">
    <button>   Clickity   </button> </a>
</body>
</html>