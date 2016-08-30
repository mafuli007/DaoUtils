package BrazilCenter.Process.mq;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
 
import org.apache.commons.lang.SerializationUtils;
 
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import BrazilCenter.Process.mqClient.MqConnector;
import BrazilCenter.models.Task;
 
 
/**
 * 读取队列的程序端，实现了Runnable接口。
 * @author syntx
 *
 */
public class QueueConsumer extends MqConnector implements Runnable, Consumer{
     
    public QueueConsumer(String endPointName) throws IOException{
        super(endPointName);        
    }
     
    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");     
    }
 
    /**
     * Called when new message is available.
     */
    public void handleDelivery(String consumerTag, Envelope env,
            BasicProperties props, byte[] body) throws IOException {
    	String msg = new String(body, "UTF-8");
    	System.out.println("Message :"+ msg + " received.");
         
    }
 
    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}


	public boolean sendTaskToSever(Task task) {
		// TODO Auto-generated method stub
		return false;
	}
}