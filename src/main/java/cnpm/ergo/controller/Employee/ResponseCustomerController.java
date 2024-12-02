package cnpm.ergo.controller.Employee;


import cnpm.ergo.DAO.implement.QuestionDAOImpl;
import cnpm.ergo.entity.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/employee/res")
public class ResponseCustomerController extends HttpServlet {
    private QuestionDAOImpl questionDAO = new QuestionDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Question> questionList = questionDAO.findByIsPending();
        req.setAttribute("pendingQuestions", questionList);
        req.getRequestDispatcher("/employee/views/phan-hoi.jsp").forward(req, resp);
    }
}
