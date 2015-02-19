package com.xoozi.alg.io;

import java.util.Locale;
import java.util.regex.Pattern;

public final class Constant{


    static final String CHARSET_NAME = "UTF-8";

    static final Locale LOCALE = Locale.US;

    static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    static final Pattern EMPTY_PATTERN = Pattern.compile("");

    static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

}
