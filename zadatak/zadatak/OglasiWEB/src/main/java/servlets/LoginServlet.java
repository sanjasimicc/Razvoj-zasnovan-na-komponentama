package servlets;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.OglasiBean;
import rzk.OglasiBeanRemote;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	public static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}

	private static String getLookupName() {

		// The app name is the application name of the deployed EJBs. This is typically
		// the ear name without the .ear suffix.
		final String appName = "OglasiEAR";
		// This is the module name of the deployed EJBs on the server. This is typically
		// the jar name of the EJB deployment, without the .jar suffix.
		final String moduleName = "OglasiEJB";
		// JBossAS allows each deployment to have an (optional) distinct name. We
		// haven't specified a distinct name for
		// our EJB deployment, so this is an empty string
		final String distinctName = "";
		// The EJB name which by default is the simple class name of the bean
		// implementation class
		final String beanName = OglasiBean.class.getSimpleName();
		// the remote interface fully qualified class name
		final String interfaceName = OglasiBeanRemote.class.getName();
		// let's do the lookup
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName
				+ "?stateful";
		return name;
	}

	private static OglasiBeanRemote doLookup() {
		Context context = null;
		OglasiBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   " + lookupName);
			bean = (OglasiBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OglasiBeanRemote bean = (OglasiBeanRemote) request.getSession().getAttribute("bean");
		
		if (bean == null) {
			bean = doLookup();
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String nickname2 = bean.login(username, password, nickname);
		if (nickname.equals("")) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else {
			request.setAttribute("bean2", bean);
			

			request.setAttribute("nickname", nickname2);
			request.getRequestDispatcher("nova-stranica.jsp").forward(request, response);

		}
	}

}
