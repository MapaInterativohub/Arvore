public class TreePrinter {

    public static <T extends Comparable<T>> void printTree(BinaryTree<T> tree) {
        printNode(tree.getRoot());
    }

    public static <T> void printNode(BinaryTree.Node<T> root) {
        print("", root, false);
    }

    private static <T> void print(String prefix, BinaryTree.Node<T> node, boolean isLeft) {
        if (node == null) return;

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.value);

        print(prefix + (isLeft ? "│   " : "    "), node.left, true);
        print(prefix + (isLeft ? "│   " : "    "), node.right, false);
    }
}
