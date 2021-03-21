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
        try {
            if (size == elementData.length) {
                final float addLen = 1.5f;
                elementData = Arrays.copyOf(elementData, (int) (size * addLen));
            }
            elementData[size++] = e;
            return true;
        } catch (UnsupportedOperationException unsupportedOperationException) {
            throw new UnsupportedOperationException();
        } catch (ClassCastException classCastException) {
            throw new ClassCastException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException();
        } catch (IllegalStateException illegalStateException) {
            throw new IllegalStateException();
        }
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
        try {
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
        } catch (ClassCastException classCastException) {
            throw new ClassCastException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(final T[] a) {
        try {
            if (a.length < size) {
                return (T[]) Arrays.copyOf(elementData, size, a.getClass());
            }
            System.arraycopy(elementData, 0, a, 0, size);
            return a;
        } catch (ArrayStoreException arrayStoreException) {
            throw new ArrayStoreException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean remove(final Object o) {
        try {
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
        } catch (ClassCastException classCastException) {
            throw new ClassCastException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        } catch (UnsupportedOperationException unsupportedOperationException) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        try {
            for (Object o : c) {
                if (!contains(o)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException classCastException) {
            throw new ClassCastException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        try {
            Object[] arr = c.toArray();
            System.arraycopy(arr, 0, elementData, size, arr.length);
            size += arr.length;
            return arr.length != 0;
        } catch (UnsupportedOperationException unsupportedOperationException) {
            throw new UnsupportedOperationException();
        } catch (ClassCastException classCastException) {
            throw new ClassCastException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException();
        } catch (IllegalStateException illegalStateException) {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        try {
            int len = 0;
            boolean modified = false;
            for (int i = 0; i < size; i++) {
                if (!c.contains(elementData[i])) {
                    elementData[len] = elementData[i];
                    len++;
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
        } catch (UnsupportedOperationException unsupportedOperationException) {
            throw new UnsupportedOperationException();
        } catch (ClassCastException classCastException) {
            throw new ClassCastException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        try {
            int len = 0;
            boolean modified = false;
            for (int i = 0; i < size; i++) {
                if (c.contains(elementData[i])) {
                    elementData[len] = elementData[i];
                    len++;
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
        } catch (UnsupportedOperationException unsupportedOperationException) {
            throw new UnsupportedOperationException();
        } catch (ClassCastException classCastException) {
            throw new ClassCastException();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        }
    }

    @Override
    public void clear() {
        try {
            for (int i = 0; i < size; i++) {
                elementData[i] = null;
            }
            size = 0;
        } catch (UnsupportedOperationException unsupportedOperationException) {
            throw new UnsupportedOperationException();
        }
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
                elementData[cursor - 1] = null;
                for (int i = 0, j = 0; i < size; i++) {
                    if (elementData[i] != null) {
                        elementData[j] = elementData[i];
                        j++;
                    }
                }
                cursor--;
                size--;
                wasNext = false;
            } catch (UnsupportedOperationException unsupportedOperationException) {
                throw new UnsupportedOperationException();
            }
        }
    }
}