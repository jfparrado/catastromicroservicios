package com.co.igg.catastro.api.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
@RequestMapping("/documento")
public class DocumentController {
	
	@Value("${filepath.from.fileUpload}")
	private String pathFile;
	
	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> download(String param) throws IOException {
		
		File file = new File(pathFile+"/"+param);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		/*Path path = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource1 = new ByteArrayResource(Files.readAllBytes(path));
	    */
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(resource);
	}
}
