package BrazilCenter.Process.mq;

import java.io.IOException;
import java.util.HashMap;

import BrazilCenter.Process.mqClient.RabbitMqProcessClient;
import BrazilCenter.models.Configuration;
import BrazilCenter.models.Task;
import junit.framework.TestCase;

public class QueueConsumerTest   {

	public static void main(String[] args){
		QueueConsumer consumer = null;
		try {
			consumer = new QueueConsumer("BrazilStoreQueue");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();

		/*RabbitMqProcessClient producer = null;
		try {
			Configuration conf = new Configuration();
			producer = new RabbitMqProcessClient("FirstQueue", conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 100; i++) {
			Task task = new Task();
			String message = "message " + i;
			task.setFilename(message);
			producer.sendTaskToSever(task);
			System.out.println("Message Number " + i + " sent.");
		}*/
	}
}
