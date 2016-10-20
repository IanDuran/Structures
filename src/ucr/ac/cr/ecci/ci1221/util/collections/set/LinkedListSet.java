package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * @author Student Name
 */
public class LinkedListSet<T> implements Set<T> {

    private Container<T> firstContainer = null;
    private int storedObjects = 0;

    @Override
    public Set<T> union(Set<T> set) {
        Set<T> union = new LinkedListSet<>();
        Iterator<T> ownIterator = this.iterator();
        Iterator<T> otherIterator = set.iterator();
        while(ownIterator.hasNext())
            union.put(ownIterator.next());

        while(otherIterator.hasNext()){
            T key = otherIterator.next();
            if(!union.isMember(key)){
                union.put(key);
            }
        }
        return union;
    }

    @Override
    public Set<T> intersection(Set<T> set) {
        Set<T> intersection = new LinkedListSet<>();
        Iterator<T> otherIterator = set.iterator();
        while(otherIterator.hasNext()){
            T key = otherIterator.next();
            if(isMember(key))
                intersection.put(key);
        }
        return intersection;
    }

    @Override
    public Set<T> difference(Set<T> set) {
        Set<T> difference = new LinkedListSet<>();
        Iterator<T> ownIterator = this.iterator();
        while(ownIterator.hasNext()){
            T key = ownIterator.next();
            if(!set.isMember(key))
                difference.put(key);
        }
        return difference;
    }

    @Override
    public boolean isMember(T key) {
        boolean isMember = false;
        if(firstContainer != null){
            Container<T> currentContainer = firstContainer;
            while(!isMember && currentContainer != null) {
                if(currentContainer.getValue().equals(key))
                    isMember = true;
                else
                    currentContainer = currentContainer.getNext();
            }
        }
        return isMember;
    }

    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    @Override
    public void clear() {
        firstContainer = null;
        storedObjects = 0;
    }

    @Override
    public void put(T key) {
        if(!isMember(key)){
            Container<T> newContainer = new Container<>(key);
            if(firstContainer == null)
                firstContainer = newContainer;
            else{
                newContainer.setNext(firstContainer);
                firstContainer = newContainer;
            }
            storedObjects++;
        }
    }

    @Override
    public void remove(T key) {
        if(firstContainer != null){
            if(firstContainer.getValue().equals(key)){
                firstContainer = firstContainer.getNext();
            }else{
                Container<T> currentContainer = firstContainer;
                boolean found = false;
                while(!found && currentContainer.getNext() != null){
                    if(currentContainer.getNext().getValue().equals(key))
                        found = true;
                    else{
                        currentContainer = currentContainer.getNext();
                    }
                }
                currentContainer.setNext(currentContainer.getNext().getNext());
            }
            storedObjects--;
        }
    }

    @Override
    public int size() {
        return storedObjects;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedSetIterator<T>(firstContainer);
    }

    private class Container<E>{
        private Container<E> next;
        private E value;

        public Container(E value){
            this.value = value;
        }

        public Container<E> getNext() {
            return next;
        }

        public void setNext(Container<E> next) {
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }

    private class LinkedSetIterator<E> implements Iterator<E> {
        private Container<E> currentContainer = null;
        public LinkedSetIterator(Container<E> first){
            currentContainer = first;
        }

        @Override
        public boolean hasNext() {
            return currentContainer != null;
        }

        @Override
        public E next() {
            E toReturn = null;
            if(currentContainer != null){
                toReturn = currentContainer.getValue();
                currentContainer = currentContainer.getNext();
            }
            return toReturn;
        }
    }
}
