package com.xoozi.alg.sort;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key>{

    private Key[]   _pq;
    private int     _size = 0;

    public MinPQ(){
        this(1);
    }
    
    @SuppressWarnings("unchecked")
    public MinPQ(int max){
        _pq = (Key[]) new Comparable[max+1];
    }

    @SuppressWarnings("unchecked")
    public MinPQ(Key[] a){
        _size = a.length;
        _pq = (Key[]) new Comparable[_size+1];

        for(int i = 0; i < _size; i++)
            _pq[i+1] = a[i];

        for(int k = _size/2; k >= 1; k--)
            _sink(k);
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    public void insert(Key v){
        if(_size >= _pq.length - 1)
            _resize(2 * _pq.length);

        _pq[++_size] = v;
        _swim(_size);
    }

    public Key min(){
        if(isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        return _pq[1];
    }

    public Key delMin(){
        if(isEmpty())
            throw new NoSuchElementException("Priority queue underflow");

        Key min = _pq[1];
        _pq[1] = null;     /**避免游离引用*/ 
        _exch(1, _size--);
        _sink(1);
       
        if((_size > 0) && (_size == (_pq.length - 1) / 4))
            _resize(_pq.length / 2);
        return min;
    }

    public boolean isEmpty(){
        return 0 == _size;
    }

    public int size(){
        return _size;
    }

    private boolean _greater(int i, int j){
        return _pq[i].compareTo(_pq[j]) > 0;
    }

    private void _exch(int i, int j){
        Key t = _pq[i];
        _pq[i] = _pq[j];
        _pq[j] = t;
    }

    private void _swim(int k){
        while(k > 1 && _greater(k/2, k)){
            _exch(k/2, k);
            k = k/2;
        }
    }

    private void _sink(int k){
        int j;
        while(2*k <= _size){
            j = 2*k;
            if(j < _size && _greater(j, j+1))  /**在叶节点中找最大*/
                j++;
            if(!_greater(k, j))
                break;

            _exch(k, j);
            k = j;
        }
    }

    @SuppressWarnings("unchecked")
    private void _resize(int capacity){
        assert capacity > _size;
        Key[] tmp = (Key[]) new Comparable[capacity];
        for(int i = 1; i <= _size; i++)
            tmp[i] = _pq[i];
        
        _pq = tmp;
    }

    private class HeapIterator implements Iterator<Key> {
        private MinPQ<Key>      _copy;

        private HeapIterator(){
            _copy = new MinPQ<Key>(_size);

            for(int i = 1; i <= _size; i++)
                _copy.insert(_pq[i]);
        }

        @Override
        public boolean hasNext() {
            return !_copy.isEmpty();
        }

        @Override
        public Key next() {
            if(_copy.isEmpty())
                throw new NoSuchElementException();
            return _copy.delMin();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    public static void main(String[] arg){
        MinPQ<String> pq = new MinPQ<String>();

        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-"))
                pq.insert(item);
            else if(!pq.isEmpty())
                StdOut.println(pq.delMin());
        }
        StdOut.println(String.format(
                    "(%d left on pq)",
                    pq.size()));
    }
}
