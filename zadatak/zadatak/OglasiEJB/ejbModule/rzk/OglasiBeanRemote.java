package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;
import model.OglasPrijava;

@Remote
public interface OglasiBeanRemote {
	
	public String login(String username, String password, String nickname);
	public List<Ogla> sviOglasi(String pretrazi);
	public Ogla saveOglas(String tekst, int brojPregleda, int idKorisnik);
	public OglasPrijava savePrijavu(String tekst, int idOglas, int idKorisnik);

}
