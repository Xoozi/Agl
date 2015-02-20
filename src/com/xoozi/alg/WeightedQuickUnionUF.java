package com.xoozi.alg;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class WeightedQuickUnionUF{
    private int[]   _id;
    private int[]   _sz;
    private int     _count;

    public WeightedQuickUnionUF(int n){
        _count = n;
        _id = new int[n];
        _sz = new int[n];

        for(int i = 0; i< n; i++){
            _id[i] = i;
            _sz[i] = 1;
        }
    }

    public int count(){
        return _count;
    }
    
    public int find(int p){

        while(p != _id[p])
            p = _id[p];
        return p;
    }

    public void union(int p, int q){
        int i = find(p);
        int j = find(q);

        if(i == j)
            return;

        if(_sz[i] < _sz[j]){
            _id[i] = j;
            _sz[j] += _sz[i];
        }else{
            _id[j] = i;
            _sz[i] += _sz[j];
        }
        _count --;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);

        int p, q;
        while(!StdIn.isEmpty()){
            p = StdIn.readInt();
            q = StdIn.readInt();
            if(uf.connected(p, q))
                continue;

            uf.union(p, q);
            //StdOut.println(p + " " + q);
        }

        StdOut.println(uf.count() + " components.");
    }
}
