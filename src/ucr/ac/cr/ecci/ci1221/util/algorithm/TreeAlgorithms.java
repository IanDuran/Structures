package ucr.ac.cr.ecci.ci1221.util.algorithm;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

/**
 * Utilitary class that provides algorithms for trees.
 *
 * @TODO Complete code implementations and javadoc.
 *
 * @author Student name.
 */
public class TreeAlgorithms {

    /**
     * Returns a list of the nodes of the tree obtained by traversing the tree by level.
     *
     * @param tree the tree.
     *
     * @return a list of the nodes of the tree in level order.
     */
    public static <T> List<Node<T>> getLevelTraversal(Tree<T> tree){
        return null;
    }

    /**
     * Returns a lists of nodes ordered by doing a in depth traversal order of the given tree.
     *
     * @param tree the tree.
     *
     * @return a list of the nodes of the tree in traversal order.
     */
    public static <T> List<Node<T>> getInDepthTraversal(Tree<T> tree){
        return null;
    }

    /**
     * Returns a list of nodes of the longest path from the root to the leaf.
     *
     * @param tree the tree.
     *
     * @param <T> the generic type.
     *
     * @return a list of nodes.
     */
    public static <T> List<Node<T>> getLongestPathFromRootToAnyLeaf(Tree<T> tree){
        return null;
    }

    /**
     * Returns the heigh of the tree, meaning the length of the longest path from the root
     * to a leaf minus 1.
     *
     * @param tree the tree.
     * @param <T> the generic type.
     *
     * @return tree height.
     */
    public static <T> int getHeight(Tree<T> tree){
        return 0;
    }

    /**
     * A list of list of nodes representing all the possible paths from the root to each node of the tree.
     *
     * @return list of list of nodes.
     */
    public static <T> List<List<Node<T>>> getPathsFromRootToAnyLeaf(Tree<T> tree){
        return null;
    }
}
