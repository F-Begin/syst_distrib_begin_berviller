<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="Un site de gestion d'étudiant !">
  <meta name="author" content="Mighstye">

  <title>Gestion Etudiant</title>
  <link rel="icon" href="img/scree.png">
  
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  
  <!-- Google Fonts -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
	  
  <!-- MDBootstrap Datatables  -->
  <link href="css/addons/datatables.min.css" rel="stylesheet">
  
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">


</head>

<body>
  <div class="d-flex" id="wrapper">
    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Gestion des étudiants</div>
      <div class="list-group list-group-flush">
      	<form action="etudiant" method="POST">
      	<button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('connect')" class="btn btn-outline-primary">Connexion / Deconnexion</button><br>
      	<c:if test="${sessionScope.connect=='no'}">
      	<button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('newUser')" class="btn btn-outline-primary">Crée un compte</button><br>
      	</c:if>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('parcEtu')" class="btn btn-outline-primary">Parcourir les étudiants</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('parcNote')" class="btn btn-outline-primary">Parcourir les notes</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('parcMatiere')" class="btn btn-outline-primary">Parcourir les matieres</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('parcUser')" class="btn btn-outline-primary">Parcourir les utilisateurs</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('addEtu')" class="btn btn-outline-primary">Ajout d'Étudiant</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('suprEtu')" class="btn btn-outline-primary">Suppresion d'Étudiant</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('addNote')" class="btn btn-outline-primary">Ajout d'une note</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('suprNote')" class="btn btn-outline-primary">Suppresion d'une note</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('addMatiere')" class="btn btn-outline-primary">Ajout d'une matière</button><br>
        <button style="display:block;margin-left:auto;margin-right:auto;" type="button" onclick="button('suprMatiere')" class="btn btn-outline-primary">Suppresion d'une matière</button><br>
        </form>
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      </nav>	
      	<div class="container-fluid">
      	<!-- Deconnexion -->
      	<div style="display:none;" id="connect" class="container">
      	<c:if test="${sessionScope.connect=='yes'}">
      		<div style="display:block;" id="connexionUp" class="container">
      		<p>Vous êtes déjà connecté en tant que ${ sessionScope.username } !</p>
      		<p>Vous pouvez vous déconnecter.</p>
      		<form action="etudiant" method="POST">
			<input type = "submit" name = "action" value = "deconnexion"/>
		</form>
      		</div>
      	</c:if>
      	<!-- Connexion -->
      	<c:if test="${sessionScope.connect=='no'}">
      		<div style="display:block;" id="connexionDown" class="container">
      		<h1>Connectez vous !</h1>
      		<form action="etudiant" method="POST">
      		Username : <input type="text" name ="username"/><br>
      		Password : <input type="password" name ="password"/><br>
      		<input type="submit" name="action" value="connexion"/>
      		</form>
      		</div>
      	</c:if>
      	</div>
      	<!-- Creation de compte -->
      	<c:if test="${sessionScope.connect=='no'}">
      		<div style="display:none;" id="newUser" class="container">
      			<h1>Création de compte</h1>
      			<form action="etudiant" method="POST">
      			Username : <input type="text" name="username"/><br>
      			Password : <input type="text" name="password"/><br>
      			
      			<div class="g-recaptcha" data-sitekey="6Lcu4zQaAAAAAPneid7e2mNA1vAaxdy_sReHqGRN"></div>
      			
      			<input type="submit" name="action" value="creationCompte"/>
      			</form>
      		</div>
      	</c:if>
      	<!-- Parcourir les étudiants -->  
      		<div style="display:block;" id="parcEtu" class="container">
      		<h1 class="mt-4">Tableau des étudiants</h1>
      		<p>Vous pouvez parcourir ici la liste des étudiants !</p>
      		<p>Vous pouvez de même ajuster l'affichage en modifiant les paramètres.</p>
      		<table id="tableEtu" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
      			<thead>
      				<tr>
      					<th class="th-sm">#
      					
      					</th>
      					<th class="th-sm">Nom
      					
      					</th>
      					<th class="th-sm">Prenom
      					
      					</th>
      					<th class="th-sm">Numéro d'étudiant
      					</th>
      				</tr>
      			</thead>
      			<tbody>
      			<c:forEach items="${etudiants}" var="etudiant">
      			<tr>
      				<td><c:out value="${etudiant.id}"></c:out></td>
      				<td><c:out value="${etudiant.nom}"></c:out></td>
      				<td><c:out value="${etudiant.prenom}"></c:out></td>
      				<td><c:out value="${etudiant.numEtu }"></c:out></td>
      			</tr>
      			</c:forEach>
      			</tbody>
      			<tfoot>
      				<tr>
      					<th>#
      					</th>
      					<th>Nom
      					</th>
      					<th>Prenom
      					</th>
      					<th>Numéro d'étudiant
      					</th>
      				</tr>
      			</tfoot>
      		</table>
      	</div>
      	<!-- Parcourir les notes -->  
      		<div style="display:none;" id="parcNote" class="container">
      		<h1 class="mt-4">Tableau des notes</h1>
      		<p>Vous pouvez parcourir ici la liste des notes !</p>
      		<p>Vous pouvez de même ajuster l'affichage en modifiant les paramètres.</p>
      		<table id="tableEtu" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
      			<thead>
      				<tr>
      					<th class="th-sm">ID étudiant
      					
      					</th>
      					<th class="th-sm">Matiere
      					
      					</th>
      					<th class="th-sm">Note
      					
      					</th>
      					<th class="th-sm">#
      					
      					</th>
      				</tr>
      			</thead>
      			<tbody>
      			<c:forEach items="${notes}" var="note">
      			<tr>
      				<td><c:out value="${note.idEtu}"></c:out></td>
      				<td><c:out value="${note.matieres}"></c:out></td>
      				<td><c:out value="${note.note}"></c:out></td>
      				<td><c:out value="${note.idNote}"></c:out></td>
      			</tr>
      			</c:forEach>
      			</tbody>
      			<tfoot>
      				<tr>
      					<th>ID étudiant
      					</th>
      					<th>Matière
      					</th>
      					<th>Note
      					</th>
      					<th>#
      					</th>
      				</tr>
      			</tfoot>
      		</table>
      	</div>
      	<!-- Parcourir les matieres -->  
      		<div style="display:none;" id="parcMatiere" class="container">
      		<h1 class="mt-4">Tableau des matières</h1>
      		<p>Vous pouvez parcourir ici la liste des matières !</p>
      		<p>Vous pouvez de même ajuster l'affichage en modifiant les paramètres.</p>
      		<table id="tableEtu" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
      			<thead>
      				<tr>
      					<th class="th-sm">Nom
      					
      					</th>
      					<th class="th-sm">Coefficient
      					</th>
      				</tr>
      			</thead>
      			<tbody>
      			<c:forEach items="${matieres}" var="matiere">
      			<tr>
      				<td><c:out value="${matiere.nom}"></c:out></td>
      				<td><c:out value="${matiere.coeff}"></c:out></td>
      			</tr>
      			</c:forEach>
      			</tbody>
      			<tfoot>
      				<tr>
      					<th>Nom
      					</th>
      					<th>Coefficient
      					</th>
      				</tr>
      			</tfoot>
      		</table>
      	</div>
      	<!-- Parcourir les utilisateurs -->  
      		<div style="display:none;" id="parcUser" class="container">
      		<h1 class="mt-4">Tableau des utilisateurs</h1>
      		<p>Vous pouvez parcourir ici la liste des utilisateurs !</p>
      		<p>Vous pouvez de même ajuster l'affichage en modifiant les paramètres.</p>
      		<table id="tableEtu" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
      			<thead>
      				<tr>
      					<th class="th-sm">#
      					
      					</th>
      					<th class="th-sm">Username
      					
      					</th>
      					<th class="th-sm">Permission
      					
      					</th>
      				</tr>
      			</thead>
      			<tbody>
      			<c:forEach items="${users}" var="user">
      			<tr>
      				<td><c:out value="${user.id}"></c:out></td>
      				<td><c:out value="${user.username}"></c:out></td>
      				<td><c:out value="${user.permission}"></c:out></td>
      			</tr>
      			</c:forEach>
      			</tbody>
      			<tfoot>
      				<tr>
      					<th>#
      					</th>
      					<th>Username
      					</th>
      					<th>Permission
      				</tr>
      			</tfoot>
      		</table>
      	</div>
		<!-- Ajout d'un étudiant -->
      <div style="display:none;" id="addEtu" class="container">
        <h1 class="mt-4">Ajout d'un étudiant</h1>
        <p>Vous pouvez ajouter ici un étudiant !</p>
        <form action="etudiant" method="POST">
		Nom : <input type="text" name ="nom"/><br>
		Prenom : <input type="text" name ="prenom"/><br>
		Numéro étudiant : <input type="text" name ="numEtu"/><br><br>
		<input type = "submit" name = "action" value = "AjoutEtudiant"/>
		</form>
      </div>
      
      <!--  Suppression d'un étudian -->
      <div style="display:none;" id="suprEtu" class="container">
      	<h1 class="mt-4">Suppresion d'un étudiant</h1>
      	<p>Vous pouvez supprimer ici un étudiant !</p>
      	<form action="etudiant" method="POST">
		ID de l'étudiant : <input type="text" name="id"/><br>
		<input type="submit" name = "action" value = "SuprEtu"/>
		</form>
	  </div>
	  
	  <!-- Ajout d'une note -->
	  <div style="display:none;" id="addNote" class="container">
	  	<h1>Ajout d'une note</h1>
	  	<p>Vous pouvez ajouter ici une note à un étudiant !</p>
	  	<form action="etudiant" method="POST">
	  	ID de l'étudiant : <input type="text" name="id"/><br>
	  	Matiere : <input type="text" name="matiere"/><br>
	  	Note : <input type="text" name="note"/><br>	
	  	<input type="submit" name="action" value="AddNote"/>
	  	</form>
	  </div>
	  
	  <!-- Suppression d'une note -->
	  <div style="display:none;" id="suprNote" class="container">
	  	<h1>Suppression d'une note</h1>
	  	<p>Vous pouvez supprimer une note ici !</p>
	  	<form action="etudiant" method="POST">
	  	ID de la note à supprimer : <input type="text" name="id"/><br>
	  	<input type="submit" name="action" value="SuprNote"/>
	  	</form>
	  </div>
	  
	  <!-- Ajouter une matiere -->
	  <div style="display:none;" id="addMatiere" class="container">
	  	<h1>Ajout d'une note</h1>
	  	<p>Vous pouvez ajouter une matière ici !</p>
	  	<form action="etudiant" method="POST">
	  	Nom de la matière : <input type="text" name="nom"/><br>
	  	Coefficient de la matière : <input type="text" name="coeff"/><br>
	  	<input type="submit" name="action" value="AddMatiere"/>
	  	</form>
	  </div>
	  
	  <!-- Supprimer une matiere -->
	  <div style="display:none;" id="suprMatiere" class="container">
	  <h1>Suppression d'une matière</h1>
	  <p>Vous pouvez supprimer une matière ici !</p>
	  <form action="etudiant" method="POST">
	  Nom de la matière : <input type="text" name="nom"/><br>
	  <input type="submit" name="action" value="SuprMatiere"/>
	  </form>
	  </div>
    </div>
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
  
    <!-- MDBootstrap Datatables  -->
  <script type="text/javascript" src="js/addons/datatables.min.js"></script>
  
  <!-- Script navigation dans la liste etudiante -->
  <script>
  $(document).ready(function () {
	  $('#tableEtu').DataTable({
	    "paging": true
	  });
	  $('.dataTables_length').addClass('bs-select');
	});
  </script>
  
  <script src='https://www.google.com/recaptcha/api.js'></script>
  
  <!-- Script bouton -->
  <script type="text/javascript" src='<c:url value="/scripts/buttonFunction.js"/>'></script>
  
  <script>
  $(document).ready(function () {
	  var message= '<%= session.getAttribute("message")%>';
	  if(message!=null && message!="null") {
		  window.alert(message);
	  }
	  <%session.removeAttribute("message");%>
  })
  </script>

</body>

</html>
