package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Comentario;
import model.Topico;
import model.Usuario;
import service.ComentarioService;
import service.TopicoService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

@WebServlet("/comentario")
public class ComentarioServlet extends HttpServlet {

    private ComentarioService comentarioService = new ComentarioService();
    private TopicoService topicoService = new TopicoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessao = req.getSession(false);
        if(sessao == null || sessao.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
        }
        else {
            Usuario uSessao = (Usuario) sessao.getAttribute("usuario");
            String acao = req.getParameter("acao");

            if(acao != null && acao.equals("mostrar")) {
                Topico t = topicoService.getTopicoById(Integer.parseInt(req.getParameter("idTopico")));
                List<Comentario> c = comentarioService.getComentariosByTopico(t);
                req.setAttribute("topico", t);
                req.setAttribute("comentarios", c);
                req.setAttribute("msg", req.getParameter("msg"));
            }
            else if(acao != null && acao.equals("excluir")) {
                comentarioService.excluirComentario(Integer.parseInt(req.getParameter("idComentario")));
                String idTopico = req.getParameter("idTopico");
                resp.sendRedirect("comentario?acao=mostrar&idTopico=" + idTopico +"&msg=excluido");
                return;
            }
            else if(acao != null && acao.equals("editar")) {
                int idComentario = Integer.parseInt(req.getParameter("idComentario"));
                int idTopico = Integer.parseInt(req.getParameter("idTopico"));
                Topico t = topicoService.getTopicoById(idTopico);
                Comentario c = comentarioService.getComentarioById(idComentario);
                c.setTopico(t);
                c.setAutor(uSessao);
                req.setAttribute("comentario", c);
                req.getRequestDispatcher("WEB-INF/pages/editarComentario.jsp").forward(req, resp);
            }

            req.getRequestDispatcher("WEB-INF/pages/comentarios.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessao = req.getSession(false);
        if(sessao == null || sessao.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
        }
        else {
            String idComentario = req.getParameter("idComentario");

            Usuario uSessao = (Usuario) sessao.getAttribute("usuario");
            Topico t = topicoService.getTopicoById(Integer.parseInt( req.getParameter("idTopico")));
            String corpoComentario = req.getParameter("corpoComentario");
            Comentario c = new Comentario(uSessao, t, corpoComentario);

            if(idComentario != null && !idComentario.trim().isEmpty()) {
                c.setIdComentario(Integer.parseInt(idComentario));
                comentarioService.updateComentario(c);
                resp.sendRedirect("comentario?acao=mostrar&idTopico=" + t.getIdTopico() +"&msg=editado");
            } else {
                comentarioService.salvarComentario(c);
                resp.sendRedirect("comentario?acao=mostrar&idTopico=" + t.getIdTopico() +"&msg=criado");
            }
        }
    }
}
