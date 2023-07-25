package rzk;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 * Session Bean implementation class CancelTimers
 */
@Stateless
public class CancelTimers implements CancelTimersRemote {

	
	@Resource
	TimerService ts;
	
    /**
     * Default constructor. 
     */
    public CancelTimers() {
        
    }
    
    public void cancelTimers(){
    	for (Timer tm : ts.getAllTimers()){
    		tm.cancel();
    	}
    }

}
