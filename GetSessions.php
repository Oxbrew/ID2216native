<?php
$dbname = 'Bandlink';
$dbuser = 'bandlink';
$dbpass = 'calin123';
$dbhost = 'localhost';

$con = mysqli_connect($dbhost, $dbuser, $dbpass) or die("Unable to Connect to '$dbhost'");
mysqli_select_db($con, $dbname) or die("Could not open the db '$dbname'");
 
$user_id = $_GET['user_id'];

$statement = $con->prepare("SELECT t_sessions.id, lastUpdated, Message.id,
                                   content, Message.timestamp, Message.user_id,
                                   message_user.username, message_user.ProfilePicture  
                            FROM t_users_sessions 
                            INNER JOIN t_sessions ON t_users_sessions.session_id = t_sessions.id
                            INNER JOIN Message ON t_sessions.id = Message.session_id
                            INNER JOIN User session_user ON t_users_sessions.user_id = session_user.user_id
                            INNER JOIN User message_user ON Message.user_id = message_user.user_id
                            WHERE t_users_sessions.user_id = ?");

$statement->bind_param("i", $user_id);

if (!$statement) {
    http_response_code(400);
    $response = array();      
    $response[message] = "Bad request."; 
    die(mysqli_error($con));                 
}

$statement->execute();
$result = $statement->get_result();

$sessions = array();
while ($row = $result->fetch_array(MYSQLI_NUM)) {
    $session = array();
    $session["id"] = $row[0];
    $session["last_updated"] = $row[1];
    $session["message_id"] = $row[2];
    $session["content"] = $row[3];
    $session["timestamp"] = $row[4];
    $session["user_id"] = $row[5];
    $session["username"] = $row[6];
    $session["ProfilePicture"] = $row[7];
    $sessions[] = $session;
}

$formattedSessions = array();
foreach ($sessions as $value) {
    $formattedSession = array();
    $formattedSession["id"] = $value["id"];
    $formattedSession["last_updated"] = $value["last_updated"];
    $formattedSession["messages"] = array();
    $message = array();
    $message["id"] = $value["message_id"];
    $message["content"] = $value["content"];
    $message["timestamp"] = $value["timestamp"];
    $message["user_id"] = $value["user_id"];
    $message["user_name"] = $value["username"];
    $message["profile_picture"] = $value["ProfilePicture"];         
    $formattedSession["messages"][] = $message;
    $formattedSessions[] = $formattedSession;
}

$lastVersionArray = array();
$lastVersionArray[0] = $formattedSessions[0];

$i = 1;
$j = 0;

while($i < count($formattedSessions)){
    if ($lastVersionArray[$j]["id"] == $formattedSessions[$i]["id"]) {
        $lastVersionArray[$j]["messages"][] = $formattedSessions[$i]["messages"][0];
    } else {
        $j = $j + 1;
        $lastVersionArray[$j] = $formattedSessions[$i];
    }
    $i++;
}

if(0==count($formattedSessions)) {
    http_response_code(404);
    $response = array();      
    $response[message] = "No messages found.";
    echo json_encode($response);
} else {
    echo json_encode($lastVersionArray);
}

mysqli_stmt_close($statement);
mysqli_close($con);
?> 
