<?php
$dbname = 'Bandlink';
$dbuser = 'bandlink';
$dbpass = 'calin123';
$dbhost = 'localhost';

$con = mysqli_connect($dbhost, $dbuser, $dbpass) or die("Unable to Connect to '$dbhost'");
mysqli_select_db($con, $dbname) or die("Could not open the db '$dbname'");
 
$statement = $con->prepare("SELECT user_id, username, ProfilePicture FROM User");

if (!$statement) {
    http_response_code(400);
    $response = array();      
    $response[message] = "Bad request."; 
    die(mysqli_error($con));                 
}

$statement->execute();
$result = $statement->get_result();

while ($row = $result->fetch_array(MYSQLI_NUM)) {
    $user = array();
    $user["id"] = $row[0];
    $user["user_name"] = $row[1];
    $user["profile_picture"] = $row[2];
    $data[] = $user;
}

if(0==count($data)) {
    http_response_code(404);
    $response = array();      
    $response[message] = "No users found.";
    echo json_encode($response);
} else {
    echo json_encode($data);
}

mysqli_stmt_close($statement);
mysqli_close($con);
?>