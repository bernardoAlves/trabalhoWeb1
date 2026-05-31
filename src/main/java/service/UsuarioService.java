package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public boolean inserirUsuario(Usuario u) {
        return dao.inserirUsuario(u);
    }

    public boolean isNomeOcupado(Usuario u) {
        return dao.isNomeOcupado(u);
    }
    public Usuario getUsuarioById(int id) { return dao.getUsuarioById(id); }

}
