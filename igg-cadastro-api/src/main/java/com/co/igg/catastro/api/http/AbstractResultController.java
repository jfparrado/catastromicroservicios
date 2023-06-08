package com.co.igg.catastro.api.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
public abstract class AbstractResultController{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
}
