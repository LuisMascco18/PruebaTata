package com.pru.otro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
/*@Component es un estereotipo y como tal permite que se inyecte un
 * bin en el cor de spring*/
@Order(Ordered.HIGHEST_PRECEDENCE)
/*Significa la importancia mas alta, con esto podemos evitar 
 * la lucha ente cors y securety por quien seria primero*/
public class CORS implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		/*Se permite el acceso a cualquier peticion para poder trabajar el backend */
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN, DOCUMENT_TYPE");

		/*Utilizado para compatibilidad con sprintcloud para la generacion
		 * de los JWT*/
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
		// chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
