package com.boombz.blog.repository;


import com.boombz.blog.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * File 存储库.
 * 
 *
 */
public interface FileRepository extends MongoRepository<File, String> {


}
