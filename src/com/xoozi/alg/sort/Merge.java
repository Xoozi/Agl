package com.xoozi.alg.sort;

import java.util.Comparator;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class Merge{

    private static Object[] _aux;

    private Merge(){
    }

    public static void sort(Object[] a){
        _aux = new Object[a.length];
        _sort(a, 0, a.length-1);
        assert isSorted(a);
    }

    private static void _sort(Object[] a, int lo, int hi){
        if(hi <= lo)
            return;
        int mid = lo + (hi - lo)/2;
        _sort(a, lo, mid);
        _sort(a, mid+1, hi);
        _merge(a, lo, mid, hi);
    }

    private static void _merge(Object[]a, int lo, int mid, int hi){
        int i = lo, j = mid+1;

        for(int k = lo; k <= hi; k++)
            _aux[k] = a[k];

        for(int k = lo; k <= hi; k++)
            if      (i > mid)               a[k] = _aux[j++];/**左边用尽*/
            else if (j > hi)                a[k] = _aux[i++];/**右边用尽*/
            else if (less(_aux[j], a[i]))   a[k] = _aux[j++];/**右边当前元素小于左边*/
            else                            a[k] = _aux[i++];
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
