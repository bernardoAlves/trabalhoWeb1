package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;
import service.LoginService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String senha = req.getParameter("senha");

        Usuario u = loginService.autenticar(nome, senha);

        if (u != null) {
            HttpSession sessao = req.getSession();
            sessao.setAttribute("usuario", u);
            resp.sendRedirect("topico");
        } else {
            req.setAttribute("erro", "Usuário ou senha inválidos");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
