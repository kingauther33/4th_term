<%@ page import="com.t1908e.WCD_assignment.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t1908e.WCD_assignment.entity.Food" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="admin" uri="/WEB-INF/tlds/admin-template.tld" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("listCat");
    Food food = (Food) request.getAttribute("food");
%>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Dashboard - HM restaurant</title>
    <link rel="icon" href="https://res.cloudinary.com/dnby4zyda/image/upload/v1621768189/m2h19du1zslemk2mdvpi.png" type="image/x-icon"/>
    ${admin:getTemplateStyles()}
    <style>
        .thumbnail-container {
            width: 100%;
            max-height: 250px;
            overflow: hidden;
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
            <h3>Edit food</h3>
        </div>
        <div class="page-heading">
            <div class="page-title">
                <div class="row">
                    <div class="col-12 col-md-6 order-md-1 order-last">
                        <h3>Create Foods </h3>
                        <p class="text-subtitle text-muted">edit an exist food</p>
                    </div>
                    <div class="col-12 col-md-6 order-md-2 order-first">
                        <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active" aria-current="page">edit food</li>
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
                        <h4 class="card-title">Enter new food information</h4>

                    </div>

                    <div class="card-body">
                        <p class="text-danger" id="validate-status">${requestScope.status}</p>
                        <form action="/admin/food/edit?id=${requestScope.food.id}" method="post"
                              id="create-food-form">
                            <input type="hidden" name="id" value="${requestScope.food.id}">
                            <div class="row">

                                <div class="col-md-6">
                                    <div class="thumbnail-container">
                                        <img src="${requestScope.food.firstImage}" alt="thumbnails"
                                             class="img img-fluid">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="name">Name</label>
                                                <input type="text" class="form-control" id="name"
                                                       placeholder="Name" name="name"
                                                       value="${requestScope.food.name}">
                                                <div class="invalid-feedback" id="name-message">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="price">Price</label>
                                                <input type="text" id="price" class="form-control" placeholder="Price"
                                                       name="price" value="${requestScope.food.price}">
                                                <div class="invalid-feedback" id="price-message">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="category">Category</label>
                                                <select class="form-select" id="category" name="categoryId">
                                                    <%for (Category category : categories) {%>
                                                    <%if (category.getId() == food.getCategoryId()) {%>
                                                    <option value="<%=category.getId()%>"
                                                            selected><%=category.getName()%>
                                                    </option>
                                                    <%} else {%>
                                                    <option value="<%=category.getId()%>"><%=category.getName()%>
                                                    </option>
                                                    <%}%>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Thumbnail</label>
                                                <div>
                                                    <button type="button" id="upload_widget" class="btn btn-secondary">
                                                        Click to update thumbnail
                                                    </button>
                                                </div>
                                                <div class="col-md-6 col-sm-6 mt-2 thumbnail">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12" style="margin-top: 20px">
                                    <div class="form-group">
                                        <label for="description" class="form-label">Descriptions</label>
                                        <textarea class="form-control" id="description"
                                                  rows="10"
                                                  name="description">${requestScope.food.getDescription()}</textarea>
                                        <div class="invalid-feedback" id="description-message">
                                        </div>
                                    </div>

                                </div>
                                <div class="col-md-12">
                                    <div class="col-sm-12 d-flex justify-content-end">
                                        <button type="submit"
                                                class="btn btn-primary me-1 mb-1">Update
                                        </button>
                                        <button type="reset"
                                                class="btn btn-light-secondary me-1 mb-1">Reset
                                        </button>
                                    </div>
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://widget.cloudinary.com/v2.0/global/all.js" type="text/javascript"></script>

<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        var myWidget = cloudinary.createUploadWidget({
                cloudName: 'dnby4zyda',
                uploadPreset: 'gunsodn9',
                multiple: true,
                form: '#create-food-form',
                fieldName: 'thumbnails',
                thumbnails: '.thumbnail',
            }, (error, result) => {
                if (!error && result && result.event === "success") {
                    var inputValues = document.querySelectorAll('input[name="thumbnails"]');
                    for (let i = 0; i < inputValues.length; i++) {
                        inputValues[i].value = inputValues[i].getAttribute('data-cloudinary-public-id')
                    }
                }
            }
        )
        document.getElementById("upload_widget").addEventListener("click", function () {
            myWidget.open();
        }, false);
        $('body').on('click', '.cloudinary-delete', function () {
            var splittedImg = $(this).parent().find('img').attr('src').split('/');
            var imgName = splittedImg[splittedImg.length - 1];
            var splittedImgName = imgName.split('.');
            var imgCode = splittedImgName[0];
            $('input[data-cloudinary-public-id="' + imgCode + '"]').remove();
        });
    })
