<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng Ký</title>
</head>
<body>
    <div class="container">
        <!-- <h1 class="form-heading">login Form</h1> -->
        <div class="login-form">
            <div class="main-div">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}">
                            ${message}
                    </div>
                </c:if>
                <form id="formSignUp">
                    <div class="form-group">
                        <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Họ Tên">
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="Tên đăng nhập">
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu" onchange='check_pass();'>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="Nhập lại mật khẩu" onchange='check_pass();'>
                    </div>

                    <span class="info" id='message'></span>
                    <br>
                    <button type="button" class="btn btn-primary" id="buttonSingUp" disabled>Đăng ký</button>
                </form>
            </div>
        </div>
    </div>
    <script>
        function check_pass() {
            if (document.getElementById('password').value === document.getElementById('confirm_password').value) {
                document.getElementById('message').style.color = 'green';
                document.getElementById('message').innerHTML = 'Password matching';
                document.getElementById('buttonSingUp').disabled = false;
            } else {
                document.getElementById('message').style.color = 'red';
                document.getElementById('message').innerHTML = 'Password not matching';
                document.getElementById('buttonSingUp').disabled = true;
            }
        }

        $('#buttonSingUp').click(function (e) {
            e.preventDefault();
            var data = {};
            var formData = $('#formSignUp').serializeArray();
            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            })
            signUpUser(data);
        })

        function signUpUser(data) {
            $.ajax({
                url: "/user/sign-up",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    window.location.href = "/dang-nhap?message=sign_up_success"
                },
                error: function (error) {
                    window.location.href = "/dang-ky?message=error_system"
                }
            });
        };
    </script>
</body>
</html>