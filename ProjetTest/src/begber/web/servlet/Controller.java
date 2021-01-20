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
					session.setAttribute("message", "�tudiant ajout� avec succ�s !");
				else
					session.setAttribute("message", "Echec de l'ajout de l'�tudiant !");
			}
			else
				session.setAttribute("message", "Un �tudiant avec le m�me num�ro existe d�j� !");
			break;
		case "SuprEtu":
			try {
				stub.suprEtu(Integer.parseInt(request.getParameter("id")));
				session.setAttribute("message", "�tudiant supprim� avec succ�s !");
			}catch(Exception e) {
				session.setAttribute("message", "�chec de suppression de l'�tudiant !");
			}
			break;
		case "AddNote":
			try {
				stub.newNote(request.getParameter("id"), stub.getMatiere(request.getParameter("matiere")).getNom(), Float.parseFloat("note"));
				session.setAttribute("message", "Note ajout� avec succ�s !");
			}catch(Exception e) {
				session.setAttribute("message", "L'ajout de la note � �chou� !");
			}
			break;
		case "SuprNote":
			try {
				stub.suprNote(Integer.parseInt(request.getParameter("id")));
				session.setAttribute("message", "Note supprim� avec succ�s !");
			}catch(Exception e) {
				session.setAttribute("message", "La suppression de la note � �chou� !");
			}
			break;
		case "AddMatiere":
			if(stub.checkAvailabilityMatiere(request.getParameter("nom"))) {
				try {
					stub.newMatiere(request.getParameter("nom"), Integer.parseInt(request.getParameter("coeff")));
					session.setAttribute("message", "Mati�re ajout� avec succ�s !");
				}catch(Exception e) {
					session.setAttribute("message", "�chec de l'ajout de la mati�re !");
				}
			}
			else {
				session.setAttribute("message", "Ce nom de mati�re est d�j� utilis� !");
				}
			break;
		case "SuprMatiere":
			try {
				stub.suprMatiere(request.getParameter("nom"));
				session.setAttribute("message", "Note supprim� avec succ�s !");
			}catch(Exception e) {
				session.setAttribute("message", "�chec lors de la suppression de la note !");
			}
		default:
			break;
		}
		doGet(request, response);
	}

}
