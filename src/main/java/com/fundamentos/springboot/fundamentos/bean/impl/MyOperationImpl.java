package com.fundamentos.springboot.fundamentos.bean.impl;

import com.fundamentos.springboot.fundamentos.bean.MyOperation;

public class MyOperationImpl implements MyOperation {
    @Override
    public int sumOne(int value) {
        return value++;
    }
}
