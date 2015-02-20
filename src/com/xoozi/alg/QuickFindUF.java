package com.xoozi.alg;

import com.xoozi.alg.io.StdIn;
import com.xoozi.alg.io.StdOut;

public class QuickFindUF{
    private int[]   _id;
    private int     _count;

    public QuickFindUF(int n){
        _count = n;
        _id = new int[n];

        for(int i = 0; i< n; i++)
            _id[i] = i;
    }

    public int count(){
        return _count;
    }
    
    public int find(int p){
        return _id[p];
    }

    public void union(int p, int q){
        int pId = find(p);
        int qId = find(q);

        if(pId == qId)
            return;
        for(int i = 0; i < _id.length; i++)
            if(_id[i] == pId)
                _id[i] = qId;
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
            StdOut.println(p + " " + q);
        }

        StdOut.println(uf.count() + " components.");
    }
}
