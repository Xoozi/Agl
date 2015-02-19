package com.xoozi.alg.io;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class StdIn{

    private StdIn(){}

    private static Scanner  _scanner;

    public static boolean isEmpty(){
        return !_scanner.hasNext();
    }

    public static boolean hasNextLine(){
        return _scanner.hasNextLine();
    }

    public static boolean hasNextChar(){
        _scanner.useDelimiter(Constant.EMPTY_PATTERN);
        boolean ret = _scanner.hasNext();
        _scanner.useDelimiter(Constant.WHITESPACE_PATTERN);
        return ret;
    }

    public static String readLine(){
        String line;
        try{
            line = _scanner.nextLine();
        }catch(Exception e){
            line = null;
        }
        return line;
    }

    public static char readChar(){
        _scanner.useDelimiter(Constant.EMPTY_PATTERN);
        String ch = _scanner.next();

        assert(1 == ch.length()) :
            "Internal (Std)In.readChar() error! Please contact the authors.";
        _scanner.useDelimiter(Constant.WHITESPACE_PATTERN);
        return ch.charAt(0);
    }

    public static String readAll(){
        if(!_scanner.hasNextLine())
            return "";

        String ret = _scanner.useDelimiter(Constant.EVERYTHING_PATTERN).next();
        _scanner.useDelimiter(Constant.WHITESPACE_PATTERN);
        return ret;
    }

    public static String readString(){
        return _scanner.next();
    }

    public static int readInt(){
        return _scanner.nextInt();
    }

    public static double readDouble(){
        return _scanner.nextDouble();
    }

    public static float readFloat(){
        return _scanner.nextFloat();
    }

    public static long readLong(){
        return _scanner.nextLong();
    }

    public static short readShort(){
        return _scanner.nextShort();
    }

    public static byte readByte(){
        return _scanner.nextByte();
    }

    public static boolean readBoolean(){
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

    public static String[] readAllStrings(){
        String[] tokens = Constant.WHITESPACE_PATTERN.split(readAll());
        int length = tokens.length;
        if(0 == length || tokens[0].length() > 0)
            return tokens;

        String[] decapitokens = new String[length-1];
        for(int i = 0; i < length - 1; i++)
            decapitokens[i] = tokens[i+1];
        return decapitokens;
    }

    public static String[] readAllLines(){
        ArrayList<String> lines = new ArrayList<String>();
        while(hasNextLine()){
            lines.add(readLine());
        }
        return lines.toArray(new String[0]);
    }

    public static int[] readAllInts(){
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        
        for(int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }

    public static double[] readAllDoubles(){
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for(int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }

    public static int[] readInts(){
        return readAllInts();
    }

    public static double[] readDoubles(){
        return readAllDoubles();
    }

    public static String[] readStrings(){
        return readAllStrings();
    }


    static{
        _resync();
    }

    private static void  _resync(){
        _setScanner(new Scanner(new java.io.BufferedInputStream(System.in), 
                    Constant.CHARSET_NAME));
    }

    private static void _setScanner(Scanner scanner){
        StdIn._scanner = scanner;
        StdIn._scanner.useLocale(Constant.LOCALE);
    }

}
