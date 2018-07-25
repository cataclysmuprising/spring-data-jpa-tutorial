package com.github.cataclysmuprising.springdatajpatutorial.util.common;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class TestAuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		return Optional.of(1l);
	}
}
