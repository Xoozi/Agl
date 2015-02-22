package com.xoozi.alg.sort;

import java.util.Comparator;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;
import com.xoozi.alg.io.StdRandom;

public class Quick{

    private Quick(){
    }

    public static void sort(Object[] a){
        /** 为了防止最坏情况，并使运行时间可以预计，
         * 在这里将a的顺序随机打乱是有其必要性的*/
        StdRandom.shuffle(a);
        _sort(a, 0, a.length-1);
        assert isSorted(a);
    }

    private static void _sort(Object[] a, int lo, int hi){
        
        if(hi <= lo)
            return;
        int j = _partition(a, lo, hi);
        _sort(a, lo, j-1);
        _sort(a, j+1, hi);
    }

    private static int _partition(Object[] a, int lo, int hi){
        int i = lo, j = hi+1;
        Object v = a[lo];

        /**主循环*/
        while(true){

            /**扫描左边直到找到大于v的对象的位置*/
            while(less(a[++i], v))
                if(i == hi)/**扫完一边就不再重扫*/
                    break;

            /**扫描右边直到找到小于v的对象的位置*/
            while(less(v, a[--j]))
                if(j == lo)
                    break;

            /**i和j相遇为结束条件*/
            if(i >= j)
                break;

            /**每次扫到两边应该交换位置的对象，就交换*/
            exch(a, i, j);
        }

        /**最后一次交换，把v值换到j的位置*/
        exch(a, lo, j);
        return j;
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
