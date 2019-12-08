<?php
$dbname = 'Bandlink';
$dbuser = 'bandlink'; 
$dbpass = 'calin123';
$dbhost = 'localhost';

$con = mysqli_connect($dbhost, $dbuser, $dbpass) or die("Unable to Connect to '$dbhost'");
mysqli_select_db($con, $dbname) or die("Could not open the db '$dbname'");

$data = json_decode(file_get_contents('php://input'), true);

$content = $data["content"];
$user_id = $data["user_id"];
$session_id = $data["session_id"];

$statement = mysqli_prepare($con, "INSERT INTO Message (content, user_id, session_id) VALUES (?, ?, ?)");
if (!$statement) { 
        die(mysqli_error($con));                 
} 
 
mysqli_stmt_bind_param($statement, "sii", $content, $user_id, $session_id);
$result = mysqli_stmt_execute($statement);

if ($result) {
        http_response_code(201);
} else {        
        http_response_code(404);
        $response = array();      
        $response[message] = "session or user does not exist.";
        echo json_encode($response);
}

mysqli_stmt_close($statement);
mysqli_close($con);
?>