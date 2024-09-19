<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${search} games</title> <!--Based on the research this page will showed up all the relative games-->
    <link type="text/css" rel="stylesheet" href="home.css">
</head>
<body>

    <ul class="top-navbar">
        <li><a href="home-page.jsp" >Home page</a></li>
        <li><h1 style="margin-left: 400px">All ${search} games</h1><li>
    </ul>

    <%@ include file = "show-games.jsp"%>

</body>
</html>
