package br.com.zapeat.site.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.topsys.util.TSUtil;
import br.com.zapeat.site.util.Constantes;

/**
 * Servlet Filter implementation class ZapeatMobileFilter
 */
@WebFilter("*.xhtml")
public class ZapeatMobileFilter implements Filter {

	public ZapeatMobileFilter() {

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getRequestURI().endsWith("menu.xhtml") ||  req.getRequestURI().endsWith("detalhamento.xhtml")
				|| !TSUtil.isEmpty(req.getSession().getAttribute(Constantes.USUARIO_LOGADO))) {

			chain.doFilter(request, response);

		} else {
			
			req.getRequestDispatcher("menu.xhtml").forward(request,response);
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
