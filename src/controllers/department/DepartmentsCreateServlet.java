package controllers.department;

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
import models.validators.DepartmentValidator;
import utils.DBUtil;

/**
 * Servlet implementation class DepartmentsCreateServlet
 */
@WebServlet("/departments/create")
public class DepartmentsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Department dp = new Department();
            dp.setName(request.getParameter("name"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            dp.setCreated_at(currentTime);
            dp.setUpdated_at(currentTime);

            List<String> errors = DepartmentValidator.validate(dp, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("department", dp);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/departments/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(dp);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath() + "/departments/index");
            }
        }
    }
}
