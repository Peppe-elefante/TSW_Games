<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri='http://java.sun.com/jsp/jstl/functions' %>

<html>
<head>
    <title>Title</title>
  <link type="text/css" rel="stylesheet" href="home.css">
</head>
<body>
<div class="game-result">
  <c:forEach items="${games}" var = "game"> <!--For Each used for showup all the games-->
    <div  class="game-display">
      <br><img src="images/${fn:replace(game.name, ' ', '')}.jpg" alt="${game.name}image">
      <p>Game = ${game.name}</p>
      <p>Price = $${game.price}.00</p>
      <form ACTION="AddToCart" method="post">
        <input type="hidden" name="gameID" value="${game.id}" />
        <input type="submit" value="Add to cart">
      </form>
      <form Action="AddToWishlist" method = "post">
        <input type="hidden" name="gameID" value="${game.id}" />
        <input type="submit" value="Add to wishlist">
      </form>
    </div><br><br>
  </c:forEach>
</div>
</body>
</html>
