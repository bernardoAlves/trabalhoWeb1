package dao;

import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario autenticar(String nome, String senha) {

        String sql = "SELECT * FROM usuario WHERE nome = ? and senha = ?";

        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }


        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Usuario> getUsuarios() {
        String sql = "SELECT * FROM usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            Connection conexao = ConexaoBD.getConexao();
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
                System.out.println(u);
            }

            return usuarios;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isNomeOcupado(Usuario usuario) {
        for(Usuario u : this.getUsuarios()) {
            if (usuario.getNome().equals(u.getNome())) {
                return true;
            }
        }
        return false;
    }

    public boolean inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, senha) VALUES(?, ?)";

        try {

            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            stmt.executeUpdate();
            return true;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Usuario getUsuarioById(int id) {
        String sql = "SELECT * FROM usuario where idUsuario = ?";
        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(id);
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
