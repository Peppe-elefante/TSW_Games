<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri='http://java.sun.com/jsp/jstl/functions' %>

<html>
<head>
    <title>Shopping cart</title>
    <link type="text/css" rel="stylesheet" href="home.css">
</head>
<body>
<ul class="top-navbar">
    <li><a href="home-page.jsp" >Home page</a></li>
    <li><h1 style="margin-left: 400px">${User.name}'s Shopping cart</h1><li>
</ul>

<h2>Total price $${Price}0</h2> <!--All the games present in the cart are showup here and also the total price of the entire cart-->
<div class="game-result">
    <c:forEach items="${Cart}" var = "game">
        <div  class="game-display">
            <br><img src="images/${fn:replace(game.name, ' ', '')}.jpg" alt="${game.name}image">
            <p>Game = ${game.name}</p>
            <p>Quantity = ${game.quantity}</p>
            <form action="RemoveFromCart" method="post">
                <input type="hidden" name="gameID" value="${game.id}" />
                <input type="submit" value="Remove from cart">
            </form>
        </div><br><br>
    </c:forEach>
</div>
<c:if test = "${not empty Cart}"> <!--If the cart is not empty, the User will see also two button: one for clean the cart and another to purchase-->
    <a href="${pageContext.request.contextPath}/EmptyCart">Empty cart</a><br><br>
    <form action="${pageContext.request.contextPath}/Purchase" method="post">
        <input type="hidden" name="cart" value="${Cart}">
        <input type="submit" value="Purchase from cart" style="background-color:#006100; color: #F0F0F0;">
    </form>
</c:if>
</body>
</html>
