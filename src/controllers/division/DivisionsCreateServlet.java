package controllers.division;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Department;
import models.Division;
import models.validators.DivisionValidator;
import utils.DBUtil;

/**
 * Servlet implementation class DivisionsCreateServlet
 */
@WebServlet("/divisions/create")
public class DivisionsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Division dv = new Division();
            dv.setName(request.getParameter("name"));

            int departmentId = Integer.parseInt(request.getParameter("department"));
            Department department = em.find(Department.class, departmentId);
            dv.setDepartment(department);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            dv.setCreated_at(currentTime);
            dv.setUpdated_at(currentTime);

            List<String> errors = DivisionValidator.validate(dv, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("division", dv);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(dv);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath() + "/divisions/index");
            }
        }
    }
}
