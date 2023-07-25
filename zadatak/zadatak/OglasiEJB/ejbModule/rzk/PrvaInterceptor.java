package rzk;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.OglasPrijava;

public class PrvaInterceptor {
	
	@AroundInvoke
	public Object prviMetod(InvocationContext ctx) throws Exception {

		System.out.println("Pozvan je metod za login");
		return ctx.proceed();
	}

}
