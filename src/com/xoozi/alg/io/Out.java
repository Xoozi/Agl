package com.xoozi.alg.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;

public final class Out{
    private PrintWriter _out;

    public Out(OutputStream os){
        try{
            OutputStreamWriter osw = new OutputStreamWriter(os, 
                    Constant.CHARSET_NAME);
            _out = new PrintWriter(osw, true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Out(){
        this(System.out);
    }

    public Out(Socket socket){
        try{
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, 
                    Constant.CHARSET_NAME);
            _out = new PrintWriter(osw, true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Out(String s){
        try{
            OutputStream os = new FileOutputStream(s);
            OutputStreamWriter osw = new OutputStreamWriter(os, 
                    Constant.CHARSET_NAME);
            _out = new PrintWriter(osw, true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Out(File file){
        try{
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(os, 
                    Constant.CHARSET_NAME);
            _out = new PrintWriter(osw, true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void close(){
        _out.close();
    }


    public  void println(){
        _out.println();
    }

    public  void println(Object x){
        _out.println(x);
    }

    public  void println(boolean x){
        _out.println(x);
    }

    public  void println(char x){
        _out.println(x);
    }

    public  void println(double x){
        _out.println(x);
    }

    public  void println(float x){
        _out.println(x);
    }

    public  void println(int x){
        _out.println(x);
    }

    public  void println(long x){
        _out.println(x);
    }

    public  void println(short x){
        _out.println(x);
    }

    public  void println(byte x){
        _out.println(x);
    }

    public  void print(){
        _out.flush();
    }

    public  void print(Object x){
        _out.print(x);
        _out.flush();
    }

    public  void print(boolean x){
        _out.print(x);
        _out.flush();
    }

    public  void print(char x){
        _out.print(x);
        _out.flush();
    }

    public  void print(double x){
        _out.print(x);
        _out.flush();
    }

    public  void print(float x){
        _out.print(x);
        _out.flush();
    }

    public  void print(int x){
        _out.print(x);
        _out.flush();
    }

    public  void print(long x){
        _out.print(x);
        _out.flush();
    }

    public  void print(short x){
        _out.print(x);
        _out.flush();
    }

    public  void print(byte x){
        _out.print(x);
        _out.flush();
    }

    public  void printf(String format, Object ... args){
        _out.printf(Constant.LOCALE, format, args);
        _out.flush();
    }


    public  void printf(Locale locale, String format, Object ... args){
        _out.printf(locale, format, args);
        _out.flush();
    }
}
