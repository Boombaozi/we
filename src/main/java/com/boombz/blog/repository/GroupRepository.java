package com.boombz.blog.repository;

import com.boombz.blog.domain.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group,Integer> {

    Page<Group> findAll(Pageable pageable);


}
