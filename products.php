<?php

$servername = "localhost";
$username = "root";
$password = "";
$database = "ioc";
 
$conn = new mysqli($servername, $username, $password, $database);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
 
$products = array();
 
$sql = "SELECT *  FROM products;";

$stmt = $conn->prepare($sql);

$stmt->execute();
 

$stmt->bind_result($productid, $productname, $productcost, $relasedate , $productcomment);

while($stmt->fetch()){
	
	//pushing fetched data in an array 
	$temp = [
		'productid'=>$productid,
        'productname'=>$productname,
        'productcost'=>$productcost,
        'releasdate'=>$relasedate,
        'productcomment'=>$productcomment,
	];
	
	//pushing the array inside the products array
	array_push($products, $temp);
}
 
//displaying the data in json format 
echo json_encode($products);















