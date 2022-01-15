<%@ page import="com.t1908e.WCD_assignment.entity.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.t1908e.WCD_assignment.entity.Category" %>
<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<%@ taglib prefix='admin' uri='/WEB-INF/tlds/admin-template.tld' %>
<%
    List<Food> list = (List<Food>) request.getAttribute("list");
    if (list == null || list.size() == 0) {
        list = new ArrayList<>();
    }

    List<Category> listCate = (List<Category>) request.getAttribute("listCate");
    if (listCate == null || listCate.size() == 0) {
        list = new ArrayList<>();
    }

%>
<!DOCTYPE html>
<html lang='en'>

<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Dashboard - HM restaurant</title>
    <link rel="icon" href="https://res.cloudinary.com/dnby4zyda/image/upload/v1621768189/m2h19du1zslemk2mdvpi.png" type="image/x-icon"/>
    ${admin:getTemplateStyles()}
</head>

<body>
<div id='app'>
    ${admin:getLeftMenu()}
    <div id='main'>
        <header class='mb-3'>
            <a href='#' class='burger-btn d-block d-xl-none'>
                <i class='bi bi-justify fs-3'></i>
            </a>
        </header>

        <div class='page-heading'>
            <h3>Profile Statistics</h3>
        </div>
        <div class='page-content'>
            <section class='row'>
                <div class='col-12 col-lg-9'>
                    <div class='row'>
                        <div class='col-12 col-lg-6 col-md-12'>
                            <div class='card'>
                                <div class='card-body px-3 py-4-5'>
                                    <div class='row'>
                                        <div class='col-md-4'>
                                            <div class='stats-icon purple'>
                                                <i class='iconly-boldShow'></i>
                                            </div>
                                        </div>
                                        <div class='col-md-8'>
                                            <h6 class='text-muted font-semibold'>Total foods in restaurant</h6>
                                            <h6 class='font-extrabold mb-0'>${requestScope.foodSize}</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class='col-12 col-lg-6 col-md-12'>
                            <div class='card'>
                                <div class='card-body px-3 py-4-5'>
                                    <div class='row'>
                                        <div class='col-md-4'>
                                            <div class='stats-icon blue'>
                                                <i class='iconly-boldProfile'></i>
                                            </div>
                                        </div>
                                        <div class='col-md-8'>
                                            <h6 class='text-muted font-semibold'>Total category in restaurant</h6>
                                            <h6 class='font-extrabold mb-0'>${requestScope.categorySize}</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class='row'>
                        <div class='col-12'>
                            <div class='card'>
                                <div class='card-header'>
                                    <h4>Current food</h4>
                                </div>
                                <div class="card-content">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Thumbnail</th>
                                            <th>Category</th>
                                            <th>Price</th>
                                            <th>Description</th>
                                            <th>Status</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%for (Food food : list) { %>
                                        <tr>
                                            <td><%= food.getName()%>
                                            </td>
                                            <td><img src="<%=food.getFirstSmallImage()%>" alt="<%=food.getName()%>"></td>
                                            <td><%=food.getCategory().getName()%>
                                            </td>
                                            <td><%= food.getPrice()%>
                                            </td>
                                            <td><%= food.getDescription()%>
                                            </td>
                                            <td>
                                                <% if (food.getStatus() == 1) {%>
                                                <span class="badge bg-success">Active</span>
                                                <%} else {%>
                                                <span class="badge bg-danger">Disabled</span>
                                                <%}%>
                                            </td>
                                        </tr>
                                        <%}%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-3">
                    <div class='card'>
                        <div class='card-header'>
                            <h4>Current categories</h4>
                        </div>
                        <div class="card-content">
                            <table class="table table-striped" id="table1">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Number of foods</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%for (Category category : listCate) { %>
                                <tr>
                                    <td><%= category.getId()%>
                                    </td>
                                    <td><%=category.getName()%>
                                    </td>
                                    <td><%= category.getFoods().size()%>
                                    </td>
                                </tr>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </section>
        </div>

        ${admin:getFooter()}
    </div>
</div>
${admin:getTemplateScripts()}
<script src='/admin/assets/js/pages/dashboard.js'></script>
<script src='/admin/assets/vendors/apexcharts/apexcharts.js'></script>
</body>

</html>
