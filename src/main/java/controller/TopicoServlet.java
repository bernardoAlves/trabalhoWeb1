package controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Topico;
import model.Usuario;
import service.TopicoService;
import service.UsuarioService;

import java.io.IOException;
import java.util.List;

@WebServlet("/topico")
public class TopicoServlet extends HttpServlet {

    private TopicoService topicoService = new TopicoService();
    private UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessao = req.getSession(false);
        if(sessao == null || sessao.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
        }
        else {
            Usuario uSessao = (Usuario) sessao.getAttribute("usuario");
            List<Topico> topicos = topicoService.getTopicos();
            String acao = req.getParameter("acao");


            if(acao != null && acao.equals("excluir")) {
                int idTopico = Integer.parseInt(req.getParameter("idTopico"));
                topicoService.excluirTopicoById(idTopico);
                resp.sendRedirect("topico?acao=recentes&msg=excluido");
                return;
            }
            else if(acao != null && acao.equals("recentes")) {
                topicos = topicoService.getTopicosRecentes();
                req.setAttribute("msg", req.getParameter("msg"));
            }
            else if(acao != null && acao.equals("em-aberto")) {
                topicos = topicoService.getTopicosEmAberto();
            }
            else if(acao != null && acao.equals("resolvidos")) {
                topicos = topicoService.getTopicosResolvidos();
            }
            else if(acao != null && acao.equals("topicosByUsuarioLogado")) {
                topicos = topicoService.getTopicosByIdAutor(uSessao.getIdUsuario());
            }
            else if(acao != null && acao.equals("editar")) {
                Topico t = topicoService.getTopicoById(Integer.parseInt(req.getParameter("idTopico")));
                req.setAttribute("idTopico", t.getIdTopico());
                req.setAttribute("titulo", t.getTitulo());
                req.setAttribute("corpoTopico", t.getCorpoTopico());
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/cadastroEdicao.jsp");
                rd.forward(req, resp);
                return;
            }
            else if(acao != null && acao.equals("cadastrar")) {
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/cadastroEdicao.jsp");
                rd.forward(req, resp);
                return;
            }
            else if(acao != null && acao.equals("reabrir")) {
                topicoService.reabrirTopico(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("topico?acao=recentes&msg=reaberto");
                return;
            }
            else if(acao != null && acao.equals("resolver")) {
                topicoService.resolverTopico(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("topico?acao=recentes&msg=resolvido");
                return;
            }

            for(Topico t : topicos) {
                System.out.println(t.getTitulo());
            }

            req.setAttribute("topicos", topicos);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/topicos.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sessao = req.getSession(false);

        if(sessao == null || sessao.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
        }
        else {
            Usuario uSessao = (Usuario) sessao.getAttribute("usuario");
            String idTopico = req.getParameter("idTopico");

            String titulo = req.getParameter("titulo");
            Usuario autor = usuarioService.getUsuarioById(uSessao.getIdUsuario());
            String corpoTopico = req.getParameter("corpoTopico");
            Topico t = new Topico(titulo, autor, corpoTopico);

            if(idTopico != null && !idTopico.trim().isEmpty()) {
                t.setIdTopico(Integer.parseInt(idTopico));
                topicoService.updateTopico(t);
                resp.sendRedirect("topico?acao=recentes&msg=editado");
            } else {
                topicoService.salvarTopico(t);
                resp.sendRedirect("topico?acao=recentes&msg=criado");
            }

        }
    }
}
