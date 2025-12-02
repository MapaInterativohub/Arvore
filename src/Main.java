import java.util.List;

public class Main {

    /**
     * Função auxiliar para imprimir títulos organizados no console.
     * Apenas facilita a visualização das seções da execução.
     */
    private static void titulo(String txt) {
        System.out.println("\n========== " + txt + " ==========");
    }

    public static void main(String[] args) {

        // ==================== CRIAÇÃO DA ÁRVORE ====================
        titulo("CRIANDO ÁRVORE");

        // Cria uma árvore binária que armazenará objetos Person.
        BinaryTree<Person> tree = new BinaryTree<>();

        // Inserção de elementos na árvore.
        // Cada inserção é ordenada usando compareTo(Person).
        tree.insert(new Person("Maria", 30));
        tree.insert(new Person("Ana", 25));
        tree.insert(new Person("Carlos", 40));
        tree.insert(new Person("Beatriz", 20));
        tree.insert(new Person("Daniel", 45));

        // ==================== PERCURSOS ====================
        titulo("PERCURSOS");

        System.out.println("In-ordem:");
        tree.inOrder(); // imprime esquerda → raiz → direita

        System.out.println("\nPré-ordem:");
        tree.preOrder(); // imprime raiz → esquerda → direita

        System.out.println("\nPós-ordem:");
        tree.postOrder(); // imprime esquerda → direita → raiz

        // ==================== ASCII TREE ====================
        titulo("DESENHO DA ARVORE");
        tree.printAsciiTree(); // mostra a árvore usando caracteres ASCII

        // ==================== BUSCAS ====================
        titulo("BUSCAS");

        // Busca baseada em comparação (compareTo + equals)
        System.out.println("Contém Ana? " + tree.contains(new Person("Ana", 25)));
        System.out.println("Contém João? " + tree.contains(new Person("João", 50)));

        // ==================== REMOÇÕES ====================
        titulo("REMOÇÕES");

        System.out.println("Removendo Carlos...");
        tree.remove(new Person("Carlos", 40));
        tree.printAsciiTree(); // imprime árvore após remoção

        System.out.println("Removendo Maria (raiz)...");
        tree.remove(new Person("Maria", 30));
        tree.printAsciiTree(); // imprime árvore após nova remoção

        // ==================== ALTURA / PROFUNDIDADE ====================
        titulo("ALTURA E PROFUNDIDADE");

        System.out.println("Altura da árvore: " + tree.height());
        System.out.println("Profundidade de Ana: " +
                tree.depth(new Person("Ana", 25)));
        System.out.println("Profundidade de João (não existe): " +
                tree.depth(new Person("João", 50)));

        // ==================== CAMINHO ATÉ UM NÓ ====================
        titulo("CAMINHO ATÉ BEATRIZ");

        List<Person> caminho = tree.pathTo(new Person("Beatriz", 20));
        System.out.println(caminho); // imprime o caminho raiz → Beatriz

        // ==================== CAMINHO ENTRE DOIS NÓS ====================
        titulo("CAMINHO ENTRE ANA E DANIEL");

        List<Person> caminhoAB = tree.pathBetween(
                new Person("Ana", 25),
                new Person("Daniel", 45)
        );
        System.out.println(caminhoAB);

        // ==================== LCA ====================
        titulo("LCA ENTRE BEATRIZ E DANIEL");

        // Lowest Common Ancestor (menor ancestral comum)
        Person lca = tree.lowestCommonAncestor(
                new Person("Beatriz", 20),
                new Person("Daniel", 45)
        );

        System.out.println("LCA = " + lca);
    }
}
