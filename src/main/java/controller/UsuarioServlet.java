package controller;


import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import service.UsuarioService;

import java.io.IOException;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private UsuarioService service = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String senha = req.getParameter("senha");

        Usuario u = new Usuario(nome, senha);

        if(service.isNomeOcupado(u)) {
            req.setAttribute("erro", "Usuário com esse nome já existe!");
            req.getRequestDispatcher("/WEB-INF/pages/cadastroUsuario.jsp").forward(req,resp);
        } else if (service.inserirUsuario(u)){
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("erro", "Erro ao cadastrar usuário");
            req.getRequestDispatcher("/WEB-INF/pages/cadastroUsuario.jsp").forward(req, resp);
        }
    }
}
