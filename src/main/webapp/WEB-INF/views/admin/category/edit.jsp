<%@ taglib prefix="FORM" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="editCategoryURL" value="/admin/category/edit" />
<c:url var="apiEditCategory" value="/api-admin/category" />
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="${pageContext.request.contextPath}/admin-home">Trang chủ</a>
                    </li>
                    <li> <a href="${pageContext.request.contextPath}/admin/new/list?page=1&limit=3">Danh sách bài viết</a></li>
                    <li class="active">Chỉnh sửa bài viết</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${not empty message}">
                            <div class="alert alert-${alert}">
                                    ${message}
                            </div>
                        </c:if>
                        <form:form id="formSubmit" modelAttribute="model">
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tên thể loại</label>
                                <div class="col-sm-9">
                                    <form:input path="name" cssClass="form-control"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Code thể loại</label>
                                <div class="col-sm-9">
                                    <form:input path="code" id="code" cssClass="form-control"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <form:hidden path="id" id="categoryId" />
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-0 col-md-9">
                                    <div class="col-sm-12">
                                        <c:if test="${not empty model.id}">
                                            <button type="button" class="btn btn-info" id="btnAddOrUpdateCategory">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                Cập nhật thể loại
                                            </button>
                                        </c:if>
                                        <c:if test="${empty model.id}">
                                            <button type="button" class="btn btn-info" id="btnAddOrUpdateCategory">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                Thêm thể loại
                                            </button>
                                        </c:if>
                                        &nbsp; &nbsp; &nbsp;
                                        <a class="btn" href="${pageContext.request.contextPath}/admin/category/list?page=1&limit=3">
                                            <i class="ace-icon fa fa-undo bigger-110"></i> Hủy </a>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>

        $('#btnAddOrUpdateCategory').click(function (e) {
            e.preventDefault();
            var data = {};
            var formData = $('#formSubmit').serializeArray();
            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            })
            var id = $('#categoryId').val();
            console.log(data)
            if (id == "") {
                addCategory(data);
            } else {
                updateCategory(id, data);
            }
        })

        function addCategory(data) {
            $.ajax({
                url: "${apiEditCategory}",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    window.location.href = "${editCategoryURL}?id="+result.id+"&message=insert_success"
                },
                error: function (error) {
                    window.location.href = "${editCategoryURL}?message=error_system"
                }
            });
        };

        function updateCategory(id, data) {
            $.ajax({
                url: "${apiEditCategory}" +"/" + id,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    window.location.href = "${editCategoryURL}?id="+result.id+"&message=update_success"
                },
                error: function (error) {
                    window.location.href = "${editCategoryURL}?message=error_system"
                }
            })
        }

    </script>
</body>
</html>
