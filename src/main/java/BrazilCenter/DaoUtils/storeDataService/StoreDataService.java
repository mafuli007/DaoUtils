package BrazilCenter.DaoUtils.storeDataService;

import java.io.File;

import BrazilCenter.DaoUtils.Utils.LogUtils;
import BrazilCenter.DaoUtils.Utils.Utils;
import BrazilCenter.models.FileObj;

/**
 * store data into file system according the standards.
 * 
 * @author Fuli Ma
 *
 */
public class StoreDataService {

	public static String storeData(FileObj task, String storePath) {
		String fileName = task.getName();
		String filePath = task.getPath();
		String fileNameWithPath = filePath + File.separator + fileName;

		String[] infolist = fileName.split("_");
		String stationId = infolist[0];
		String deviceId = infolist[1];
		String dataId = infolist[0] + '_' + infolist[1] + '_' + infolist[2] + '_' + infolist[3] + '_' + infolist[4];
		String dataDate = infolist[5].substring(0, 8);

		if (stationId != null && deviceId != null && dataId != null && dataDate != null) {
			storePath = storePath + File.separator + stationId + File.separator + deviceId + File.separator + dataId + File.separator + dataDate;
		} else {
			LogUtils.logger.error("Failed to parse the filename: " + fileName);
			return null;
		}
		String storeFileNameWithPath = storePath + File.separator + fileName;
		if (Utils.CopyFile(fileNameWithPath, storeFileNameWithPath)) {
			return storeFileNameWithPath;
		} else {
			return null;
		}
	}
}
