package com.yang.base.util.log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public final class Logger {
  public static final int SIMPLE = 1;
  public static final int VERBOSE = 2;
  public static final int DEBUG = 3;
  public static final int INFO = 4;
  public static final int WARN = 5;
  public static final int ERROR = 6;
  public static final int ASSERT = 7;

  @NonNull
  private static Printer printer = new Printer();

  private Logger() {
  }

  public static void init (String tag ,boolean isLoggable) {
    printer.init(tag,isLoggable);
  }

  /**
   * 设置tag
   * @param tag
   * @return
   */
  public static Printer t(@Nullable String tag) {
    return printer.t(tag);
  }

  public static void simple(@Nullable Object object) {
    printer.simple(object);
  }

  public static void d(@Nullable Object object) {
    printer.d(object);
  }

  public static void e(@NonNull String message) {
    printer.e(null, message);
  }

  public static void e(@Nullable Throwable throwable, @NonNull String message) {
    printer.e(throwable, message);
  }

  public static void i(@NonNull String message) {
    printer.i(message);
  }

  public static void w(@NonNull String message) {
    printer.w(message);
  }

  public static void wtf(@NonNull String message) {
    printer.wtf(message);
  }

  public static void json(@Nullable String json) {
    printer.json(json);
  }

  public static void xml(@Nullable String xml) {
    printer.xml(xml);
  }

}
