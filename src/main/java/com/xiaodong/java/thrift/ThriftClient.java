package com.xiaodong.java.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import shared.SharedStruct;

/**
 * Created by xiaodong on 2017/5/29.
 */
public class ThriftClient {

    public static String type = "simple";

    public static void main(String[] args) {
        try {
            TTransport transport;
            if ("simple".equals(type)) {
                transport = new TSocket("localhost", 9090);
                transport.open();
            } else {
                TSSLTransportFactory.TSSLTransportParameters parameters = new TSSLTransportFactory.TSSLTransportParameters();
                parameters.setTrustStore("","thrift", "", "");
                transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, parameters);
            }
            TProtocol protocol = new TBinaryProtocol(transport);
            Calculator.Client client = new Calculator.Client(protocol);
            perform(client);
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void perform(Calculator.Client client) throws TException {
        client.ping();
        int sum = client.add(1, 1);

        Work work = new Work();
        work.op = Operation.DIVIDE;
        work.num1 = 1;
        work.num2 = 0;
        try {
            int quotient = client.calculate(1, work);
        } catch (InvalidOperation io) {
            io.printStackTrace();
        }

        work.op = Operation.SUBTRACT;
        work.num1 = 15;
        work.num2 = 10;
        int diff = client.calculate(1, work);

        SharedStruct log = client.getStruct(1);
        System.out.println(log);

    }
}
