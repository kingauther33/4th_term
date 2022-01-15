<%@ page import="com.t1908e.WCD_assignment.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" uri="/WEB-INF/tlds/admin-template.tld" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("listCat");
%>
<html>
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
            <h3>Create new category</h3>
        </div>
        <div class="page-heading">
            <div class="page-title">
                <div class="row">
                    <div class="col-12 col-md-6 order-md-1 order-last">
                        <h3>Create Foods </h3>
                        <p class="text-subtitle text-muted">Create new category in your restaurant</p>
                    </div>
                    <div class="col-12 col-md-6 order-md-2 order-first">
                        <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Create category</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <div class='page-content'>
            <section class="section">
                <div class="card">
                    <div class="card-header">
                        <h4 class="card-title">Enter category information</h4>

                    </div>

                    <div class="card-body">
                        <p class="text-danger" id="validate-status">${requestScope.status}</p>
                        <form action="/admin/category/create" method="post" id="create-category-form">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="name">Name</label>
                                        <input type="text" class="form-control" id="name"
                                               placeholder="Name" name="name">
                                        <div class="invalid-feedback" id="name-message">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 d-flex justify-content-end">
                                    <button type="submit"
                                            class="btn btn-primary me-1 mb-1">Submit
                                    </button>
                                    <button type="reset"
                                            class="btn btn-light-secondary me-1 mb-1">Reset
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </div>
        ${admin:getFooter()}
    </div>
</div>
${admin:getTemplateScripts()}

<script>
    //form elements
    const nameInput = document.getElementById("name");
    const createCategoryForm = document.getElementById("create-category-form");
    const submitFormButton = document.querySelector("button[type='submit']");
    //erros message
    const nameMsg = document.getElementById("name-message");
    //count errors
    let nameErrs = 0;

    //validators

    function checkName() {
        const value = nameInput.value;
        if (value == null || value === "") {
            nameErrs++;
            nameMsg.textContent = "Name is required"
            nameInput.classList.add("is-invalid")
        } else {
            nameErrs = 0;
            nameMsg.textContent = ""

            nameInput.classList.remove("is-invalid");
            nameInput.classList.add("is-valid")
        }
    }


    //add event listener

    nameInput.addEventListener("input", checkName)
    //submit form
    submitFormButton.addEventListener('click', e => {
        e.preventDefault();
        checkName()
        //total
        if (nameErrs === 0) {
            createCategoryForm.submit()
        } else {
            document.getElementById("validate-status").textContent = "Check your input fields then try again"
        }
    })
</script>
</body>
</html>
