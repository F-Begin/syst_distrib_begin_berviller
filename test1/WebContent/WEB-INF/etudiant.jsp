<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${resultat}" var="etudiant">
<ul>
<li><c:out value="${etudiant.id} - ${etudiant.nom} - ${etudiant.prenom}"></c:out></li>
</ul>
</c:forEach>

	<form action="etudiant" method="POST">
		Id <input type="text" name ="id"/><br>
		Nom : <input type="text" name ="nom"/><br>
		Prenom : <input type="text" name ="prenom"/><br>
		<input type = "submit" name = "action" value = "AjoutEtudiant"/>
	</form>
	
	<form action="etudiant" method="POST">
	ID de l'étudiant : <input type="text" name="id"/><br>
	<input type="submit" name = "action" value = "SuprEtu"/>
	</form>
	
	<form action="etudiant" method="POST">
	Valuer Ã  modifier :
		<input type="radio" id="nom" name="args" value="nom">
		<label for="nom">Nom</label>
		<input type="radio" id="prenom" name="args" value="prenom">
		<label for="prenom">Prenom</label>
	Nouvelle valeur : <input type="text" name = "value"/><br>
	ID de l'étudiant Ã  modifier : <input type="text" name="id"/><br>
	<input type = "submit" name = "action" value = "ModEtu"/>
	
</body>
</html>