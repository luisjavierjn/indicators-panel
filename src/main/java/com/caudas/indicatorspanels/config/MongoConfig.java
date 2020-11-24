package com.caudas.indicatorspanels.config;

import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories(basePackages = "com.caudas.indicatorspanels.repository")
public class MongoConfig {

}
