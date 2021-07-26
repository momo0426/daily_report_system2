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

import models.Division;
import models.validators.DivisionValidator;
import utils.DBUtil;

/**
 * Servlet implementation class DivisionsUpdateServlet
 */
@WebServlet("/divisions/update")
public class DivisionsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionsUpdateServlet() {
        super();

    }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {
                EntityManager em = DBUtil.createEntityManager();

                Division dv = em.find(Division.class, (Integer)(request.getSession().getAttribute("division_id")));

                Boolean nameDuplicateCheckFlag = true;
                if(dv.getName().equals(request.getParameter("name"))) {
                    nameDuplicateCheckFlag = false;
                } else {
                    dv.setName(request.getParameter("name"));
                }

                dv.setName(request.getParameter("name"));
                dv.setUpdated_at(new Timestamp(System.currentTimeMillis()));

                List<String> errors = DivisionValidator.validate(dv, nameDuplicateCheckFlag);
                if(errors.size() > 0) {
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("division", dv);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/edit.jsp");
                    rd.forward(request, response);
                } else {
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    em.close();

                    request.getSession().setAttribute("flush", "更新が完了しました。");

                    request.getSession().removeAttribute("division_id");

                    response.sendRedirect(request.getContextPath() + "/divisions/index");
                }

            }
        }


}
