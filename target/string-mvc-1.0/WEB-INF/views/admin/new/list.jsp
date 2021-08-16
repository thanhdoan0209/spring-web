<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Danh sách bài viết</title>
</head>

<body>
  <div class="main-content">

    <form action="<c:url value = '/admin/new/list'/>" id="formSubmit" method="get">
      <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
          <ul class="breadcrumb">
            <li>
              <i class="ace-icon fa fa-home home-icon"></i>
              <a href="/admin-home">Trang chủ</a>
            </li>
            <li class="active">Danh sách bài viết</li>
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
              <div class="widget-box table-filter">
                <div class="table-btn-controls">
                  <div class="pull-right tableTools-container">
                    <div class="dt-buttons btn-overlap btn-group">
                      <a
                         class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                         data-toggle="tooltip" title='Thêm bài viết'
                         href='<c:url value="/admin/new/edit"/>'>
                        <span><i class="fa fa-plus-circle bigger-110 purple"></i></span>
                      </a>
                      <button id="btnDelete" type="button" onclick="warningBeforeDelete()"
                              class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                              data-toggle="tooltip" title='Xóa bài viết'>
                        <span><i class="fa fa-trash-o bigger-110 pink"></i></span>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-xs-12">
                  <div class="table-responsive">
                    <table class="table table-bordered">
                      <thead>
                      <tr>
                          <th><input type="checkbox" class="form-check-input"/></th>
                          <th>Tên bài viết</th>
                          <th>Mô tả ngắn</th>
                          <th>Thao tác</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="item" items="${model.listResult}">
                        <tr>
                          <td id="checkboxList"><input class="form-check-input" type="checkbox" value="${item.id}" id="${item.id}" name="checkbox"></td>
                          <td>${item.title}</td>
                          <td>${item.shortDescription}</td>
                          <td>
                            <c:url var="updateNewURL" value="/admin/new/edit">
                              <c:param name="id" value="${item.id}"></c:param>
                            </c:url>
                            <a class="btn btn-sm btn-primary btn-edit"
                               data-toggle="tooltip" title="Cập nhật bài viết"
                               href='${updateNewURL}'><i class="fa fa-pencil-square-o"aria-hidden="true"></i>
                            </a>
                          </td>
                        </tr>
                      </c:forEach>
                      </tbody>
                    </table>
                    <ul class="pagination" id="pagination"> </ul>
                    <input type="hidden" value="" id="page" name="page"/>
                    <input type="hidden" value="" id="limit" name="limit"/>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>

  </div><!-- /.main-content -->

  <script>


    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
      window.pagObj = $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 3,
        startPage: currentPage,
        onPageClick: function (event, page) {
          if (currentPage != page) {
            $('#limit').val(3);
            $('#page').val(page);
            $('#formSubmit').submit();
          }
        }
      });
    });

    function warningBeforeDelete() {
      swal({
        title: "Xác nhận xóa",
        text: "Bạn có chắc chắn muốn xóa hay không",
        type: "warning",
        showCancelButton: true,
        confirmButtonClass: "btn-success",
        cancelButtonClass: "btn-danger",
        confirmButtonText: "Xác nhận",
        cancelButtonText: "Hủy bỏ",
      }).then(function(isConfirm) {
        if (isConfirm) {
          var ids = $('tbody input[type=checkbox]:checked').map(() => {
            return $(this).val();
          }).get();
          deleteNew(ids);
        }
      });

      function deleteNew(data) {
        $.ajax({
          url: '/api-admin/new/',
          type: 'DELETE',
          contentType: 'application/json',
          data: JSON.stringify(data),
          success: function (result) {
            window.location.href = "/admin/new/list?page=1&limit=3&message=delete_success";
          },
          error: function (error) {
            window.location.href = "/admin/new/list?page=1&limit=3&message=error_system";
          }
        });
      }
    }

  </script>

</body>

</html>