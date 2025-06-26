package br.edu;

import br.edu.enums.EstadoConservacaoEnum;
import br.edu.model.Especie;
import br.edu.model.Habitat;
import br.edu.model.RegistroOcorrencia;
import br.edu.service.HabitatService;
import br.edu.service.RegistroOcorrenciaService;
import jakarta.persistence.*;
import br.edu.service.EspecieService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgresPU");
        em = emf.createEntityManager();

        EspecieService especieService = new EspecieService(em);
        HabitatService habitatService = new HabitatService(em);
        RegistroOcorrenciaService registroOcorrenciaService = new RegistroOcorrenciaService(em);


        boolean continuar = true;

        while (continuar) {
            System.out.println("----- MENU -----");
            System.out.println("1. Cadastrar Especie");
            System.out.println("2. Alterar Especie");
            System.out.println("3. Excluir Especie");
            System.out.println("4. Consultar Especie por ID");
            System.out.println("5. Consultar Especie por Nome Cientifico");
            System.out.println("6. Listar todas as Especies");
            System.out.println("7. Cadastrar Habitat");
            System.out.println("8. Alterar Habitat");
            System.out.println("9. Excluir Habitat");
            System.out.println("10. Consultar Habitat por ID");
            System.out.println("11. Listar todos os Habitats");
            System.out.println("12. Cadastrar Registro de Ocorrencia");
            System.out.println("13. Alterar Registro de Ocorrencia");
            System.out.println("14. Consultar Registro de Ocorrencia por ID");
            System.out.println("15. Consultar Registros de Ocorrencia por Observador");
            System.out.println("16. Consultar Registro de Ocorrencia por Especie");
            System.out.println("17. Consultar Registro de Ocorrencia por Habitat");
            System.out.print("Escolha uma opção: ");


            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarEspecie(especieService);
                case 2 -> alterarEspecie(especieService);
                case 3 -> excluirEspecie(especieService);
                case 4 -> consultarEspeciePorId(especieService);
                case 5 -> System.out.println("Funcionalidade ainda não implementada.");
                case 6 -> listarEspecies(especieService);
                case 7 -> cadastrarHabitat(habitatService);
                case 8 -> alterarHabitat(habitatService);
                case 9 -> excluirHabitat(habitatService);
                case 10 -> consultarHabitatPorId(habitatService);
                case 11 -> listarHabitats(habitatService);
                case 12 -> cadastrarRegistroDeOcorrencia(registroOcorrenciaService, habitatService, especieService);
                case 13 -> alterarRegistroOcorrencia(registroOcorrenciaService);
                case 14 -> System.out.println("Funcionalidade ainda não implementada.");
                case 15 -> System.out.println("Funcionalidade ainda não implementada.");
                case 16 -> System.out.println("Funcionalidade ainda não implementada.");
                case 17 -> System.out.println("Funcionalidade ainda não implementada.");
                case 18 -> System.out.println("Funcionalidade ainda não implementada.");
                case 19 -> System.out.println("Funcionalidade ainda não implementada.");
                case 20 -> System.out.println("Funcionalidade ainda não implementada.");
                case 0 -> continuar = false;
                default -> System.out.println("Opção inválida!");
            }
        }
        em.close();
        emf.close();
        System.out.println("Programa encerrado.");
    }

    private static void cadastrarEspecie(EspecieService especieService) {
        System.out.print("Digite o nome comum: ");
        String nomeComum = scanner.nextLine();

        System.out.print("Digite o nome cientifico: ");
        String nomeCientifico = scanner.nextLine();

        System.out.print("Digite o reino da especie: ");
        String reino = scanner.nextLine();

        System.out.print("Digite o filo da especie: ");
        String filo = scanner.nextLine();

        System.out.print("Digite a classe da especie: ");
        String classe = scanner.nextLine();

        System.out.print("Digite a ordem da especie: ");
        String ordem = scanner.nextLine();

        System.out.print("Digite a familia da especie: ");
        String familia = scanner.nextLine();

        System.out.print("Digite o genero da especie: ");
        String genero = scanner.nextLine();

        System.out.print("Digite a populacao estimada da especie: ");
        int numPopulacao = scanner.nextInt();

        System.out.print("Digite o estado de conservacao: ");
        System.out.println("Estados de Conservação disponíveis:");
        for (EstadoConservacaoEnum e : EstadoConservacaoEnum.values()) {
            System.out.println(" - " + e.name());
        }
        String estado = scanner.nextLine();
        EstadoConservacaoEnum estadoConservacao = EstadoConservacaoEnum.valueOf(estado.toUpperCase());

        Especie especie = new Especie(nomeComum, nomeCientifico, reino, filo, classe, ordem, familia, genero, numPopulacao, estadoConservacao);
        especieService.inserir(especie);
        System.out.println("Especie cadastrada com sucesso!");
    }

    private static void alterarEspecie(EspecieService especieService) {
        System.out.print("Digite o ID da especie a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(id);
        if (especie != null) {
            System.out.print("Digite o novo comum: ");
            especie.setNomeComum(scanner.nextLine());

            System.out.print("Digite o nome cientifico: ");
            especie.setNomeCientifico(scanner.nextLine());

            System.out.print("Digite o reino da especie: ");
            especie.setReino(scanner.nextLine());

            System.out.print("Digite o filo da especie: ");
            especie.setFilo(scanner.nextLine());

            System.out.print("Digite a classe da especie: ");
            especie.setClasse(scanner.nextLine());

            System.out.print("Digite a ordem da especie: ");
            especie.setOrdem(scanner.nextLine());

            System.out.print("Digite a familia da especie: ");
            especie.setFamilia(scanner.nextLine());

            System.out.print("Digite o genero da especie: ");
            especie.setGenero(scanner.nextLine());

            System.out.print("Digite o reino da especie: ");
            especie.setNumPopulacao(scanner.nextInt());

            especieService.alterar(especie);
            System.out.println("Especie alterada com sucesso!");
        } else {
            System.out.println("Especie não encontrada.");
        }
    }

    private static void excluirEspecie(EspecieService especieService) {
        System.out.print("Digite o ID da especie a ser excluída: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(id);
        if (especie != null) {
            especieService.excluir(especie);
            System.out.println("Especie excluída com sucesso!");
        } else {
            System.out.println("Especie não encontrada.");
        }
    }

    private static void consultarEspeciePorId(EspecieService especieService) {
        System.out.print("Digite o ID da especie: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(id);
        System.out.println(especie != null ? especie.toString() : "Especie não encontrada.");
    }

    private static void consultarEspeciePorNomeCientifico(EspecieService especieService) {
        System.out.print("Digite o nome científico da espécie: ");
        String nomeCientifico = scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorNomeCientifico(nomeCientifico);
        System.out.println(especie != null ? especie.toString() : "Espécie não encontrada.");
    }

    private static void listarEspecies(EspecieService especieService) {
        List<Especie> especies = especieService.buscarTodasAsEspecies();
        especies.forEach(System.out::println);
    }

    private static void cadastrarHabitat(HabitatService habitatService) {
        System.out.print("Digite o nome da regiao do habitat: ");
        String regiao = scanner.nextLine();
        System.out.print("Digite o estado do habitat: ");
        String estado = scanner.nextLine();
        System.out.print("Digite a latitude do habitat: ");
        double latitude = scanner.nextDouble();
        System.out.print("Digite a longitude do habitat: ");
        double longitude = scanner.nextDouble();
        System.out.print("Digite o bioma do habitat: ");
        String bioma = scanner.nextLine();
        System.out.print("Digite a extensao do habitat: ");
        double extensao = scanner.nextDouble();

        Habitat habitat = new Habitat(regiao, estado, latitude, longitude, bioma, extensao);
        habitatService.inserir(habitat);
        System.out.println("Especie cadastrada com sucesso!");
    }

    private static void alterarHabitat(HabitatService habitatService) {
        System.out.print("Digite o ID do habitat a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(id);
        if (habitat != null) {
            System.out.print("Digite o nome da regiao do habitat: ");
            habitat.setRegiao(scanner.nextLine());

            System.out.print("Digite o estado do habitat: ");
            habitat.setEstado(scanner.nextLine());

            System.out.print("Digite a latitude do habitat: ");
            habitat.setLatitude(scanner.nextDouble());

            System.out.print("Digite a longitude do habitat: ");
            habitat.setLongitude(scanner.nextDouble());

            System.out.print("Digite a extensao do habitat: ");
            habitat.setExtensao(scanner.nextDouble());

            System.out.print("Digite o bioma do habitat: ");
            habitat.setBioma(scanner.nextLine());

            habitatService.alterar(habitat);
            System.out.println("Habitat alterada com sucesso!");

        } else {
            System.out.println("Habitat não encontrada.");
        }
    }

    private static void excluirHabitat(HabitatService habitatService) {
        System.out.print("Digite o ID do habitat habitat a ser excluída: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(id);
        if (habitat != null) {
            habitatService.excluir(habitat);
            System.out.println("Habitat excluído com sucesso!");
        } else {
            System.out.println("Habitat não encontrado.");
        }
    }

    private static void consultarHabitatPorId(HabitatService habitatService) {
        System.out.print("Digite o ID da habitat: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(id);
        System.out.println(habitat != null ? habitat.toString() : "Habitat não encontrado.");
    }

    private static void listarHabitats(HabitatService habitatService) {
        List<Habitat> habitats = habitatService.buscarTodosOsHabitats();
        habitats.forEach(System.out::println);
    }

    private static void cadastrarRegistroDeOcorrencia(RegistroOcorrenciaService registroOcorrenciaService, HabitatService habitatService, EspecieService especieService) {
        System.out.print("Digite a data e hora do registro (formato yyyy-MM-ddTHH:mm): ");
        String dataHoraString = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraString);

        System.out.print("Digite o observador responsável pelo registro: ");
        String observador = scanner.nextLine();

        System.out.print("Digite a latitude do registro: ");
        double latitude = scanner.nextDouble();

        System.out.print("Digite a longitude do registro: ");
        Long longitude = scanner.nextLong();

        //Habitat
        System.out.print("Digite o id do habitat do registro: ");
        Long idHabitat = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(idHabitat);
        if (habitat == null) {
            System.out.println("Habitat informada nao encontrada.");
            return;
        }

        System.out.print("Digite o ID da Especie de Ocorrencia: ");
        Long idEspecie = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(idEspecie);
        if (especie == null) {
            System.out.println("Especie informada nao encontrada.");
            return;
        }

        RegistroOcorrencia registroOcorrencia = new RegistroOcorrencia(dataHora, observador, latitude, longitude, habitat, especie);
        registroOcorrenciaService.inserir(registroOcorrencia);
        System.out.println("Registro de Ocorrencia com sucesso!");
    }

    private static void alterarRegistroOcorrencia(RegistroOcorrenciaService registroOcorrenciaService, HabitatService habitatService, EspecieService especieService) {
        System.out.print("Digite o ID do habitat a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        RegistroOcorrencia registroOcorrencia = registroOcorrenciaService.buscarRegistroOcorrenciaPorId(id);
        if (registroOcorrencia != null) {
            System.out.print("Digite a data e hora do registro (formato yyyy-MM-ddTHH:mm): ");
            String dataHoraString = scanner.nextLine();
            registroOcorrencia.setDataHora(LocalDateTime.parse(dataHoraString));
            System.out.print("Digite o observador responsável pelo registro: ");
            registroOcorrencia.setObservador(scanner.nextLine());
            System.out.print("Digite a latitude do registro: ");
            registroOcorrencia.setLatitude(scanner.nextDouble());
            System.out.print("Digite a longitude do registro: ");
            registroOcorrencia.setLongitude(scanner.nextDouble());

            System.out.print("Digite o id do habitat do registro: ");
            Long idHabitat = scanner.nextLong();
            scanner.nextLine();
            Habitat habitat = habitatService.buscarHabitatPorID(idHabitat);
            if (habitat == null) {
                System.out.println("Habitat informada nao encontrada.");
                return;
            } else {
                registroOcorrencia.setHabitat(habitat);
            }

            System.out.print("Digite o ID da Especie: ");
            Long idEspecie = scanner.nextLong();
            scanner.nextLine();
            Especie especie = especieService.buscarEspeciePorId(idEspecie);
            if (especie == null) {
                System.out.println("Especie informada nao encontrada.");
                return;
            } else {
                registroOcorrencia.setEspecie(especie);
            }

            registroOcorrenciaService.alterar(registroOcorrencia);
            System.out.println("Registro de Ocorrencia alterado com sucesso!");

        } else {
            System.out.println("Registro de Ocorrencia não encontrado.");
        }
    }
}