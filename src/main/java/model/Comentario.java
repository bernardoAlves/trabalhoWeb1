package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comentario {
    private int idComentario;
    private Usuario autor;
    private Topico topico;
    private LocalDateTime dataComentario;
    private String corpoComentario;
    private String dataFormatada;

    public Comentario(Usuario autor, Topico topico, String corpoComentario) {
        this.autor = autor;
        this.topico = topico;
        this.corpoComentario = corpoComentario;
    }

    public Comentario() {}

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    public String getDataFormatada() {
        return this.dataFormatada;
    }
    public void setDataComentario(LocalDateTime dataComentario) {
        this.dataComentario = dataComentario;
        this.dataFormatada = this.dataComentario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss"));
    }

    public String getCorpoComentario() {
        return corpoComentario;
    }

    public void setCorpoComentario(String corpoComentario) {
        this.corpoComentario = corpoComentario;
    }
}

