<%@ page import="com.t1908e.WCD_assignment.entity.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.t1908e.WCD_assignment.entity.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" uri="/WEB-INF/tlds/admin-template.tld" %>
<%
  List<Category> list = (List<Category>) request.getAttribute("list");
  if (list == null || list.size() == 0) {
    list = new ArrayList<>();
  }

%>
<html>
<head>
  <meta charset='UTF-8'>
  <meta name='viewport' content='width=device-width, initial-scale=1.0'>
  <title>Dashboard - HM restaurant</title>
  <link rel="icon" href="https://res.cloudinary.com/dnby4zyda/image/upload/v1621768189/m2h19du1zslemk2mdvpi.png" type="image/x-icon"/>
  ${admin:getTemplateStyles()}
  <link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
        integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
        crossorigin="anonymous" referrerpolicy="no-referrer"/>
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
      <h3>List all categories</h3>
    </div>
    <div class="page-heading">
      <div class="page-title">
        <div class="row">
          <div class="col-12 col-md-6 order-md-1 order-last">
            <h3>List categories </h3>
            <p class="text-subtitle text-muted">All categories in your restaurant</p>
          </div>
          <div class="col-12 col-md-6 order-md-2 order-first">
            <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                <li class="breadcrumb-item active" aria-current="page">Food category</li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
      <section class="section">
        <div class="card">
          <div class="card-header">
            List categories
          </div>
          <div class="card-body">
            <%if (list.size() == 0) { %>
            <h5 class="text-danger">Opps! There is nothing to show</h5>
            <%} else {%>
            <table class="table table-striped" id="table1">
              <thead>
              <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Number of foods</th>
              </tr>
              </thead>
              <tbody>
              <%for (Category category : list) { %>
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
            <%}%>
          </div>
        </div>

      </section>
    </div>
    ${admin:getFooter()}
  </div>
</div>
${admin:getTemplateScripts()}

<script src="assets/vendors/simple-datatables/simple-datatables.js"></script>
<script>
  // Simple Datatable
  let table1 = document.querySelector('#table1');
  let dataTable = new simpleDatatables.DataTable(table1);
</script>

