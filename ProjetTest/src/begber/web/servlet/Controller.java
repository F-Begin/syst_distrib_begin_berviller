package begber.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import begber.web.servlet.client.gestions.GestionsService;
import begber.web.servlet.client.gestions.ProjetGestionEtudiant;
import begber.web.servlet.users.User;



/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProjetGestionEtudiant stub = new GestionsService().getProjetGestionEtudiantPort();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("etudiants", stub.getListeEtu());
		request.setAttribute("matieres", stub.getListeMatiere());
		request.setAttribute("notes", stub.getListeNote());
		request.setAttribute("users", stub.getListeUser());
		HttpSession session = request.getSession();
		if((session.getAttribute("username")==null) || (session.getAttribute("password")==null)) {
			session.setAttribute("connect", "no");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		switch (request.getParameter("action")) {
		case "AjoutEtudiant":
			if(stub.checkAvailabilityEtu(request.getParameter("numEtu"))) {
				if(stub.newEtu(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("numEtu")))
					session.setAttribute("message", "Étudiant ajouté avec succès !");
				else
					session.setAttribute("message", "Echec de l'ajout de l'Étudiant !");
			}
			else
				session.setAttribute("message", "Un étudiant avec le même numéro existe déjà !");
			break;
		case "SuprEtu":
			try {
				stub.suprEtu(Integer.parseInt(request.getParameter("id")));
				session.setAttribute("message", "Étudiant supprimé avec succès !");
			}catch(Exception e) {
				session.setAttribute("message", "Échec de suppression de l'Étudiant !");
			}
			break;
		case "AddNote":
			try {
				stub.newNote(request.getParameter("id"),stub.getMatiere(request.getParameter("matiere")).getNom(), Float.parseFloat(request.getParameter("note")));
				session.setAttribute("message", "Note ajouté avec succès !");
			}catch(Exception e) {
				e.printStackTrace();
				session.setAttribute("message", "L'ajout de la note à échoué !");
			}
			break;
		case "SuprNote":
			try {
				stub.suprNote(Integer.parseInt(request.getParameter("id")));
				session.setAttribute("message", "Note supprimé avec succès !");
			}catch(Exception e) {
				session.setAttribute("message", "La suppression de la note à échoué !");
			}
			break;
		case "AddMatiere":
			if(stub.checkAvailabilityMatiere(request.getParameter("nom"))) {
				try {
					stub.newMatiere(request.getParameter("nom"), Integer.parseInt(request.getParameter("coeff")));
					session.setAttribute("message", "Matière ajouté avec succès !");
				}catch(Exception e) {
					session.setAttribute("message", "Échec de l'ajout de la matière !");
				}
			}
			else {
				session.setAttribute("message", "Ce nom de matière est déjà utilisé !");
				}
			break;
		case "SuprMatiere":
			try {
				stub.suprMatiere(request.getParameter("nom"));
				session.setAttribute("message", "Note supprimé avec succès !");
			}catch(Exception e) {
				session.setAttribute("message", "Échec lors de la suppression de la note !");
			}
		case "connexion":
			try {
				if(stub.connexion(request.getParameter("username"), request.getParameter("password"))) {
					session.setAttribute("username", request.getParameter("username"));
					session.setAttribute("password", request.getParameter("password"));
					for (begber.web.servlet.client.gestions.User user : stub.getListeUser()) {
						if(user.getUsername().equals(request.getParameter("username")))
							session.setAttribute("perm", user.getPermission());
					}
					session.setAttribute("connect", "yes");
					session.setAttribute("message", "Bienvenue "+request.getParameter("username"+" !"));
				}
				else
					session.setAttribute("message", "Identifiants incorrectes.");
			}catch(Exception e) {
				session.setAttribute("message", "Erreur lors de la connexion.");
			}
			break;
		case "deconnexion":
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.removeAttribute("perm");
			session.setAttribute("connect", "no");
			session.setAttribute("message", "A bientot !");
			break;
		case "creationCompte":
			try {
				if(stub.newUsers(stub.getListeUser().size()+1, request.getParameter("username"), request.getParameter("password"))) {
					session.setAttribute("username", request.getParameter("username"));
					session.setAttribute("password", request.getParameter("password"));
					for (begber.web.servlet.client.gestions.User user : stub.getListeUser()) {
						if(user.getUsername().equals(request.getAttribute("username")))
							session.setAttribute("perm", user.getPermission());
					}
					session.setAttribute("connect", "yes");
					session.setAttribute("message", "Bienvenue !");
				}
				else
					session.setAttribute("message", "Erreur lors de la création du compte.");
			}catch(Exception e) {
				session.setAttribute("message", "Erreur !");
			}
			break;
		default:
			session.setAttribute("message", null);
			break;
		}
		doGet(request, response);
	}

}
