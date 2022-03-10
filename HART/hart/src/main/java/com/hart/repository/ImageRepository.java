package com.hart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hart.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

	List<Image> findAllByMobilenumber(String mobilenumber);
	@Transactional
	long deleteByMobilenumber(String mobilenumber);

	
}