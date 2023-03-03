package com.sample.jpa.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import com.sample.jpa.util.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	private long id;
	private String name;
	private long creationTime;
	
	 
	public EmployeeDTO(String name) {
		this.name= name;
		this.creationTime = Instant.now().toEpochMilli();
	}
	
	public LocalDateTime getLocalTimeDateFromCreationDate() {
		return CommonUtils.getLocalTimeDateFromEpoch(this.creationTime);
	}
}
