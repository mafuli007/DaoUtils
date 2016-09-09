package BrazilCenter.HeartBeat.Utils;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import BrazilCenter.DaoUtils.Utils.LogUtils;
import BrazilCenter.models.HeartBeatObj;

public class HeartBeatUtils {

	/**
 	 */
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
	/**
 	 */
	public static String MakeXMLHeartbeat(HeartBeatObj heartbeatobj) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}

		String heartbeatstr = null;
		Element root = document.createElement("info");
		document.appendChild(root);

 		Element msgType = document.createElement("MessageType");
		msgType.appendChild(document.createTextNode("HeartBeat"));
		root.appendChild(msgType);

 		Element softwareid = document.createElement("SoftwareId");
		softwareid.appendChild(document.createTextNode(heartbeatobj.getSoftwareid()));
		root.appendChild(softwareid);

 		Element localip = document.createElement("LocalIp");
		localip.appendChild(document.createTextNode(heartbeatobj.getLocalip()));
		root.appendChild(localip);

 		Element currenttime = document.createElement("CurrentTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datestr = format.format(new Date());
		currenttime.appendChild(document.createTextNode(datestr));
		root.appendChild(currenttime);

		/** Duration */
		Element duration = document.createElement("Duration");
		duration.appendChild(document.createTextNode(heartbeatobj.getLastingtime()));
		root.appendChild(duration);

 		Element hostname = document.createElement("HostName");
		hostname.appendChild(document.createTextNode(heartbeatobj.getHostname()));
		root.appendChild(hostname);

 		TransformerFactory tf = TransformerFactory.newInstance();
		ByteArrayOutputStream bos = null;
		try {
			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			bos = new ByteArrayOutputStream();
			t.transform(new DOMSource(document), new StreamResult(bos));
			heartbeatstr = bos.toString();
		} catch (Exception e) {
			LogUtils.logger.error(e.getMessage());
		}

		return heartbeatstr;
	}
	
	/**
	 * function: parse the heart beat obj.
	 */
	public static HeartBeatObj ParseHeartbeattXML(String msg) {
		HeartBeatObj hbobj = new HeartBeatObj();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(msg)));

			Element root = doc.getDocumentElement();
			NodeList books = root.getChildNodes();
			if (books != null) {
				for (int i = 0; i < books.getLength(); i++) {
					Node book = books.item(i);
					String strvalue = book.getFirstChild().getNodeValue();
					if (book.getNodeName().compareTo("MessageType") == 0) {
						///////
					} else if (book.getNodeName().compareTo("SoftwareId") == 0) {
						hbobj.setSoftwareid(strvalue);
					} else if (book.getNodeName().compareTo("LocalIp") == 0) {
						hbobj.setLocalip(strvalue);
					} else if (book.getNodeName().compareTo("CurrentTime") == 0) {
						hbobj.setRecvtime(strvalue);
					} else if (book.getNodeName().compareTo("Duration") == 0) {
						hbobj.setLastingtime(strvalue);
					} else if (book.getNodeName().compareTo("HostName") == 0) {
						hbobj.setHostname(strvalue);
					} else {
					}
				}
			}
		} catch (Exception e) {
			LogUtils.logger.error(e.getMessage());
		}
		return hbobj;
	}
}
