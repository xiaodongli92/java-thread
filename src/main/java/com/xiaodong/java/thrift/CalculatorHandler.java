package com.xiaodong.java.thrift;

import org.apache.thrift.TException;
import shared.SharedStruct;

import java.util.HashMap;

/**
 * Created by xiaodong on 2017/5/29.
 */
public class CalculatorHandler implements Calculator.Iface {

    private HashMap<Integer, SharedStruct> log;

    public CalculatorHandler() {
        log = new HashMap<>();
    }

    @Override
    public SharedStruct getStruct(int key) throws TException {
        System.out.println("getStruct("+key+")");
        return log.get(key);
    }

    @Override
    public void ping() throws TException {
        System.out.println("ping()");
    }

    @Override
    public int add(int num1, int num2) throws TException {
        System.out.println("add("+num1+","+num2+")");
        return num1 + num2;
    }

    @Override
    public int calculate(int logid, Work w) throws InvalidOperation, TException {
        System.out.println("calculate("+logid+",{"+w.op+","+w.num1+","+w.num2+"})");
        int result = 0;
        switch (w.op) {
            case ADD:
                result = w.num1 + w.num2;
                break;
            case SUBTRACT:
                result = w.num1 - w.num2;
                break;
            case MULTIPLY:
                result = w.num1 * w.num2;
                break;
            case DIVIDE:
                if (w.num2 == 0) {
                    InvalidOperation io = new InvalidOperation();
                    io.whatOp = w.op.getValue();
                    io.why = "Cannot divide by 0";
                    throw io;
                }
                result = w.num1 / w.num2;
                break;
            default:
                InvalidOperation io = new InvalidOperation();
                io.whatOp = w.op.getValue();
                io.why = "Unknown operation";
        }
        SharedStruct entry = new SharedStruct();
        entry.key = logid;
        entry.value = Integer.toString(result);
        log.put(logid, entry);

        return result;
    }

    @Override
    public void zip() throws TException {
        System.out.println("zip()");
    }
}
