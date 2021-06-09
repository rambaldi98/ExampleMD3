
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Product</title>

    <style>
        .message {
            text-align: center;
            color: deeppink;
        }

        h1 {
            color: deeppink;
            text-align: center;
        }

        .btn {
            background-color: violet
        }

        .back {
            margin-left: 10px;
        }

        p {
            color: red;
        }

        .form-control {
            font-weight: bold;
            color: blue;
        }

        option {
            color: blue;
        }

        select {
            color: blue;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>Information Product</h1>

    <h3 class="message">${messageComplete}</h3>

    <form action="/product" method="post">
        <input type="hidden" name="actionProduct" value="createNewProduct" />
        <div class="form-group has-success">
            <label for="name">Name :</label>
            <input type="text" class="form-control" name="name" id="name" value="${product.name}" required/>
            <p>${messageName}</p>
        </div>
        <div class="form-group has-warning">
            <label for="price">Price :</label>
            <input type="text" class="form-control" name="price" id="price" value="${product.price}" required/>
            <p>${messagePrice}</p>
        </div>
        <div class="form-group has-error">
            <label for="quantity">Quantity :</label>
            <input type="text" class="form-control" name="quantity" id="quantity" value="${product.quantity}" required/>
            <p>${messageQuantity}</p>
        </div>
        <div class="form-group has-success">
            <label for="color">Color :</label>
            <select class="form-control" name="color" id="color" required>
                <c:forEach var="color" items="${colorList}">
                    <c:choose>
                        <c:when test="${color.equals(product.color)}">
                            <option value="<c:out value='${color}'/>" selected><c:out value="${color}"></c:out></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${color}">${color}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="form-group has-warning">
            <label for="description">Description :</label>
            <input type="text" class="form-control" name="description" id="description" value="${product.description}" />
        </div>
        <div class="form-group">
            <label>Select Category : </label>
            <label for="category"></label><select name="category" id="category" required>
                <c:forEach var="category" items="${categoryList}">
                    <c:choose>
                        <c:when test="${category.id.equals(product.idCategory)}">
                            <option value="<c:out value='${category.id}'/>" selected><c:out value="${category.name}"></c:out></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${category.id}">${category.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <input type="submit" class="btn btn-info" value="Create New" /><span>
            <a href="/product" class="btn btn-info back">Back</a></span>
    </form>

</div>
</body>
</html>