<%-- 
    Document   : cart.jsp
    Created on : Sep 2, 2024, 10:58:53 AM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              </head>
        <style>
            table{
                border:1px solid chocolate;
            }
            th{
                border: 1px solid chocolate;
            }
            td{
                border:1px solid chocolate;
            }
        </style>
    <body>
    <div class=container>
        <div class="inner-logo">
            <a href="home"><img src="images/icon-logo.png" alt="logo"/></a>
        </div>
        <h1>${sessionScope.notification}</h1>
        <table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Ảnh sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.items}" var="i" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${i.product.name}</td>
                        <td><img src="${i.product.image}" alt="image" style="width:80px"/></td>
                        <td>${i.product.price}</td>
                        <td>
                            <a href="process?id=${i.product.id}&action=desc">
                                <button>-</button>
                            </a>
                            <input style="width:50px" type="number" value="${i.quantity}"/>
                            <a href="process?id=${i.product.id}&action=incr">
                                <button>+</button>
                            </a>

                        </td>
                        <td>
                            <a href="process?id=${i.product.id}&action=delete">
                                <button class="btn btn-warning">Delete</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h2 style="color:red">Tổng tiền thanh toán là: ${requestScope.totalMoney}</h2>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>
