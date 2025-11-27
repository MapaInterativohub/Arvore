import java.util.List;

public class Main {

    private static void titulo(String txt) {
        System.out.println("\n========== " + txt + " ==========");
    }

    public static void main(String[] args) {

        titulo("CRIANDO ÁRVORE");
        BinaryTree<Person> tree = new BinaryTree<>();

        tree.insert(new Person("Maria", 30));
        tree.insert(new Person("Ana", 25));
        tree.insert(new Person("Carlos", 40));
        tree.insert(new Person("Beatriz", 20));
        tree.insert(new Person("Daniel", 45));

        titulo("PERCURSOS");

        System.out.println("In-ordem:");
        tree.inOrder();

        System.out.println("\nPré-ordem:");
        tree.preOrder();

        System.out.println("\nPós-ordem:");
        tree.postOrder();

        titulo("DESENHO DA ARVORE");
        tree.printAsciiTree();

        titulo("BUSCAS");

        System.out.println("Contém Ana? " + tree.contains(new Person("Ana", 25)));
        System.out.println("Contém João? " + tree.contains(new Person("João", 50)));

        titulo("REMOÇÕES");

        System.out.println("Removendo Carlos...");
        tree.remove(new Person("Carlos", 40));
        tree.printAsciiTree();

        System.out.println("Removendo Maria (raiz)...");
        tree.remove(new Person("Maria", 30));
        tree.printAsciiTree();

        titulo("ALTURA E PROFUNDIDADE");

        System.out.println("Altura da árvore: " + tree.height());
        System.out.println("Profundidade de Ana: " + tree.depth(new Person("Ana", 25)));
        System.out.println("Profundidade de João (não existe): " + tree.depth(new Person("João", 50)));

        titulo("CAMINHO ATÉ BEATRIZ");

        List<Person> caminho = tree.pathTo(new Person("Beatriz", 20));
        System.out.println(caminho);

        titulo("CAMINHO ENTRE ANA E DANIEL");
        List<Person> caminhoAB = tree.pathBetween(new Person("Ana", 25), new Person("Daniel", 45));
        System.out.println(caminhoAB);

        titulo("LCA ENTRE BEATRIZ E DANIEL");

        Person lca = tree.lowestCommonAncestor(
            new Person("Beatriz", 20),
            new Person("Daniel", 45)
        );

        System.out.println("LCA = " + lca);
    }
}
