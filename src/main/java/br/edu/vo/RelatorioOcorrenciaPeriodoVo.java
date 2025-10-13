package br.edu.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RelatorioOcorrenciaPeriodoVo implements Serializable {
    private String nomeEspecie;
    private Long idHabitat;
    private String observador;
    private LocalDateTime dataObservacao;

    public RelatorioOcorrenciaPeriodoVo(String nomeEspecie, Long idHabitat, String observador, LocalDateTime dataObservacao) {
        this.nomeEspecie = nomeEspecie;
        this.idHabitat = idHabitat;
        this.observador = observador;
        this.dataObservacao = dataObservacao;
    }

    @Override
    public String toString() {
        return "Ocorrência: Especie = " + nomeEspecie +
                ", Habitat = " + idHabitat +
                ", Observador = " + observador +
                ", Data = " + dataObservacao;
    }
}
