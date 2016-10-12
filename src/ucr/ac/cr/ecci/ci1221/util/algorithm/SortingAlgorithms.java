package ucr.ac.cr.ecci.ci1221.util.algorithm;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * Utilitary class that provides sorting algorithms for sorting elements in lists.
 *
 * @author Ian Duran
 */
public class SortingAlgorithms {

    /**
     * Sorts a list using Bubble Sort.
     * What Bubble Sort does is that for every pair of elements in the list, if the first is greater than the second,
     * it swaps them. Since in every iteration the greatest element of the list ends up in the last position
     * the outer loop shrinks the range of indexes that need to be checked by one each iteration.
     * @param list list to be sorted with Bubble Sort
     * @param <T> class of the objects inside the list, the class needs to implement the Comparable interface
     */
    public static <T extends Comparable<? super T>> void bubbleSort(List<T> list){
        int size = list.size();
        if(size > 1){
            for(int i = size - 1; i >= 0; i--){
                for(int j = 0; j < i; j++){
                    if(list.get(j).compareTo(list.get(j + 1)) > 0){
                        swap(list, j, j + 1);
                    }
                }
            }
        }
    }

    /**
     * Sorts a list using Insertion Sort.
     * What insertion sort does is that it separates the list in two parts, the sorted part and the unsorted part.
     * In every iteration it augments the sorted part by one, the new item in the sorted part is then swapped backwards until there are
     * no more greater elements than it, assuring that the sorted part remains sorted. That is done until the sorted part size is the same as
     * the list's size.
     * @param list list to be sorted using Insertion Sort
     * @param <T> class of the objects inside the list, the class needs to implement the Comparable interface
     */
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list){
        int size = list.size();
        if(size > 1){
            for(int i = 0; i < size; i++){
                int sortedSideIndex = i;
                while(sortedSideIndex > 0 && list.get(sortedSideIndex - 1).compareTo(list.get(sortedSideIndex)) > 0){
                    swap(list, sortedSideIndex, sortedSideIndex - 1);
                    sortedSideIndex--;
                }
            }
        }
    }

    /**
     * Sorts a list using Selection Sort.
     * Selection Sort has two cycles. The outer one iterates over the whole list. Inside it, the current index is stored as the lowest one,
     * then it iterates over the elements after that one, if a lower element is found, its index is then stored as the lowest one.
     * After it gets the index of the lowest item, it then gets swapped with the one in the current index of the outer loop.
     * When the two loops are finished, the list is sorted.
     * @param list list to be sorted using Selection Sort
     * @param <T> class of the objects inside the list, the class needs to implement the Comparable interface
     */
    public static <T extends Comparable<? super T>> void selectionSort(List<T> list){
        int size = list.size();
        if(size > 1){
            for(int i = 0; i < size; i++){
                int lowestIndex = i;
                for(int j = i; j < size; j++){
                    if(list.get(lowestIndex).compareTo(list.get(j)) > 0){
                        lowestIndex = j;
                    }
                }
                if(lowestIndex != i){
                    swap(list, i, lowestIndex);
                }
            }
        }
    }

    /**
     * Calls the recursive Quicksort with the default parameters needed
     * @param list list to be sorted using Quicksort
     * @param <T> class of the objects inside the list, the class needs to implement the Comparable interface
     */
    public static <T extends Comparable<? super T>> void quickSort(List<T> list){
        if(list.size() > 1)
            quickSort(list, 0, list.size() - 1);
    }

    /**
     * Sorts a list using Quicksort.
     * What Quicksort does is, first to check if the sublist has more than one element, if it has only one, that part is considered sorted.
     * The chosen pivot is the last element in the list.
     * If it has more than one element, it sets two ints, i and j to be equal to the first index of the sublist and the last one respectively.
     * Then it goes into a loop that iterates while i is less or equal to j. In the loop, first it augments i by one until it reaches the index
     * of an element that is greater than the pivot, then it decrements j until it reaches an element lesser than the pivot, after that
     * it checks if i is not equal to j, if the condition is true, the elements in i and j are swapped and i is augmented if its different
     * to the beginIndex variable (because without that check, i never reaches the value of the element in beginIndex if it needs to be swapped),
     * j is decremented if its different to the finishIndex variable. Finally, when i is greater than j,
     * if i is lesser than the last element index, it swaps the element in the finishIndex with the element in i and calls itself recursively
     * with changed values.
     * @param list list that needs to be sorted
     * @param beginIndex first valid index in the list or sublist that will be sorted
     * @param finishIndex last valid index in the list or sublist that will be sorted
     * @param <T> class of the objects inside the list, the class needs to implement the Comparable interface
     */
    private static <T extends Comparable<? super T>> void quickSort(List<T> list, int beginIndex, int finishIndex){
        if(beginIndex + 1 <= finishIndex){
            int i = beginIndex;
            int j = finishIndex;
            while(i <= j){
                while(list.get(i).compareTo(list.get(finishIndex)) < 0 && i < finishIndex){
                    i++;
                }
                while(list.get(j).compareTo(list.get(finishIndex)) >= 0 && j > beginIndex){
                    j--;
                }
                if(i <= j) {
                    if(i != j)
                        swap(list, i, j);
                    if(i != beginIndex)
                        i++;
                    if(j != finishIndex)
                        j--;
                }
            }
            if(i < finishIndex)
                swap(list, i, finishIndex);

            quickSort(list, beginIndex, i - 1);
            quickSort(list, i + 1, finishIndex);
        }
    }

    /**
     * Calls the private mergeSort method with the default parameters needed.
     * @param list list of objects to be sorted.
     * @param <T> type of the objects stored in the list, must implement Comparable interface
     */
    public static <T extends Comparable<? super T>> void mergeSort(List<T> list){
        if(list.size() > 1){
            mergeSort(list, 0, (list.size()/2), list.size() - 1, (T[])new Comparable[list.size()]);
        }
    }

    /**
     * Makes the recursive Merge Sort calls and the Merge method call. First it check if the sublists
     * that need to be ordered are of size 1. If they are, they are then merged, if they are not, it calls itself recursively.
     * After the recursion, when its sublists are ordered, it merges them if the beginning index is lesser then the finish one, since sublists
     * don't need to be ordered if they are of size 0.
     * @param list list of objects that need to be sorted.
     * @param begin first index of the sublist that is being sorted.
     * @param mid middle index if the sublist that is being sorted.
     * @param finish last index of the sublist that is being sorted.
     * @param auxiliaryArray auxiliary array needed in order to store temporary values when the merge method is called
     * @param <T> type of the objects in the list that is being sorted.
     */
    private static <T extends Comparable<? super T>> void mergeSort(List<T> list, int begin, int mid, int finish, T[] auxiliaryArray){
        if(begin + 1 == finish)
            merge(list, begin, mid, mid + 1, finish, auxiliaryArray);
        else{
            if(begin < finish){
                mergeSort(list, begin, (mid + begin)/2, mid, auxiliaryArray);
                mergeSort(list, mid + 1, (finish + mid)/2, finish, auxiliaryArray);
                merge(list, begin, mid, mid + 1, finish, auxiliaryArray);
            }
        }
    }

    /**
     * Private method used to merge two parts of a given list.
     * For it to work, both sublists must be sorted.
     * First it creates indexes to navigate through the sublists and one to navigate through the
     * auxiliary array of Comparable objects. Then, it goes into a loop that iterates until one of the sublists is
     * completely inside the auxiliary array. The loop check which of the current elements in the sublists is lesser
     * and adds it to the auxiliary array so the elements inside it are in order. After that it adds any element that
     * was left outside the array. Finally, it changes the values of the elements in the list so that it matches
     * the order of the ones in the auxiliary array.
     * @param list list that is being sorted.
     * @param firstBegin first index of the first sublist that will be merged.
     * @param firstFinish last index of the first sublist that will be merged.
     * @param secondBegin first index of the second sublist that will be merged.
     * @param secondFinish last index of the second sublist that will be merged.
     * @param auxiliaryArray auxiliary array used to store the objects of the merged sublists.
     * @param <T> class of the objects stored in the list, must implement Comparable interface.
     */
    private static <T extends Comparable<? super T>> void merge(List<T> list, int firstBegin, int firstFinish, int secondBegin, int secondFinish, T[] auxiliaryArray){
        int firstIndex = firstBegin;
        int secondIndex = secondBegin;
        int auxiliaryIndex = 0;
        while(firstIndex <= firstFinish && secondIndex <= secondFinish){
            if(list.get(firstIndex).compareTo(list.get(secondIndex)) <= 0){
                auxiliaryArray[auxiliaryIndex] = list.get(firstIndex);
                firstIndex++;
            }else{
                auxiliaryArray[auxiliaryIndex] = list.get(secondIndex);
                secondIndex++;
            }
            auxiliaryIndex++;
        }
        while(firstIndex <= firstFinish){
            auxiliaryArray[auxiliaryIndex] = list.get(firstIndex);
            firstIndex++;
            auxiliaryIndex++;
        }
        while(secondIndex <= secondFinish){
            auxiliaryArray[auxiliaryIndex] = list.get(secondIndex);
            secondIndex++;
            auxiliaryIndex++;
        }
        auxiliaryIndex = 0;
        for(int i = firstBegin; i <= secondFinish; i++){
            list.set(i, auxiliaryArray[auxiliaryIndex]);
            auxiliaryIndex++;
        }
    }

    /**
     * Swaps the elements in the x and y indexes in the list passed as a parameter
     * @param list list that holds the elements to be swapped
     * @param x index of the first item to be swapped
     * @param y index of the second item to be swapped
     * @param <T> type of the objects stored in the list
     */
    private static <T> void swap(List<T> list, int x, int y){
        T temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }
}
