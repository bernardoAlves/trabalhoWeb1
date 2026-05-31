package dao;

import model.Topico;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TopicoDAO {



    public ArrayList<Topico> getTopicos() {
        String sql = "SELECT * FROM topico JOIN usuario ON topico.autor = usuario.idUsuario";
        ArrayList<Topico> topicos = new ArrayList<>();
        try {
            Connection conexao = ConexaoBD.getConexao();
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Topico t = new Topico();
                t.setIdTopico(rs.getInt("idTopico"));
                t.setTitulo(rs.getString("titulo"));
                t.setAutor(new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("senha")));
                t.setDataTopico(rs.getObject("dataTopico", LocalDateTime.class));
                t.setCorpoTopico(rs.getString("corpoTopico"));
                t.setResolvido(rs.getBoolean("resolvido"));
                topicos.add(t);
            }

            return topicos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Topico> getTopicosRecentes() {
        String sql = "SELECT * FROM topico JOIN usuario ON topico.autor = usuario.idUsuario ORDER BY dataTopico DESC";
        ArrayList<Topico> topicos = new ArrayList<>();
        try {
            Connection conexao = ConexaoBD.getConexao();
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Topico t = new Topico();
                t.setIdTopico(rs.getInt("idTopico"));
                t.setTitulo(rs.getString("titulo"));
                t.setAutor(new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("senha")));
                t.setDataTopico(rs.getObject("dataTopico", LocalDateTime.class));
                t.setCorpoTopico(rs.getString("corpoTopico"));
                t.setResolvido(rs.getBoolean("resolvido"));
                topicos.add(t);
            }

            return topicos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Topico> getTopicosEmAberto() {
        String sql = "SELECT * FROM topico JOIN usuario ON topico.autor = usuario.idUsuario WHERE resolvido = false";
        ArrayList<Topico> topicos = new ArrayList<>();
        try {
            Connection conexao = ConexaoBD.getConexao();
            Statement stmt = conexao.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);

            while(rs.next()) {
                Topico t = new Topico();
                t.setIdTopico(rs.getInt("idTopico"));
                t.setTitulo(rs.getString("titulo"));
                t.setAutor(new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("senha")));
                t.setDataTopico(rs.getObject("dataTopico", LocalDateTime.class));
                t.setCorpoTopico(rs.getString("corpoTopico"));
                t.setResolvido(rs.getBoolean("resolvido"));
                topicos.add(t);
            }

            return topicos;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Topico> getTopicosResolvidos() {
        String sql = "SELECT * FROM topico JOIN usuario ON topico.autor = usuario.idUsuario WHERE resolvido = true";
        ArrayList<Topico> topicos = new ArrayList<>();
        try {
            Connection conexao = ConexaoBD.getConexao();
            Statement stmt = conexao.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);

            while(rs.next()) {
                Topico t = new Topico();
                t.setIdTopico(rs.getInt("idTopico"));
                t.setTitulo(rs.getString("titulo"));
                t.setAutor(new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("senha")));
                t.setDataTopico(rs.getObject("dataTopico", LocalDateTime.class));
                t.setCorpoTopico(rs.getString("corpoTopico"));
                t.setResolvido(rs.getBoolean("resolvido"));
                topicos.add(t);
            }

            return topicos;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Topico> getTopicoByIdAutor(int id) {
        String sql = "SELECT * FROM topico JOIN usuario ON topico.autor = usuario.idUsuario WHERE autor = ? ORDER BY dataTopico DESC";
        ArrayList<Topico> topicos = new ArrayList<>();
        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Topico t = new Topico();
                t.setIdTopico(rs.getInt("idTopico"));
                t.setTitulo(rs.getString("titulo"));
                t.setAutor(new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("senha")));
                t.setDataTopico(rs.getObject("dataTopico", LocalDateTime.class));
                t.setCorpoTopico(rs.getString("corpoTopico"));
                t.setResolvido(rs.getBoolean("resolvido"));
                topicos.add(t);
            }

            return topicos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public boolean excluirTopicoById(int id) {
        String sql = "DELETE FROM topico WHERE idTopico = ?";
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

    public boolean updateTopico(Topico topico) {
        String sql = "UPDATE topico SET titulo = ?, autor = ?, dataTopico = CURRENT_TIMESTAMP, corpoTopico = ? WHERE idTopico = ?";
        try{
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, topico.getTitulo());
            stmt.setInt(2, topico.getAutor().getIdUsuario());
            stmt.setString(3, topico.getCorpoTopico());
            stmt.setInt(4, topico.getIdTopico());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean salvarTopico(Topico topico) {
        String sql = "INSERT INTO topico(titulo, autor, dataTopico, corpoTopico, resolvido) VALUES(?,?,current_timestamp,?, false)";
        try{
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, topico.getTitulo());
            stmt.setInt(2, topico.getAutor().getIdUsuario());
            stmt.setString(3, topico.getCorpoTopico());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Topico getTopicoById(int id) {
        String sql = "SELECT * FROM topico JOIN usuario ON topico.autor = usuario.idUsuario WHERE idTopico = ?";

        try {
            Connection conexao = ConexaoBD.getConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Topico t = new Topico();
                t.setIdTopico(rs.getInt("idTopico"));
                t.setTitulo(rs.getString("titulo"));
                t.setAutor(new Usuario(rs.getInt("idUsuario"), rs.getString("nome"), rs.getString("senha")));
                t.setDataTopico(rs.getObject("dataTopico", LocalDateTime.class));
                t.setCorpoTopico(rs.getString("corpoTopico"));
                t.setResolvido(rs.getBoolean("resolvido"));
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean resolverTopico(int id) {
        String sql = "UPDATE topico SET resolvido = true, dataTopico = current_timestamp WHERE idTopico = ?";
        try{
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

    public boolean reabrirTopico(int id) {
        String sql = "UPDATE topico SET resolvido = false, dataTopico = current_timestamp WHERE idTopico = ?";
        try{
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





}
