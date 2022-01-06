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
	public LoginManager loginManager;
	
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
	    String url = httpServletRequest.getRequestURL().toString();
		
	    if (loginManager.isLogado() || url.contains("/index.xhtml")) {
	    	filterChain.doFilter(servletRequest, servletResponse);
	    }else {
	        httpServletResponse.sendRedirect(
	            httpServletRequest.getContextPath() + "/index.xhtml?faces-redirect=true");
	    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub
		
	}

}