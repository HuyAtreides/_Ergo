package cnpm.ergo.controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("/employee/common/menu.jsp").forward(req, resp);
    }
    public void destroy() {
    }
}