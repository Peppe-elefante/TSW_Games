<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title> <!--Page accessible only for the administrator-->
    <link type="text/css" rel="stylesheet" href="home.css">
    <script src="search-bar.js" defer></script>
</head>
<body>
    <ul class="top-navbar">
        <li><a href="home-page.jsp" >Home page</a></li>
        <li> <a href="add-game.html"> Add Game</a> </li>
        <li><h1 style="margin-left: 400px">Admin Page</h1><li>

    </ul>
    <div class="login"> <!--By the name of the game, the admim will see how many times that game was purchase-->
        <input type="text" id="search" placeholder="Search bought games" onkeyup="searchFunction()">
        <div id="results"></div>
    </div>

</body>
</html>
