<?php

$dbname = 'User';
$dbuser = 'bandlink';
$dbpass = 'calin123';
$dbhost = 'localhost';

$con = mysqli_connect($dbhost, $dbuser, $dbpass) or die("Unable to Connect to '$dbhost'");
mysqli_select_db($con, $dbname) or die("Could not open the db '$dbname'");


        $email = $_POST['email'];
        $password = $_POST['password'];

        $statement = mysqli_prepare($con, "SELECT * FROM User WHERE email = ? AND password = ?");
        mysqli_stmt_bind_param($statement, "ss", $email, $password);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        mysqli_stmt_bind_result($statement, $username, $email, $password, $user_id);

        $user = array();
	$user[username] = "NOT_FOUND";
	$user[email] = "NOT_FOUND";
	$user[password] = "NOT_FOUND";

        while(mysqli_stmt_fetch($statement)){
                $user[username] = $username;
                $user[email] = $email;
                $user[password] = $password;
        }

        echo json_encode($user);
        mysqli_stmt_close($statement);


        mysqli_close($con);
?>



