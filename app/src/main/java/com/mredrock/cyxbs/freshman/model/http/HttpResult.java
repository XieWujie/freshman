package com.mredrock.cyxbs.freshman.model.http;

public class HttpResult<T> {
    private int index;
    private int amount;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private T data;



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
