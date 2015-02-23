package com.xoozi.alg.sort;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.xoozi.alg.io.StdOut;
import com.xoozi.alg.io.StdRandom;

public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer>{

    private int     _size = 0;
    private int[]   _pq;
    private int[]   _qp;
    private Key[]   _keys;

    @SuppressWarnings("unchecked")
    public IndexMaxPQ(int max){
        _keys   = (Key[]) new Comparable[max+1];
        _pq     = new int[max+1];
        _qp     = new int[max+1];
        for(int i = 0; i <= max; i++)
            _qp[i] = -1;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    public boolean isEmpty(){
        return 0 == _size;
    }

    public boolean contains(int i){
        return -1 != _qp[i];
    }

    public int size(){
        return _size;
    }

    public void insert(int i, Key key){
        if(contains(i))
            throw new IllegalArgumentException(
                    "index is already in the priority queue");

        _size++;
        _qp[i]      = _size;
        _pq[_size]  = i;
        _keys[i]    = key;
        _swim(_size);
    }

    public int maxIndex(){
        if(isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        return _pq[1];
    }

    public Key maxKey(){
        if(isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        return _keys[_pq[1]];
    }


    public int delMax(){
        if(isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        int maxIndex = _pq[1];
        _exch(1, _size--);
        _sink(1);
        _qp[maxIndex] = -1;
        _keys[_pq[_size+1]] = null;
        _pq[_size+1] = -1;
        return maxIndex;
    }

    public Key  keyOf(int i){
        if(!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");
        else
            return _keys[i];
    }

    public void changeKey(int i, Key key){
        if(!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");
        
        _keys[i] = key;
        _swim(_qp[i]);
        _sink(_qp[i]);
    }

    public void increaseKey(int i, Key key){
        if(!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");

        if(_keys[i].compareTo(key) >= 0)
            throw new IllegalArgumentException(
                    "Calling increaseKey() with given argument would not strictly increase the key");

        _keys[i] = key;
        _swim(_qp[i]);
    }

    public void decreaseKey(int i, Key key){
        if(!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");

        if(_keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException(
                    "Calling decreaseKey() with given argument would not strictly decrease the key");

        _keys[i] = key;
        _sink(_qp[i]);
    }

    public void delete(int i){
        if(!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");
        int index = _qp[i];
        _exch(index, _size--);
        _swim(index);
        _sink(index);
        _keys[i] = null;
        _qp[i] = -1;
    }

    private boolean _less(int i, int j){
        return _keys[_pq[i]].compareTo(_keys[_pq[j]]) < 0;
    }

    private void _exch(int i, int j){
        int swp = _pq[i];
        _pq[i] = _pq[j];
        _pq[j] = swp;
        _qp[_pq[i]] = i;
        _qp[_pq[j]] = j;
    }

    private void _swim(int k){
        while(k > 1 && _less(k/2, k)){
            _exch(k/2, k);
            k = k/2;
        }
    }

    private void _sink(int k){
        int j;
        while(2*k <= _size){
            j = 2*k;
            if(j < _size && _less(j, j+1))  /**在叶节点中找最大*/
                j++;
            if(!_less(k, j))
                break;

            _exch(k, j);
            k = j;
        }
    }


    private class HeapIterator implements Iterator<Integer> {
        private IndexMaxPQ<Key>      _copy;

        private HeapIterator(){
            _copy = new IndexMaxPQ<Key>(_pq.length - 1);

            for(int i = 1; i <= _size; i++)
                _copy.insert(_pq[i], _keys[_pq[i]]);
        }

        @Override
        public boolean hasNext() {
            return !_copy.isEmpty();
        }

        @Override
        public Integer next() {
            if(_copy.isEmpty())
                throw new NoSuchElementException();
            return _copy.delMax();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    public static void main(String[] arg){
        String[] strings = {"it", "was", "the", "best", "of",
                            "time", "it", "was", "the", "worst"};
        IndexMaxPQ<String> pq = new IndexMaxPQ<String>(strings.length);
        for(int i = 0; i < strings.length; i++){
            pq.insert(i, strings[i]);
        }

        for(int i: pq){
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        for(int i = 0; i < strings.length; i++){
            if(StdRandom.uniform() < 0.5)
                pq.increaseKey(i, strings[i] + strings[i]);
            else
                pq.decreaseKey(i, strings[i].substring(0, 1));
        }

        while(!pq.isEmpty()){
            String key = pq.maxKey();
            int i = pq.delMax();
            StdOut.println(i + " " + key);
        }
        StdOut.println();


        for(int i = 0; i < strings.length; i++){
            pq.insert(i, strings[i]);
        }

        int[] perm = new int[strings.length];
        for(int i = 0; i < strings.length; i++)
            perm[i] = i;

        StdRandom.shuffle(perm);
        for(int i = 0; i < perm.length; i++){
            String key = pq.keyOf(perm[i]);
            pq.delete(perm[i]);
            StdOut.println(perm[i] + " " + key);
        }
    }


}
