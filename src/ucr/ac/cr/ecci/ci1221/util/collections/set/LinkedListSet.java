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
        return null;
    }

    @Override
    public Set<T> intersection(Set<T> set) {
        return null;
    }

    @Override
    public Set<T> difference(Set<T> set) {
        return null;
    }

    @Override
    public boolean isMember(T key) {
        boolean isMember = false;
        if(firstContainer != null){
            Container<T> currrentContainer = firstContainer;
            while(!isMember && currrentContainer != null) {
                if(currrentContainer.getValue().equals(key))
                    isMember = true;
                else
                    currrentContainer = currrentContainer.getNext();
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
    }

    @Override
    public void put(T key) {
        Container<T> newContainer = new Container<>(key);
        if(firstContainer == null)
            firstContainer = newContainer;
        else{
            newContainer.setNext(firstContainer);
            firstContainer = newContainer;
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
        }
    }

    @Override
    public int size() {
        return storedObjects;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
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
}
