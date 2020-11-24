package com.caudas.indicatorspanels.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.caudas.indicatorspanels.model.IndicatorEvent;

import reactor.core.publisher.Flux;

public interface IndicatorEventRepository extends ReactiveMongoRepository<IndicatorEvent, Long> {

	@Tailable
	Flux<IndicatorEvent> findWithTailableCursorBy();
}
