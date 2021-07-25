package controllers.division;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Division;

/**
 * Servlet implementation class DivisionsNewServlet
 */
@WebServlet("/divisions/new")
public class DivisionsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionsNewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        CSR対策
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("division", new Division());
//おまじないとしてインスタンスを生成
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/new.jsp");
        rd.forward(request, response);
    }

}
