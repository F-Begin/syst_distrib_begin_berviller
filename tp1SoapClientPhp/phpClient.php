<?php

$montant = $_GET['mt'];
$clientSoap = new SoapClient("http://localhost:8686/wsConversion/?wsdl");
$param = new stdClass();
$param->mt=$montant;
$resultat=$clientSoap->__soapCall("Convertir", array($param));

?>
<!DOCTYPE <unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title> php Client</title>

<body>
	<form action="phpClient.php" method="get">
		<input type="text" name ="mt">
			<button> Convertir </button>
	</form>
	
	<?php 
	   echo "Le résultat renvoyé par le service est $resultat->return"
	?>
</body>
</html>