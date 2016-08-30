package BrazilCenter.Process.clientInterface;

import BrazilCenter.models.Configuration;

public abstract class ProcessClient implements IServiceConnect{

	private Configuration conf;
	
	public void setConfiguration(Configuration confr) {
		// TODO Auto-generated method stub
		this.conf = confr;
	}
}
