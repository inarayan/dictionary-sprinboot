package com.example.inarayan.domain;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.example.inarayan.model.Dictionary;
import com.example.inarayan.repository.DictionaryRepository;

@Configuration
public class DictionaryService {

	@Autowired
	private DictionaryRepository dictionaryRepository;

	public DictionaryRepository getDictionaryRepository() {
		return dictionaryRepository;
	}

	public void setDictionaryRepository(DictionaryRepository dictionaryRepository) {
		this.dictionaryRepository = dictionaryRepository;
	}
	
	public boolean findByWords(String word){
		
		
		
		
		if(dictionaryRepository.findByWord(word).size()!=0) {
			return word.equalsIgnoreCase(dictionaryRepository.findByWord(word).get(0).getWord());
		}
		else {
			//there are no words in the dictionary
			return false;
		}
		
		
	}

	public void insertWord(MultipartFile file) throws IOException {
		
		byte[] arr = file.getBytes();
		
		String sentence = new String (arr);
		Pattern p = Pattern.compile("[a-zA-Z]+"); 
		
		Matcher m1 = p.matcher(sentence);
		
		while (m1.find()) {
			String word = m1.group();
			if(dictionaryRepository.findByWord(word).size()==0) {
				Dictionary wordCreated = new Dictionary();
				wordCreated.setWord(word);
				wordCreated.setDefinition("");
				dictionaryRepository.save(wordCreated);
			}	
		}		
			
		}
		
		
		
	
	public DictionaryService(DictionaryRepository dictionaryRepository) {
		super();
		this.dictionaryRepository = dictionaryRepository;
	}
}
