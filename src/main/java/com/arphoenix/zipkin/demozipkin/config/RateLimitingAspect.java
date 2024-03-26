package com.arphoenix.zipkin.demozipkin.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arphoenix.zipkin.demozipkin.exception.RateLimitExceededException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RateLimitingAspect {
	private static final ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
	private static final int REQUEST_LIMIT = 5;
	private static final long TIME_LIMIT = 60000; // 1 minute

	@Before("@annotation(RateLimited)")
	public void beforeRequest() {
		String clientId = getClientId(); // Implement method to get client ID
		// If the specified key is not already associated with a value,attempts to compute its value using the given mapping functionand
		// enters it into this map unless null
		AtomicInteger count = requestCounts.computeIfAbsent(clientId, key -> new AtomicInteger(0));
		if (count.incrementAndGet() > REQUEST_LIMIT) {
			throw new RateLimitExceededException("Rate limit exceeded");
		}
		if (requestCounts.size() == 1) {
			resetRequestCounts();
		}
	}

	private String getClientId() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = requestAttributes.getRequest();
			// Aqui você precisa decidir como obter o ID do cliente. Pode ser um cabeçalho HTTP, parâmetro de consulta, etc.
			// Vou dar um exemplo simples usando um cabeçalho HTTP chamado "X-Client-ID"
			return request.getHeader("X-Client-ID");
		}
		return null; // ou lançar uma exceção, dependendo do comportamento desejado
	}

	private void resetRequestCounts() {
		new Thread(() -> {
			try {
				Thread.sleep(TIME_LIMIT);
				requestCounts.clear();
			} catch (InterruptedException e) {
				log.error("Interrupted!", e.getMessage());
				// Restore interrupted state...
				Thread.currentThread().interrupt();
			}
		}).start();
	}
}