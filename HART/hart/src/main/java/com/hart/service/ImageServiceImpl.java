package com.hart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.entity.Image;

import com.hart.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public void saveImage(Image img) {
		
		imageRepository.save(img);
	}
	@Override
	public List<Image> getallimages() {
		
		return imageRepository.findAll();
	}

	@Override
	public List<Image> getallimagesbyid(String mobilenumber) {
		
		return imageRepository.findAllByMobilenumber(mobilenumber);
	}

	@Override
	public Image getimagebyid(Long id) {
		
		return imageRepository.findById(id).orElse(null);
	}

	@Override
	public String deleteimagebyid(Long id) {
		try {
			imageRepository.deleteById(id);
			return "Delete image : "+id;
		} catch (Exception e) {
			return "Image not found";
		}
		
	}

	@Override
	public String deleteallimagesbyid(String mobilenumber) {
		imageRepository.deleteByMobilenumber(mobilenumber);
		return "All images are deleted";
	}

	@Override
	public Image updateImage(Image image) {
		try {
			  Image existingImage =imageRepository.findById(image.getId()).orElse(null);
			  existingImage.setUrl(image.getUrl());
			  existingImage.setPrice(image.getPrice());
			  existingImage.setTitle(image.getTitle());
			  existingImage.setDescription(image.getDescription());
			  return imageRepository.save(existingImage);
			 
		} catch (Exception e) {
			
			System.out.print("error");
			return image;
		}
	}

	
	

}