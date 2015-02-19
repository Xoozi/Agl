package com.xoozi.alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class Stack<Item> implements Iterable<Item> {

    private Node<Item>  _first;
    private int         _size;

    public Stack(){
        _first = null;
        _size = 0;
    }

    public boolean isEmpty(){
        return null == _first;
    }

    public int size(){
        return _size;
    }

    public void push(Item item){
        Node<Item> old = _first;
        _first = new Node<Item>();
        _first.item = item;
        _first.next = old;
        _size ++;
    }

    public Item pop(){
        if(isEmpty())
            throw new NoSuchElementException("Stack underflow");
        Item item = _first.item;
        _first = _first.next;
        _size --;
        return item;
    }

    public Item peek(){
        if(isEmpty())
            throw new NoSuchElementException("Stack underflow");
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
        Stack<String> s = new Stack<String>();

        while(!StdIn.isEmpty()){
            String item = StdIn.readString();

            if(!item.equals("-"))
                s.push(item);
            else if(!s.isEmpty())
                StdOut.println(" pop:"+ s.pop());
        }

        StdOut.println(String.format(
                    "(%d left on stack)",
                    s.size()));
    }
}
