package ucr.ac.cr.ecci.ci1221.util.algorithm;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Utilitary class that provides searching algorithms for looking for elements in lists.
 *
 * @author Ian Duran
 */
public class SearchAlgorithms{

    /**
     * Searches a list linearly for an object equal to the one passed as a parameter.
     * Iterates over the list passed as parameter until it finds the object or reaches the
     * end of the list.
     * @param list list that is being searched
     * @param key object that is being searched in the list
     * @param <T> class of the objects inside the list, it must implement the Comparable interface.
     * @return the object searched for or null if its not found.
     */
    public static <T extends Comparable<? super T>> T linearSearch(List<T> list, T key){
        T toReturn = null;
        int size = list.size();
        if(key != null && size > 1){
            int index = 0;
            while(toReturn == null && index < size){
                if(key.compareTo(list.get(index)) == 0)
                    toReturn = list.get(index);
                index++;
            }
        }
        return toReturn;
    }

    /**
     * Uses iterative binary search in order to find an object in a sorted list.
     * First it creates a type T object and sets it to null. After that it checks if the
     * object passed as a key is null and if the list has a size greater than one.
     * Then, it sets variables for the lower and upper limits and sets them to 0 and
     * the size of the list minus 1, respectively. Then it goes into a loop until either
     * the value is found or the lower limit gets bigger than the upper one. Inside the loop
     * it creates an int value and sets it to the middle of the sublist that is between the
     * limits. Then it checks if the object in the middle is the one being looked for, if it is,
     * the return variable is set and exits the loop. If it isn't, it checks if the searched value
     * is greater or lesser than the object in the middle. If the middle is greater, the upper limit
     * is set to be the middle minus one. If the middle is lesser, the lower limit is set to be
     * the middle plus one.
     * @param list list that is being searched.
     * @param key object that is being searched in the list.
     * @param <T> class of the objects inside the list, must implement Comparable interface.
     * @return the object that is being searched or null if it isn't found.
     */
    public static <T extends Comparable<? super T>> T binarySearch(List<T> list, T key){
        T toReturn = null;
        if(key != null && list.size() > 1){
            int lowerLimit = 0;
            int upperLimit = list.size() - 1;
            while(toReturn == null && lowerLimit <= upperLimit){
                int middle = (lowerLimit + upperLimit) / 2;
                if(list.get(middle).compareTo(key) == 0){
                    toReturn = list.get(middle);
                }else{
                    if(list.get(middle).compareTo(key) > 0){
                        upperLimit = middle - 1;
                    }else{
                        lowerLimit = middle + 1;
                    }
                }
            }
        }
        return toReturn;
    }
}
