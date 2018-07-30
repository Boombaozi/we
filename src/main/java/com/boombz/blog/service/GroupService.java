package com.boombz.blog.service;


import com.boombz.blog.domain.Group2;
import com.boombz.blog.util.ServerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;

public interface GroupService {

    public ServerResponse<Page<Group2>> findAllGroup(Pageable pageable);

    public ServerResponse deleteGroupById(Integer id);

    public ServerResponse<Group2> addGroup(Group2 group, HttpSession session);

    public ServerResponse<Group2> updateGroup(Group2 group);

    public ServerResponse realDeleteGroupById(Integer id);

    public ServerResponse recoverGroupById(Integer id);
}
