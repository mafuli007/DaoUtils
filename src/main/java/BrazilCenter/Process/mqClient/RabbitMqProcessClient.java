package BrazilCenter.Process.mqClient;

import java.io.File;
import java.io.IOException;
import BrazilCenter.models.Task;

public class RabbitMqProcessClient extends MqConnector{
	
	public RabbitMqProcessClient(String endpointName) throws IOException {
		super(endpointName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 关闭channel和connection。并非必须，因为隐含是自动调用的。
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
