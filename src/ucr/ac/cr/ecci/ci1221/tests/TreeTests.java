package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.TreeAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTree;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTreeNode;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

import java.util.Iterator;

public class TreeTests {

    public Tree<Integer> makeTree(){
        Tree<Integer> tree = new PointerTree<>(new PointerTreeNode<>(1));
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
        ln.get(2).addChild(new PointerTreeNode<>(21));
        return tree;
    }

    public void treeAlgorithmsTest(){
        Tree<Integer> t = this.makeTree();
        System.out.println("Because of the way the nodes were arranged, the level traversal should appear in increasing order");
        List<Node<Integer>> levelTraversal = TreeAlgorithms.getLevelTraversal(t);
        for(int i = 0; i < levelTraversal.size(); i++)
            System.out.println(levelTraversal.get(i).getLabel());

        System.out.println();

        System.out.println("In depth traversal");
        List<Node<Integer>> depthTraversal = TreeAlgorithms.getInDepthTraversal(t);
        for(int i = 0; i < depthTraversal.size(); i++)
            System.out.println(depthTraversal.get(i).getLabel());

        System.out.println();
        System.out.println("Showing all paths from the root to any leaf");
        List<List<Node<Integer>>> allPaths = TreeAlgorithms.getPathsFromRootToAnyLeaf(t);
        for(int i = 0; i < allPaths.size(); i++){
            for(int j = 0; j < allPaths.get(i).size(); j++)
                System.out.print(allPaths.get(i).get(j).getLabel() + " ");
            System.out.println();
        }

        System.out.println();
        System.out.println("Height should be 4: " + TreeAlgorithms.getHeight(t));
        System.out.println();
        System.out.println("Longest path from root to any leaf should contain nodes 1, 2, 5, 16 and 21");
        List<Node<Integer>> longestPath = TreeAlgorithms.getLongestPathFromRootToAnyLeaf(t);
        Iterator<Node<Integer>> i = longestPath.iterator();
        while(i.hasNext())
            System.out.println(i.next().getLabel());
        System.out.println();

    }

    public static void main(String... args){
        TreeTests t = new TreeTests();
        t.treeAlgorithmsTest();
    }
}
