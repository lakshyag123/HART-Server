package com.hart.service;

import java.util.List;

import com.hart.entity.Image;

public interface ImageService {

	public void saveImage(Image img);
	public List<Image> getallimagesbyid(String id);
	public Image getimagebyid(Long id);
	public String deleteimagebyid(Long id);
	public String deleteallimagesbyid(String id);
	public Image updateImage(Image image);
	public List<Image> getallimages();

	

}