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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/etudiant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on d�finit un objet de la classe m�tier... on fait apprel � la m�thode addStudiant(Etudiant etudiant)
		if(request.getParameter("action").equals("AjoutEtudiant")) {
			Etudiant e = new Etudiant(Integer.parseInt(request.getParameter("id")), request.getParameter("nom"), request.getParameter("prenom"));
			new Etudiants().addStudent(e);
		}
		
		if(request.getParameter("action").equals("SuprEtu")) {
			new Etudiants().suprStudent(Integer.parseInt(request.getParameter("id")));
		}
		
		if(request.getParameter("action").equals("ModEtu")) {
			new Etudiants().modEtu(request.getParameter("args"), Integer.parseInt(request.getParameter("id")), request.getParameter("value"));
		}
		
		doGet(request, response);
	}

}
