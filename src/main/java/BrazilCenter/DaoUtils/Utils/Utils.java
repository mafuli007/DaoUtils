package BrazilCenter.DaoUtils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Utils {

	public static String dateFormat24 = "yyyy-MM-dd HH:mm:ss";
	public static String dateFormat24Mis = "yyyy-MM-dd HH:mm:ss.SSS";
	public static String dateFormat12 = "yyyy-MM-dd hh:mm:ss";
	public static String dateDayFormat = "yyyyMMdd";

	public static boolean isFileUnlocked(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			if (in != null)
				in.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String formatTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

		StringBuffer sb = new StringBuffer();
		if (day >= 0) {
			sb.append(day + "d ");
		}
		if (hour >= 0) {
			sb.append(hour + "h ");
		}
		if (minute >= 0) {
			sb.append(minute + "m ");
		}
		if (second >= 0) {
			sb.append(second + "s");
		}
		return sb.toString();
	}

	public static boolean delFile(File file) {
		return FileUtils.deleteQuietly(file);
	}

	public static boolean CopyFile(String oldPath, String newPath) {
		 try {
			FileUtils.copyFile(new File(oldPath), new File(newPath));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogUtils.logger.error("Failed to copy: " + oldPath + ", error: " +e.getMessage());
			return false;
		}
	}

	public static boolean MoveFile(String oldPath, String newPath) {
		try {
			FileUtils.moveFile(new File(oldPath), new File(newPath));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogUtils.logger.error("Failed to move file: " + oldPath);
			return false;
		}
	}


	public static boolean CreateFile(String fileName, String data) {
		File tmpfile = new File(fileName);
		try {
			if (!tmpfile.exists()) {
				if (!tmpfile.getParentFile().exists()) {
					tmpfile.getParentFile().mkdirs();
				}
				tmpfile.createNewFile();
			}
			FileOutputStream fop = null;
			try {
				fop = new FileOutputStream(tmpfile);
				byte[] contentInBytes = data.getBytes();
				fop.write(contentInBytes);
				fop.flush();
				fop.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fop != null) {
						fop.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
