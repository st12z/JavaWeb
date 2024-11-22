<%-- 
    Document   : create-category.jsp
    Created on : Nov 22, 2024, 7:48:42 PM
    Author     : T
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="/Shop/admin/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="main">
            <%@ include file='sider.jsp' %>
            <div class="container">
                <form method="POST" action="/Shop/admin/edit-product/${product.id}" form-product enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="name">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="name" value="${product.name}" name="name" />
                    </div>
                    <div class="form-group">
                        <label for="category">Danh mục</label>
                        <select name="categoryId" id="category" class="form-control" required>
                            <option value="" disabled>-- Chọn danh mục --</option>
                            <c:forEach items="${requestScope.listCategories}" var="item">
                                <option value="${item.id}" 
                                        <c:if test="${item.id == product.category.id}">
                                            selected
                                        </c:if>
                                        >${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Số lượng</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" value="${product.quantity}" required />
                    </div>
                    <div class="form-group">
                        <label for="price">Giá</label>
                        <input type="text" class="form-control" id="price" name="price" value="${product.getPriceVND()}" required/>
                    </div>
                    <div class="form-group">
                        <label for="discountPercentage">Giảm giá</label>
                        <input type="number" class="form-control" id="discountPercentage" value="${product.discountPercentage}" name="discountPercentage" required/>
                    </div>
                    <div class="form-group" upload-image >
                        <label class="form-label" class="small mb-1" for="avatar">Ảnh </label>
                        <input upload-image-input  class="form-control-file" type="file" id="avatar" name="image" accept="images/*" />
                        <img src="/Shop/${product.image}" width="200px" class="image-preview"/>
                    </div> 
                    <div class="form-check form-check-group form-check-inline">
                        <input type="radio"  class="form-check-input" id="active" name="status" value="active" checked/>
                        <label for="active" class="form-check-label form-check-inline">Hoạt động</label>
                    </div>
                    <div class="form-check form-check-group form-check-inline">
                        <input type="radio" value="inactive" class="form-check-input" id="inactive" name="status" />
                        <label for="inactive"  class="form-check-label form-check-inline">Ngừng hoạt động</label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                    </div>

                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous">
        </script>
        <script src="/Shop/admin/js/script.js"></script>
    </body>
</html>
