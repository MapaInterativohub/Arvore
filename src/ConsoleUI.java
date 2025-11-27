import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BinaryTree<Person> tree = new BinaryTree<>();

    public static void main(String[] args) {
        iniciarMenu();
    }

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

            int op = lerInt();

            switch (op) {
                case 1 -> inserir();
                case 2 -> buscar();
                case 3 -> remover();
                case 4 -> percursos();
                case 5 -> TreePrinter.printTree(tree);
                case 6 -> System.out.println("Altura: " + tree.height());
                case 7 -> profundidade();
                case 8 -> caminhoAte();
                case 9 -> caminhoEntre();
                case 0 -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // -------------------- Funções do menu --------------------

    private static void inserir() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = lerInt();

        tree.insert(new Person(nome, idade));
        System.out.println("Pessoa inserida!");
    }

    private static void buscar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
       
        int idade = lerInt();

        System.out.println("Encontrado: " + tree.contains(new Person(nome, idade)));
    }

    private static void remover() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = lerInt();

        tree.remove(new Person(nome, idade));
        System.out.println("Remoção concluída (se existia).");
    }

    private static void percursos() {
        System.out.println("\nIn-ordem:");
        tree.inOrder();

        System.out.println("\nPré-ordem:");
        tree.preOrder();

        System.out.println("\nPós-ordem:");
        tree.postOrder();
    }

    private static void profundidade() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = lerInt();

        System.out.println("Profundidade: " + tree.depth(new Person(nome, idade)));
    }

    private static void caminhoAte() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = lerInt();

        List<Person> caminho = tree.pathTo(new Person(nome, idade));
        System.out.println(caminho);
    }

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
