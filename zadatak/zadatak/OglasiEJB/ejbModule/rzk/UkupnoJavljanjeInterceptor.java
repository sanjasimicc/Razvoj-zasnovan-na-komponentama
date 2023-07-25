package rzk;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;
import model.OglasPrijava;

public class UkupnoJavljanjeInterceptor {
	
	@EJB
	Statistika statistika;
	
	@AroundInvoke
	public Object ukupnoJavljanje(InvocationContext ctx) throws Exception {

		List<OglasPrijava> prijave = (List<OglasPrijava>) ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());

		
		for (OglasPrijava p : prijave) {
			int ukupno = statistika.ukupnoJavljanjeZaDan(p);
		}
		return ctx.proceed();
	}

}
