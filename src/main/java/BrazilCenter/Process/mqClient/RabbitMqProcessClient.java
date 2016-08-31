package BrazilCenter.Process.mqClient;

import java.io.File;
import java.io.IOException;

import BrazilCenter.DaoUtils.Utils.LogUtils;
import BrazilCenter.Process.ClientInterface.IServiceConnect;
import BrazilCenter.Process.MqInterface.MqConnector;
import BrazilCenter.models.Configuration;
import BrazilCenter.models.Task;

public class RabbitMqProcessClient extends MqConnector implements IServiceConnect{
	
	private Configuration conf;
	
	public Configuration getConf() {
		return conf;
	}

	public RabbitMqProcessClient(String endpointName, Configuration confr) throws IOException {
		super(endpointName);
		this.conf = confr;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Close channel and connection. Not necessary as it happens implicitly any way.
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.channel.close();
		this.connection.close();
	}

	public boolean sendTaskToSever(Task task) {
		// TODO Auto-generated method stub
		String fileName =  task.getFilepath() + File.separator + task.getFilename();
	
		 try {
			channel.basicPublish("",endPointName, null, fileName.getBytes());
			LogUtils.logger.info("Send : " + fileName + " to MqServer.");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogUtils.logger.error("Failed to send message to MQ: " + fileName);
		}
		return false;
	}
}
