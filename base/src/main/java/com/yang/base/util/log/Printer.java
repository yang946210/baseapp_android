package com.yang.base.util.log;

import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yang.base.BaseSdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static com.yang.base.util.log.Logger.ASSERT;
import static com.yang.base.util.log.Logger.DEBUG;
import static com.yang.base.util.log.Logger.ERROR;
import static com.yang.base.util.log.Logger.INFO;
import static com.yang.base.util.log.Logger.WARN;


public class Printer {


  /**
   * tag
   */
  private String localTag = "defLogTag";


  /**
   * 是否打印开关
   */
  private boolean isLoggable=true;

  /**
   * 打印策略
   */
  private LogcatStrategy logcatStrategy;

  private LogFileStrategy logFileStrategy;

  public Printer() {
  }


  public Printer t(String localTag) {
    this.localTag=localTag;
    return this;
  }


  public void d(@Nullable Object object) {
    log(DEBUG, getTag(), Utils.toString(object),null);
  }

  public void e(@NonNull String message) {
    e(null, message);
  }

  public void e(@Nullable Throwable throwable, @NonNull String message) {
    log(ERROR, getTag(), message,throwable);
  }

  public void w(@NonNull String message) {
    log(WARN, getTag(), message,null);
  }

  public void i(@NonNull String message) {
    log(INFO, getTag(), message,null);
  }

  public void wtf(@NonNull String message) {
    log(ASSERT, getTag(), message,null);
  }

  public void json(@Nullable String json) {
    if (Utils.isEmpty(json)) {
      d("Empty/Null json content");
      return;
    }
    try {
      json = json.trim();
      if (json.startsWith("{")) {
        JSONObject jsonObject = new JSONObject(json);
        String message = jsonObject.toString(2);
        d(message);
        return;
      }
      if (json.startsWith("[")) {
        JSONArray jsonArray = new JSONArray(json);
        String message = jsonArray.toString(2);
        d(message);
        return;
      }
      e("Invalid Json");
    } catch (JSONException e) {
      e("Invalid Json");
    }
  }

  public void xml(@Nullable String xml) {
    if (Utils.isEmpty(xml)) {
      d("Empty/Null xml content");
      return;
    }
    try {
      Source xmlInput = new StreamSource(new StringReader(xml));
      StreamResult xmlOutput = new StreamResult(new StringWriter());
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      transformer.transform(xmlInput, xmlOutput);
      d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
    } catch (TransformerException e) {
      e("Invalid xml");
    }
  }

  public synchronized void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
    if (throwable != null && message != null) {
      message += " : " + Utils.getStackTraceString(throwable);
    }
    if (throwable != null && message == null) {
      Utils.getStackTraceString(throwable);
    }
    if (Utils.isEmpty(message)) {
      message = "Empty/NULL log message";
    }
    if (isLoggable) {
      logcatStrategy.log(priority, tag, message);
      logFileStrategy.writeLog(priority, tag, message);
    }
  }

  public void init(String localTag,boolean isLoggable) {
    this.localTag=localTag;
    this.isLoggable=isLoggable;
    this.logcatStrategy = LogcatStrategy.newBuilder().tag(localTag).build();
    if (logFileStrategy == null) {
      String folder= BaseSdk.getInstance().getContext().getExternalFilesDir("log").getAbsolutePath();
      HandlerThread ht = new HandlerThread("AndroidFileLogger." + folder);
      ht.start();
      Handler handler = new LogFileStrategy.WriteHandler(ht.getLooper(), folder);
      logFileStrategy = new LogFileStrategy(handler);
    }
  }

  private String getTag() {
    return localTag;
  }
}
