/**    
 * @Title: Logger.java  
 * @Package 
 */
package com.automic.retorfitnetdemo.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @ClassName: Logger
 * @Description: Log工具类，便于发布版本时关闭log输出
 * @author gyg
 * @date 2015-5-29 下午14:45:01
 */
public class LogUtils {
	private static boolean debug = AppUtils.DEBUG_FLAG;

	public static void d(String tag, String msg) {
		if (debug)
			Log.d(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (debug)
			Log.e(tag, msg);
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (debug)
			Log.e(tag, msg, tr);
	}

	public static void i(String tag, Object msg) {
		if (debug)
			Log.i(tag, msg.toString());
	}

	public static void i(String tag, String msg) {
		if (debug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (debug)
			Log.v(tag, msg);
	}

	public static void w(String tag, String msg) {
		if (debug)
			Log.w(tag, msg);
	}
	
	public static void system_print (String msg){
		if(debug)
			System.out.println(msg);
	}
	
	public static void system_print (Object msg){
		if(debug)
			System.out.println(msg.toString());
	}
	  /**
	   * 保存log文件
	 * @param strcontent
	 */
	public static void save(String strcontent)
	  {
	    String strFilePath = Environment.getExternalStorageDirectory()
	      .getAbsolutePath() + "/guardlog.txt";
	    String strTime = getCurrentTime();

	    String strContent = strcontent + "[" + strTime + "]" + "\n";
	    try {
	      File file = new File(strFilePath);
	      if (!file.exists()) {
	        Log.d("TestFile", "Create the file:" + strFilePath);
	        file.createNewFile();
	      }
	      RandomAccessFile raf = new RandomAccessFile(file, "rw");
	      raf.seek(file.length());
	      raf.write(strContent.getBytes());
	      raf.close();
	    } catch (Exception e) {
	      Log.e("TestFile", "Error on write File.");
	    }
	  }

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		return str;
	}
}
