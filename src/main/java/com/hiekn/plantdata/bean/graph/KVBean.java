package com.hiekn.plantdata.bean.graph;

public class KVBean<W, T> {
    private W k;
    private T v;

    public W getK() {
        return this.k;
    }

    public T getV() {
        return this.v;
    }

    public KVBean(W k, T v) {
        this.k = k;
        this.v = v;
    }
}