package com.boombz.blog.service;


import com.boombz.blog.domain.Group2;
import com.boombz.blog.domain.User;
import com.boombz.blog.repository.GroupRepository;
import com.boombz.blog.util.ServerResponse;
import com.boombz.blog.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class GroupServiceImpl implements  GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public ServerResponse<Page<Group2>> findAllGroup(Pageable pageable) {
     Page<Group2> groups = groupRepository.findAll(pageable);
     if(groups!=null) {
         return ServerResponse.createBySuccess(groups);
     }
     else {
         return ServerResponse.createByErrorMessage("查找失败");
     }
    }

    @Override
    public ServerResponse deleteGroupById(Integer id) {
      Group2 group=  groupRepository.findOne(id);
        group.setStatus("2");
        group.setUpdatetime(new Date());
      Group2 group1 = groupRepository.save(group);
        if(group1!=null) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public ServerResponse<Group2> addGroup(Group2 group, HttpSession session) {
        group.setUpdatetime(new Date());
        group.setCreatetime(new Date());
        group.setStatus("1");
        group.setGroupcheck(UUIDUtils.getUUID());
        User user= (User) session.getAttribute("user");
        group.setCreateby(user.getId());

     Group2 group1=   groupRepository.save(group);
        if(group1!=null) {
            return ServerResponse.createBySuccessMessage("新建成功");
        }
        else {
            return ServerResponse.createByErrorMessage("新建失败");
        }

    }

    @Override
    public ServerResponse<Group2> updateGroup(Group2 group) {
        group.setUpdatetime(new Date());
        Group2 group1=   groupRepository.save(group);
        if(group1!=null) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        else {
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }


    @Override
    public ServerResponse realDeleteGroupById(Integer id) {
        groupRepository.delete(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse recoverGroupById(Integer id) {
        Group2 group=  groupRepository.findOne(id);
        group.setStatus("1");
        group.setUpdatetime(new Date());
        Group2 group1 = groupRepository.save(group);
        if(group1!=null) {
            return ServerResponse.createBySuccessMessage("恢复成功");
        }
        else {
            return ServerResponse.createByErrorMessage("恢复失败");
        }
    }


}
