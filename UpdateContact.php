

<?php

		header("Access-Control-Allow-Origin: *");

		header("Expires: 0");

		header("Last-Modified: " . gmdate("D, d M Y H:i:s") . " GMT");

		header("Cache-Control: no-store, no-cache, must-revalidate");

		header("Cache-Control: post-check=0, pre-check=0", false);

		header("Pragma: no-cache");

		header("Content-Type: application/json; charset=UTF-8");



		$servername = "www.franklinpracticum.com";

		$username = "frank73_f17grad";

		$password = "Grade.f17";

		$dbname = "frank73_f17grad";


		// $userid = $_GET['userid'];
		$courseid = $_GET['courseid'];
		$coursename = $_GET['name'];
		$coursedesc = $_GET['desc'];


		// Create connection

		$conn = new mysqli($servername, $username, $password, $dbname);

		// Check connection

		if ($conn->connect_error) {

			die("Connection failed: " . $conn->connect_error);

		}


{



		$sql = "UPDATE Contacts SET ContactFirstName='$contactFirstName',ContactLastName='$contactLastName', ContactEmail='$contactEmail', ContactPhone='$contactPhone',  
    WHERE CourseID='$courseid'";

		mysqli_query($conn, 'SET NAMES utf8');

		$res = $conn->query($sql);







		$conn->close();

		echo $res;
}


?>
