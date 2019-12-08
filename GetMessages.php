<?php
$dbname = 'Bandlink';
$dbuser = 'bandlink';
$dbpass = 'calin123';
$dbhost = 'localhost';

$data = array();

$con = mysqli_connect($dbhost, $dbuser, $dbpass) or die("Unable to Connect to '$dbhost'");
mysqli_select_db($con, $dbname) or die("Could not open the db '$dbname'");
 
$session_id = $_GET['session_id'];

$statement = $con->prepare("SELECT id, content, timestamp, User.user_id, session_id, User.username, User.ProfilePicture  FROM Message 
                            INNER JOIN User ON Message.user_id = User.user_id 
                            WHERE session_id = ?");
$statement->bind_param("i", $session_id);

if (!$statement) {
    http_response_code(400);
    $response = array();      
    $response[message] = "Bad request."; 
    die(mysqli_error($con));                 
}

$statement->execute();
$result = $statement->get_result();

while ($row = $result->fetch_array(MYSQLI_NUM)) {
    $message = array();
    $message["id"] = $row[0];
    $message["content"] = $row[1];
    $message["timestamp"] = $row[2];
    $message["user_id"] = $row[3];
    $message["session_id"] = $row[4];
    $message["user_name"] = $row[5];
    $message["profile_picture"] = $row[6];
    $data[] = $message;
}
if(0==count($data)) {
    echo json_encode([]);
} else {
    echo json_encode($data);
}

mysqli_stmt_close($statement);
mysqli_close($con);
?>