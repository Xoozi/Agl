package com.xoozi.alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class Queue<Item> implements Iterable<Item> {
    
    private Node<Item>  _first;
    private Node<Item>  _last;
    private int         _size;

    public Queue(){
        _first = _last = null;
        _size = 0;
    }

    public boolean isEmpty(){
        return null == _first;
    }

    public int  size(){
        return _size;
    }

    public void enqueue(Item item){
        Node<Item> oldlast = _last;
        _last = new Node<Item>();
        _last.item = item;
        _last.next = null;

        if(isEmpty())
            _first = _last;
        else
            oldlast.next = _last;
        _size ++;
    }

    public Item dequeue(){
        if(isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = _first.item;
        _first = _first.next;
        if(isEmpty())
            _last = null;
        _size --;
        return item;
    }

    public Item peek(){
        if(isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return _first.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Item item : this)
            sb.append(item + " ");
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(_first);
    }


    public static void main(String[] args){
        Queue<String> q = new Queue<String>();

        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-"))
                q.enqueue(item);
            else if(!q.isEmpty())
                StdOut.println("dequeue: " + q.dequeue());
        }

        StdOut.println(String.format(
                    "(%d left on queue)",
                    q.size()));
    }
}
