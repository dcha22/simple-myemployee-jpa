package com.sample.jpa.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSerialize
public class OkJsonResponse {

	private String status;
	
	public OkJsonResponse(String status) {
		this.status = status;
	}
}
