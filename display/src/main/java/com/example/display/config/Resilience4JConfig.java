package com.example.display.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfig() {
		CircuitBreakerConfig circuitBreakerConfig =
				CircuitBreakerConfig.custom()
									.ringBufferSizeInClosedState(5) // 닫힌 상태에서의 호출 수로, 써킷을 열어야 할지 결정할 때 사용
									.ringBufferSizeInHalfOpenState(1) // 반열림 상태에서의 호출 수, 써킷을 다시 열거나 닫힘 상태로 돌아갈지를 결정할 때 사용 한다.
									.failureRateThreshold(50) // 실패한 호출에 대한 임계값(백분율), 이 값을 초과하면 써킷 오픈
									.waitDurationInOpenState(Duration.ofMillis(10000)) // 써킷 오픈 지속 시간
									.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // 통계 집계 방식, 마지막 N번의 호출 결과를 집계, COUNT_BASED / TIME_BASED
									.build();

		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
															   .timeoutDuration(Duration.ofSeconds(3)) // 타임 아웃 기간
															   .build();

		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(timeLimiterConfig)
				.circuitBreakerConfig(circuitBreakerConfig)
				.build()
		);
	}
}
