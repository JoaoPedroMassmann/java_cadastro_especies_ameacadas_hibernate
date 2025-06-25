package br.edu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgresPU");
        em = emf.createEntityManager();

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
            System.out.println("11. Cadastrar Registro de Ocorrencia");
            System.out.println("12. Alterar Registro de Ocorrencia");
            System.out.println("13. Consultar Registro de Ocorrencia por ID");
            System.out.println("14. Consultar Registros de Ocorrencia por Observador");
            System.out.println("15. Consultar Registro de Ocorrencia por Especie");
            System.out.println("16. Consultar Registro de Ocorrencia por Habitat");
            System.out.print("Escolha uma opção: ");


            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 0 -> continuar = false;
                default -> System.out.println("Opção inválida!");
            }
        }

        em.close();
        emf.close();
        System.out.println("Programa encerrado.");
    }
}