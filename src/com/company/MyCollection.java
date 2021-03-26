package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

class MyCollection<E> implements Collection<E> {
    private static final int LEN = 10;
    private int size;

    private Object[] elementData = new Object[LEN];

    @Override
    public boolean add(final E e) {
        if (size == elementData.length) {
            final float addLen = 1.5f;
            elementData = Arrays.copyOf(elementData, (int) (size * addLen));
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public boolean contains(final Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(final T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        return a;
    }

    @Override
    public boolean remove(final Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    if (size - i - 1 > 0) {
                        System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                    }
                    size--;
                    elementData[size] = null;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    if (size - i - 1 > 0) {
                        System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                    }
                    size--;
                    elementData[size] = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            add(e);
            result = true;
        }
        return result;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean result = false;
        for (Object o : c) {
            while (this.contains(o)) {
                result = this.remove(o);
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        int len = 0;
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            for (Object o : c) {
                if (o == null & elementData[i] == null) {
                    elementData[len] = elementData[i];
                    len++;
                } else if (o != null && o.equals(elementData[i])) {
                    elementData[len] = elementData[i];
                    len++;
                }
            }
        }
        if (len != size) {
            for (int i = len; i < size; i++) {
                elementData[i] = null;
            }
            size = len;
            modified = true;
        }

        return modified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    private class MyIterator<T> implements Iterator<T> {
        private boolean wasNext = false;
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            wasNext = true;
            return (T) elementData[cursor++];
        }

        @Override
        public void remove() {
            try {
                if (cursor == 0 || !wasNext) {
                    throw new IllegalStateException();
                }
                MyCollection.this.remove(elementData[cursor - 1]);
                cursor--;
                wasNext = false;
            } catch (UnsupportedOperationException unsupportedOperationException) {
                throw new UnsupportedOperationException("remove");
            }
        }
    }
}