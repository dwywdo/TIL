package me.dwywdo.labs.java.syntax.package_inner_class;

import java.util.Iterator;

public class DataStructure {
    private final static int SIZE = 15;
    private final int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        for (int i=0; i<SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {
        final DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }
    }

    interface DataStructureIterator extends Iterator<Integer> { }

    private class EvenIterator implements DataStructureIterator {
        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return (nextIndex <= SIZE - 1);
        }

        @Override
        public Integer next() {
            final Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]); // Directly access to instance variable
            nextIndex += 2;
            return retValue;
        }
    }

    public static void main(String[] args) {
        final DataStructure ds = new DataStructure();
        ds.printEven();
    }
}
