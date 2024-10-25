package Controlers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class errorController {
    public static void redirectError(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", message);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/errorPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
