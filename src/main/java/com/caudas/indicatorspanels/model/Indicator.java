package com.caudas.indicatorspanels.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Indicator {

	@Id
	private String id;
	private String type;
	private Double volume;
	private Double price;
}