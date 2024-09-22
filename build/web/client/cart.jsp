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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    </head>


    <style>

        td{
            border:1px solid chocolate;
        }
        th{
            border:1px solid chocolate;
        }

    </style>
    <body>

        <c:if test="${not empty requestScope.items}">
            <div class="container">

                <div class="row">
                    <div class="col-12">
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
                                        <td>${i.product.getPriceVND()} VNĐ</td>
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
                    </div>
                </div>

                <a href="payment">
                    <button class="btn btn-primary">
                        Thanh toán
                    </button>
                </a>
                <h2 style="color:red">Tổng tiền thanh toán là: ${requestScope.totalMoney} VNĐ</h2>
            </div>
        </c:if>
        <c:if test="${empty requestScope.items}">
            <h1>Giỏ hàng trống</h1>
        </c:if>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>