</script>
<script>
    //form elements
    const nameInput = document.getElementById("name");
    const priceInput = document.getElementById("price");
    const descriptionInput = document.getElementById("description");
    const createFoodForm = document.getElementById("create-food-form");
    const submitFormButton = document.querySelector("button[type='submit']");
    //erros message
    const nameMsg = document.getElementById("name-message");
    const priceMsg = document.getElementById("price-message");
    const descriptionMsg = document.getElementById("description-message");

    //count errors
    let priceErrs = 0;
    let nameErrs = 0;
    let descriptionErrs = 0;

    //validators
    function checkPrice() {
        const value = priceInput.value;
        console.log(value)
        if (value == null || value === "") {
            priceErrs++;
            priceMsg.textContent = "Price is required"
            priceInput.classList.add("is-invalid")
        } else if (isNaN(value)) {
            priceErrs++;
            priceMsg.textContent = "Price must be a number"
            priceInput.classList.add("is-invalid");
        } else if (value <= 0) {
            priceErrs++;
            priceMsg.textContent = "price must be greater than 0"
            priceInput.classList.add("is-invalid");
        } else {
            priceErrs = 0;
            priceMsg.textContent = ""
            priceInput.classList.remove("is-invalid");
            priceInput.classList.add("is-valid");
        }

    }

    function checkName() {
        const value = nameInput.value;
        console.log("name: " + value);
        if (value == null || value === "") {
            nameErrs++;
            nameMsg.textContent = "Name is required"
            nameInput.classList.add("is-invalid")
        } else if (value.length < 7) {
            nameErrs++;
            nameMsg.textContent = "Name must be longer than 7 characters"
            nameInput.classList.add("is-invalid")
        } else {
            nameErrs = 0;
            nameMsg.textContent = ""

            nameInput.classList.remove("is-invalid");
            nameInput.classList.add("is-valid")
        }
    }

    function checkDescription() {
        const value = descriptionInput.value;
        console.log("description: " + value);
        if (value == null || value === "") {
            descriptionErrs++;
            descriptionMsg.textContent = "Description is required"
            descriptionInput.classList.add("is-invalid")

        } else {
            descriptionErrs = 0;
            descriptionMsg.textContent = ""
            descriptionInput.classList.remove("is-invalid");
            descriptionInput.classList.add("is-valid")
        }
    }

    //add event listener
    priceInput.addEventListener("input", checkPrice)
    nameInput.addEventListener("input", checkName)
    descriptionInput.addEventListener("input", checkDescription)
    //submit form
    submitFormButton.addEventListener('click', e => {
        e.preventDefault();
        checkDescription()
        checkName()
        checkPrice()
        console.log("name errs count: " + nameErrs);
        console.log("price errs count: " + priceErrs);
        console.log("description errs count: " + descriptionErrs);
        //total
        if (nameErrs + priceErrs + descriptionErrs === 0) {
            createFoodForm.submit()
        } else {
            document.getElementById("validate-status").textContent = "Check your input fields then try again"
        }
    })
</script>
</body>
</html>
