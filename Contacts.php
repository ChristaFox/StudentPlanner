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



		// Create connection

		$conn = new mysqli($servername, $username, $password, $dbname);

		// Check connection

		if ($conn->connect_error) {

			die("Connection failed: " . $conn->connect_error);

		}



		$sql = "SELECT * FROM Contacts where UserID = = '".$_GET["UserID"]."' and CourseID = '".$_GET["CourseID"]."'";

		mysqli_query($conn, 'SET NAMES utf8');

		$res = $conn->query($sql);

		$result = array();

		while( $row = $res->fetch_assoc() )

			array_push($result, array('contactID' => $row["ContactID"],
contactFirstName, contactLastName, contactEmail, contactPhone, contactNotes
						  'contactFirstName' => $row["ContactFirstName"],
						  'contactLastName' => $row ["ContactLastName"],
							'contactEmail' => $row ["ContactEmail"],
							'contactPhone' => $row ["ContactPhone"]));



		$conn->close();

		echo json_encode($result);



?>
