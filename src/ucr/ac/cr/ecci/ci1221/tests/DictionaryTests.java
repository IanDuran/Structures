package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.TreeAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.RedBlackTree;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Trie;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTree;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTreeNode;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

import java.util.Iterator;

public class DictionaryTests {
    public static void main(String[] args){
        /*Tree<Integer> tree = new PointerTree<>(new PointerTreeNode<Integer>(1));
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
        List<Node<Integer>> path = TreeAlgorithms.getLongestPathFromRootToAnyLeaf(tree);
        for(int i = 0; i < path.size(); i++)
            System.out.println(path.get(i).getLabel());*/
        Trie t = new Trie();
        Trie t2 = new Trie();
        t.put("car");
        t.put("cat");
        t.put("abomination");
        t.put("abomasnow");
        t2.put("cat");
        t2.put("blasphemy");
        t2.put("blasphemous");
        System.out.println(t.isMember("cars"));
        Set<String> difference = t.intersection(t2);
        Iterator<String> i = difference.iterator();
        while(i.hasNext())
            System.out.println(i.next());
    }
}
