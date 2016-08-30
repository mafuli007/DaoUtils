package BrazilCenter.Process.clientInterface;

import BrazilCenter.models.Configuration;
import BrazilCenter.models.Task;

/**
 * interface with methods used to send task to server. 
 * @author Fuli Ma
 *
 */
public interface IServiceConnect {

	public boolean sendTaskToSever(Task task);

	public void setConfiguration(Configuration conf);
	
}
