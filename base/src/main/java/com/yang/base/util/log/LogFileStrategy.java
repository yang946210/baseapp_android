package com.yang.base.util.log;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yang.base.util.BaseTimeHelper;

import static com.yang.base.util.log.Utils.checkNotNull;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;



/**
 * @desc  log日志文件存储策略
 * @author yanggq
 */

public class LogFileStrategy {

  private final Handler handler;

  public LogFileStrategy(@NonNull Handler handler) {
    this.handler = checkNotNull(handler);
  }

  public void writeLog(int priority, @Nullable String tag, @NonNull String message) {
    checkNotNull(message);
    handler.sendMessage(handler.obtainMessage(priority, message));
  }

  static class WriteHandler extends Handler {

    private final String folder;

    WriteHandler(@NonNull Looper looper, @NonNull String folder) {
      super(checkNotNull(looper));
      this.folder = checkNotNull(folder);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
      String level= Utils.logLevel(msg.what);
      String data=BaseTimeHelper.getTime(new Date(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss");
      String content="\n\n---------"+level+"-------------------------------------------------------------------------------------------------------\n";
      content += data +"===>>>   "+ msg.obj;

      FileWriter fileWriter = null;
      File logFile = getLogFile(folder, "logs");

      try {
        fileWriter = new FileWriter(logFile, true);
        fileWriter.append(content);
      }catch (IOException e) {
      }finally {
        if (fileWriter != null) {
          try {
            fileWriter.flush();
            fileWriter.close();
          } catch (IOException e1) {}
        }
      }
    }

    private File getLogFile(@NonNull String folderName, @NonNull String fileName) {
      checkNotNull(folderName);
      checkNotNull(fileName);
      File folder = new File(folderName);
      if (!folder.exists()) {
        folder.mkdirs();
      }
      String fileItemName = BaseTimeHelper.getTime(new Time(System.currentTimeMillis()),"yyyy-MM-dd");
      File file = new File(folder, String.format("%s_%s.txt", fileName, fileItemName));
      return file;
    }
  }
}
