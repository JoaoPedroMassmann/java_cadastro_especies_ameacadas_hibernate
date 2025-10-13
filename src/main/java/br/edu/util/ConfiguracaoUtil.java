package br.edu.util;

import br.edu.config.Configuracao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfiguracaoUtil {
    private static final String CONFIG_FILE = "config.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Configuracao carregarConfiguracao() {
        try {
            File file = new File(CONFIG_FILE);
            if (!file.exists()) {
                Configuracao configPadrao = criarConfiguracaoPadrao();
                salvarConfiguracao(configPadrao);
                return configPadrao;
            }
            return mapper.readValue(file, Configuracao.class);
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de configuração: " + e.getMessage());
            return null;
        }
    }

    public static void salvarConfiguracao(Configuracao config) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CONFIG_FILE), config);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo de configuração: " + e.getMessage());
        }
    }

    private static Configuracao criarConfiguracaoPadrao() {
        Configuracao config = new Configuracao();
        config.setModo("producao");
        config.setIdioma("pt-BR");
        config.setDiretorioRelatorios("relatoriosSerializados/");
        config.setMensagemBoasVindas("Bem-vindo ao Sistema de Cadastro de Espécies Ameaçadas! Autor: João Pedro Massmann");
        return config;
    }
}