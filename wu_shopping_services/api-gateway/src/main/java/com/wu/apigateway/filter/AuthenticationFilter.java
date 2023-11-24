package com.wu.apigateway.filter;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.net.HttpHeaders;
import com.wu.apigateway.util.JwtUtil;
import com.wu.apigateway.util.SessionUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator routeValidator;

//	@Autowired
//	private RestTemplate template;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private SessionUtil sessionUtil;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (routeValidator.isSecured.test(exchange.getRequest())) {
				// header contains token or not
				if (!exchange.getRequest().getHeaders().containsKey("AUTH_TOKEN")) {
					throw new RuntimeException("Missing authorization header!!!");
				}
				if ((!exchange.getRequest().getHeaders().containsKey("SESSION_ID"))
						|| null == exchange.getRequest().getHeaders().get("SESSION_ID").get(0)) {
					throw new RuntimeException("Missing Session ID!!!");
				}

				// HttpHeaders.AUTHORIZATION
				String authHeader = exchange.getRequest().getHeaders().get("AUTH_TOKEN").get(0);
				if (authHeader != null && authHeader.startsWith("Bearer")) {
					authHeader = authHeader.substring(7);
				}
				try {
					// Rest Call to auth-service
//					template.getForObject("http://auth-service//validate?token"+authHeader, String.class);
					jwtUtil.validateToken(authHeader);
				} catch (Exception e) {
					System.out.println("Invalid access");
					throw new RuntimeException("Un authorized access to app");
				}

				String userNameFromToken = jwtUtil.getUserNameFromToken(authHeader);
				String rolesFromToken = jwtUtil.getRolesFromToken(authHeader);
				if (exchange.getRequest().getPath().toString().contains("/product/add")
						|| exchange.getRequest().getPath().toString().contains("/product/update")
						|| exchange.getRequest().getPath().toString().contains("/product/delete")) {
//					return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body("Access denied - Roles not authorized!!")
					if (!rolesFromToken.contains("ADMIN"))
						throw new RuntimeException("Access denied - Roles not authorized!!");
				}

				// Validate Session
				String sessionId = exchange.getRequest().getHeaders().get("SESSION_ID").get(0);
				sessionUtil.validateSession(userNameFromToken, sessionId);
//				try {
//					sessionUtil.validateSession("risap");
//				} catch (Exception e) {
//					System.out.println("Invalid Session Id");
//					throw new RuntimeException("Un authorized access to app - Invalid Session Id");
//				}

			}

			return chain.filter(exchange);

		});

	}

	public static class Config {

	}

}
