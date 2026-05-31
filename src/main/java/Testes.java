import dao.UsuarioDAO;
import model.Usuario;

public class Testes {
    public static void main(String[] args) {
        Usuario usuario = new UsuarioDAO().autenticar("admin", "admin");
        System.out.println(usuario.getIdUsuario());

    }
}
