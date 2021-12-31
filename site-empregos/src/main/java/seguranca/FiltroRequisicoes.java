package seguranca;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltroRequisicoes implements Filter
{

	@Inject
	public LoginManager logManager;
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException 
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
	    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		
	    if (logManager.isLogado()) {
	    	filterChain.doFilter(servletRequest, servletResponse);
	    }else {
	        httpServletResponse.sendRedirect(
	            httpServletRequest.getContextPath() + "/login.xhtml?faces-redirect=true");
	    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub
		
	}

}
