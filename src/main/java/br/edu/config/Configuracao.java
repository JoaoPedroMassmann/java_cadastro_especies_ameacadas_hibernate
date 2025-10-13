package br.edu.config;

public class Configuracao {
    private String modo;
    private String idioma;
    private String diretorioRelatorios;
    private String mensagemBoasVindas;

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDiretorioRelatorios() {
        return diretorioRelatorios;
    }

    public void setDiretorioRelatorios(String diretorioRelatorios) {
        this.diretorioRelatorios = diretorioRelatorios;
    }

    public String getMensagemBoasVindas() {
        return mensagemBoasVindas;
    }

    public void setMensagemBoasVindas(String mensagemBoasVindas) {
        this.mensagemBoasVindas = mensagemBoasVindas;
    }
}