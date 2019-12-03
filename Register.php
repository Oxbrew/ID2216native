<?php

$dbname='User';
$dbuser = 'bandlink';
$dbpass = 'calin123';
$dbhost = 'localhost';

$con = mysqli_connect($dbhost, $dbuser, $dbpass) or die("Unable to Connect to '$dbhost'");
mysqli_select_db($con, $dbname) or die("Could not open the db '$dbname'");


		       $username = $_POST['username'];
		       $email = $_POST['email'];
		       $password = $_POST['password'];


        $statement = mysqli_prepare($con, "INSERT INTO User (username, email, password) VALUES (?, ? , ?)");
	if(!$statement){
	die(mysqli_error($con));
}
        mysqli_stmt_bind_param($statement, "sss", $username, $email , $password);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);

        mysqli_close($con);
?>





