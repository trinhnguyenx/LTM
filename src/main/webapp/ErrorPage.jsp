<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
        }

        h1 {
            color: #e74c3c;
        }

        p {
            color: #555;
        }

        a {
            color: #3498db;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Oops! Có lỗi xảy ra.</h1>
        <p>Xin lỗi, có vẻ như có một lỗi trong quá trình xử lý yêu cầu của bạn.</p>
        <p><a href="HomePage.jsp">Trở về trang chủ</a></p>
    </div>
</body>
</html>
