<%@ page import="com.t1908e.WCD_assignment.entity.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" uri="/WEB-INF/tlds/admin-template.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    List<Food> list = (List<Food>) request.getAttribute("list");
    if (list == null || list.size() == 0) {
        list = new ArrayList<>();
    }

%>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Dashboard - HM restaurant</title>
    <link rel="icon" href="https://res.cloudinary.com/dnby4zyda/image/upload/v1621768189/m2h19du1zslemk2mdvpi.png"
          type="image/x-icon"/>
    ${admin:getTemplateStyles()}
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        .action-btn-group {
            display: flex;
            justify-content: space-between;
            font-size: 1.4rem;
        }

        .action-btn {
            display: inline;
            background: transparent;
            border: none;
            color: red;
        }

        .action-btn:hover {
            background: transparent;
        }

        .paginate-container {
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .page-item {
            display: inline-block;
            padding: 15px;
            color: black;
            border: 1px solid black;
            border-radius: 5px;
            cursor: pointer;
            transition: .5s;
            text-align: center;
            margin: 0 10px;
        }

        .page-item:hover {
            background: #0f5132;
            color: #fff;
        }

        .page-item.active {
            background: #0f5132;
            color: #fff;
        }

        .description-txt {
            width: 110px;
            white-space: pre-wrap;
            overflow: hidden;
            text-overflow: ellipsis;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            display: -webkit-box;
        }
    </style>
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
            <h3>Manage food</h3>
        </div>
        <div class="page-heading">
            <div class="page-title">
                <div class="row">
                    <div class="col-12 col-md-6 order-md-1 order-last">
                        <h3>List Foods </h3>
                        <p class="text-subtitle text-muted">Manage foods in your restaurant</p>
                    </div>
                    <div class="col-12 col-md-6 order-md-2 order-first">
                        <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Manage food</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <section class="section">
            <div class="card">
                <div class="card-header">
                    List foods
                </div>
                <div class="card-body">
                    <%if (list.size() == 0) { %>
                    <h5 class="text-danger">Opps! There is nothing to show</h5>
                    <%} else {%>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Thumbnail</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Actions</th>
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
                            <td><span class="price"><%= food.getPrice()%></span>
                            </td>
                            <td><p class="description-txt"><%=food.getDescription()%>
                            </p>
                            </td>
                            <td>
                                <% if (food.getStatus() == 1) {%>
                                <span class="badge bg-success">Active</span>
                                <%} else {%>
                                <span class="badge bg-danger">Disabled</span>
                                <%}%>
                            </td>
                            <td>
                                <div class="action-btn-group">
                                    <%if (food.getStatus() == 1) {%>
                                    <a href="/admin/food/edit?id=<%=food.getId()%>"
                                       title="Edit"><i
                                            class="fas fa-edit"></i></a>
                                    <%}%>
                                    <% if (food.getStatus() == 1) {%>
                                    <form action="/admin/food/toggle?id=<%=food.getId()%>" method="post">
                                        <button type="submit" title="Disable"
                                                class="action-btn"><i
                                                class="fas fa-ban"></i></button>
                                    </form>
                                    <% } else { %>
                                    <form action="/admin/food/toggle?id=<%=food.getId()%>" method="post">
                                        <button type="submit" class="btn btn-primary"
                                                title="Active">Active this food
                                        </button>
                                    </form>

                                    <% } %>
                                    <%if (food.getStatus() != -1) {%>
                                    <div class="modal-danger me-1 mb-1 d-inline-block">
                                        <!-- Button trigger for danger theme modal -->
                                        <button type="button" class="action-btn"
                                                data-bs-toggle="modal" data-bs-target="#delete<%=food.getId()%>">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>

                                        <!--Danger theme Modal -->
                                        <div class="modal fade text-left" id="delete<%=food.getId()%>" tabindex="-1"
                                             role="dialog" aria-labelledby="myModalLabel120"
                                             aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable"
                                                 role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header bg-danger">
                                                        <h5 class="modal-title white" id="myModalLabel120">
                                                            Confirm delete
                                                        </h5>
                                                        <button type="button" class="close"
                                                                data-bs-dismiss="modal" aria-label="Close">
                                                            <i data-feather="x"></i>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>Do you really want to delete <%=food.getName()%>
                                                        </p>
                                                        <p>This action can not undo</p>

                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button"
                                                                class="btn btn-light-secondary"
                                                                data-bs-dismiss="modal">
                                                            <i class="bx bx-x d-block d-sm-none"></i>
                                                            <span class="d-none d-sm-block">Close</span>
                                                        </button>
                                                        <form action="/admin/food/delete?id=<%=food.getId()%>"
                                                              method="post" style="display: inline-block;">

                                                            <button type="submit" class="btn btn-danger ml-1">
                                                                <i class="bx bx-check d-block d-sm-none"></i>
                                                                <span class="d-none d-sm-block">Accept</span>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                    <%}%>
                    <div class="paginate-container">
                        <c:if test="${!empty requestScope.previousPage}">
                            <a href="/admin/food?limit=${requestScope.currentLimit}&page=${requestScope.previousPage}"
                               class="page-item">Previous</a>
                        </c:if>
                        <c:forEach var="i" begin="1" end="${requestScope.totalPage}">
                            <c:if test="${requestScope.currentPage == i}">
                                <a href="/admin/food?limit=${requestScope.currentLimit}&page=${i}"
                                   class="page-item active">${i}</a>
                            </c:if>
                            <c:if test="${requestScope.currentPage != i}">
                                <a href="/admin/food?limit=${requestScope.currentLimit}&page=${i}"
                                   class="page-item">${i}</a>
                            </c:if>
                        </c:forEach>
                        <c:if test="${!empty requestScope.nextPage}">
                            <a href="/admin/food?limit=${requestScope.currentLimit}&page=${requestScope.nextPage}"
                               class="page-item">Next</a>
                        </c:if>
                    </div>
                </div>
            </div>

        </section>
    </div>
    ${admin:getFooter()}
</div>
</div>
${admin:getTemplateScripts()}
<script>
    const priceEls = document.querySelectorAll(".price");

    function format2(n, currency) {
        return currency + n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
    }

    priceEls.forEach(el => {
        const format = format2(parseFloat(el.textContent), "vnd ");
        el.textContent = format
    });

</script>


