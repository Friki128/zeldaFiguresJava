package Controlers;

import Exceptions.emtyNameException;
import Exceptions.nameAlreadyInUseException;
import Exceptions.passwordLengthException;
import Services.userService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class registerController extends HttpServlet {
    userService userService = new userService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") != null){ resp.sendRedirect("/");}
        else {
            String error = req.getParameter("error");
            req.setAttribute("error", error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/register.jsp");
            requestDispatcher.forward(req, resp);
    }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("/");
        } else {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            try {
                userService.register(name, password);
                resp.sendRedirect("/login");
            } catch (passwordLengthException e) {
                errorController.redirectErrorToPage("The password must be at least 6 characters in length.", req, resp, "register?");
            } catch (nameAlreadyInUseException e) {
                errorController.redirectErrorToPage("This name is already in use.", req, resp, "register?");
            } catch (emtyNameException e) {
                errorController.redirectErrorToPage("A name must be provided.", req, resp, "register?");
            }
        }
    }
}
