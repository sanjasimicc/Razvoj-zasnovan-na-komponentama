package rzk;

import javax.ejb.Remote;

@Remote
public interface CancelTimersRemote {
	
	 public void cancelTimers();

}
