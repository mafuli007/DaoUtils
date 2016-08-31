package BrazilCenter.Process.MqInterface;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * public class used to connect to rabbitmq service. for both
 * mq-message-consumer and mq-message-producer
 * 
 * @author Fuli Ma
 *
 */
public abstract class MqConnector{

	protected Channel channel;
	protected Connection connection;
	protected String endPointName;

	public MqConnector(String endpointName) throws IOException {
		this.endPointName = endpointName;

		// Create a connection factory
		ConnectionFactory factory = new ConnectionFactory();

		// hostname of your rabbitmq server
		factory.setHost("192.168.183.129");
		
		factory.setUsername("admin");
		factory.setPassword("admin");

		// getting a connection
		connection = factory.newConnection();

		// creating a channel
		channel = connection.createChannel();

		// declaring a queue for this channel. If queue does not exist,
		// it will be created on the server.
		channel.queueDeclare(endpointName, false, false, false, null);
	}

	/**
	 * 关闭channel和connection。并非必须，因为隐含是自动调用的。
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.channel.close();
		this.connection.close();
	}
}
