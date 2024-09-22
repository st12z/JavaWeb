<%-- 
    Document   : home
    Created on : Aug 31, 2024, 3:57:06 PM
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
        <link rel="stylesheet" href="client/css/style.css">
    </head>
    <body>
        <!--header-->
        <%@ include file="header.jsp" %>
        <!--header-->
        <!--main-->

        <h1>${requestScope.customer.customerName}</h1>
        <div class="container mt-3">
            <h1>
                Danh sách sản phẩm
            </h1>
            <ul class="nav nav-tabs">
                <li class="nav-item ">
                    <a class="nav-link ${cid==null?"active":""}"  dataCategory-id="0">ALL</a>
                </li>
                <c:forEach items="${requestScope.categories}" var="c">
                    <li class="nav-item ">
                        <a class="nav-link ${requestScope.cid==c.id?"active":""}"   dataCategory-id="${c.id}">${c.name}</a>
                    </li>
                </c:forEach>

            </ul>
        </div>
        <!--main-->
        <main>
            <div class="container mt-3">
                <div>
                    <select name="select-sort" select-sort>
                        <option value="title-asc" ${requestScope.conditionSort.equals("title-asc")?"selected":""}>Sắp xếp A-Z</option>
                        <option value="title-desc" ${requestScope.conditionSort.equals("title-desc")?"selected":""}>Sắp xếp Z-A</option>
                        <option value="price-asc" ${requestScope.conditionSort.equals("price-asc")?"selected":""}>Giá tăng dần</option>
                        <option value="price-desc" ${requestScope.conditionSort.equals("price-desc")?"selected":""}>Giá giảm dần</option>
                    </select>
                </div>
                <div class="price-checkbox">
                    <input ${requestScope.radioPrice.equals("")?"checked":""} type="radio" name="radio-price" id="radio-price0" value=""/>
                    <label for="radio-price0">All</label>
                    <br>
                    <input ${requestScope.radioPrice.equals("<5")?"checked":""} type="radio" name="radio-price" id="radio-price1" value="<5"/>
                    <label for="radio-price1">Dưới 5 triệu VNĐ</label>
                    <br>
                    <input ${requestScope.radioPrice.equals("6-10")?"checked":""} type="radio" id="radio-price2" name="radio-price" value ="6-10"/>
                    <label for="radio-price2">Từ 6 đến 10 triệu VNĐ</label>
                    <br>
                    <input ${requestScope.radioPrice.equals(">10")?"checked":""} type="radio" name="radio-price" id="radio-price3" value=">10"/>
                    <label for="radio-price3">Trên 10 triệu VNĐ</label>
                </div>
            </div>
            <div class="container mt-3">
                <nav aria-label="...">
                    <ul class="pagination">
                        <c:if test="${requestScope.currentPage>1}">
                            <li class="page-item">
                                <button class="page-link" 
                                        button-pagination data-page="${requestScope.currentPage-1}"  data-keyword="${keyword}"  >Previous</button>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${requestScope.totalPage}" var="i">
                            <li class="page-item ${requestScope.currentPage==0 && i==1||requestScope.currentPage==i || requestScope.totalPage==1?"active":""}" >
                                <button class="page-link " button-pagination data-cid="${cid}" data-page="${i}" data-keyword="${keyword}">
                                    ${i}
                                </button>
                            </li>
                        </c:forEach>
                        <c:if test="${requestScope.currentPage+1<=requestScope.totalPage}">
                            <li class="page-item">
                                <button class="page-link" 
                                        button-pagination data-cid="${cid}" data-page="${requestScope.currentPage+1}" data-keyword="${keyword}"   >Next</button>
                            </li>
                        </c:if>    
                    </ul>
                </nav>
                <div class="row">

                    <c:forEach items="${requestScope.products}" var="c">
                        <div class="col-xl-4 col-lg-4 col-sm-4 col-12 mb-3">
                            <div class="card" style="width:18rem;">
                                <img src="${c.image}" alt="logo" class="card-img-top">
                                <div class="card-body">
                                    <h5 class="card-title">${c.name}</h5>
                                    <p>${c.getPriceVND()} VNĐ</p>
                                    <a href="detail?id=${c.id}">
                                        <i class="fa-solid fa-eye"></i>
                                    </a>
                                    <a href="add-cart?id=${c.id}">
                                        <i class="fa-solid fa-cart-shopping"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>

        </main>
        <%@include file='comment.jsp' %>
        <!--main-->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <script src="client/js/script.js"></script>
    </body>
</html>
