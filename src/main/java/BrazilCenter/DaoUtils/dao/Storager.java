package BrazilCenter.DaoUtils.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import BrazilCenter.DaoUtils.Utils.LogUtils;
import BrazilCenter.DaoUtils.Utils.Utils;
import BrazilCenter.DaoUtils.model.FKT_DPS01_IIG_L31_STP;
import BrazilCenter.DaoUtils.persistence.BrazilCenterDAOServiceImpl;
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
	private BrazilCenterDAOServiceImpl BrazilCenterDaoService;

	public Storager() {
		this.BrazilCenterDaoService = new BrazilCenterDAOServiceImpl();
	}

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}


	/*public boolean IntoDB(String fileNameWithPath) {

		File file = new File(fileNameWithPath);
		String filePath = fileNameWithPath.substring(0, fileNameWithPath.lastIndexOf(File.separator));
		String fileName = fileNameWithPath.substring(fileNameWithPath.lastIndexOf(File.separator) + 1);
		String curTime = (new SimpleDateFormat(Utils.dateFormat24)).format(new Date()).toString();
		String[] infolist = fileName.split("_");
		if (infolist.length == 6) {
			String dataTime = infolist[5].substring(0, infolist[5].indexOf('.'));
			String tableName = infolist[0] + '_' + infolist[1] + '_' + infolist[2] + '_' + infolist[3] + '_'
					+ infolist[4];

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
		}
		return true;
	}
*/
	/** */
	private boolean StoreIntoDB(String fileNameWithPath) {

		File file = new File(fileNameWithPath);
		String filePath = fileNameWithPath.substring(0, fileNameWithPath.lastIndexOf(File.separator));
		String fileName = fileNameWithPath.substring(fileNameWithPath.lastIndexOf(File.separator) + 1);
		String curTime = (new SimpleDateFormat(Utils.dateFormat24)).format(new Date()).toString();
		String[] infolist = fileName.split("_");
		if (infolist.length == 6) {
			String dataTime = infolist[5].substring(0, infolist[5].indexOf('.'));
			String tableName = infolist[0] + '_' + infolist[1] + '_' + infolist[2] + '_' + infolist[3] + '_'
					+ infolist[4];
			if (fileNameWithPath.contains("FKT_DPS01_IIG_L31_STP")) {
				FKT_DPS01_IIG_L31_STP obj = new FKT_DPS01_IIG_L31_STP();
				obj.setFileName(fileName);
				obj.setFilePath(filePath);
				obj.setDataTime(dataTime);
				obj.setFileSize(file.length());
				obj.setInTime(new Date());

				this.BrazilCenterDaoService.getFTK_DPS01_IIG_L31_STP_DAO().insertOrUpdate(obj);

			} else {
			}
		}
		return true;
	}

	/***
	 * (1) store the data into the file system
	 * (2) store the data information into the database
	 * @param fileobj
	 * @param rootDir
	 */
	public void Store(FileObj fileobj, String rootDir) {
		/** 1. store into the file system. */
		String storeFileNameWithPath = StoreDataService.storeData(fileobj, rootDir);

		/** 2. store into the database. */
		if (storeFileNameWithPath != null) {
			if (this.StoreIntoDB(storeFileNameWithPath) == false) {
			}
		} else {
			LogUtils.logger.error("Failed to store the data into filesystem : " + fileobj.getName());
		}
	}
}
