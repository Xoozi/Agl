package com.xoozi.alg.sort;

import java.util.Comparator;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class Insertion{

    private Insertion(){
    }

    public static void sort(Object[] a){
        int n = a.length;
        for(int i = 1; i < n; i++){
            
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
        assert isSorted(a);
    }

    protected static boolean less(Object v, Object w){
        @SuppressWarnings("unchecked")
        Comparable<Object> cv = (Comparable<Object>)v;
        return cv.compareTo(w) < 0;
    }

    protected static boolean less(Comparator<Object> c, Object v, Object w){
        return c.compare(v, w) < 0;
    }

    protected static void exch(Object[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    protected static boolean isSorted(Object[] a, int lo, int hi){
        for(int i = lo + 1; i <= hi; i++)
            if(less(a[i], a[i-1]))
                return false;
        return true;
    }

    protected static boolean isSorted(Object[] a){
        return isSorted(a, 0, a.length - 1);
    }

    protected static void show(Object[] a){
        for(int i = 0; i < a.length - 1; i++)
            StdOut.println(a[i]);
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }
}
