package com.caudas.indicatorspanels.resource;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.caudas.indicatorspanels.model.Indicator;
import com.caudas.indicatorspanels.model.IndicatorEvent;
import com.caudas.indicatorspanels.service.IndicatorService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/indicator")
public class IndicatorResource {

	@Autowired
	private IndicatorService indicatorService;

	@GetMapping(value = "/stream", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE })
	public @ResponseBody Flux<IndicatorEvent> stream() {
		return indicatorService.streamIndicatorEvents();
	}

	@PostMapping
	public @ResponseBody Mono<IndicatorEvent> newDevice(@RequestBody IndicatorEvent event) {
		return indicatorService.createIndicatorEvent(event);
	}

	@PostMapping(value = "/random")
	public @ResponseBody Mono<IndicatorEvent> generate() {
		IndicatorEvent event = IndicatorEvent.builder().indicator(
				new Indicator(UUID.randomUUID().toString(), getRandomType(), getRandomDouble(), getRandomDouble()))
				.date(LocalDateTime.now()).build();
		return indicatorService.createIndicatorEvent(event);
	}

	private double getRandomDouble() {
		String[] tempratures = "1,2,3,4,5,6,7,8,9".split(",");
		return Double.valueOf(tempratures[new Random().nextInt(tempratures.length)]);
	}

	private String getRandomType() {
		String[] types = "B,S".split(",");
		return types[new Random().nextInt(types.length)];
	}

}
