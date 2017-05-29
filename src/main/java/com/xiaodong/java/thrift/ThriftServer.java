package com.xiaodong.java.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by xiaodong on 2017/5/29.
 */
public class ThriftServer {

    public static CalculatorHandler handler;

    public static Calculator.Processor processor;

    public static void main(String[] args) {
        try {
            handler = new CalculatorHandler();
            processor = new Calculator.Processor(handler);
            Runnable simple = new Runnable() {
                @Override
                public void run() {
                    simple(processor);
                }
            };
//            Runnable secure = new Runnable() {
//                @Override
//                public void run() {
//                    secure(processor);
//                }
//            };
            new Thread(simple).start();
//            new Thread(secure).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void simple(Calculator.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            //单线程
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            //多线程
//            TServer multiThreadServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server");
            server.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void secure(Calculator.Processor processor) {
        try {
            TSSLTransportFactory.TSSLTransportParameters parameters = new TSSLTransportFactory.TSSLTransportParameters();
            parameters.setKeyStore("", "thrift", null, null);
            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, null);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

//            TServer multiThreadServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the secure server ...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
