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
        .tokenBox {
            position: relative;
            padding: 2rem 5rem;
            color: #fff;
            background-color: #bd2130;
            letter-spacing: .2rem;
        }
        .my-4 {
            margin: 3rem 0;
        }
        .m-0 {
            margin: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <img src="https://res.cloudinary.com/tlims-kech/image/upload/v1602321937/logo.jpg" height="120" width="200">
    <div class="inner-box">
        <h2 class="title">Hi ${name}!</h2>
        <p>Use the token below to reset your password.</p>
        <p class="my-4 text-center"><span class="tokenBox">${token}</span></p>
        <div class="closure">
            <p class="m-0">Best Regards,</p>
            <p class="m-0">Tlims-Kech Team.</p>
        </div>
    </div>
</div>
</body>
</html>
