package com.xoozi.alg.sort;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class Heap{

    private Heap(){
    }

    public static void sort(Object[] a){
        int n = a.length;
        for(int k = n/2; k >= 1; k --)
            _sink(a, k, n);
        while(n > 1){
            _exch(a, 1, n--);
            _sink(a, 1, n);
        }
    }

    public  static boolean isSorted(Object[] a){
        for(int i = 1; i < a.length; i++)
            if(_less(a[i], a[i-1]))
                return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    private static boolean _less(Object[]a, int i, int j){
        Comparable<Object> ci = (Comparable<Object>)a[i-1];
        Comparable<Object> cj = (Comparable<Object>)a[j-1];
        return ci.compareTo(cj) < 0;
    }

    @SuppressWarnings("unchecked")
    protected static boolean _less(Object v, Object w){
        Comparable<Object> cv = (Comparable<Object>)v;
        return cv.compareTo(w) < 0;
    }

    private static void _exch(Object[] a, int i, int j){
        Object swap = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = swap;
    }

    private static void _sink(Object[] a, int k, int n){
        while(2*k <= n){
            int j = 2*k;
            if(j < n && _less(a, j, j+1))
                j++;
            if(!_less(a, k, j))
                break;
            _exch(a, k, j);
            k = j;
        }
    }


    private static void show(Object[] a){
        for(int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        sort(a);

        show(a);
    }
}
