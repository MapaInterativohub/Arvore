import java.util.ArrayList; 
import java.util.List;

/**
 * Implementação genérica de uma Árvore Binária de Busca (BST).
 * 
 * - Armazena elementos comparáveis (Comparable<T>)
 * - Suporta inserção, busca, remoção
 * - Suporta percursos (in-ordem, pré-ordem, pós-ordem)
 * - Calcula altura, profundidade
 * - Encontra caminhos e LCA 
 * - Exibe a árvore em ASCII
 */
public class BinaryTree<T extends Comparable<T>> {

    // ============================================================
    //                         CLASSE NODE
    // ============================================================
    /**
     * Representa um nó da árvore.
     * Contém:
     * - valor armazenado
     * - ponteiros para os filhos esquerdo e direito
     */
    public static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root; // raiz da árvore


    // ============================================================
    //                         INSERÇÃO
    // ============================================================
    /**
     * Insere um novo valor na árvore seguindo as regras de BST.
     */
    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    /**
     * Inserção recursiva padrão de árvore binária de busca.
     */
    private Node<T> insertRecursive(Node<T> current, T value) {
        if (current == null)
            return new Node<>(value); // encontrou posição vazia

        if (value.compareTo(current.value) < 0)
            current.left = insertRecursive(current.left, value);  // vai para a esquerda
        else
            current.right = insertRecursive(current.right, value); // vai para a direita

        return current;
    }


