import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

    // ====== NÓ DA ÁRVORE ======
    public static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;

    // ====== INSERÇÃO ======
    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    private Node<T> insertRecursive(Node<T> current, T value) {
        if (current == null)
            return new Node<>(value);

        if (value.compareTo(current.value) < 0)
            current.left = insertRecursive(current.left, value);
        else
            current.right = insertRecursive(current.right, value);

        return current;
    }

    // ====== BUSCA ======
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) return false;
        if (node.value.equals(value)) return true;

        return (value.compareTo(node.value) < 0)
                ? containsRecursive(node.left, value)
                : containsRecursive(node.right, value);
    }

    // ====== PERCURSOS ======
    public void inOrder() {
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node<T> node) {
        if (node == null) return;
        inOrderRecursive(node.left);
        System.out.print(node.value + " ");
        inOrderRecursive(node.right);
    }

    public void preOrder() {
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node<T> node) {
        if (node == null) return;
        System.out.print(node.value + " ");
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
        System.out.print(node.value + " ");
    }

    // ====== REMOÇÃO ======
    public void remove(T value) {
        root = removeRecursive(root, value);
    }

    private T encontrarMenorValor(Node<T> node) {
        while (node.left != null)
            node = node.left;
        return node.value;
    }

    private Node<T> removeRecursive(Node<T> current, T value) {
        if (current == null) return null;

        if (value.compareTo(current.value) < 0)
            current.left = removeRecursive(current.left, value);

        else if (value.compareTo(current.value) > 0)
            current.right = removeRecursive(current.right, value);

        else {
            if (current.left == null)
                return current.right;
            if (current.right == null)
                return current.left;

            T menorValor = encontrarMenorValor(current.right);
            current.value = menorValor;
            current.right = removeRecursive(current.right, menorValor);
        }

        return current;
    }

    // ====== ALTURA ======
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

    // ====== PROFUNDIDADE ======
    public int depth(T value) {
        return depthRecursive(root, value, 0);
    }

    private int depthRecursive(Node<T> node, T value, int level) {
        if (node == null) return -1;
        if (node.value.equals(value)) return level;

        if (value.compareTo(node.value) < 0)
            return depthRecursive(node.left, value, level + 1);

        return depthRecursive(node.right, value, level + 1);
    }

    // ====== CAMINHO ATÉ UM NÓ ======
    public List<T> pathTo(T value) {
        List<T> path = new ArrayList<>();
        if (pathRecursive(root, value, path)) return path;
        return new ArrayList<>();
    }

    private boolean pathRecursive(Node<T> node, T value, List<T> path) {
        if (node == null) return false;

        path.add(node.value);

        if (node.value.equals(value)) return true;

        if (value.compareTo(node.value) < 0) {
            if (pathRecursive(node.left, value, path)) return true;
        } else {
            if (pathRecursive(node.right, value, path)) return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    // ====== CAMINHO ENTRE DOIS NÓS ======
    public List<T> pathBetween(T a, T b) {
        List<T> pa = pathTo(a);
        List<T> pb = pathTo(b);

        if (pa.isEmpty() || pb.isEmpty()) return new ArrayList<>();

        int i = 0;
        while (i < pa.size() && i < pb.size() && pa.get(i).equals(pb.get(i))) {
            i++;
        }

        int lcaIndex = i - 1;

        List<T> result = new ArrayList<>();

        for (int j = pa.size() - 1; j > lcaIndex; j--)
            result.add(pa.get(j));

        result.add(pa.get(lcaIndex));

        for (int j = lcaIndex + 1; j < pb.size(); j++)
            result.add(pb.get(j));

        return result;
    }

    // ====== LCA ======
    public T lowestCommonAncestor(T a, T b) {
        Node<T> lca = lcaRecursive(root, a, b);
        return (lca != null ? lca.value : null);
    }

    private Node<T> lcaRecursive(Node<T> node, T a, T b) {
        if (node == null) return null;

        if (a.compareTo(node.value) < 0 && b.compareTo(node.value) < 0)
            return lcaRecursive(node.left, a, b);

        if (a.compareTo(node.value) > 0 && b.compareTo(node.value) > 0)
            return lcaRecursive(node.right, a, b);

        return node;
    }

    // ====== PRINT ASCII ======
    public void printAsciiTree() {
        System.out.println("\nÁRVORE EM ASCII:");
        if (root == null)
            System.out.println("(vazia)");
        else
            TreePrinter.printNode(root);
    }

    // Permite que outras classes acessem a raiz
    public Node<T> getRoot() {
        return root;
    }
}
