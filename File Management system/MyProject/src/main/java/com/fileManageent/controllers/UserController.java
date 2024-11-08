package com.fileManageent.controllers;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fileManageent.entities.FileRepo;
import com.fileManageent.entities.Files;
import com.fileManageent.entities.User;
import com.fileManageent.entities.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private FileRepo fileRepo;
	
	@Autowired
	private UserRepo userRepo;

    @GetMapping("/")
    public String userHome(Principal p,Model m) {
    	
    	User user = this.userRepo.getUserByUserName(p.getName());
    	List<Files> files = this.fileRepo.findFilesByUser(user);
    	
    	m.addAttribute("files", files);
        return "user/userHome";
    }
    
    
    @GetMapping("/uploadFile")
    public String uploadFile(Model m) {
    	m.addAttribute("file", new Files());
        return "user/uploadFileForm";
    }
    
    @PostMapping("/uploadFileProcess")
    public String uploadFileProcess(@RequestParam("file") MultipartFile file,Principal p) throws IOException {
    	Files files = new Files();
    	User user = this.userRepo.getUserByUserName(p.getName());
    	
    	int randomNumber = (int)(Math.random() * 900000) + 100000;
    	
    	int size = this.fileRepo.findAll().size();
    	
    	if(file!=null) {
    		files.setUserFile(file.getBytes());
    		files.setDate(LocalDate.now());
    		
    		
    		double megabytes = (double) file.getSize() / (1024 * 1024);
    		double roundedSize = Math.round(megabytes * 100.0) / 100.0; // Round to 2 decimal places
    		files.setSize(roundedSize);
    		    		
    		
        	files.setSecretCode(randomNumber+size);
        	files.setUser(user);
        	files.setFilename(file.getOriginalFilename());
        	this.fileRepo.save(files);
    	}
    	
    	
    	
        return "redirect:/user/";
    }
    
    @GetMapping("/deleteFile/{id}")
    public String deleteFile(@PathVariable("id") Long id,Principal p) {
    	
    	User user = this.userRepo.getUserByUserName(p.getName());
    	
    	Files file = this.fileRepo.findFileById(id);
    	
    	if(file.getUser()==user) {
    		this.fileRepo.deleteById(id);
    	}else {
    		
    	}
    	
    	
        return "redirect:/user/";
    }
    
    
    
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id,Principal p) {
        Files file = this.fileRepo.findFileById(id);
        
      User user = this.userRepo.getUserByUserName(p.getName());
      
      
        if (file!=null) {
            if(user==file.getUser()) {
            	byte[] fileDown = file.getUserFile();
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileDown);
            }else {
            	return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}