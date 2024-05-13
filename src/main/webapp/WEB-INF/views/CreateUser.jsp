<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/security-layout.css">
</head>
<body>

<div class="wrapperRegister">
    <div class="containerRegister">

        <form action="/register/save" method="POST">

            <p><b>Account details</b></p>

            <div class="row">

                <div class="col-75">
                    <input type="text" id="login" name="login" placeholder="Username" required>
                </div>
            </div>

            <div class="row">

                <div class="col-75">
                    <input type="password" id="password" name="password" placeholder="Password" required>
                </div>
            </div>

            <p><b>Contact details</b></p>

            <div class="row">

                <div class="col-75">
                    <input type="email" id="email" name="email"placeholder="Email" required>
                </div>
            </div>

            <div class="row">

                <div class="col-75">
                    <input type="text" id="firstName" name="firstName" placeholder="First Name" required>
                </div>
            </div>

            <div class="row">

                <div class="col-75">
                    <input type="text" id="lastName" name="lastName" placeholder="Last Name"required>
                </div>
            </div>


            <div class="row">

                <div class="col-75">
                    <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone number" pattern="[0-9]{1,10}" title="Please enter no more than 10 digits" required>
                </div>
            </div>
            <input type="hidden" id="role" name="role" value="STUDENT">

                <div>
                    <input type="submit" value="Save">
                </div>



        </form>
        <form action="/login" method="GET">
            <div class="row">
                <input type="submit" value="Back">
            </div>
        </form>
    </div>
</div>

</body>
</html>
