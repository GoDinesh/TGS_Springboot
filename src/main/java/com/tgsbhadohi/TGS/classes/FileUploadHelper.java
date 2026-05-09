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

import com.tgsbhadohi.TGS.entities.masters.UploadedDocuments;
import com.tgsbhadohi.TGS.entities.student.Registration;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR= "";//new ClassPathResource("static/image/").getFile().getAbsolutePath(); //"C:\\Users\\dines\\Angular_project\\TGS\\src\\main\\resources\\static\\image";
	//public final String UPLOAD_DIR="";
	public FileUploadHelper()throws IOException
	{
		try {
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
		
	}
	
	public boolean uploadfile(MultipartFile file, Registration reg, boolean singleUpload) {
		boolean flag = false;
		try {
				String PATH = "";
				if(singleUpload) {
					PATH = UPLOAD_DIR+File.separator+reg.getAcademicYearCode()+File.separator+reg.getStandard()+File.separator+reg.getRegistrationNo()+File.separator+"ProfileImage";
				}else {
					PATH = UPLOAD_DIR+File.separator+reg.getAcademicYearCode()+File.separator+reg.getStandard()+File.separator+reg.getRegistrationNo()+File.separator+"Documents";
				}
			    String directoryName = PATH;
	
			    File directory = new File(directoryName);
			    if (! directory.exists()){
			        directory.mkdirs();
			    }

			    try{
			    	Files.copy(file.getInputStream(),Paths.get(PATH+File.separator+file.getOriginalFilename() ), StandardCopyOption.REPLACE_EXISTING);
			    }
			    catch (IOException e){
			        e.printStackTrace();
			        System.exit(-1);
			    }
			flag = true;
		}catch (Exception e) {
			System.out.println("File upload failed: " + e.getMessage());
		}
		return flag;
	}
	
	public String generatelinkForImage(MultipartFile file, Registration reg, boolean singleUpload) {
		String PATH = "";
		if(singleUpload) {
			PATH = "/image/"+reg.getAcademicYearCode()+"/"+reg.getStandard()+"/"+reg.getRegistrationNo()+"/"+"ProfileImage/";
		}else {
			PATH = "/image/"+reg.getAcademicYearCode()+"/"+reg.getStandard()+"/"+reg.getRegistrationNo()+"/"+"Documents/";
		}

		String temp = ServletUriComponentsBuilder.fromCurrentContextPath().path(PATH).path(file.getOriginalFilename()).toUriString();
		return temp;
	}
	
	public boolean deleteFile(UploadedDocuments document, Registration reg) {
        boolean result = false;
        
        // Build the path where the file should be
        String filePath = UPLOAD_DIR + File.separator + reg.getAcademicYearCode() + File.separator +
            reg.getStandard() + File.separator + reg.getRegistrationNo() + File.separator +
            "Documents" + File.separator + document.getFileName();
            
        try {
            File file = new File(filePath);
            if(file.exists()) {
                result = file.delete(); // Deletes the file and returns true if successful
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
