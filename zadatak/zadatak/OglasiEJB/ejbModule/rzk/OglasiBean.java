package rzk;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;
import oracle.net.aso.o;

/**
 * Session Bean implementation class OglasiBean
 */
@Stateful
@LocalBean
public class OglasiBean implements OglasiBeanRemote {
	
	int idKorisnik;
	OglasKorisnik korisnik;
	
	int idOglas;
	Ogla oglas;
	
	@PersistenceContext
	EntityManager em;
	

    /**
     * Default constructor. 
     */
    public OglasiBean() {
        // TODO Auto-generated constructor stub
    }

    @Interceptors(PrvaInterceptor.class)
	@Override
	public String login(String username, String password, String nickname) {
		Query q = em.createQuery("SELECT o FROM OglasKorisnik o WHERE o.username LIKE :user AND o.password LIKE :pass AND o.nickname LIKE :nick");
		q.setParameter("user", username);
		q.setParameter("pass", password);
		q.setParameter("nick", nickname);
		List<OglasKorisnik> korisnici = q.getResultList();
		System.out.println(korisnici.size());
		try {

			korisnik = korisnici.get(0);
			idKorisnik = korisnici.get(0).getIdKorisnik();

			return korisnici.get(0).getNickname();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Interceptors(PreglediInterceptor.class)
	@Override
	public List<Ogla> sviOglasi(String pretrazi) {
		Query q = em.createQuery("SELECT o from Ogla o WHERE o.text LIKE :naziv");
		q.setParameter("naziv", pretrazi);
		List<Ogla> oglasi = q.getResultList();
		return oglasi;
	}

	@Override
	public Ogla saveOglas(String tekst, int brojPregleda, int idKorisnik) {
		try {
			OglasKorisnik okk = em.find(OglasKorisnik.class, idKorisnik);
			Ogla oo = new Ogla();
			oo.setText(tekst);
			oo.setOglasKorisnik(okk);
			oo.setBrojPregleda(0);
			em.persist(oo);
			return oo;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	@Interceptors(UkupnoJavljanjeInterceptor.class)
	@Override
	public OglasPrijava savePrijavu(String tekst, int idOglas, int idKorisnik) {
		try {
			Ogla o = em.find(Ogla.class, idOglas);
			OglasKorisnik ok = em.find(OglasKorisnik.class, idKorisnik);
			OglasPrijava op = new OglasPrijava();
			op.setText(tekst);
			op.setOglasKorisnik(ok);
			op.setOgla(o);
			em.persist(op);
			
			return op;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
