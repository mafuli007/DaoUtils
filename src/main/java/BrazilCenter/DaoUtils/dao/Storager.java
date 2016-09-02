package BrazilCenter.DaoUtils.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import BrazilCenter.DaoUtils.Utils.HibernateUtil;
import BrazilCenter.DaoUtils.Utils.LogUtils;
import BrazilCenter.DaoUtils.Utils.Utils;
import BrazilCenter.DaoUtils.storeDataService.StoreDataService;
import BrazilCenter.models.FileObj;

/**
 * *
 * 
 * @author maful
 *
 */
public class Storager {

	private String rootDir;
	
	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	private void createTable(String tableName) {
		String createTableSql = "CREATE TABLE `" + tableName + "`  (`filename` varchar(255) NOT NULL,"
				+ "`filepath` varchar(256) NOT NULL," + "`filesize` int(11) DEFAULT NULL,"
				+ "`dataTime` varchar(32) DEFAULT NULL," + "`InTime` datetime DEFAULT NULL,"
				+ "PRIMARY KEY (`filename`)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		// db.update(createTableSql);
		HibernateUtil.update(createTableSql);
	}

	public boolean IntoDB(String fileNameWithPath) {

		File file = new File(fileNameWithPath);
		String filePath = fileNameWithPath.substring(0, fileNameWithPath.lastIndexOf(File.separator));
		String fileName = fileNameWithPath.substring(fileNameWithPath.lastIndexOf(File.separator) + 1);
		String curTime = (new SimpleDateFormat(Utils.dateFormat24)).format(new Date()).toString();
		String[] infolist = fileName.split("_");
		String dataTime = infolist[5].substring(0, infolist[5].indexOf('.'));
		String tableName = infolist[0] + '_' + infolist[1] + '_' + infolist[2] + '_' + infolist[3] + '_' + infolist[4];

		if (HibernateUtil.isTableExist(tableName)) {  
		} else {  
			this.createTable(tableName);
		}

		String updateSql = "";
		if (HibernateUtil.isFileExist(tableName, fileName)) { 
			updateSql = " update " + tableName + " set filepath='" + filePath + "', filesize=" + file.length()
					+ ", datatime='" + dataTime + "', intime='" + curTime + "' where filename ='" + fileName + "'";
		} else { 
			updateSql = "insert into " + tableName + " values (" + "'" + fileName + "'," + "'" + filePath + "',"
					+ file.length() + "," + "'" + dataTime + "'," + "'" + curTime + "')";
		}
		HibernateUtil.update(updateSql);

		return false;
	}

	public void Store(FileObj fileobj, String rootDir) {
		String storeFileNameWithPath = StoreDataService.storeData(fileobj, rootDir);
		if (storeFileNameWithPath != null) {
			if (this.IntoDB(storeFileNameWithPath) == false) {
			}
		} else {
			LogUtils.logger.error("Failed to store the data into filesystem : " + fileobj.getName());
		}
	}
}
