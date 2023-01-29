package com.assignment.model;

import lombok.Data;

@Data
public class VoteCount {
	private String candidateName;
    private Long total;
    
    public VoteCount(String candidateName, Long total) {
    	this.candidateName = candidateName;
    	this.total = total;
    }
}
