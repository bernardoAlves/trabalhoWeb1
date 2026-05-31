package dao;

import model.Comentario;
import model.Topico;
import model.Usuario;
import service.TopicoService;
import service.UsuarioService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ComentarioDAO {

    public boolean excluirComentariosByIdTopico(int idTopico) {
        String sql = "DELETE FROM comentario WHERE topico = ?";

        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTopico);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Comentario> getComentariosByTopico(Topico topico) {
        String sql = "SELECT * FROM comentario JOIN usuario ON comentario.autor = usuario.idUsuario WHERE topico = ? ORDER BY dataComentario DESC";
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, topico.getIdTopico());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Comentario c = new Comentario();
                c.setIdComentario(rs.getInt("idComentario"));
                c.setDataComentario(rs.getObject("dataComentario", LocalDateTime.class));
                c.setCorpoComentario(rs.getString("corpoComentario"));
                c.setAutor(new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("senha")));
                comentarios.add(c);
            }
            return comentarios;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean salvarComentario(Comentario comentario) {
        String sql = "INSERT INTO comentario(autor, topico, corpoComentario, dataComentario) VALUES(?,?,?,current_timestamp)";
        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, comentario.getAutor().getIdUsuario());
            stmt.setInt(2, comentario.getTopico().getIdTopico());
            stmt.setString(3, comentario.getCorpoComentario());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean excluirComentario(int id) {
        String sql = "DELETE FROM comentario WHERE idComentario = ?";
        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateComentario(Comentario comentario) {
        String sql = "UPDATE comentario SET corpoComentario = ? WHERE idComentario = ?";
        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, comentario.getCorpoComentario());
            stmt.setInt(2, comentario.getIdComentario());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Comentario getComentarioById(int id) {
        String sql = "SELECT * FROM comentario WHERE idComentario = ?";
        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Comentario c = new Comentario();
                c.setIdComentario(rs.getInt("idComentario"));
                c.setCorpoComentario(rs.getString("corpoComentario"));
                c.setDataComentario(rs.getObject("dataComentario", LocalDateTime.class));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
