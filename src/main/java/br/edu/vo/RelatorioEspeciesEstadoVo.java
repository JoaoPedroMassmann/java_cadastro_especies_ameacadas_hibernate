package br.edu.vo;

import br.edu.enums.EstadoConservacaoEnum;

import java.io.Serializable;

public class RelatorioEspeciesEstadoVo implements Serializable {
    private EstadoConservacaoEnum estado;
    private Long quantidade;

    public RelatorioEspeciesEstadoVo(EstadoConservacaoEnum estado, Long quantidade) {
        this.estado = estado;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Estado: " + estado
                + " | Quantidade de espécies: " + quantidade;
    }
}
