<?php

$servername = "localhost";
$username = "root";
$password = "";
$database = "ioc";
 
$conn = new mysqli($servername, $username, $password, $database);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
 
$heroes = array(); 
 
$sql = "SELECT *  FROM products;";

$stmt = $conn->prepare($sql);
 
//executing that statment
$stmt->execute();
 
//binding results for that statment 
$stmt->bind_result($productid, $productname, $productcost, $relasedate , $productcomment);
 
//looping through all the records
while($stmt->fetch()){
	
	//pushing fetched data in an array 
	$temp = [
		'productid'=>$productid,
        'productname'=>$productname,
        'productcost'=>$productcost,
        'releasdate'=>$relasedate,
        'productcomment'=>$productcomment,
	];
	
	//pushing the array inside the hero array 
	array_push($heroes, $temp);
}
 
//displaying the data in json format 
echo json_encode($heroes);















