<%-- 
    Document   : order
    Created on : Sep 17, 2024, 3:58:34 PM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="client/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
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
        <a href="home">Quay lại</a>
        <c:if test="${not empty requestScope.items}">
            <h1>Đơn hàng của bạn</h1>
            <div class="container mt-3 ">
                <div class="row ">
                    <div class="col-12">
                        <div class="table-payment d-flex justify-content-center">
                            <table>
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Ảnh sản phẩm</th>
                                        <th>Giá</th>
                                        <th>Số lượng</th>
                                        <th>Tổng tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.items}" var="i" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${i.product.name}</td>
                                            <td><img src="${i.product.image}" alt="image" style="width:80px"/></td>
                                            <td>${i.product.getPriceVND()} VNĐ</td>
                                            <td>${i.quantity}</td>
                                            <td>${i.getMoneyVND()} VNĐ</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <h2 class="d-flex justify-content-center" style="color:red">Tổng tiền thanh toán là: ${requestScope.totalMoney} VNĐ</h2>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty requestScope.items}">
                <h1>Bạn không có đơn hàng nào</h1>
            </c:if>
            <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>
