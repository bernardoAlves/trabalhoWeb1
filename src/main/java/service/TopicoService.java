package service;

import dao.ComentarioDAO;
import dao.TopicoDAO;
import dao.UsuarioDAO;
import model.Topico;

import java.util.List;

public class TopicoService {

    private TopicoDAO dao = new TopicoDAO();
    private UsuarioDAO usuarioService = new UsuarioDAO();
    private ComentarioDAO comentarioService = new ComentarioDAO();


    public List<Topico> getTopicos() {
        return dao.getTopicos();
    }

    public List<Topico> getTopicosRecentes() {
        return dao.getTopicosRecentes();
    }

    public List<Topico> getTopicosEmAberto() {
        return dao.getTopicosEmAberto();
    }

    public List<Topico> getTopicosResolvidos() {
        return dao.getTopicosResolvidos();
    }

    public List<Topico> getTopicosByIdAutor(int id) {
        return dao.getTopicoByIdAutor(id);
    }

    public Topico getTopicoById(int id) {
        return dao.getTopicoById(id);
    }

    public boolean excluirTopicoById(int id) {
        comentarioService.excluirComentariosByIdTopico(id);
        return dao.excluirTopicoById(id);
    }

    public boolean updateTopico(Topico topico) {
        return dao.updateTopico(topico);
    }
    public boolean salvarTopico(Topico topico) {
        return dao.salvarTopico(topico);
    }
    public boolean resolverTopico(int id) {
        return dao.resolverTopico(id);
    }
    public boolean reabrirTopico(int id) {
        return dao.reabrirTopico(id);
    }
}
