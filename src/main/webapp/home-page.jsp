<%@ page import="model.GameDAO" %>
<%@ page import="model.Game" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri='http://java.sun.com/jsp/jstl/functions' %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TSW Games</title>
    <link type="text/css" rel="stylesheet" href="home.css">
    <link type="text/css" rel="stylesheet" href="navbar.css">
</head>
<body>
<div class="top-navbar">
    <ul>
        <li class="logo">
            <img src="images/TSWGames.jpg" alt="TSW Games Logo">
        </li>
        <li class="title">
            <h1>TSW Games</h1>
        </li>
        <li class="search-form">
            <form action="SearchCategory" method="get">
                <label for="category">Category:</label>
                <select name="category" id="category" class="category-select">
                    <option value="Nintendo">Nintendo</option>
                    <option value="PS4">PS4</option>
                    <option value="XBOX">XBOX</option>
                    <option value="PC">PC</option>
                </select>
                <input type="submit" value="Search">
            </form>
        </li>

        <c:if test="${empty User}">
            <li class="auth-link">
                <a href="login.html">Login</a>
            </li>
        </c:if>

        <c:if test="${not empty User}">
            <li class="auth-link">
                <a href="profile-page.jsp">${User.name}</a>
            </li>
            <li class="cart-link">
                <a href="${pageContext.request.contextPath}/GetCart">Cart</a>
            </li>
        </c:if>
    </ul>
</div>

<h2>Highest selling game</h2> <!--The highest selling game of the moment, is showed up in the middled of the page-->
<%
    GameDAO getGame = new GameDAO();
    HttpSession ssn = request.getSession(true);
    int gameid = getGame.MostBoughtGame();
    Game bestGame = getGame.getByID(gameid);
    ssn.setAttribute("BestGame", bestGame);
%>
<div class="most-bought">
    <img src="images/${fn:replace(BestGame.name, ' ', '')}.jpg" alt="${game.name}image">
</div>
<h2>Trending games</h2> <!--All the treanding games are the last section off the homepage -->
<%
    GameDAO gameSearch = new GameDAO();
    List<Game> games = gameSearch.getTrending();
    ssn.setAttribute("games", games);
%>
<%@include file = "show-games.jsp"%>

</body>
</html>
