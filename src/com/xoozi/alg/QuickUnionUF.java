package com.xoozi.alg;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class QuickUnionUF{
    private int[]   _id;
    private int     _count;

    public QuickUnionUF(int n){
        _count = n;
        _id = new int[n];

        for(int i = 0; i< n; i++)
            _id[i] = i;
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
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot)
            return;

        _id[pRoot] = qRoot;
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
