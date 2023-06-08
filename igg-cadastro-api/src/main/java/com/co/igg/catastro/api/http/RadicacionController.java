package com.co.igg.catastro.api.http;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@Controller
@RequestMapping("/radicar")
public class RadicacionController {
	
	@PostMapping(value = "/")
	public void submit(
			@RequestPart("sForm") String sForm, 
			@RequestPart("file") List<MultipartFile> file
			) {
		System.out.print("Lleho");
	}
}
