package com.booking.cab.domain.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class GetNow {
	public LocalDateTime execute() {
		return LocalDateTime.now();
	}
}
