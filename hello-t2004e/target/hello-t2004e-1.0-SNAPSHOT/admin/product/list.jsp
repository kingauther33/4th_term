<%@ page import="com.t2004e.hellot2004e.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="mf" uri="http://example.com/functions" %>
<%
    request.setCharacterEncoding("utf-8");
    ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/admin/include/header.jsp">
        <jsp:param name="title" value="My admin page"/>
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
        <h5><b><i class="fa fa-list"></i> List Products</b></h5>
        <strong>${mf:addTwoNumber(1, 2)}</strong>
    </header>

    <div class="w3-row-padding w3-margin-bottom">
        <table class="w3-table-all w3-hoverable">
            <thead>
            <tr class="w3-light-grey">
                <th>ID</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (int i = 0; i < list.size(); i++) {
            %>
            <tr class="w3-light-grey">
                <th><%=list.get(i).getId()%>
                </th>
                <th>
                    <img src="<%=list.get(i).getThumbnail()%>" class="w3-border w3-padding" width="100" alt="<%=list.get(i).getName()%>">
                </th>
                <th><%=list.get(i).getName()%>
                </th>
                <th><%=list.get(i).getPrice()%>
                </th>
                <th><%=list.get(i).getPrice()%>
                </th>
                <th><%=list.get(i).getStatus()%>
                </th>
                <th>
                    <a href="/admin/product/detail?id=<%=list.get(i).getId()%>">Detail</a>&nbsp;
                    <a href="/admin/product/edit?id=<%=list.get(i).getId()%>">Edit</a>&nbsp;
                    <a class="btn-delete" onclick="deleteProduct(<%=list.get(i).getId()%>)" href="javascript:void(0)">Delete</a>&nbsp;
                </th>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <!-- Footer -->
    <jsp:include page="/admin/include/footer.jsp"/>

    <!-- End page content -->
</div>
<jsp:include page="/admin/include/script.jsp"/>
<script>
    function deleteProduct(id) {
        if(confirm("Bạn có chắc chắn muốn xóa sản phẩm không?")) {
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if(xhr.readyState == 4 && xhr.status == 200) {
                    alert('Delete thành công');
                    window.location.reload();
                }
            }
            xhr.open('DELETE', '/admin/product/delete?id=' + id);
            xhr.send();
        }
    }
</script>
</body>
</html>
