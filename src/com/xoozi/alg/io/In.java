package com.xoozi.alg.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public final class In{

    private Scanner     _scanner;

    public In(){
        _scanner = new Scanner(new BufferedInputStream(System.in), 
                Constant.CHARSET_NAME);
        _scanner.useLocale(Constant.LOCALE);
    }

    public In(java.net.Socket socket){
        try{
            InputStream is = socket.getInputStream();
            _scanner = new Scanner(new BufferedInputStream(is), 
                    Constant.CHARSET_NAME);
            _scanner.useLocale(Constant.LOCALE);
        }catch(IOException e){
            System.err.println("Could not open " + socket);
        }
    }


    public In(URL url){
        try{
            URLConnection site  = url.openConnection();
            InputStream is      = site.getInputStream();
            _scanner = new Scanner(new BufferedInputStream(is), 
                    Constant.CHARSET_NAME);
            _scanner.useLocale(Constant.LOCALE);
        }catch(IOException e){
            System.err.println("Could not open " + url);
        }
    }


    public In(File file){
        try{
            _scanner = new Scanner(file, Constant.CHARSET_NAME);
            _scanner.useLocale(Constant.LOCALE);
        }catch(IOException e){
            System.err.println("Could not open " + file);
        }
    }

    public In(String s){

        try{
            do{
                File file = new File(s);
                if(file.exists()){
                    _scanner = new Scanner(file, Constant.CHARSET_NAME);
                    break;
                }

                URL url = getClass().getResource(s);
                if(null == url)
                    url = new URL(s);

                URLConnection site  = url.openConnection();
                InputStream is      = site.getInputStream();
                _scanner = new Scanner(new BufferedInputStream(is), 
                        Constant.CHARSET_NAME);
            }while(false);

            _scanner.useLocale(Constant.LOCALE);
        }catch(IOException e){
            System.err.println("Could not open " + s);
        }
    }

    public In(Scanner scanner){
        _scanner = scanner;
    }

    public void close(){
        _scanner.close();
    }


    public boolean exists(){
        return null != _scanner;
    }

    public boolean isEmpty(){
        return !_scanner.hasNext();
    }

    public boolean hasNextLine(){
        return _scanner.hasNextLine();
    }

    public boolean hasNextChar(){
        _scanner.useDelimiter(Constant.EMPTY_PATTERN);
        boolean ret = _scanner.hasNext();
        _scanner.useDelimiter(Constant.WHITESPACE_PATTERN);
        return ret;
    }


    public String readLine(){
        String line;
        try{
            line = _scanner.nextLine();
        }catch(Exception e){
            line = null;
        }

        return line;
    }

    public char readChar(){
        _scanner.useDelimiter(Constant.EMPTY_PATTERN);
        String ch = _scanner.next();
        assert(1 == ch.length()) :
            "Internal In.readChar() error! Please contact the authors.";
        _scanner.useDelimiter(Constant.WHITESPACE_PATTERN);

        return ch.charAt(0);
    }

    public String readAll(){
        String ret;
        do{
            if(!_scanner.hasNextLine()){
                ret = "";
                break;
            }

            ret = _scanner.useDelimiter(Constant.EVERYTHING_PATTERN).next();
            _scanner.useDelimiter(Constant.WHITESPACE_PATTERN);
        }while(false);
        return ret;
    }

    public String readString(){
        return _scanner.next();
    }


    public int readInt(){
        return _scanner.nextInt();
    }

    public double readDouble(){
        return _scanner.nextDouble();
    }

    public float readFloat(){
        return _scanner.nextFloat();
    }

    public long readLong(){
        return _scanner.nextLong();
    }

    public short readShort(){
        return _scanner.nextShort();
    }

    public byte readByte(){
        return _scanner.nextByte();
    }

    public boolean readBoolean(){
        String s = readString();
        if(s.equalsIgnoreCase("true"))
            return true;
        if(s.equalsIgnoreCase("false"))
            return false;
        if(s.equals("1"))
            return true;
        if(s.equals("0"))
            return false;
        throw new InputMismatchException();
    }

    public String[] readAllStrings(){
        String[] tokens = Constant.WHITESPACE_PATTERN.split(readAll());
        int length = tokens.length;
        if(0 == length || tokens[0].length() > 0)
            return tokens;

        String[] decapitokens = new String[length-1];
        for(int i = 0; i < length - 1; i++)
            decapitokens[i] = tokens[i+1];
        return decapitokens;
    }

    public String[] readAllLines(){
        ArrayList<String> lines = new ArrayList<String>();
        while(hasNextLine()){
            lines.add(readLine());
        }
        return lines.toArray(new String[0]);
    }

    public int[] readAllInts(){
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        
        for(int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }

    public double[] readAllDoubles(){
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for(int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }

    public static int[] readInts(String filename){
        return new In(filename).readAllInts();
    }

    public static double[] readDoubles(String filename){
        return new In(filename).readAllDoubles();
    }

    public static String[] readStrings(String filename){
        return new In(filename).readAllStrings();
    }
}
