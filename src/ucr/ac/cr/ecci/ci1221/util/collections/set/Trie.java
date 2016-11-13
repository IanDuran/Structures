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

    /**
     * Constructor for the Trie class.
     */
    public Trie(){
        firstCharacters = new TrieCharacter[256];
        storedElements = 0;
    }

    /**
     * Returns a Set of Strings that is the union between the current Trie
     * and the Set passed as parameter. To do this, it creates an iterator for both
     * sets, then it stores all of the elements of the current set into the new union set.
     * After that it iterates over the values in the other set and if the value is not in the union set,
     * the value is added. Finally it returns the union set.
     * @param set the set to union.
     * @return a set containing the strings in the current set and the other one, without repeating.
     */
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

    /**
     * Returns a set of strings that contains the strings that are in both sets.
     * To do this, it creates an iterator for the other set and goes over the values.
     * If the values of the other set are in the Trie, they are added to the intersection set.
     * Finally it is returned.
     * @param set the set to intersect.
     * @return a set containing the values found in both sets.
     */
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

    /**
     * Returns a set of strings that contains the ones that are inside the current trie, but not in the set
     * passed as parameter.
     * To do this, it creates an iterator for the current Trie and goes over its values.
     * If the current value is not contained in the other set, it is added to the new difference set.
     * Finally, the difference set is returned.
     * @param set the set to substract.
     * @return a set that contains the values inside the current one minus the values of the other set.
     */
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

    /**
     * Tells if a word passed as parameter is inside the Trie.
     * In this method, a boolean that tells if the passed key is a member is initialized in true,
     * since is easier to check when a value is not a member. Then, if the Trie is empty,
     * the boolean is set to false and returned. If is not empty, it iterates over the arrays of
     * the TrieCharacter objects, if the value in the position of the character is null, then it is not a member,
     * if the last TrieCharacter has a null value in the position 0 (that corresponds to the end of the word),
     * then the word is not a member. Finally, the boolean is returned.
     * @param key the element to look for.
     * @return true if the passed word is in the Trie, false otherwise.
     */
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

    /**
     * Inserts a new String into the Trie.
     * It iterates over the characters in the string, if the position corresponding to
     * the integer value of the character is not initialized, it is then initialized.
     * Then it moves into the sub array contained by the TrieCharacter in the position and does
     * the same until all the word is stored. When all the word is stored it initializes a
     * new TrieCharacter in the position zero with the character zero (null), that way, it does
     * not initializes an extra array of TrieCharacters and ends there.
     * @param key the element to add.
     */
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

    /**
     * Removes a given String from the Trie.
     * First, to erase it checks if the given string is a member of the Trie.
     * If it is, it checks if the size of the Trie is 1, in which case, the Trie is cleared.
     * Otherwise, it iterates over the sub-arrays in the characters until it reaches the
     * TrieCharacter containing the end of the word character (0 or null) then that
     * TrieCharacter is set to null so the word gets out of scope and gets deleted by
     * the garbage collector, somehow it works in every case that should be taken into
     * account at the time of removing a word from the Trie.
     * @param key the element to remove.
     */
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

    /**
     * Returns whether the Trie is empty or not.
     * @return true if the Trie is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedElements == 0;
    }

    /**
     * Clears the Trie by setting its variables to the default values.
     */
    @Override
    public void clear() {
        firstCharacters = new TrieCharacter[256];
        storedElements = 0;
    }

    /**
     * Returns the number of strings stored in the Trie.
     * @return the number of strings stored in the Trie.
     */
    @Override
    public int size() {
        return storedElements;
    }

    /**
     * Returns a new instance of the TrieIterator class.
     * @return a new instance of the TrieIterator class.
     */
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator(firstCharacters);
    }

    /**
     * Private TrieCharacter class, it contains a character and a sub-array of TrieCharacters
     */
    private class TrieCharacter{
        private Character value;
        private TrieCharacter[] nextCharacters;

        /**
         * Constructor of the TrieCharacter class. If the character passed has the value 0 (null)
         * the sub-array does not initialize, thus ending the word. Otherwise it creates a new
         * array of TrieCharacters to store more characters.
         * @param value character to be stored in the instance of the class.
         */
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

    /**
     * TrieIterator class
     */
    private class TrieIterator implements Iterator<String>{
        private Queue<String> words;

        /**
         * Constructor for the TrieIterator class.
         * It takes an array of TrieCharacters and puts it's word into a
         * Queue of strings. To do this it calls a private recursive method.
         * @param values
         */
        public TrieIterator(TrieCharacter[] values){
            words = new LinkedListQueue<>();
            for(int i = 0; i < values.length; i++)
                if(values[i] != null)
                    this.getWords(words, "", values, i);
        }

        /**
         * Private recursive method to store the word of the Trie into a Queue. To do this,
         * it needs a queue, an string, an array of TrieCharacter objects and an index.
         * It adds to the String the character inside the TrieCharacter in the position index
         * of the passed array. If the array of TrieCharacter is not null it means the end of
         * the word has not been reached, so it iterates over the array calling itself recursively
         * for every non-null position of the array. If the array is null, the endo of the word has been
         * reached, so the current string minus its last character (null) is stored in the queue.
         * @param queue queue that is storing the words from the Trie.
         * @param currentLetters current String being formed.
         * @param values current array of TrieCharacters being used.
         * @param index position of the array that contains the next character to be concatenated to the String.
         */
        private void getWords(Queue<String> queue, String currentLetters, TrieCharacter[] values, int index){
            currentLetters += values[index].getValue();
            TrieCharacter[] currentValues = values[index].getNextCharacters();
            if(currentValues != null){
                for(int i = 0; i < values.length; i++)
                    if(currentValues[i] != null)
                        this.getWords(queue, currentLetters, currentValues, i);

            }else
                queue.enqueue(currentLetters.substring(0, currentLetters.length() - 1));

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
