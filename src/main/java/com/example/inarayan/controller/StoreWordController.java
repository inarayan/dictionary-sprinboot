package com.example.inarayan.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Generated;
import javax.websocket.server.PathParam;

import org.apache.catalina.manager.StatusTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.inarayan.domain.DictionaryService;
import com.example.inarayan.exception.NotFoundException;
import com.example.inarayan.exception.ValidationException;



@RestController
@RequestMapping("/dictionary")

public class StoreWordController {
	
	@Autowired
	DictionaryService dictionaryService;
	
	Logger log = LoggerFactory.getLogger(StoreWordController.class);
	
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@RequestMapping(value = "/words/{word}", produces= {"application/json"}, method=RequestMethod.GET)
	public ResponseEntity<Boolean> searchWord( @PathVariable(value="word") String word) throws Exception{
		
		try {
			log.debug("The Input from User is: "+word);
			if(word==null || word.isEmpty()) {
				log.error("Word provided is empty or null");
				throw new ValidationException("InValid Word provided by the user. Word cannot be empty or null");
				//return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
			}
			else {
				if(dictionaryService.findByWords(word)) {
					log.debug("Calling the service method");
					return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				}
				else {
					log.error("Word not found");
					throw new NotFoundException("Word does not exist in the dictionary");
					//return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
				}
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException();
			throw e;
			//return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	@RequestMapping(value = "/words/create", method=RequestMethod.POST, produces= {"application/json"})
	public ResponseEntity<Boolean> createWord(@RequestPart("file") MultipartFile file) throws Exception{
		
		String strMethodName= "createWord";
		try {
			log.info("Entering the method .." + strMethodName);
			log.info(file.getContentType());
			if(file==null || file.isEmpty() || file.getBytes().length==0 || !file.getContentType().equalsIgnoreCase("text/plain")) {
				log.error("Uploaded file is empty or invalid");
				throw new ValidationException("Uploaded file is not valid. Please check the file type and the contents.Only text/plain file type is allowed");
				//return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
			}
			else {
				log.debug("Calling the service to insert the word in the database..");	
				try {
					dictionaryService.insertWord(file);
				}
				catch(Exception e) {
					throw  e;
				}
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
					
				}
		}
		catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
			
		}
	}

