package controllers.division;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Division;
import utils.DBUtil;

/**
 * Servlet implementation class DivisionsShowServlet
 */
@WebServlet("/divisions/show")
public class DivisionsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionsShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Division dv = em.find(Division.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("division", dv);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/show.jsp");
        rd.forward(request, response);
    }

}
