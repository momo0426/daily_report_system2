package controllers.department;

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
 * Servlet implementation class DivisionEditServlet
 */
@WebServlet("/divisions/edit")
public class DivisionsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionsEditServlet() {
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
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("division_id", dv.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/edit.jsp");
        rd.forward(request, response);
    }
}
