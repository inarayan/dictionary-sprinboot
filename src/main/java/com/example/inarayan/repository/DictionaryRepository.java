package com.example.inarayan.repository;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.inarayan.model.Dictionary;
import java.lang.String;

@Repository
public interface DictionaryRepository extends MongoRepository<Dictionary, String>{

	List<Dictionary> findByWord(String word);
	
	
	
}
