package com.xoozi.alg;

import java.util.Iterator;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class Bag<Item> implements Iterable<Item> {

    private Node<Item>  _first;
    private int         _size;

    public Bag(){
        _first = null;
        _size = 0;
    }

    public void add(Item item){
        Node<Item> oldfirst = _first;
        _first = new Node<Item>();
        _first.item = item;
        _first.next = oldfirst;

        _size ++;
    }

    public int size(){
        return _size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(_first);
    }

    public static void main(String[] args){
        Bag<String> bag = new Bag<String>();


        while(!StdIn.isEmpty()){
            String item = StdIn.readString();

            bag.add(item);
        }

        StdOut.println("bag size:" + bag.size());
        
        for(String item: bag){
            StdOut.println(item);
        }
    }
}
