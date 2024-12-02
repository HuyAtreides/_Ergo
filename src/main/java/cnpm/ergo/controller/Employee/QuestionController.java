package cnpm.ergo.controller.Employee;


import cnpm.ergo.DAO.implement.QuestionDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employee/handle-question")
public class QuestionController extends HttpServlet {
    private QuestionDAOImpl questionDAO = new QuestionDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
