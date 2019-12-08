<?php
$dbname = 'Bandlink';
$dbuser = 'bandlink'; 
$dbpass = 'calin123';
$dbhost = 'localhost';

$con = mysqli_connect($dbhost, $dbuser, $dbpass) or die("Unable to Connect to '$dbhost'");
mysqli_select_db($con, $dbname) or die("Could not open the db '$dbname'");

$con->autocommit(FALSE);

$data = json_decode(file_get_contents('php://input'), true);

$user_id = $data["user_id"];
$target_user_id = $data["target_user_id"];

$statement1 = mysqli_prepare($con, "INSERT INTO t_sessions (lastUpdated) VALUES (?)");

$now = date("Y-m-d H:i:s");
mysqli_stmt_bind_param($statement1, "s", $now);

if (mysqli_stmt_execute($statement1) == false) {
    http_response_code(400);
    echo json_encode("Bad request");
}
mysqli_stmt_close($statement1);
$last_insert_id = $con->insert_id;
$con->commit();

$statement2 = mysqli_prepare($con, "INSERT INTO t_users_sessions (user_id, session_id) VALUES (?, ?), (?, ?)");
mysqli_stmt_bind_param($statement2, "iiii", $user_id, $last_insert_id, $target_user_id, $last_insert_id); 

if (mysqli_stmt_execute($statement2) == false) {
    http_response_code(400);
    echo json_encode("Bad request");
}

http_response_code(201);
$response = array();
$response[session_id] = $last_insert_id;
echo json_encode($response); 

mysqli_stmt_close($statement2);
$con->commit();
mysqli_close($con);
?>