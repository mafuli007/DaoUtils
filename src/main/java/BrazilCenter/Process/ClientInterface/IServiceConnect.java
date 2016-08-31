package BrazilCenter.Process.ClientInterface;

import BrazilCenter.models.Task;

/**
 * interface with methods used to send task to server. 
 * @author Fuli Ma
 *
 */
public interface IServiceConnect {

	public boolean sendTaskToSever(Task task);
	
}
