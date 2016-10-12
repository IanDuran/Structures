package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.domain.Song;
import ucr.ac.cr.ecci.ci1221.util.algorithm.SearchAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.algorithm.SortingAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.DoubleLinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

import java.util.Iterator;

/**
 * An example of a test case. Each student has to write his own
 * in different classes, for each data structure and all operators and document them.
 */
public class Main {

    public static void main(String[] args) {
	    List<Song> list = new LinkedList<Song>();

        System.out.println("Size should be zero: " + list.size() + " and be empty: " + list.isEmpty());

        Song bohemianRhapsody = new Song("Bohemian Rhapsody", 367);

        list.add(new Song("The Show Must Go On", 262));
        list.add(bohemianRhapsody);
        list.add(new Song("Radio Ga Ga", 356));
        list.add(new Song("It's a Hard Life", 254));

        System.out.println("Size should be 4: " + list.size() + " and be empty false: " + list.isEmpty());

        Song searchedSong = SearchAlgorithms.linearSearch(list, bohemianRhapsody);
        System.out.println("The song retrieved should be the same: " + bohemianRhapsody.equals(searchedSong));

        list.remove(0);
        System.out.println("Size should be 3: " + list.size() + " and be empty false: " + list.isEmpty());

        Song song = list.get(0);
        System.out.println("The song retrieved should be Bohemian Rhapsody: " + bohemianRhapsody.equals(song));

        Iterator<Song> iterator = list.iterator();
        while(iterator.hasNext()){
            Song element = iterator.next();
            System.out.println("Song name: " + element.getTitle() + " \tDuration: " + element.getSeconds());
        }

        SortingAlgorithms.bubbleSort(list);
        System.out.println("The song retrieved should be It's a Hard Life: " + list.get(0).getTitle());

        list.clear();
        System.out.println("Size should be zero: " + list.size() + " and be empty: " + list.isEmpty());
    }
}
