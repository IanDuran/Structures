package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.TreeAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.RedBlackTree;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTree;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTreeNode;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

import java.util.Iterator;

public class DictionaryTests {
    public static void main(String[] args){
        Tree<Integer> tree = new PointerTree<>(new PointerTreeNode<Integer>(1));
        Node<Integer> root = tree.getRoot();
        root.addChild(new PointerTreeNode<>(2));
        root.addChild(new PointerTreeNode<>(3));
        root.addChild(new PointerTreeNode<>(4));
        List<Node<Integer>> rootChildren = root.getChildren();
        rootChildren.get(0).addChild(new PointerTreeNode<>(5));
        rootChildren.get(0).addChild(new PointerTreeNode<>(6));
        rootChildren.get(0).addChild(new PointerTreeNode<>(7));
        rootChildren.get(1).addChild(new PointerTreeNode<>(8));
        rootChildren.get(1).addChild(new PointerTreeNode<>(9));
        rootChildren.get(1).addChild(new PointerTreeNode<>(10));
        rootChildren.get(2).addChild(new PointerTreeNode<>(11));
        rootChildren.get(2).addChild(new PointerTreeNode<>(12));
        rootChildren.get(2).addChild(new PointerTreeNode<>(13));

        List<Node<Integer>> nodeChildren = rootChildren.get(0).getChildren();
        nodeChildren.get(0).addChild(new PointerTreeNode<>(14));
        nodeChildren.get(0).addChild(new PointerTreeNode<>(15));
        nodeChildren.get(0).addChild(new PointerTreeNode<>(16));
        List<Node<Integer>> ln = nodeChildren.get(0).getChildren();
        ln.get(0).addChild(new PointerTreeNode<>(21));
        List<Node<Integer>> path = TreeAlgorithms.getLongestPathFromRootToAnyLeaf(tree);
        //System.out.println(TreeAlgorithms.getHeight(tree));
        List<List<Node<Integer>>> paths = TreeAlgorithms.getPathsFromRootToAnyLeaf(tree);
        for(int i = 0; i < paths.size(); i++){
            for(int j = 0; j < paths.get(i).size(); j++)
                System.out.println(paths.get(i).get(j).getLabel());
            System.out.println();
        }
    }
}
