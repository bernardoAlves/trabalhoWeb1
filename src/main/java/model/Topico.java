package model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Topico {
    private int idTopico;
    private String titulo;
    private Usuario autor;
    private LocalDateTime dataTopico;
    private String corpoTopico;
    private boolean resolvido;
    private String dataFormatada;

    public Topico(String titulo, Usuario autor, String corpoTopico) {
        this.titulo = titulo;
        this.autor = autor;
        this.corpoTopico = corpoTopico;
    }

    public Topico(int idTopico, String titulo, Usuario autor, LocalDateTime dataTopico, boolean resolvido, String corpoTopico, String dataFormatada) {
        this.idTopico = idTopico;
        this.titulo = titulo;
        this.autor = autor;
        this.dataTopico = dataTopico;
        this.resolvido = resolvido;
        this.corpoTopico = corpoTopico;
        this.dataFormatada = dataFormatada;
    }

    public Topico() {

    }

    public int getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(int idTopico) {
        this.idTopico = idTopico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public LocalDateTime getDataTopico() {
        return dataTopico;
    }

    public void setDataTopico(LocalDateTime dataTopico) {
        this.dataTopico = dataTopico;
        this.dataFormatada = this.dataTopico.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss"));
    }

    public String getCorpoTopico() {
        return corpoTopico;
    }

    public void setCorpoTopico(String corpoTopico) {
        this.corpoTopico = corpoTopico;
    }

    public boolean isResolvido() {
        return resolvido;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }

    public String getDataFormatada() {
        return this.dataFormatada;
    }

}
