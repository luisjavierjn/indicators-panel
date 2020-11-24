package com.caudas.indicatorspanels.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.caudas.indicatorspanels.model.IndicatorEvent;
import com.caudas.indicatorspanels.repository.IndicatorEventRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IndicatorService {

	@Autowired
	private IndicatorEventRepository indicatorEventRepository;

	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;

	@PostConstruct
	public void init() {
		reactiveMongoTemplate.dropCollection("indicatorEvent").then(reactiveMongoTemplate
				.createCollection("indicatorEvent", CollectionOptions.empty().capped().size(2048).maxDocuments(10000)))
				.subscribe();
	}

	public Flux<IndicatorEvent> streamIndicatorEvents() {
		return indicatorEventRepository.findWithTailableCursorBy();
	}

	public Mono<IndicatorEvent> createIndicatorEvent(IndicatorEvent event) {
		return this.indicatorEventRepository.save(event);
	}

}
