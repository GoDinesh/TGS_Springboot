package com.tgsbhadohi.TGS.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR= new ClassPathResource("static/image/").getFile().getAbsolutePath(); //"C:\\Users\\dines\\Angular_project\\TGS\\src\\main\\resources\\static\\image";
	
	public FileUploadHelper()throws IOException
	{
		
	}
	
	public boolean uploadfile(MultipartFile file) {
		boolean flag = false;
		try {
			System.out.println(Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename() ));
			Files.copy(file.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename() ), StandardCopyOption.REPLACE_EXISTING);
			flag = true;
		}catch (Exception e) {
			System.out.println("File upload failed: " + e.getMessage());
		}
		return flag;
	}
	
	public String generatelinkForImage(MultipartFile file) {
		String temp = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString();
		System.out.println(temp);            
		return temp;
		
	}
}
