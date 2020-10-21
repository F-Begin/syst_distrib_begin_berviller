<!DOCTYPE <unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<?php 
header( 'content-type: text/html; charset=utf-8' );
?>
<html>
<head lang="fr">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> php Client</title>
</head>
<?php 
$lastaction = "";
?>
<body>
	<h1>Créer un étudiant</h1>
	<form action="GestionEtudiant.php" method="POST">
		Nom : <input type="text" name ="nom"/><br>
		Prenom : <input type="text" name ="prenom"/><br>
		Email : <input type="text" name ="email"/><br>
		<input type = "submit" name = "action" value = "AjoutEtudiant"/>
		<?php 
		if(isset($_POST['action']) && isset($_POST) && ($_POST['action']=='AjoutEtudiant')){
		  echo "Création d'étudiant en cours...";
		  $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
		  if(isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["email"])) {
		      $params = array("nom"=>$_POST["nom"], "prenom"=>$_POST["prenom"], "email"=>$_POST["email"]);
		      echo print_r($params);
		      $clientSoap->AjoutEtudiant($params);
		      $lastaction = "AjoutEtudiant";
		  }
		}
		?>
	</form>
	<?php 
	if($lastaction=="AjoutEtudiant")
	    echo "Etudiant ajouté !";
	?>
	<h1>Chercher l'id d'un étudiant</h1>
	<form action="GestionEtudiant.php" method="POST">
		Nom : <input type="text" name ="nom_id_recherche"/><br>
		Prenom : <input type="text" name ="prenom_id_recherche"/><br>
		Email : <input type="text" name ="email_id_recherche"/><br>
		<input type = "submit" name = "action" value = "GetEtuId"/>
		<?php 
		if(isset($_POST['action']) && isset($_POST) && ($_POST['action']=='GetEtuId')){
		  echo "Recherche de l'id de l'étudiant...";
		  $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
		  if(isset($_POST["nom_id_recherche"]) && isset($_POST["prenom_id_recherche"]) && isset($_POST["email_id_recherche"])) {
		      $params = array("nom"=>$_POST["nom_id_recherche"], "prenom"=>$_POST["prenom_id_recherche"], "email"=>$_POST["email_id_recherche"]);
		      $result = $clientSoap->GetEtuId($params);
		      $lastaction = "GetEtuId";
		  }
		}
		?>
	</form>
	<?php 
	if($lastaction=="GetEtuId")
	    echo "L'étudiant rechercher possède l'id ",$result->return,".";
	?>
	<h1>Modifier les informations d'un étudiant</h1>
	<form action="GestionEtudiant.php" method="POST">
	Valuer à modifier :
		<input type="radio" id="nom" name="args" value="nom">
		<label for="nom">Nom</label>
		<input type="radio" id="prenom" name="args" value="prenom">
		<label for="prenom">Prenom</label>
		<input type="radio" id="email" name="args" value="email">
		<label for="email">Email</label><br>
	Nouvelle value : <input type="text" name = "value"/><br>
	ID de l'étudiant à modifier : <input type="text" name="id"/><br>
	<input type = "submit" name = "action" value = "ModifieEtudiant"/>
	<?php 
	if(isset($_POST['action']) && isset($_POST) && ($_POST['action']=='ModifieEtudiant')) {
	    echo "Modification de l'étudiant...";
	    $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
	    if(isset($_POST['args']) && isset($_POST['value']) && isset($_POST['id'])) {
	        $params = array("attribueModifie"=>$_POST["args"], "nouvelleValeur"=>$_POST["value"], "idEtudiant"=>$_POST["id"]);
	        $clientSoap->ModifieEtudiant($params);
	        $lastaction = "ModifieEtudiant";
	    }
	}
	?>
	</form>
	<?php 
	if($lastaction == "ModifieEtudiant")
	    echo "Etudiant modifié avec succès !";
	?>
	<h1>Supprimer un étudiant</h1>
	<form action="GestionEtudiant.php" method="POST">
	ID de l'étudiant : <input type="text" name="id"/><br>
	<input type="submit" name = "action" value = "SuprEtudiant"/>
	<?php 
	if(isset($_POST["action"]) && isset($_POST) && ($_POST["action"]=="SuprEtudiant")) {
	    echo "Suppression de l'étudiant...";
	    $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
	    if(isset($_POST["id"])) {
	        $params = array("id"=>$_POST["id"]);
	        $clientSoap->SuprEtudiant($params);
	        $lastaction = "SuprEtudiant";
	    }
	}
	?>
	</form>
	<?php 
	if($lastaction == "SuprEtudiant")
	    echo "Etudiant supprimé avec succès !";
	?>
	<h1>Information étudiant</h1>
	<form action="GestionEtudiant.php" method="POST">
	ID de l'étudiant : <input type="text" name="id"/><br>
	<input type="submit" name = "action" value = "GetEtuInfo"/>
	<?php 
	if(isset($_POST['action']) && isset($_POST) && ($_POST["action"]=="GetEtuInfo")) {
	    echo "Récupération des informations...";
	    $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
	    if(isset($_POST["id"])) {
	      $params = array("id"=>$_POST["id"]);
	      $result = $clientSoap->GetEtuInfo($params);
	      $lastaction = "GetEtuInfo";
	    }
	}
	?>
	</form>
	<?php 
	if($lastaction == "GetEtuInfo"){
	    echo "Nom : ",$result->return->nom,"<br>";
	    echo "Prenom : ",$result->return->prenom,"<br>";
	    echo "Email : ",$result->return->email,"<br>";
	}
	?>
	<h1>Information de tout les étudiants</h1>
	<form action="GestionEtudiant.php" method="POST">
	<input type="submit" name = "action" value ="InfoAll"/>
	<?php 
	if(isset($_POST['action']) && isset($_POST) && ($_POST["action"]=="InfoAll")) {
	    echo "Affichage de tout les étudiants...";
	    $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
	    $result = $clientSoap->InfoAll();
	    $lastaction = "InfoAll";
	}
	?>
	</form>
	<?php 
	if($lastaction == "InfoAll"){
	    foreach($result->return as $key=>$etu){
	        echo "Etudiant numéro ",$key,"<br>";
	        echo "Nom : ",$etu->nom,"<br>";
	        echo "Prenom : ",$etu->prenom,"<br>";
	        echo "Email : ",$etu->email,"<br>";
	        echo "<br>";
	    }
	}
	?>
	<h1>Import / Export</h1>
	<form action="GestionEtudiant.php" method="POST">
	<input type="submit" name = "action" value = "Import">
	<input type="submit" name = "action" value = "Export">
	<?php 
	if(isset($_POST['action']) && isset($_POST) && ($_POST["action"]=="Import")){
	    echo "Importation...";
	    $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
	    $clientSoap->Import();
	    $lastaction = "Import";
	}
	if(isset($_POST['action']) && isset($_POST) && ($_POST["action"]=="Export")){
	    echo "Exportation...";
	    $clientSoap = new SoapClient("http://localhost:8686/wsGestEtu/?wsdl");
	    $clientSoap->Export();
	    $lastaction = "Export";
	}
	?>
	</form>
	<?php 
	if($lastaction=="Import")
	    echo "Données importées avec succès !";
	if($lastaction=="Export")
	    echo "Données exportées avec succès !";
	?>
</body>
</html>