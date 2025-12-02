import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    // Scanner para entrada de dados pelo usuário
    private static final Scanner scanner = new Scanner(System.in);

    // Árvore binária que armazena objetos Person
    private static final BinaryTree<Person> tree = new BinaryTree<>();

    public static void main(String[] args) {
        iniciarMenu(); // Inicia o menu principal
    }

    // -------------------- MENU PRINCIPAL --------------------

    /**
     * Exibe o menu e controla o loop principal do programa.
     */
    public static void iniciarMenu() {
        while (true) {
            System.out.println("\n========== MENU DA ÁRVORE ==========");
            System.out.println("1 - Inserir pessoa");
            System.out.println("2 - Buscar pessoa");
            System.out.println("3 - Remover pessoa");
            System.out.println("4 - Exibir percursos");
            System.out.println("5 - Desenhar árvore ");
            System.out.println("6 - Altura da árvore");
            System.out.println("7 - Profundidade de um nó");
            System.out.println("8 - Caminho até um nó");
            System.out.println("9 - Caminho entre dois nós");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int op = lerInt(); // Lê opção do usuário

            // Execução conforme a opção escolhida
            switch (op) {
                case 1 -> inserir();
                case 2 -> buscar();
                case 3 -> remover();
                case 4 -> percursos();
                case 5 -> TreePrinter.printTree(tree); // Imprime árvore graficamente
                case 6 -> System.out.println("Altura: " + tree.height());
                case 7 -> profundidade();
                case 8 -> caminhoAte();
                case 9 -> caminhoEntre();
                case 0 -> {
                    System.out.println("Encerrando...");
                    return; // Sai do programa
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // -------------------- Funções do menu --------------------

    /**
     * Lê dados de uma pessoa e insere na árvore.
     */
    private static void inserir() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInt();

        tree.insert(new Person(nome, idade)); // Insere na árvore
        System.out.println("Pessoa inserida!");
    }

    /**
     * Busca uma pessoa na árvore.
     */
    private static void buscar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInt();

        System.out.println("Encontrado: " + tree.contains(new Person(nome, idade)));
    }

    /**
     * Remove uma pessoa da árvore.
     */
    private static void remover() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInt();

        tree.remove(new Person(nome, idade));
        System.out.println("Remoção concluída (se existia).");
    }

    /**
     * Exibe as três travessias clássicas da árvore:
     * in-order, pre-order e post-order.
     */
    private static void percursos() {
        System.out.println("\nIn-ordem:");
        tree.inOrder();

        System.out.println("\nPré-ordem:");
        tree.preOrder();

        System.out.println("\nPós-ordem:");
        tree.postOrder();
    }

    /**
     * Exibe a profundidade de um nó específico.
     */
    private static void profundidade() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInt();

        System.out.println("Profundidade: " + tree.depth(new Person(nome, idade)));
    }

    /**
     * Mostra o caminho da raiz até o nó desejado.
     */
    private static void caminhoAte() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInt();

        List<Person> caminho = tree.pathTo(new Person(nome, idade));
        System.out.println(caminho);
    }

    /**
     * Mostra o caminho entre dois nós da árvore.
     */
    private static void caminhoEntre() {

        System.out.println("Primeira pessoa:");
        System.out.print("Nome: ");
        String n1 = scanner.nextLine();
        System.out.print("Idade: ");
        int i1 = lerInt();

        System.out.println("Segunda pessoa:");
        System.out.print("Nome: ");
        String n2 = scanner.nextLine();
        System.out.print("Idade: ");
        int i2 = lerInt();

        System.out.println(tree.pathBetween(new Person(n1, i1), new Person(n2, i2)));
    }

    // -------------------- Leitura segura --------------------

    /**
     * Lê um inteiro do usuário com validação contra valores inválidos.
     */
    private static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}
