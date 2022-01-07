<%@ page import="com.t2004e.hellot2004e.entity.Product" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setCharacterEncoding("utf-8");
    Product product = (Product) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/admin/include/header.jsp">
        <jsp:param name="title" value="Edit Product"/>
        <jsp:param name="description" value="Admin area"/>
        <jsp:param name="keywords" value="admin, page...."/>
    </jsp:include>
</head>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
    <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i
            class="fa fa-bars"></i>  Menu
    </button>
    <span class="w3-bar-item w3-right">Logo</span>
</div>

<jsp:include page="/admin/include/left-menu.jsp"/>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

    <!-- Header -->
    <header class="w3-container" style="padding-top:22px">
        <h5><b><i class="fa fa-dashboard"></i> Update Product</b></h5>
    </header>

    <div class="w3-row-padding w3-margin-bottom">
        <form action="/admin/product/edit" method="post" class="w3-container w3-card-4">
            <input class="w3-input" type="hidden" name="id" value="<%=product.getId()%>">
            <div class="w3-margin">
                <label>Name</label>
                <input class="w3-input" type="text" name="name" value="<%=product.getName()%>">
            </div>
            <div class="w3-margin">
                <label>Price</label>
                <input class="w3-input" type="number" name="price" value="<%=product.getPrice()%>">
            </div>
            <div class="w3-margin">
                <label>Description</label>
                <input class="w3-input" type="text" name="description" value="<%=product.getDescription()%>">
            </div>
            <div class="w3-margin">
                <label>Thumbnail</label>
                <input class="w3-input" type="url" name="thumbnail" value="<%=product.getThumbnail()%>">
            </div>
            <div class="w3-margin">
                <label>Status</label>
                <input class="w3-input" type="number" name="status" value="<%=product.getStatus()%>">
            </div>

            <button class="w3-btn w3-blue w3-margin">Submit</button>
        </form>
    </div>

    <!-- Footer -->
    <jsp:include page="/admin/include/footer.jsp"/>

    <!-- End page content -->
</div>


<jsp:include page="/admin/include/script.jsp"/>

</body>
</html>







