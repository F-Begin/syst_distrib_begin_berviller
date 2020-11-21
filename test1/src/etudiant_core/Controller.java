package etudiant_core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("resultat", new Etudiants().allStudentsDisplay());
		if(request.getAttribute("lastAction")==null) {
			request.setAttribute("acceuil", true);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on définit un objet de la classe métier... on fait apprel à la méthode addStudiant(Etudiant etudiant)
		if(request.getParameter("action").equals("AjoutEtudiant")) {
			new Etudiants().addStudent(request.getParameter("nom"), request.getParameter("prenom"));
			request.setAttribute("lastAction", "addEtu");
		}
		
		else if(request.getParameter("action").equals("SuprEtu")) {
			new Etudiants().suprStudent(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("lastAction", "suprEtu");
		}
		
		else if(request.getParameter("action").equals("ModEtu")) {
			new Etudiants().modEtu(request.getParameter("args"), Integer.parseInt(request.getParameter("id")), request.getParameter("value"));
			request.setAttribute("lastAction", "modInfo");
		}
		
		request.setAttribute("acceuil", false);
		doGet(request, response);
	}

}
