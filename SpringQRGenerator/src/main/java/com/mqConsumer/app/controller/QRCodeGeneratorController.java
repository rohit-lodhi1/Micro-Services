package com.mqConsumer.app.controller;


import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.mqConsumer.app.utils.QRCodeGenerator;

@RestController
@CrossOrigin("*")
public class QRCodeGeneratorController {

	 private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

	    @GetMapping("/")
	    public ResponseEntity<ByteArrayResource> getQRCode(){
	        String medium="https://rahul26021999.medium.com/";
	        String github="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLLqxHKBHepENZbzbl7kcZJxbkFm7LQU-tyw&usqp=CAU";
            
	        byte[] image =null;
	        try {
   
	        	 image =   QRCodeGenerator.generateByteCodeQr(UUID.randomUUID().toString(),250,250,QR_CODE_IMAGE_PATH);

	        } catch (WriterException | IOException e) {
	            e.printStackTrace();
	        }
	       
	    	        // Create a ByteArrayResource from the image data
	    	        ByteArrayResource resource = new ByteArrayResource(image);

	    	        // Set the Content-Type header to indicate the image format
	    	        HttpHeaders headers = new HttpHeaders();
	    	        headers.setContentType(MediaType.IMAGE_JPEG);

	    	        // Return the image as a Blob with appropriate headers
	    	        return ResponseEntity.ok()
	    	                .headers(headers)
	    	                .contentLength(image.length)
	    	                .body(resource);
		}
	
}
