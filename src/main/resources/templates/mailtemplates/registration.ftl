<html>
<head>
    <title>Welcome Email</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style type="text/css">
        body {
            font-family: 'Poppins', sans-serif;
            width: 100%;
            min-height: 100vh;
            margin: 0;
            padding: 0;
            /*display: flex;
            justify-content: center;*/
        }
        .container {
            background: #eaeaea;
            padding-bottom: 1rem;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
        }

        @media (min-width: 576px) {
            .container {
                max-width: 540px;
            }
        }

        @media (min-width: 768px) {
            .container {
                max-width: 720px;
            }
        }

        @media (min-width: 992px) {
            .container {
                max-width: 700px;
            }
        }

        @media (min-width: 1200px) {
            .container {
                max-width: 700px;
            }
        }
        img {
            margin: 2rem 0;
        }
        .inner-box {
            padding: 2rem 1rem;
            background: #ffffff;
            margin-bottom: 1rem;
        }
        p {
            color: #333333;
        }
        .title {
            color: #122b3b;
        }
        .closure {
            margin-top: 2rem;
        }
        a {
            color: #fff;
            text-decoration: none;
            background-color: transparent;
        }

        a:hover {
            color: #fff;
            text-decoration: none;
        }
        img {
            vertical-align: middle;
            border-style: none;
            margin: 2rem 0;
        }
        .text-center {
            text-align: center;
        }
        .btn {
            display: inline-block;
            font-weight: 400;
            color: #212529;
            text-align: center;
            vertical-align: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-color: transparent;
            border: 1px solid transparent;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: 0.25rem;
            transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
        }
        .btn-danger {
            color: #fff;
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .btn-danger:hover {
            color: #fff;
            background-color: #c82333;
            border-color: #bd2130;
        }

        .btn-danger:focus, .btn-danger.focus {
            color: #fff;
            background-color: #c82333;
            border-color: #bd2130;
            box-shadow: 0 0 0 0.2rem rgba(225, 83, 97, 0.5);
        }

        .btn-danger.disabled, .btn-danger:disabled {
            color: #fff;
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .btn-danger:not(:disabled):not(.disabled):active, .btn-danger:not(:disabled):not(.disabled).active,
        .show > .btn-danger.dropdown-toggle {
            color: #fff;
            background-color: #bd2130;
            border-color: #b21f2d;
        }

        .btn-danger:not(:disabled):not(.disabled):active:focus, .btn-danger:not(:disabled):not(.disabled).active:focus,
        .show > .btn-danger.dropdown-toggle:focus {
            box-shadow: 0 0 0 0.2rem rgba(225, 83, 97, 0.5);
        }
    </style>
</head>
<body>
<div class="container">
    <img src="https://res.cloudinary.com/tlims-kech/image/upload/v1602321937/logo.jpg" height="120" width="200">
    <div class="inner-box">
        <h2 class="title">Welcome to Tlims-Kech Dear ${name}!</h2>
        <p>Thank you for signing up with Tlims-Kech! We hope you enjoy your time with us. Click the link below to confirm your email and start creating unlimited ads.</p>
        <p class="my-4 text-center"><a href="${link}" class="btn btn-danger">Confirm Email</a></p>
        <div class="closure">
            <p>Best Regards,</p>
            <p>Tlims-Kech Team </p>
        </div>
    </div>
</div>
</body>
</html>
