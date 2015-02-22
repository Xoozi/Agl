package com.xoozi.alg.sort;

import java.util.Comparator;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;
import com.xoozi.alg.io.StdRandom;

public class Quick3Way{

    private Quick3Way(){
    }

    public static void sort(Object[] a){
        /** 为了防止最坏情况，并使运行时间可以预计，
         * 在这里将a的顺序随机打乱是有其必要性的*/
        StdRandom.shuffle(a);
        _sort(a, 0, a.length-1);
        assert isSorted(a);
    }

    @SuppressWarnings("unchecked")
    private static void _sort(Object[] a, int lo, int hi){
        
        if(hi <= lo)
            return;
        int lt = lo, i = lo, gt = hi;
        int cmp;
        Comparable<Object> v = (Comparable<Object>)a[lo];
        while(i <= gt){
            cmp = ((Comparable<Object>)a[i]).compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }
        _sort(a, lo, lt-1);
        _sort(a, gt+1, hi);
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
        for(int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }
}
