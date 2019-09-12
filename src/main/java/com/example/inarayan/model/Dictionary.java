package com.example.inarayan.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;


public class Dictionary {
	
	@Id
	private String id;
	
	private String word;
	
	private String definition;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	

}
