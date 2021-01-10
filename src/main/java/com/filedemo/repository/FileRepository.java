package com.filedemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filedemo.model.File;

public interface FileRepository extends JpaRepository<File, Integer>{
	
	List<File> findFirstByOrderByIdDesc();
	
	Optional<File> findByFileName(String fileName);


}
