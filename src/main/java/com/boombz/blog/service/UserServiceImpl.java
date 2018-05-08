package com.boombz.blog.service;

import com.boombz.blog.domain.User;
import com.boombz.blog.repository.UserRepository;
import com.boombz.blog.util.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public ServerResponse<User> login(String username, String password) {
        System.out.println("服务层"+username);
      User user=userRepository.findUserByUsernameAndPassword(username,password);


       if (user!=null&& !user.getStatus().equals("2")){
           return ServerResponse.createBySuccess(user);
       }else {
           return ServerResponse.createByErrorMessage("用户名或密码错误");
       }
    }

    @Transactional
    @Override
    public ServerResponse<User> register(User user) {
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setRole("1");
        user.setStatus("1");
        user=  userRepository.save(user);
    if(user==null){
        return ServerResponse.createByErrorMessage("参数为空或者错误");
    }else {
        return ServerResponse.createBySuccess(user);
    }

    }

    @Override
    public ServerResponse<User> edit(User user) {
        user.setUpdatetime(new Date());
       User user1=userRepository.save(user);
        if(user1==null){
            return ServerResponse.createByErrorMessage("修改失败");
        }else {
            return ServerResponse.createBySuccess(user1);
        }
    }

    @Override
    public ServerResponse addGroup(User user) {

        user.setUpdatetime(new Date());
       User user1= userRepository.save(user);
        if(user1==null){
            return ServerResponse.createByErrorMessage("修改失败");
        }else {
            return ServerResponse.createBySuccess(user1);
        }
    }

    @Override
    public ServerResponse<Page<User>> userList(Pageable pageable) {
         Page<User> users=  userRepository.findAll(pageable);
        if(users==null){
            return ServerResponse.createByErrorMessage("查询失败");
        }else {
            return ServerResponse.createBySuccess(users);
        }

    }

    @Override
    public ServerResponse<Page<User>> findUserbygroupid(Integer id,Pageable pageable) {
        Page<User> users =userRepository.findAllByGroupid(id,pageable);
        if(users==null){
            return ServerResponse.createByErrorMessage("查询失败");
        }else {
            return ServerResponse.createBySuccess(users);
        }
    }

    @Override
    public ServerResponse<User> findUserById(Integer id) {
    User user =   userRepository.findById(id);
        if(user==null){
            return ServerResponse.createByErrorMessage("查询失败");
        }else {
            return ServerResponse.createBySuccess(user);
        }
    }
    @Transactional
    @Override
    public ServerResponse<User> deleteUserById(Integer id) {
      User user = userRepository.findById(id);
        user.setStatus("2");
        user.setUpdatetime(new Date());
       userRepository.save(user);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Transactional
    @Override
    public ServerResponse<User> realDeleteUserById(Integer id) {
        userRepository.delete(id);

        return ServerResponse.createBySuccessMessage("真正删除成功");
    }
    @Transactional
    @Override
    public ServerResponse<User> recoverUserById(Integer id) {
        User user = userRepository.findById(id);
        user.setStatus("1");
        user.setUpdatetime(new Date());
        userRepository.save(user);
        return ServerResponse.createBySuccessMessage("恢复成功");
    }

    @Override
    public ServerResponse<Page<User>> findAllUserByGroup(HttpSession session,Pageable pageable) {
          User user =(User)  session.getAttribute("user");
          if(user.getGroupid()==null){
              return ServerResponse.createByErrorMessage("该用户没有所属组");
          }
        Page<User> users=userRepository.findUsersByGroupid(user.getGroupid(),pageable);
          return ServerResponse.createBySuccess(users);
    }


}
