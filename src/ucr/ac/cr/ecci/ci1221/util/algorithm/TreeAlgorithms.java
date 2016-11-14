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
     * Using an auxiliary Queue, it enqueues the root, then it goes in a loop that iterates while the queue is not empty
     * dequeuing the next node and enqueuing all of it's children, finally it inserts the node in the list. When the loop ends
     *, all the Nodes are inside the list in level order.
     * @param tree the tree.
     * @param <T> class stored inside the Tree
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
     * It uses the generalization of the Tree traversal to return the list with all the nodes
     * using the In-Depth-Traversal.
     * @param tree the tree.
     * @param <T> class stored inside the Tree
     * @return a list of the nodes of the tree in traversal order.
     */
    public static <T> List<Node<T>> getInDepthTraversal(Tree<T> tree){
        List<Node<T>> inDepth = null;
        if(!tree.isEmpty()){
            inDepth = new LinkedList<>();
            depthTraversal(inDepth, tree.getRoot());
        }
        return inDepth;
    }

    /**
     * Private recursive method to insert all the Nodes of a Tree using In-Depth-Traversal.
     * First the current Node is added to the list, then it gets it's children, and if the node has any
     * the method calls itself recursively for every child the node has.
     * @param list List containing the Nodes.
     * @param node Current node being traveled.
     * @param <T> class that the Node holds.
     */
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
     * First it check if the Tree is empty, if its not, it fills a list with the longest path from root to
     * any leaf, since this list is backwards, with the root as the last element, it pushes it into a stack
     * and then clears the list and adds every element into the list in order.
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

    /**
     * Private recursive method used to get the longest path from the root to any leaf.
     * First it creates a new List, the it gets the children of the node. If the node has any children,
     * It creates a list by calling itself recursively. If the list is bigger, the path is set to be
     * the new one. It finally adds the node to the path list and returns it. It uses the same logic as the
     * height algorithm.
     * @param node The node being currently traveled.
     * @param <T> The class being stored in the node.
     * @return the longest list that corresponds to the loingest path from the root to any leaf.
     */
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
     * Returns the height of the tree, meaning the length of the longest path from the root
     * to a leaf minus 1.
     * First it checks if the tree is empty. If the tree is not empty, it calls the private height method.
     * @param tree the tree.
     * @param <T> the generic type.
     * @return tree height.
     */
    public static <T> int getHeight(Tree<T> tree){
        int height = 0;
        if(!tree.isEmpty())
            height = height(tree.getRoot()) - 1;
        return height;
    }

    /**
     * Private recursive algorithm to get the height of the Tree.
     * It first starts by declaring an integer that corresponds to the
     * height of the current node. Then, if the node has children, it calls itself recursively
     * for every one of them, the height is set to be the biggest value returned by the calls.
     * @param node Node being currently traveled.
     * @param <T> The class that the Node holds
     * @return the height of the tree.
     */
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
     * It creates a list of lists that contain nodes. Then, it checks if the tree is empty. If its not,
     * it call the private recursive method to get all the paths from the root to the leaves.
     * @param <T> class stored inside the Tree
     * @param tree Tree from which to get the paths
     * @return list of list of nodes.
     */
    public static <T> List<List<Node<T>>> getPathsFromRootToAnyLeaf(Tree<T> tree){
        List<List<Node<T>>> paths = null;
        if(!tree.isEmpty()){
            paths = new LinkedList<>();
            paths(paths, tree.getRoot(), new LinkedList<Node<T>>());
        }
        return paths;
    }

    /**
     * Private recursive method that gets all the paths from the root to the leaves.
     * It receives the current path that is being formed, the current node in the path
     * and the list of the paths. First it adds the node to the current path, then
     * if the node has children if calls itself recursively and add all of its children to a new
     * branch of the current path. If it doesn't have any children, it means it has reached a leaf.
     * In that case, it adds the current path to the list of paths.
     * @param paths The list containing all the paths.
     * @param node Current node being traveled
     * @param currentPath The current branch of the path being formed.
     * @param <T> Class that the node holds.
     */
    private static <T> void paths(List<List<Node<T>>> paths, Node<T> node, List<Node<T>> currentPath){
        currentPath.add(node);
        List<Node<T>> children = node.getChildren();
        if(children != null){
            for(int i = 0; i < children.size(); i++){
                paths(paths, children.get(i), cloneList(currentPath));
            }
        }else
            paths.add(currentPath);
    }

    /**
     * Creates a new List containing the nodes inside the list passed as parameter. It is needed in order to
     * use correctly the method to get all the paths from the root to the leaves. Since in Java the non-primitive types
     * are passed by reference, not by copy, in order to make new branches of a given path it would use the same list over and over again,
     * ruining the algorithm. It clones a given list in order to avoid that.
     * @param otherList The list to be cloned.
     * @param <T> Class that is being stored by the nodes inside the list.
     * @return A new List instance with all the elements from the one passed as parameter.
     */
    private static <T> List<Node<T>> cloneList(List<Node<T>> otherList){
        List<Node<T>> newList = new LinkedList<>();
        for(int i = 0; i < otherList.size(); i++)
            newList.add(otherList.get(i));
        return newList;
    }
}
