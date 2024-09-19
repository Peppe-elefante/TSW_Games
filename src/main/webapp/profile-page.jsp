<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri='http://java.sun.com/jsp/jstl/functions' %>
<html>
<head>
    <title>Profile Page</title>
    <link type="text/css" rel="stylesheet" href="home.css">
</head>
<body>
    <ul class="top-navbar">
        <li><a href="home-page.jsp" >Home page</a></li> <!--Link to return in the homepage-->
        <li><h1 style="margin-left: 350px">${User.name} ${User.lastName}'s profile</h1><li> <!--User nickname-->
        <c:if test="${not empty Permission and Permission}">
        <li><a href="${pageContext.request.contextPath}/CreateAdmin">Admin Page</a></li> <!--If the User have the admin permission, he can see the link fot the admin page-->
        </c:if>
        <li><a href="${pageContext.request.contextPath}/Logout">Log out</a></li> <!--Logout button-->
    </ul>

    <p>Email : ${User.email}</p><br> <!--User email-->
    <h2>Bought games:</h2><br> <!--All the games that the User has purchase over time-->
    <div class="game-result">
        <c:forEach items="${BoughtGames}" var = "game">
            <div  class="game-display">
                <br><img src="images/${fn:replace(game.name, ' ', '')}.jpg" alt="${game.name}image">
                <p>Game = ${game.name}</p>
                <form ACTION="AddToCart" method="post"> <!--Add to cart button to let the User purchase another time the game-->
                    <input type="hidden" name="gameID" value="${game.id}" />
                    <input type="submit" value="Purchase Again"><br>
                </form>

            </div><br><br>
        </c:forEach>
    </div>
    <h2>Wishlist</h2><br> <!--All the game that the User desider to buy-->
    <div class="game-result">
        <c:forEach items="${Wishlist}" var = "game">
            <div  class="game-display">
                <br><img src="images/${fn:replace(game.name, ' ', '')}.jpg" alt="${game.name}image">
                <p>Game = ${game.name}</p>
                <form ACTION="AddToCart" method="post"> <!--Add to cart button to let the User purchase the game-->
                    <input type="hidden" name="gameID" value="${game.id}" />
                    <input type="submit" value="Add to cart"><br>
                </form>
                <form ACTION="RemoveFromWishlist" method="post"> <!--Remove from wishlist button to delete the game from the wishlist-->
                    <input type="hidden" name="gameID" value="${game.id}">
                <input type="submit" value="Remove">
                </form>

            </div><br><br>
        </c:forEach>
    </div>

</body>
</html>
