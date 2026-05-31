package service;

import dao.ComentarioDAO;
import dao.TopicoDAO;
import dao.UsuarioDAO;
import model.Comentario;
import model.Topico;
import model.Usuario;

import java.util.List;

public class ComentarioService {
    private ComentarioDAO dao = new ComentarioDAO();

    public boolean excluirComentariosByIdTopico(int idTopico) {
        return dao.excluirComentariosByIdTopico(idTopico);
    }

    public List<Comentario> getComentariosByTopico(Topico topico) {
        List<Comentario> comentarios = dao.getComentariosByTopico(topico);
        for (Comentario c: comentarios) {
            c.setTopico(topico);
        }
        return comentarios;
    }

    public boolean salvarComentario(Comentario comentario) {
        return dao.salvarComentario(comentario);
    }
    public boolean excluirComentario(int id) {
        return dao.excluirComentario(id);
    }
    public boolean updateComentario(Comentario comentario) {
        return dao.updateComentario(comentario);
    }
    public Comentario getComentarioById(int id) {
        return dao.getComentarioById(id);
    }



}
