package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 2;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 2];
                index++;
                return value;
            }
        };


    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 3;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 3];
                index++;
                return value;
            }
        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 5;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();

                }
                int value = array[index / 5];
                index++;
                return value;
            }
        };
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
//        return new Iterator<String>() {
//            private int i, j;
//            @Override
//            public boolean hasNext() {
//                return i < rows.length && j < columns.length;
//            }
//
//            @Override
//            public String next() {
//                if(!hasNext()){
//                    throw new NoSuchElementException();
//                }

//                return "";
//            }
//        };
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    private int i, j;

                    @Override
                    public boolean hasNext() {
                        return i < rows.length && j < columns.length;
                    }

                    @Override
                    public String next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        String cell = columns[j] + rows[i];
                        i++;
                        if (i >= rows.length) {
                            j++;
                            i = 0;
                        }
                        return cell;
                    }
                };
            }
        };

    }



}
