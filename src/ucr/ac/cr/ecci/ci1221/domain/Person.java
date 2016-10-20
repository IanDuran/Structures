package ucr.ac.cr.ecci.ci1221.domain;

import ucr.ac.cr.ecci.ci1221.util.collections.set.Enumerable;

public class Person implements Enumerable, Comparable<Person> {
    private String name = "";
    private int index = 0;
    public Person(String name){
        this.name = name;
        index = Index.getIndex();
    }
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Person o) {
        int toReturn = 0;
        if(this.toString().compareTo(o.toString()) > 0)
            toReturn = 1;
        else if(this.toString().compareTo(o.toString()) < 0)
            toReturn = -1;
        return toReturn;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        if(o instanceof Person)
            equal = name.equals(o.toString());
        return equal;
    }
}
