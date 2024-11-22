<%-- 
    Document   : categories.jsp
    Created on : Nov 22, 2024, 6:13:14 PM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/Shop/admin/css/style.css">
    </head>
    <style>
        .table-cart {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            height: 400px;
            overflow-y: scroll;
        }

        .table-cart th, .table-cart td {
            padding: 15px;
            text-align: left;
            border: 1px solid #ddd;
        }

        .table-cart th {
            background-color: #f4f4f4;
        }

        .table-cart td {
            background-color: #ffffff;
        }

        .table-cart a {
            text-decoration: none;
            color: inherit;
        }

        .table-cart i {
            font-size: 18px;
            padding: 10px;
        }

        .card {
            margin: 20px 0;
            border-radius: 8px;
        }

        .card-header {
            background-color: #007bff;
            color: #fff;
            padding: 10px 15px;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .card-body {
            padding: 20px;
        }

    </style>
    <body>
        <%@ include file="header.jsp" %>
        <div class="main">
            <%@ include file='sider.jsp' %>
            <div class="container">
                <div class="mb-4">
                    <a href="/Shop/admin/create-product">
                        <button type="button" class="btn btn-outline-secondary">
                            <i class="fa-solid fa-plus"></i>Thêm mới sản phẩm
                        </button>
                    </a>
                </div>
                <div class="row">
                    <div class="card">
                        <div class="card-header">
                            Quản lý sản phẩm
                        </div>
                        <div class="card-body">
                            <table class="table-cart" >
                                <thead>
                                <th>STT</th>
                                <th>Tên sản phẩm</th>
                                <th>Ảnh</th>
                                <th>Giá</th>
                                <th>Giảm giá</th>
                                <th>Đánh giá</th>
                                <th>Hành động</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listProducts}" var="item" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${item.name}</td>
                                            <td><img src="/Shop/${item.image}" width="100px"/></td>
                                            <td>${item.getPriceVND()}</td>
                                            <td>${item.discountPercentage} %</td>
                                            <td>${item.rating} </td>
                                            <td>
                                                <a href="/Shop/admin/edit-product/${item.id}">
                                                    <i class="fa-solid fa-pen-to-square" width="150px"></i>
                                                </a>
                                                <a href="/Shop/admin/delete-product/${item.id}">
                                                    <i class="fa-solid fa-trash" width="150px"></i>
                                                </a>        
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <script src="/Shop/admin/js/script.js"></script>
    </body>
</html>
