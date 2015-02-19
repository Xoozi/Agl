package com.xoozi.alg.io;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public final class StdOut{

    private static PrintWriter _out;

    static{
        try{
            _out = new PrintWriter(
                    new OutputStreamWriter(System.out, Constant.CHARSET_NAME), true);
        }catch(UnsupportedEncodingException e){
            System.out.println(e);
        }
    }

    private StdOut(){
    }

    public  static void close(){
        _out.close();
    }

    public static void println(){
        _out.println();
    }

    public static void println(Object x){
        _out.println(x);
    }

    public static void println(boolean x){
        _out.println(x);
    }

    public static void println(char x){
        _out.println(x);
    }

    public static void println(double x){
        _out.println(x);
    }

    public static void println(float x){
        _out.println(x);
    }

    public static void println(int x){
        _out.println(x);
    }

    public static void println(long x){
        _out.println(x);
    }

    public static void println(short x){
        _out.println(x);
    }

    public static void println(byte x){
        _out.println(x);
    }

    public static void print(){
        _out.flush();
    }

    public static void print(Object x){
        _out.print(x);
        _out.flush();
    }

    public static void print(boolean x){
        _out.print(x);
        _out.flush();
    }

    public static void print(char x){
        _out.print(x);
        _out.flush();
    }

    public static void print(double x){
        _out.print(x);
        _out.flush();
    }

    public static void print(float x){
        _out.print(x);
        _out.flush();
    }

    public static void print(int x){
        _out.print(x);
        _out.flush();
    }

    public static void print(long x){
        _out.print(x);
        _out.flush();
    }

    public static void print(short x){
        _out.print(x);
        _out.flush();
    }

    public static void print(byte x){
        _out.print(x);
        _out.flush();
    }

    public static void printf(String format, Object ... args){
        _out.printf(Constant.LOCALE, format, args);
        _out.flush();
    }


    public static void printf(Locale locale, String format, Object ... args){
        _out.printf(locale, format, args);
        _out.flush();
    }
}
