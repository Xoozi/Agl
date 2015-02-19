package com.xoozi.alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class ListIterator<T> implements Iterator<T> {
    private Node<T> _current;

    ListIterator(Node<T> first){
        _current = first;
    }

    @Override
    public boolean hasNext() {
        return null != _current;
    }

    @Override
    public T next() {
        if(!hasNext())
            throw new NoSuchElementException();
        T item = _current.item;
        _current = _current.next;
        return item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
