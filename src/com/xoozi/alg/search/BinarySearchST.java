package com.xoozi.alg.search;

import java.util.Arrays;
import java.util.NoSuchElementException;

import com.xoozi.alg.Queue;
import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class BinarySearchST<Key extends Comparable<Key>, Value>{

    private static final int INIT_CAPACITY = 2;
    
    private Key[]       _keys;
    private Value[]     _values;
    private int         _size = 0;

    public BinarySearchST(){
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity){
        _keys = (Key[]) new Comparable[capacity];
        _values = (Value[]) new Object[capacity];
    }


    public boolean  contains(Key key){
        return null != get(key);
    }

    public int size(){
        return _size;
    }

    public boolean isEmpty(){
        return 0 == size();
    }

    public Value get(Key key){

        Value ret_val = null;
        do{

            if(isEmpty())
                break;
            
            int i = rank(key);

            if(i < 0 || i >= size())
                break;

            if(0 == _keys[i].compareTo(key))
                ret_val = _values[i];
        }while(false);

        return ret_val;
    }

    public int rank(Key key){
        int lo = 0, hi = _size - 1;

        while(lo <= hi){
            int m = lo + (hi - lo)/2;
            int cmp = key.compareTo(_keys[m]);
            if(cmp < 0)
                hi = m - 1;
            else if (cmp > 0)
                lo = m + 1;
            else
                return m;
        }

        return lo;
    }

    public void put(Key key, Value value){

        do{

            if(null == value){
                remove(key);
                break;
            }

            int i = rank(key);
            if(i > 0 && i < size() &&
                    _keys[i].compareTo(key) == 0){
                _values[i] = value;
                break;
            }

            if(size() == _keys.length)
                _resize(2*_keys.length);

            for(int j = _size; j > i; j--){
                _keys[j] = _keys[j-1];
                _values[j] = _values[j-1];
            }
            _keys[i] = key;
            _values[i] = value;
            _size++;

            assert _check();
        }while(false);
    }


    public void remove(Key key){

        do{
            if(isEmpty())
                break;

            int i = rank(key);
            if(size() == i || _keys[i].compareTo(key) != 0)
                break;

            for(int j = i; j < size() - 1; j++){
                _keys[j] = _keys[j+1];
                _values[j] = _values[j+1];
            }

            _size--;
            _keys[_size] = null;
            _keys[_size] = null;

            if(size() > 0 && size() == _keys.length/4)
                _resize(_keys.length/2);

            assert _check();
        }while(false);
    }


    public void removeMin(){

        if(isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        remove(min());
    }

    public void removeMax(){

        if(isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        remove(max());
    }

    public Key min(){
        if(isEmpty())
            return null;
        return _keys[0];
    }

    public Key max(){
        if(isEmpty())
            return null;
        return _keys[size()-1];
    }

    public Key select(int index){
        if(index < 0 || index >= size())
            return null;
        return _keys[index];
    }

    public Key floor(Key key){
        int i = rank(key);
        if(i < size() && key.compareTo(_keys[i]) == 0)
            return _keys[i];
        else
            return _keys[i-1];
    }

    public Key ceiling(Key key){
        
        int i = rank(key);
        if(i == size())
            return null;
        else
            return _keys[i];
    }

    public int range(Key lo, Key hi){
        if(lo.compareTo(hi) > 0)
            return 0;

        if(contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<Key>();
        do{
            if(null == lo && null == hi)
                break;

            if(null == lo)
                throw new NullPointerException("lo is null");
            if(null == hi)
                throw new NullPointerException("hi is null");

            if(lo.compareTo(hi) > 0)
                break;
            int loi = rank(lo), hii = rank(hi);
            for(int i = loi; i < hii; i++){
                queue.enqueue(_keys[i]);
            }

            if(contains(hi))
                queue.enqueue(_keys[hii]);
        }while(false);

        return queue;
    }

    private void _resize(int capacity){
        assert capacity >= _size;
        _keys = Arrays.copyOf(_keys, capacity);
        _values = Arrays.copyOf(_values, capacity);
    }


    private boolean _check(){
        return _isSorted() && _rankCheck();
    }

    private boolean _isSorted(){
        for(int i = 1; i < size(); i++)
            if(_keys[i].compareTo(_keys[i-1]) < 0)
                return false;
        return true;
    }

    private boolean _rankCheck(){
        for(int i = 0; i < size(); i++){
            if(i != rank(select(i)))
                return false;

            if(_keys[i].compareTo(select(rank(_keys[i]))) != 0)
                return false;
        }
        return true;
    }


    public static void main(String[] args){
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();

        for(int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }

        for(String s: st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
