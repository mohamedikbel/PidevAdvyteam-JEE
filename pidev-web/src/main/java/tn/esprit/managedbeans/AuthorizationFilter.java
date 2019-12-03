package tn.esprit.managedbeans;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter ("/advyteam/*")
public class AuthorizationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		 /* Cast des objets request et response */
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        /* Récupération de la session depuis la requête */
        LoginBean loginBean = (LoginBean) servletRequest.getSession().getAttribute("loginBean");
        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( loginBean!= null ||servletRequest.getRequestURI().toString().contains("login.jsf") ) {
            /* Redirection vers la page publique */
chain.doFilter(servletRequest, servletResponse);        
} else {
            /* Affichage de la page restreinte */

servletResponse.sendRedirect(servletRequest.getContentType()+"/login.jsf");}
		
		
  
	}
	
	
	
	}


