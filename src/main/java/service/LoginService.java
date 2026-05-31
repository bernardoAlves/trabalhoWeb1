package service;

import dao.UsuarioDAO;
import model.Usuario;

public class LoginService {

    public Usuario autenticar(String nome, String senha) {
        try {
            return new UsuarioDAO().autenticar(nome, senha);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
