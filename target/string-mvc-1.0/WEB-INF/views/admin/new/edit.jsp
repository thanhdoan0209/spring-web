<%@ taglib prefix="FORM" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="editNewURL" value="/admin/new/edit" />
<c:url var="apiEditNew" value="/api-admin/new" />
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
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">
                                    <form:select path="categoryCode" id="categoryCode" cssClass="form-control">
                                        <form:option value="" label="-- Chọn thể loại -- "/>
                                        <form:options items="${categories}"/>
                                    </form:select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tên bài viết</label>
                                <div class="col-sm-9">
                                    <form:input path="title" cssClass="form-control"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-9">
                                    <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail" value="${model.thumbnail}"/>                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9">
                                    <form:input path="shortDescription" id="shortDescription" cssClass="form-control"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">
                                    <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">${model.content}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <form:hidden path="id" id="newId" />
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-0 col-md-9">
                                    <div class="col-sm-12">
                                        <c:if test="${not empty model.id}">
                                            <button type="button" class="btn btn-info" id="btnAddOrUpdateNew"/>
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            Cập nhật bài viết
                                            </button>
                                        </c:if>
                                        <c:if test="${empty model.id}">
                                            <button type="button" class="btn btn-info" id="btnAddOrUpdateNew"/>
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            Thêm bài viết
                                            </button>
                                        </c:if>
                                        &nbsp; &nbsp; &nbsp;
                                        <a class="btn" href="${pageContext.request.contextPath}/admin/new/list?page=1&limit=3">
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
        var editor = '';
        $(document).ready(function(){
            editor = CKEDITOR.replace( document.getElementById( "content" ) , {
                language_list: ["ar:Arabic:rtl", "fr:French", "es:Spanish", "en:English"],
                disableNativeSpellChecker: true,
                removeButtons: "Subscript,Superscript",
                extraPlugins: "colorbutton,print,font,autolink,justify",
                removePlugins:

                    "sourcearea,maximize,image,stylescombo,scayt,wsc,elementspath,blockquote,link,specialchar,resize",
                title: this.title,
                readOnly: false,
                resize_enabled: false,
                autoGrow_minHeight: 200,
                autoGrow_bottomSpace: 50,
                autoGrow_onStartup: true,
                toolbarStartupExpanded: false,
                toolbarGroups: [
                    { name: "others" },
                    { name: "clipboard", groups: ["clipboard", "undo"] },
                    { name: "editing", groups: ["find", "selection", "spellchecker"] },
                    { name: "links" },
                    { name: "insert" },
                    { name: "forms" },
                    { name: "tools" },
                    { name: "styles" },
                    "/",
                    { name: "basicstyles", groups: ["basicstyles", "cleanup"] },
                    {
                        name: "paragraph",
                        groups: ["list", "indent", "blocks", "align", "bidi"]
                    },
                    { name: "colors" },
                    { name: "document", groups: ["mode", "document", "doctools"] }
                ]
            });
        });

        $('#btnAddOrUpdateNew').click(function (e) {
            e.preventDefault();
            var data = {};
            var formData = $('#formSubmit').serializeArray();
            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            })
            data["content"] = editor.getData();
            var id = $('#newId').val();
            console.log(data)
            if (id == "") {
                addNew(data);
            } else {
                updateNew(id, data);
            }
        })

        function addNew(data) {
            $.ajax({
                url: "${apiEditNew}",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    window.location.href = "${editNewURL}?id="+result.id+"&message=insert_success"
                },
                error: function (error) {
                    window.location.href = "${editNewURL}?message=error_system"
                }
            });
        };

        function updateNew(id, data) {
            $.ajax({
                url: "${apiEditNew}" +"/" + id,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    window.location.href = "${editNewURL}?id="+result.id+"&message=update_success"
                },
                error: function (error) {
                    window.location.href = "${editNewURL}?message=error_system"
                }
            })
        }

    </script>
</body>
</html>
