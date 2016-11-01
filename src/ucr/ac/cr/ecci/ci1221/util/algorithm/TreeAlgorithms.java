package ucr.ac.cr.ecci.ci1221.util.algorithm;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.LinkedListQueue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.Queue;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.LinkedListStack;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.Stack;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

/**
 * Utilitary class that provides algorithms for trees.
 * @author Ian Duran.
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
        List<Node<T>> level = new LinkedList<>();
        Queue<Node<T>> queue = new LinkedListQueue<>();
        queue.enqueue(tree.getRoot());
        while(!queue.isEmpty()){
            Node<T> node = queue.dequeue();
            List<Node<T>> children = node.getChildren();
            if(children != null){
                for(int i = 0; i < children.size(); i++)
                    queue.enqueue(children.get(i));
            }
            level.add(node);
        }
        return level;
    }

    /**
     * Returns a lists of nodes ordered by doing a in depth traversal order of the given tree.
     *
     * @param tree the tree.
     *
     * @return a list of the nodes of the tree in traversal order.
     */
    public static <T> List<Node<T>> getInDepthTraversal(Tree<T> tree){
        List<Node<T>> inDepth = null;
        if(!tree.isEmpty()){
            inDepth = new LinkedList<Node<T>>();
            depthTraversal(inDepth, tree.getRoot());
        }
        return inDepth;
    }

    private static <T> void depthTraversal(List<Node<T>> list, Node<T> node){
        list.add(node);
        List<Node<T>> children = node.getChildren();
        if(children != null){
            for(int i = 0; i < children.size(); i++)
                depthTraversal(list, children.get(i));
        }
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
        List<Node<T>> longestPath = null;
        if(!tree.isEmpty()){
            longestPath = longestPath(tree.getRoot());
            Stack<Node<T>> stack = new LinkedListStack<>();
            for(int i = 0; i < longestPath.size(); i++)
                stack.push(longestPath.get(i));
            longestPath.clear();
            while(!stack.isEmpty())
                longestPath.add(stack.pop());
        }
        return longestPath;
    }

    private static <T> List<Node<T>> longestPath(Node<T> node){
        List<Node<T>> path = new LinkedList<>();
        List<Node<T>> children = node.getChildren();
        if(children != null){
            for(int i = 0; i < children.size(); i++){
                List<Node<T>> prospectiveList = longestPath(children.get(i));
                if(path.size() < prospectiveList.size())
                    path = prospectiveList;
            }
        }
        path.add(node);
        return path;
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
        int height = 0;
        if(!tree.isEmpty())
            height = height(tree.getRoot()) - 1;
        return height;
    }

    private static <T> int height(Node<T> node){
        int height = 0;
        List<Node<T>> children = node.getChildren();
        if(children != null){
            for(int i = 0; i < children.size(); i++){
                int prospectiveHeight = height(children.get(i));
                if(height < prospectiveHeight)
                    height = prospectiveHeight;
            }
        }
        height++;
        return height;
    }

    /**
     * A list of list of nodes representing all the possible paths from the root to each node of the tree.
     *
     * @return list of list of nodes.
     */
    public static <T> List<List<Node<T>>> getPathsFromRootToAnyLeaf(Tree<T> tree){
        return null;
    }

    private static <T> List<List<Node<T>>> paths(Node<T> node, List<Node<T>> currentPath){
        List<List<Node<T>>> paths = new LinkedList<>();
        return paths;
    }
}
