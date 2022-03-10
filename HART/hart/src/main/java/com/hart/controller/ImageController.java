package com.hart.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.hart.entity.Image;
import com.hart.entity.User;
import com.hart.service.ImageService;
import com.hart.service.UserService;


@RestController
@CrossOrigin
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/getallimages")
	public List<Image> getallimages() {
		return imageService.getallimages();
	}
	@GetMapping("/allimages/{id}")
	public List<Image> getallimagesbyid(@PathVariable String id) {
		return imageService.getallimagesbyid(id);
	}
	
	@GetMapping("/image/{id}")
	public Image getimagebyid(@PathVariable Long id) {
		System.out.print(id);
		return imageService.getimagebyid(id);
	}
	
	@PostMapping("/uploadimage/{id}")
	public ResponseEntity<String> upload(@PathVariable String id,@RequestParam("file") MultipartFile file,
			@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("price") Long price
			)throws IOException {
		
		if(file.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Response must contain file");
		}
		if(!file.getContentType().equals("image/jpeg")&&!file.getContentType().equals("image/png")&&!file.getContentType().equals("image/webp"))
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Response must contain image file");
		}
   	 	
   	    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	   			"cloud_name", "",
	   			"api_key", "",
	   			"api_secret", ""));
	    Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	    
	    String imgurl=(String) uploadResult.get("url");
	    Image img=new Image();
	    User savUser=userService.getUserbyid(id);
	    img.setUser(savUser);
	   img.setMobilenumber(id);
	    img.setUrl(imgurl);
	    img.setPrice(price);
	    img.setTitle(title);
	    img.setDescription(description);
	    
		imageService.saveImage(img);
        return ResponseEntity.ok("working");
   }
	
	@PutMapping("/updateimage/{id}")
	public ResponseEntity<String> updateimage(@PathVariable Long id,@RequestParam("file") MultipartFile file,
	 @RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("price") Long price
    	)throws IOException {
		if(file.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Response must contain file");
		}
		if(!file.getContentType().equals("image/jpeg")&&!file.getContentType().equals("image/png")&&!file.getContentType().equals("image/webp"))
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Response must contain image file");
		}
   	 	System.out.print(file.getOriginalFilename());
   	    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	   			"cloud_name", "",
	   			"api_key", "",
	   			"api_secret", ""));
	    Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
	    
	    String imgurl=(String) uploadResult.get("url");
	    Image img=new Image();
	    img.setId(id);
	    img.setUrl(imgurl);
	    img.setPrice(price);
	    img.setTitle(title);
	    img.setDescription(description);
		imageService.updateImage(img);
        return ResponseEntity.ok("updated");
   }
    
	@DeleteMapping("/deleteimage/{id}")
	 public String deleteimage(@PathVariable Long id) {
			return imageService.deleteimagebyid(id);			
	}
	@DeleteMapping("/deleteallimages/{id}")
	 public String deleteallimages(@PathVariable String id) {
			return imageService.deleteallimagesbyid(id);			
	}
}
