package ucr.ac.cr.ecci.ci1221.util.collections.set;

import ucr.ac.cr.ecci.ci1221.util.collections.queue.LinkedListQueue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.Queue;

import java.util.Iterator;

/**
 * @author Ian Duran
 */
public class Trie implements Set<String> {

    private TrieCharacter[] firstCharacters;
    private int storedElements;

    public Trie(){
        firstCharacters = new TrieCharacter[256];
        storedElements = 0;
    }

    @Override
    public Set<String> union(Set<String> set) {
        Set<String> union = new Trie();
        Iterator<String> ownIterator = this.iterator();
        Iterator<String> otherIterator = set.iterator();
        while(ownIterator.hasNext())
            union.put(ownIterator.next());

        while(otherIterator.hasNext()){
            String value = otherIterator.next();
            if(!union.isMember(value))
                union.put(value);

        }
        return union;
    }

    @Override
    public Set<String> intersection(Set<String> set) {
        Set<String> intersection = new Trie();
        Iterator<String> otherIterator = set.iterator();
        while (otherIterator.hasNext()){
            String value = otherIterator.next();
            if(this.isMember(value))
                intersection.put(value);
        }
        return intersection;
    }

    @Override
    public Set<String> difference(Set<String> set) {
        Set<String> difference = new Trie();
        Iterator<String> ownIterator = this.iterator();
        while(ownIterator.hasNext()){
            String value = ownIterator.next();
            if(!set.isMember(value))
                difference.put(value);
        }
        return difference;
    }

    @Override
    public boolean isMember(String key) {
        boolean isMember = true;
        if(this.isEmpty())
            isMember = false;

        else{
            TrieCharacter[] currentSet = firstCharacters;
            for(int i = 0; i < key.length(); i++){
                if(currentSet[(int) key.charAt(i)] == null || currentSet[(int) key.charAt(i)].getValue() != key.charAt(i))
                    isMember = false;
                if(currentSet[(int) key.charAt(i)] != null)
                    currentSet = currentSet[(int) key.charAt(i)].getNextCharacters();
            }
            if(currentSet[0] == null)
                isMember = false;
        }
        return isMember;
    }

    @Override
    public void put(String key) {
        if(!this.isMember(key)){
            TrieCharacter[] currentSet = firstCharacters;
            for(int i = 0; i < key.length(); i++){
                if(currentSet[(int)key.charAt(i)] == null)
                    currentSet[(int) key.charAt(i)] = new TrieCharacter(key.charAt(i));

                currentSet = currentSet[(int) key.charAt(i)].getNextCharacters();
            }
            currentSet[0] = new TrieCharacter((char)0);
            storedElements++;
        }
    }

    @Override
    public void remove(String key) {
        if(this.isMember(key)){
            if (storedElements == 1)
                this.clear();

            else {
                TrieCharacter[] currentSet = firstCharacters;
                for (int i = 0; i < key.length(); i++)
                    currentSet = currentSet[(int) key.charAt(i)].getNextCharacters();

                currentSet[0] = null;
            }
            storedElements--;
        }
    }

    @Override
    public boolean isEmpty() {
        return storedElements == 0;
    }

    @Override
    public void clear() {
        firstCharacters = new TrieCharacter[256];
        storedElements = 0;
    }

    @Override
    public int size() {
        return storedElements;
    }

    @Override
    public Iterator<String> iterator() {
        return new TrieIterator(firstCharacters);
    }

    private class TrieCharacter{
        private Character value;
        private TrieCharacter[] nextCharacters;

        public TrieCharacter(Character value){
            this.value = value;
            if(value != 0)
                nextCharacters = new TrieCharacter[256];

        }

        public Character getValue() {
            return value;
        }

        public void setValue(Character value) {
            this.value = value;
        }

        public TrieCharacter[] getNextCharacters() {
            return nextCharacters;
        }
    }

    private class TrieIterator implements Iterator<String>{
        private Queue<String> words;

        public TrieIterator(TrieCharacter[] values){
            words = new LinkedListQueue<>();
            for(int i = 0; i < values.length; i++)
                if(values[i] != null)
                    this.getWords(words, "", values, i);
        }

        private void getWords(Queue<String> stack, String currentLetters, TrieCharacter[] values, int index){
            currentLetters += values[index].getValue();
            TrieCharacter[] currentValues = values[index].getNextCharacters();
            if(currentValues != null){
                for(int i = 0; i < values.length; i++){
                    if(currentValues[i] != null)
                        this.getWords(stack, currentLetters, currentValues, i);
                }
            }else{
                stack.enqueue(currentLetters.substring(0, currentLetters.length() - 1));
            }
        }

        @Override
        public boolean hasNext() {
            return !words.isEmpty();
        }

        @Override
        public String next() {
            return words.dequeue();
        }
    }
}