    // ============================================================
    //                         BUSCA
    // ============================================================
    /**
     * Retorna true se o valor está presente na árvore.
     */
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) return false;
        if (node.value.equals(value)) return true;

        // decide se busca pela esquerda ou direita
        return (value.compareTo(node.value) < 0)
                ? containsRecursive(node.left, value)
                : containsRecursive(node.right, value);
    }


    // ============================================================
    //                         PERCURSOS
    // ============================================================
    public void inOrder() {
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node<T> node) {
        if (node == null) return;
        inOrderRecursive(node.left);
        System.out.print(node.value + " "); // visita
        inOrderRecursive(node.right);
    }

    public void preOrder() {
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node<T> node) {
        if (node == null) return;
        System.out.print(node.value + " "); // visita
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
    }

    public void postOrder() {
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(Node<T> node) {
        if (node == null) return;
        postOrderRecursive(node.left);
        postOrderRecursive(node.right);
        System.out.print(node.value + " "); // visita
    }


    // ============================================================
    //                         REMOÇÃO
    // ============================================================
    /**
     * Remove um valor da árvore (caso exista).
     */
    public void remove(T value) {
        root = removeRecursive(root, value);
    }

    /**
     * Retorna o menor valor da subárvore (usado ao remover um nó com 2 filhos).
     */
    private T encontrarMenorValor(Node<T> node) {
        while (node.left != null)
            node = node.left;
        return node.value;
    }

    /**
     * Algoritmo completo de remoção em BST:
     * - Caso 1: nó folha → remover direto
     * - Caso 2: nó com 1 filho → substituir pelo filho
     * - Caso 3: nó com 2 filhos → substituir pelo menor da subárvore direita
     */
    private Node<T> removeRecursive(Node<T> current, T value) {
        if (current == null) return null;

        if (value.compareTo(current.value) < 0)
            current.left = removeRecursive(current.left, value);

        else if (value.compareTo(current.value) > 0)
            current.right = removeRecursive(current.right, value);

        else {
            // ---- CASO 1: nó sem filhos ----
            if (current.left == null && current.right == null)
                return null;

            // ---- CASO 2: só 1 filho ----
            if (current.left == null)
                return current.right;

            if (current.right == null)
                return current.left;

            // ---- CASO 3: 2 filhos ----
            T menorValor = encontrarMenorValor(current.right);
            current.value = menorValor; // substitui pelo sucessor
            current.right = removeRecursive(current.right, menorValor);
        }

        return current;
    }


    // ============================================================
    //                       ALTURA DA ÁRVORE
    // ============================================================
    /**
     * Retorna altura da árvore.
     * Altura de árvore vazia = -1
     */
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node<T> node) {
        if (node == null) return -1;

        return 1 + Math.max(
                heightRecursive(node.left),
                heightRecursive(node.right)
        );
    }


    // ============================================================
    //                       PROFUNDIDADE
    // ============================================================
    /**
     * Profundidade (nível) de um valor na árvore.
     */
    public int depth(T value) {
        return depthRecursive(root, value, 0);
    }

    private int depthRecursive(Node<T> node, T value, int level) {
        if (node == null) return -1;
        if (node.value.equals(value)) return level;

        return (value.compareTo(node.value) < 0)
                ? depthRecursive(node.left, value, level + 1)
                : depthRecursive(node.right, value, level + 1);
    }


    // ============================================================
    //                CAMINHO DA RAIZ ATÉ UM VALOR
    // ============================================================
    public List<T> pathTo(T value) {
        List<T> path = new ArrayList<>();
        if (pathRecursive(root, value, path)) return path;
        return new ArrayList<>(); // vazio se não encontrou
    }

    private boolean pathRecursive(Node<T> node, T value, List<T> path) {
        if (node == null) return false;

        path.add(node.value); // adiciona atual ao caminho

        if (node.value.equals(value)) return true;

        // tenta esquerda ou direita
        if (value.compareTo(node.value) < 0) {
            if (pathRecursive(node.left, value, path)) return true;
        } else {
            if (pathRecursive(node.right, value, path)) return true;
        }

        // backtrack
        path.remove(path.size() - 1);
        return false;
    }


    // ============================================================
    //                CAMINHO ENTRE DOIS NÓS
    // ============================================================
    /**
     * Retorna o caminho completo entre dois valores.
     */
    public List<T> pathBetween(T a, T b) {
        List<T> pa = pathTo(a);
        List<T> pb = pathTo(b);

        if (pa.isEmpty() || pb.isEmpty()) return new ArrayList<>();

        int i = 0;

        // procura onde os caminhos começam a divergir
        while (i < pa.size() && i < pb.size() && pa.get(i).equals(pb.get(i))) {
            i++;
        }

        int lcaIndex = i - 1; // índice do LCA (último igual)

        List<T> result = new ArrayList<>();

        // sobe de A até o LCA
        for (int j = pa.size() - 1; j > lcaIndex; j--)
            result.add(pa.get(j));

        // adiciona o LCA
        result.add(pa.get(lcaIndex));

        // desce do LCA até B
        for (int j = lcaIndex + 1; j < pb.size(); j++)
            result.add(pb.get(j));

        return result;
    }


    // ============================================================
    //                LCA (Ancestral Comum Mais Próximo)
    // ============================================================
    public T lowestCommonAncestor(T a, T b) {
        Node<T> lca = lcaRecursive(root, a, b);
        return (lca != null ? lca.value : null);
    }

    private Node<T> lcaRecursive(Node<T> node, T a, T b) {
        if (node == null) return null;

        // ambos estão à esquerda
        if (a.compareTo(node.value) < 0 && b.compareTo(node.value) < 0)
            return lcaRecursive(node.left, a, b);

        // ambos à direita
        if (a.compareTo(node.value) > 0 && b.compareTo(node.value) > 0)
            return lcaRecursive(node.right, a, b);

        // estão em lados diferentes → achou LCA
        return node;
    }


    // ============================================================
    //                      PRINT ASCII
    // ============================================================
    public void printAsciiTree() {
        System.out.println("\nÁRVORE EM ASCII:");
        if (root == null)
            System.out.println("(vazia)");
        else
            TreePrinter.printNode(root);
    }


    // ============================================================
    //                       GETTERS
    // ============================================================
    public Node<T> getRoot() {
        return root;
    }
}
