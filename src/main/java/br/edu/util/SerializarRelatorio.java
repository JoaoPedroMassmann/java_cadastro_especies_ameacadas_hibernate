package br.edu.util;

import br.edu.vo.RelatorioEspeciesEstadoVo;
import br.edu.vo.RelatorioOcorrenciaPeriodoVo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SerializarRelatorio {
    private static String DIR;
    private static final DateTimeFormatter FILE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");


    public static void configurarDiretorio(String dir) {
        if (dir == null || dir.isBlank()) {
            throw new IllegalArgumentException("Diretório de relatórios não pode ser nulo ou vazio.");
        }

        DIR = dir.endsWith("/") ? dir : dir + "/";
        File pasta = new File(DIR);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    public static String serializarRelatorioEspeciesEstado(List<RelatorioEspeciesEstadoVo> relatorioEspeciesEstado){
        if(relatorioEspeciesEstado == null || relatorioEspeciesEstado.isEmpty()){
            System.out.println("O relatório gerado não possui dados serializáveis.");
            return null;
        }
        String time = LocalDateTime.now().format(FILE_TIME_FORMATTER);
        String file = DIR + "relatorio_especies_estado_" + time + ".ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(relatorioEspeciesEstado);
            return file;
        } catch (IOException e) {
            System.err.println("Erro ao serializar o relatório:  " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<RelatorioEspeciesEstadoVo> deserializarRelatorioEspeciesEstado(String file){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<RelatorioEspeciesEstadoVo> relatorioEspecieEstado = (List<RelatorioEspeciesEstadoVo>) ois.readObject();
            System.out.println("Relatório desserializado com sucesso a partir de: " + file);
            return relatorioEspecieEstado;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao desserializar o relatório" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String serializarRelatorioOcorrenciaPeriodo(List<RelatorioOcorrenciaPeriodoVo> relatorioOcorrenciaPeriodo){
        if(relatorioOcorrenciaPeriodo == null || relatorioOcorrenciaPeriodo.isEmpty()){
            System.out.println("O relatório gerado não possui dados serializáveis.");
            return null;
        }
        String time = LocalDateTime.now().format(FILE_TIME_FORMATTER);
        String file = DIR + "relatorio_ocorrencias_periodo" + time + ".ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(relatorioOcorrenciaPeriodo);
            return file;
        } catch (IOException e) {
            System.err.println("Erro ao serializar o relatório:  " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<RelatorioOcorrenciaPeriodoVo> deserializarRelatorioOcorrenciaPeriodo(String file){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<RelatorioOcorrenciaPeriodoVo> relatorioOcorrenciaPeriodo = (List<RelatorioOcorrenciaPeriodoVo>) ois.readObject();
            return relatorioOcorrenciaPeriodo;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao desserializar o relatório" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
