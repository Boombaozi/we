package com.boombz.blog.serviceTest;

import com.boombz.blog.domain.Group;
import com.boombz.blog.util.ServerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;

public interface GroupService {

    public ServerResponse<Page<Group>> findAllGroup(Pageable pageable);

    public ServerResponse deleteGroupById(Integer id);

    public ServerResponse<Group> addGroup(Group group, HttpSession session);

    public ServerResponse<Group> updateGroup(Group group);

    public ServerResponse realDeleteGroupById(Integer id);

    public ServerResponse recoverGroupById(Integer id);
}
