package br.edu;

import br.edu.config.Configuracao;
import br.edu.enums.EstadoConservacaoEnum;
import br.edu.model.Especie;
import br.edu.model.EspecieHabitat;
import br.edu.model.Habitat;
import br.edu.model.RegistroOcorrencia;
import br.edu.service.EspecieHabitatService;
import br.edu.service.HabitatService;
import br.edu.service.RegistroOcorrenciaService;
import br.edu.util.ConfiguracaoUtil;
import br.edu.util.SerializarRelatorio;
import br.edu.vo.RelatorioEspeciesEstadoVo;
import br.edu.vo.RelatorioOcorrenciaPeriodoVo;
import jakarta.persistence.*;
import br.edu.service.EspecieService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgresPU");
        em = emf.createEntityManager();

        Configuracao config = ConfiguracaoUtil.carregarConfiguracao();
        System.out.println(config.getMensagemBoasVindas());

        EspecieService especieService = new EspecieService(em);
        HabitatService habitatService = new HabitatService(em);
        RegistroOcorrenciaService registroOcorrenciaService = new RegistroOcorrenciaService(em);
        EspecieHabitatService especieHabitatService = new EspecieHabitatService(em);

        SerializarRelatorio.configurarDiretorio("relatoriosSerializados");

        boolean continuar = true;


        while (continuar) {
            System.out.println("----- MENU PRINCIPAL -----");
            System.out.println("1. Operações com Espécie");
            System.out.println("2. Operações com Habitats");
            System.out.println("3. Operações com Registro de Ocorrência");
            System.out.println("4. Relação Espécie-Habitat");
            System.out.println("5. Relatórios");
            System.out.println("0.sair");
            System.out.print("Escolha uma opção: ");

            int opcaoPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoPrincipal) {
                case 1 -> {
                    System.out.println("----- MENU ESPÉCIES -----");
                    System.out.println("1. Cadastrar");
                    System.out.println("2. Alterar");
                    System.out.println("3. Excluir");
                    System.out.println("4. Consultar por ID");
                    System.out.println("5. Consultar por Nome Científico");
                    System.out.println("6. Listar todas");
                    System.out.println("7. Listar todas as espécies com população crítica (<100 indivíduos)");
                    System.out.print("Escolha uma opção: ");


                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1 -> cadastrarEspecie(especieService);
                        case 2 -> alterarEspecie(especieService);
                        case 3 -> excluirEspecie(especieService);
                        case 4 -> consultarEspeciePorId(especieService);
                        case 5 -> consultarEspeciePorNomeCientifico(especieService);
                        case 6 -> listarEspecies(especieService);
                        case 7 -> listarEspeciesComPopulacaoCritica(especieService);
                        default -> System.out.println("Opção inválida!");
                    }
                }
                case 2 -> {
                    System.out.println("----- MENU HABITATS -----");
                    System.out.println("1. Cadastrar");
                    System.out.println("2. Alterar");
                    System.out.println("3. Excluir");
                    System.out.println("4. Consultar por ID");
                    System.out.println("5. Listar todos");
                    System.out.print("Escolha uma opção: ");

                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1 -> cadastrarHabitat(habitatService);
                        case 2 -> alterarHabitat(habitatService);
                        case 3 -> excluirHabitat(habitatService);
                        case 4 -> consultarHabitatPorId(habitatService);
                        case 5 -> listarHabitats(habitatService);
                        default -> System.out.println("Opção inválida!");
                    }
                }
                case 3 -> {
                    System.out.println("----- MENU REGISTROS DE OCORRÊNCIA -----");
                    System.out.println("1. Cadastrar");
                    System.out.println("2. Alterar");
                    System.out.println("3. Excluir");
                    System.out.println("4. Consultar por ID");
                    System.out.println("5. Consultar por Observador");
                    System.out.println("6. Consultar por Espécie");
                    System.out.println("7. Consultar por Habitat");
                    System.out.println("8. Listar todos");
                    System.out.print("Escolha uma opção: ");

                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1 -> cadastrarRegistroDeOcorrencia(registroOcorrenciaService, habitatService, especieService);
                        case 2 -> alterarRegistroDeOcorrencia(registroOcorrenciaService, habitatService, especieService);
                        case 3 -> excluirRegistroDeOcorrencia(registroOcorrenciaService);
                        case 4 -> consultarRegistroDeOcorrenciaPorId(registroOcorrenciaService);
                        case 5 -> consultarRegistrosDeOcorrenciaPorObservador(registroOcorrenciaService);
                        case 6 -> consultarRegistrosDeOcorrenciaPorEspecie(registroOcorrenciaService);
                        case 7 -> consultarRegistrosDeOcorrenciaPorHabitat(registroOcorrenciaService);
                        case 8 -> listarRegistrosDeOcorrencia(registroOcorrenciaService);
                        default -> System.out.println("Opção inválida!");
                    }
                }
                case 4 -> {
                    System.out.println("----- MENU ESPÉCIE-HABITAT -----");
                    System.out.println("1. Cadastrar relação");
                    System.out.println("2. Alterar relação");
                    System.out.println("3. Excluir relação");
                    System.out.println("4. Consultar por ID");
                    System.out.println("5. Listar todas");
                    System.out.println("6. Buscar Espécies em um Habitat");
                    System.out.println("7. Buscar Habitats de uma Espécie");
                    System.out.print("Escolha uma opção: ");

                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1 -> cadastrarEspecieEmHabitat(especieHabitatService, habitatService, especieService);
                        case 2 -> alterarEspecieHabitat(especieHabitatService, especieService, habitatService);
                        case 3 -> excluirEspecieHabitat(especieHabitatService);
                        case 4 -> consultarEspecieHabitatPorId(especieHabitatService);
                        case 5 -> listarRelacoesEspecieHabitat(especieHabitatService);
                        case 6 -> buscarEspeciesEmHabitat(especieHabitatService);
                        case 7 -> buscarHabitatsDeUmaEspecie(especieHabitatService);
                        default -> System.out.println("Opção inválida!");
                    }
                }
                case 5 -> {
                    System.out.println("----- MENU RELATÓRIOS -----");
                    System.out.println("1. Relatório de Espécies por Estado de Conservação");
                    System.out.println("2. Relatório de Registros de Ocorrência por Período");
                    System.out.println("3. Acessar Relatório de Espécies por Estado de Conservação");
                    System.out.println("4. Acessar Relatórios de Ocorrências por Período");
                    System.out.print("Escolha uma opção: ");

                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1 -> relatorioEspeciesPorEstadoConservacao(especieService);
                        case 2 -> relatorioOcorrenciasPorPeriodo(registroOcorrenciaService);
                        case 3 -> desserializarRelatorioEspeciesEstado();
                        case 4 -> desserializarRelatorioOcorrenciasPeriodo();
                        default -> System.out.println("Opção inválida!");
                    }
                }
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
        scanner.nextLine();

        System.out.print("Digite o estado de conservacao: ");
        System.out.println("Estados de conservação disponíveis:");
        for (EstadoConservacaoEnum e : EstadoConservacaoEnum.values()) {
            System.out.println(" - " + e.name());
        }
        EstadoConservacaoEnum estadoConservacao = null;
        while (estadoConservacao == null) {
            System.out.print("Digite o estado de conservacao: ");
            String estadoInput = scanner.nextLine();
            try {
                estadoConservacao = EstadoConservacaoEnum.valueOf(estadoInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Valor inválido! Por favor, digite um dos valores listados.");
                for (EstadoConservacaoEnum eEnum : EstadoConservacaoEnum.values()) {
                    System.out.println(" - " + eEnum.name());
                }
            }
        }

        Especie especie = new Especie(nomeComum, nomeCientifico, reino, filo, classe, ordem, familia, genero, numPopulacao, estadoConservacao);
        especieService.inserir(especie);
        System.out.println("Especie cadastrada com sucesso!");
    }

    private static void alterarEspecie(EspecieService especieService) {
        System.out.print("Digite o ID da espécie a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(id);
        if (especie != null) {
            System.out.print("Digite o novo nome comum: ");
            especie.setNomeComum(scanner.nextLine());

            System.out.print("Digite o novo nome cientifico: ");
            especie.setNomeCientifico(scanner.nextLine());

            System.out.print("Digite o novo reino da espécie: ");
            especie.setReino(scanner.nextLine());

            System.out.print("Digite o novo filo da espécie: ");
            especie.setFilo(scanner.nextLine());

            System.out.print("Digite a nova classe da espécie: ");
            especie.setClasse(scanner.nextLine());

            System.out.print("Digite a nova ordem da espécie: ");
            especie.setOrdem(scanner.nextLine());

            System.out.print("Digite a nova família da espécie: ");
            especie.setFamilia(scanner.nextLine());

            System.out.print("Digite o novo genero da espécie: ");
            especie.setGenero(scanner.nextLine());

            System.out.print("Digite o novo número da população da espécie: ");
            especie.setNumPopulacao(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Digite o novo estado de conservacao: ");
            System.out.println("Estados de conservação disponíveis:");
            for (EstadoConservacaoEnum e : EstadoConservacaoEnum.values()) {
                System.out.println(" - " + e.name());
            }
            EstadoConservacaoEnum estadoConservacao = null;
            while (estadoConservacao == null) {
                System.out.print("Digite o estado de conservacao: ");
                String estadoInput = scanner.nextLine();
                try {
                    estadoConservacao = EstadoConservacaoEnum.valueOf(estadoInput.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Valor inválido! Por favor, digite um dos valores listados.");
                    for (EstadoConservacaoEnum eEnum : EstadoConservacaoEnum.values()) {
                        System.out.println(" - " + eEnum.name());
                    }
                }
            }

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

        if (especie == null) {
            System.out.println("Espécie não encontrada com esse nome científico.");
        } else {
            System.out.println(especie);
        }
    }

    private static void listarEspecies(EspecieService especieService) {
        List<Especie> especies = especieService.buscarTodasAsEspecies();
        listar(especies);
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
        scanner.nextLine();

        System.out.print("Digite o bioma do habitat: ");
        String bioma = scanner.nextLine();

        System.out.print("Digite a extensao do habitat: ");
        double extensao = scanner.nextDouble();
        scanner.nextLine();

        Habitat habitat = new Habitat(regiao, estado, latitude, longitude, bioma, extensao);
        habitatService.inserir(habitat);
        System.out.println("Habitat cadastrada com sucesso!");
    }

    private static void alterarHabitat(HabitatService habitatService) {
        System.out.print("Digite o ID do habitat a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(id);
        if (habitat != null) {
            System.out.print("Digite o novo nome da regiao do habitat: ");
            habitat.setRegiao(scanner.nextLine());

            System.out.print("Digite o novo estado do habitat: ");
            habitat.setEstado(scanner.nextLine());

            System.out.print("Digite a nova latitude do habitat: ");
            habitat.setLatitude(scanner.nextDouble());

            System.out.print("Digite a nova longitude do habitat: ");
            habitat.setLongitude(scanner.nextDouble());
            scanner.nextLine();

            System.out.print("Digite o novo bioma do habitat: ");
            habitat.setBioma(scanner.nextLine());

            System.out.print("Digite a nova extensao do habitat: ");
            habitat.setExtensao(scanner.nextDouble());
            scanner.nextLine();

            habitatService.alterar(habitat);
            System.out.println("Habitat alterada com sucesso!");

        } else {
            System.out.println("Habitat não encontrada.");
        }
    }

    private static void excluirHabitat(HabitatService habitatService) {
        System.out.print("Digite o ID do habitat a ser excluído: ");
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
        System.out.print("Digite o ID do habitat: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(id);
        System.out.println(habitat != null ? habitat.toString() : "Habitat não encontrado.");
    }

    private static void listarHabitats(HabitatService habitatService) {
        List<Habitat> habitats = habitatService.buscarTodosOsHabitats();
        listar(habitats);
    }

    private static void cadastrarRegistroDeOcorrencia(RegistroOcorrenciaService registroOcorrenciaService, HabitatService habitatService, EspecieService especieService) {
        LocalDateTime dataHora = null;

        while (dataHora == null) {
            System.out.print("Digite a data e hora do registro (ex: 2025-06-27T14:30): ");
            String dataHoraString = scanner.nextLine();
            try {
                dataHora = LocalDateTime.parse(dataHoraString);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Formato de data e hora inválido! Tente novamente.");
            }
        }
        System.out.print("Digite o observador responsável pelo registro: ");
        String observador = scanner.nextLine();

        System.out.print("Digite a latitude do registro: ");
        double latitude = scanner.nextDouble();

        System.out.print("Digite a longitude do registro: ");
        double longitude = scanner.nextDouble();
        scanner.nextLine();

        //Habitat
        System.out.print("Digite o ID do habitat do registro: ");
        Long idHabitat = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(idHabitat);
        if (habitat == null) {
            System.out.println("Habitat informado nao encontrada.");
            return;
        }

        System.out.print("Digite o ID da especie do registro: ");
        Long idEspecie = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(idEspecie);
        if (especie == null) {
            System.out.println("Especie informada nao encontrada.");
            return;
        }
        scanner.nextLine();

        RegistroOcorrencia registroOcorrencia = new RegistroOcorrencia(dataHora, observador, latitude, longitude, habitat, especie);
        registroOcorrenciaService.inserir(registroOcorrencia);
        System.out.println("Registro de Ocorrencia com sucesso!");
    }

    private static void alterarRegistroDeOcorrencia(RegistroOcorrenciaService registroOcorrenciaService, HabitatService habitatService, EspecieService especieService) {
        System.out.print("Digite o ID do Registro de Ocorrencia a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        RegistroOcorrencia registroOcorrencia = registroOcorrenciaService.buscarRegistroOcorrenciaPorId(id);
        if (registroOcorrencia != null) {
            LocalDateTime dataHora = null;

            while (dataHora == null) {
                System.out.print("Digite a nova data e hora do registro (ex: 2025-06-27T14:30): ");
                String dataHoraString = scanner.nextLine();
                try {
                    dataHora = LocalDateTime.parse(dataHoraString);
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println("Formato de data e hora inválido! Tente novamente.");
                }
            }
            System.out.print("Digite o novo observador responsável pelo registro: ");
            registroOcorrencia.setObservador(scanner.nextLine());

            System.out.print("Digite a latitude do registro: ");
            registroOcorrencia.setLatitude(scanner.nextDouble());

            System.out.print("Digite a longitude do registro: ");
            registroOcorrencia.setLongitude(scanner.nextDouble());
            scanner.nextLine();

            //Habitat
            System.out.print("Digite o novo ID do habitat do registro: ");
            Long idHabitat = scanner.nextLong();
            scanner.nextLine();
            Habitat habitat = habitatService.buscarHabitatPorID(idHabitat);
            if (habitat == null) {
                System.out.println("Habitat informado nao encontrada.");
                return;
            } else {
                registroOcorrencia.setHabitat(habitat);
            }

            System.out.print("Digite o novo ID da especie do registro: ");
            Long idEspecie = scanner.nextLong();
            scanner.nextLine();
            Especie especie = especieService.buscarEspeciePorId(idEspecie);
            if (especie == null) {
                System.out.println("Especie informada nao encontrada.");
                return;
            } else {
                registroOcorrencia.setEspecie(especie);
            }
            scanner.nextLine();

            registroOcorrenciaService.alterar(registroOcorrencia);
            System.out.println("Registro de Ocorrencia alterada com sucesso!");
        } else {
            System.out.println("Registro de Ocorrencia não encontrado.");
        }
    }

    private static void excluirRegistroDeOcorrencia(RegistroOcorrenciaService registroOcorrenciaService) {
        System.out.print("Digite o ID do registro de ocorrência a ser excluído: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        RegistroOcorrencia registroOcorrencia = registroOcorrenciaService.buscarRegistroOcorrenciaPorId(id);
        if (registroOcorrencia != null) {
            registroOcorrenciaService.excluir(registroOcorrencia);
            System.out.println("Registro de ocorrência excluído com sucesso!");
        } else {
            System.out.println("Registro de ocorrência não encontrado.");
        }
    }

    private static void consultarRegistroDeOcorrenciaPorId(RegistroOcorrenciaService registroOcorrenciaService) {
        System.out.print("Digite o ID do registro de ocorrência: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        RegistroOcorrencia registroOcorrencia = registroOcorrenciaService.buscarRegistroOcorrenciaPorId(id);
        System.out.println(registroOcorrencia != null ? registroOcorrencia.toString() : "Registro do ocorrência não encontrado.");
    }

    private static void consultarRegistrosDeOcorrenciaPorObservador(RegistroOcorrenciaService registroOcorrenciaService) {
        System.out.print("Digite o nome do observador: ");
        String observador = scanner.nextLine();

        listar(registroOcorrenciaService.buscarRegistrosOcorrenciaPorObservador(observador));
    }

    private static void consultarRegistrosDeOcorrenciaPorEspecie(RegistroOcorrenciaService registroOcorrenciaService) {
        System.out.print("Digite o ID da espécie: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        listar(registroOcorrenciaService.buscarRegistrosOcorrenciaPorEspecie(id));
    }

    private static void consultarRegistrosDeOcorrenciaPorHabitat(RegistroOcorrenciaService registroOcorrenciaService) {
        System.out.print("Digite o ID do habitat: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        listar(registroOcorrenciaService.buscarRegistrosOcorrenciaPorHabitat(id));
    }

    private static void listarRegistrosDeOcorrencia(RegistroOcorrenciaService registroOcorrenciaService) {
        List<RegistroOcorrencia> registrosOcorrencia = registroOcorrenciaService.buscarTodosOsRegistrosDeOcorrencia();
        listar(registrosOcorrencia);
    }

    private static void listarEspeciesComPopulacaoCritica(EspecieService especieService) {
        System.out.println("Espécies com menos de 100 indivíduos:");

        List<Especie> especiesCriticas = especieService.buscarTodasAsEspecies().stream()
                .filter(e -> e.getNumPopulacao() < 100)
                .sorted(Comparator.comparing(Especie::getNumPopulacao))
                .toList();

        if (especiesCriticas.isEmpty()) {
            System.out.println("Nenhuma espécie com população inferior a 100 indivíduos foi encontrada.");
        } else {
            especiesCriticas.forEach(e -> {
                System.out.println("Espécie: " + e.getNomeCientifico() +
                        " | Nome comum: " + e.getNomeComum() +
                        " | População: " + e.getNumPopulacao());
            });
        }
    }

    private static void cadastrarEspecieEmHabitat(EspecieHabitatService especieHabitatService, HabitatService habitatService, EspecieService especieService) {
        System.out.print("Digite o id do habitat: ");
        Long idHabitat = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(idHabitat);
        if (habitat == null) {
            System.out.println("Habitat informado nao encontrada.");
            return;
        }

        System.out.print("Digite o ID da especie: ");
        Long idEspecie = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(idEspecie);
        if (especie == null) {
            System.out.println("Especie informada nao encontrada.");
            return;
        }

        System.out.print("Digite a populacao estimada da especie naquele habitat: ");
        int populacao = scanner.nextInt();
        scanner.nextLine();

        EspecieHabitat especieHabitat = new EspecieHabitat(especie, habitat, populacao);
        especieHabitatService.inserir(especieHabitat);
        System.out.println("Especie cadastrada no Habitat com sucesso!");
    }

    private static void alterarEspecieHabitat(EspecieHabitatService especieHabitatService, EspecieService especieService, HabitatService habitatService) {
        System.out.print("Digite o ID da relação espécie-habitat a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        EspecieHabitat relacao = especieHabitatService.buscarRelacaoEspecieHabitatPorId(id);
        if (relacao == null) {
            System.out.println("Relação espécie-habitat não encontrada.");
            return;
        }

        System.out.print("Digite o novo ID da espécie: ");
        Long idEspecie = scanner.nextLong();
        scanner.nextLine();
        Especie especie = especieService.buscarEspeciePorId(idEspecie);
        if (especie == null) {
            System.out.println("Espécie não encontrada.");
            return;
        }
        relacao.setIdEspecie(especie);

        System.out.print("Digite o novo ID do habitat: ");
        Long idHabitat = scanner.nextLong();
        scanner.nextLine();
        Habitat habitat = habitatService.buscarHabitatPorID(idHabitat);
        if (habitat == null) {
            System.out.println("Habitat não encontrado.");
            return;
        }

        System.out.print("Digite a nova população estimada da espécie no habitat: ");
        int populacao = scanner.nextInt();
        scanner.nextLine();
        relacao.setPopulacao(populacao);

        especieHabitatService.alterar(relacao);
        System.out.println("Relação especie-habitat atualizada com sucesso.");
    }

    private static void excluirEspecieHabitat(EspecieHabitatService especieHabitatService) {
        System.out.print("Digite o ID da relação espécie-habitat a ser excluída: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        EspecieHabitat relacao = especieHabitatService.buscarRelacaoEspecieHabitatPorId(id);
        if (relacao != null) {
            especieHabitatService.excluir(relacao);
            System.out.println("Relação excluída com sucesso.");
        } else {
            System.out.println("Relação não encontrada.");
        }
    }

    private static void consultarEspecieHabitatPorId(EspecieHabitatService especieHabitatService) {
        System.out.print("Digite o ID da relação: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        EspecieHabitat relacao = especieHabitatService.buscarRelacaoEspecieHabitatPorId(id);
        if (relacao != null) {
            System.out.println(relacao);
        } else {
            System.out.println("Relação não encontrada.");
        }
    }

    private static void listarRelacoesEspecieHabitat(EspecieHabitatService especieHabitatService) {
        List<EspecieHabitat> relacoes = especieHabitatService.buscarTodasAsRelacoesEspeciesHabitat();
        listar(relacoes);
    }

    private static void buscarEspeciesEmHabitat(EspecieHabitatService especieHabitatService) {
        System.out.print("Digite o ID do habitat: ");
        Long idHabitat = scanner.nextLong();
        scanner.nextLine();

        List<EspecieHabitat> lista = especieHabitatService.buscarEspeciesEmUmHabitat(idHabitat);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma espécie associada a esse habitat.");
        } else {
            System.out.println("Espécies associadas ao habitat:");
            lista.forEach(eh -> System.out.println(eh.getIdEspecie()));
        }
    }

    private static void buscarHabitatsDeUmaEspecie(EspecieHabitatService especieHabitatService) {
        System.out.print("Digite o ID da espécie: ");
        Long idEspecie = scanner.nextLong();
        scanner.nextLine();

        List<EspecieHabitat> lista = especieHabitatService.buscarHabitatsDeUmaEspecie(idEspecie);
        if (lista.isEmpty()) {
            System.out.println("Nenhum habitat associado a essa espécie.");
        } else {
            System.out.println("Habitats associados à espécie:");
            lista.forEach(eh -> System.out.println(eh.getIdHabitat()));
        }
    }

    private static void relatorioEspeciesPorEstadoConservacao(EspecieService especieService) {
        List<RelatorioEspeciesEstadoVo> relatorio = especieService.relatorioPorEstadoConservacao();

        if (relatorio.isEmpty()) {
            System.out.println("Nenhuma espécie cadastrada.");
        } else {
            System.out.println("Relatório de espécies por estado de conservação:");
            relatorio.forEach(System.out::println);
        }

        String caminho = SerializarRelatorio.serializarRelatorioEspeciesEstado(relatorio);
        if (caminho != null) {
            System.out.println("Relatório serializado com sucesso em: " + caminho);
        }
    }

    private static void relatorioOcorrenciasPorPeriodo(RegistroOcorrenciaService registroOcorrenciaService) {
        System.out.println("Digite a data inicial (AAAA-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine());

        System.out.println("Digite a data final (AAAA-MM-DD): ");
        LocalDate fim = LocalDate.parse(scanner.nextLine());

        List<RelatorioOcorrenciaPeriodoVo> relatorio = registroOcorrenciaService.relatorioPorPeriodo(inicio, fim);
        if (relatorio.isEmpty()) {
            System.out.println("Nenhum registro encontrado no período.");
        } else {
            relatorio.forEach(System.out::println);
        }

        String caminho = SerializarRelatorio.serializarRelatorioOcorrenciaPeriodo(relatorio);
        if (caminho != null) {
            System.out.println("Relatório serializado com sucesso em: " + caminho);
        }
    }

    private static void desserializarRelatorioEspeciesEstado() {
        System.out.print("Digite o caminho do arquivo (.ser) do relatório de espécies por estado de conservação: ");
        String caminho = scanner.nextLine();
        List<RelatorioEspeciesEstadoVo> lista = SerializarRelatorio.deserializarRelatorioEspeciesEstado(caminho);

        if (lista != null) {
            lista.forEach(System.out::println);
        } else {
            System.out.println("Não foi possível desserializar o relatório.");
        }
    }

    private static void desserializarRelatorioOcorrenciasPeriodo() {
        System.out.print("Digite o caminho do arquivo (.ser) do relatório de ocorrências por período: ");
        String caminho = scanner.nextLine();
        List<RelatorioOcorrenciaPeriodoVo> lista = SerializarRelatorio.deserializarRelatorioOcorrenciaPeriodo(caminho)
                ;
        if (lista != null) {
            lista.forEach(System.out::println);
        } else {
            System.out.println("Não foi possível desserializar o relatório.");
        }
    }

    private static <T> void listar(List<T> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum item encontrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }
}