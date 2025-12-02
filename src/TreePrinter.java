public class TreePrinter {

    /**
     * Imprime uma árvore inteira a partir do seu objeto BinaryTree.
     * Usa a raiz da árvore como ponto inicial.
     */
    public static <T extends Comparable<T>> void printTree(BinaryTree<T> tree) {
        printNode(tree.getRoot());
    }

    /**
     * Imprime a árvore iniciando por um nó específico.
     * 
     */
    public static <T> void printNode(BinaryTree.Node<T> root) {
        // Chama a função recursiva com prefixo vazio e indicando que
        // o nó raiz não é "esquerda" nem "direita".
        print("", root, false);
    }

    /**
     * Função recursiva que imprime cada nó da árvore em estilo ASCII.
     *
     * @param prefix  Prefixo visual que define o espaçamento e os ramos anteriores.
     * @param node    Nó atual a ser impresso.
     * @param isLeft  Indica se o nó atual é filho da esquerda.
     */
    private static <T> void print(String prefix, BinaryTree.Node<T> node, boolean isLeft) {
        // Caso base: se o nó é nulo, nada é impresso.
        if (node == null) return;

        // Imprime o prefixo + o símbolo correto + o valor do nó.
        // ├── é usado para nó da esquerda
        // └── é usado para nó da direita
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.value);

        // Chamada recursiva para o filho esquerdo.
        // Se é da esquerda, adiciona "│   " para mostrar continuidade.
        // Se é da direita, adiciona "    " para manter o alinhamento.
        print(prefix + (isLeft ? "│   " : "    "), node.left, true);

        // Chamada recursiva para o filho direito.
        print(prefix + (isLeft ? "│   " : "    "), node.right, false);
    }
}
