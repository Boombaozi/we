package com.boombz.blog.repository;


import com.boombz.blog.domain.Group2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GroupRepository extends JpaRepository<Group2,Integer> {

    Page<Group2> findAll(Pageable pageable);


    Group2 findByGroupcheckAndStatus(String c,String status);
}
