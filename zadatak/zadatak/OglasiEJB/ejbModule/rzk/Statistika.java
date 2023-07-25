package rzk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;
import model.OglasPrijava;

/**
 * Session Bean implementation class Statistika
 */
@Singleton
@LocalBean
public class Statistika implements StatistikaLocal {

	private HashMap<Integer, Integer> pregledi;
	

	@PersistenceContext
	EntityManager em;
	@Resource
	TimerService ts;
	private Timer tm;

	/**
	 * Default constructor.
	 */
	public Statistika() {
		pregledi = new HashMap<Integer, Integer>();
		startTimer();
	}



	
	public HashMap<Integer, Integer> getPregledi() {
		return pregledi;
	}

	public void setPregledi(HashMap<Integer, Integer> pregledi) {
		this.pregledi = pregledi;
	}

	// 1. metoda za update Mape
	public void updateMap(Ogla o) {
		if (pregledi.containsKey(o.getIdOglas())) {
			pregledi.put(o.getIdOglas(), pregledi.get(o.getIdOglas()) + 1);
		} else {
			pregledi.put(o.getIdOglas(), 1);
		}
	}

	// 2. metoda za update baze
//programski ili automatski?
	@Timeout
	public void updateDB(Timer t) {
		for (Entry<Integer, Integer> entry : pregledi.entrySet()) {
			Ogla o = em.find(Ogla.class, entry.getKey());
			o.setBrojPregleda(o.getBrojPregleda() + entry.getValue());
			em.merge(o);
		}
	}
	
     public void startTimer() {
		
		TimerConfig config = new TimerConfig();
        config.setPersistent(false);
        tm = ts.createIntervalTimer(0, 900000, config);
	}
	
	
	  public void cancelTimer() {
		  tm.cancel();
	    }
	
	@Schedule( persistent = false)
	public int ukupnoJavljanjeZaDan(OglasPrijava p) {
		ArrayList<OglasPrijava> prijave = new ArrayList<>();
		prijave.add(p);
		int ukupno = prijave.size();
		return ukupno;
	}
}
