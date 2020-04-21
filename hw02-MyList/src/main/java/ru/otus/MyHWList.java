package ru.otus;

import java.util.*;

public class MyHWList<E> implements List<E> {

    final private static int DEFAULT_SIZE = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private int size;
    private int modCounter;

    private E[] elements;

    public MyHWList(int initialSize) {
        if (initialSize < 0 || initialSize > MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException("Wrong list size.");
        }
        elements = (E[]) new Object[initialSize];
    }

    public MyHWList() {
        this(DEFAULT_SIZE);
    }

    private void checkIndex(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index not in List range: " + index);
        }
    }

    private void extendSizeOfArray(int index) {
        if (size <= index) {
            if (size + (index - size) > MAX_ARRAY_SIZE) {
                throw new IndexOutOfBoundsException("Can not create so large list.");
            }
            int newLength = size + (index - size);
            elements = Arrays.copyOf(elements, newLength);
            size = newLength;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }
    @Override
    public void add(int index, E element) {
        checkIndex(index);
        extendSizeOfArray(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index -1);
        elements[index] = element;
        modCounter++;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E prev = elements[index];
        elements[index] = element;
        return prev;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removed = elements[index];
        int newSize = size - 1;
        if (index < newSize) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        } else if (index == newSize) {
            elements[newSize] = null;
        }
        size = newSize;
        modCounter++;
        return removed;
    }

    @Override
    public boolean remove(Object element) {
        boolean b = false;
        for (int index = 0; index < size; index++) {
            if (elements[index].equals(element)) {
                remove(index);
                b = true;
            }
        }
        return b;
    }

    public int indexOf(Object element) {
        int k = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                k = i;
                break;
            }
        }
        return k;
    }

    @Override
    public boolean contains(Object element) {
        for (int index = 0; index < size; index++) {
            if (elements[index].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    public ListIterator<E> listIterator(int index) {
        checkIndex(index);
        return new ListItr(index);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        modCounter++;
    }

    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCounter;

        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            this.checkForComodification();
            int i = cursor;
            Object[] elementData = MyHWList.this.elements;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            } else {
                this.cursor = i + 1;
                return (E) elementData[this.lastRet = i];
            }
        }

        public void remove() {
            this.checkForComodification();
            try {
                MyHWList.this.remove(this.lastRet);
                this.cursor = this.lastRet;
                this.lastRet = -1;
                this.expectedModCount = MyHWList.this.modCounter;
            } catch (IndexOutOfBoundsException var2) {
                throw new ConcurrentModificationException();
            }
        }

        public void checkForComodification() {
            if (modCounter != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override
        public E previous() {
            this.checkForComodification();
            int i = this.cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            } else {
                E[] elementData = MyHWList.this.elements;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i;
                    return elementData[this.lastRet = i];
                }
            }
        }

        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @Override
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override
        public void set(E e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            this.checkForComodification();
            try {
                MyHWList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException var3) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            this.checkForComodification();
            try {
                int ex = this.cursor;
                MyHWList.this.add(ex, (E) e);
                this.cursor = ex + 1;
                this.lastRet = -1;
            } catch (IndexOutOfBoundsException var3) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public ListIterator listIterator() {
        return new ListItr(0);
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    //Unsupported

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("subList");
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("subList");
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList");
    }
    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("lastIndexOf");
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("retainAll");
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("removeAll");
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("containsAll");
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("toArray(Object[] a)");
    }
}
