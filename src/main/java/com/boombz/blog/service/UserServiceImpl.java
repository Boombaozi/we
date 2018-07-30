package com.boombz.blog.service;

import com.boombz.blog.domain.Group2;
import com.boombz.blog.domain.User;
import com.boombz.blog.repository.GroupRepository;
import com.boombz.blog.repository.UserRepository;
import com.boombz.blog.util.ServerResponse;
import com.boombz.blog.util.UUIDUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender; //自动注入的Bean

    @Autowired
    private GroupRepository groupRepository;

    @Value("${spring.mail.username}")
    private String Sender;

    @Override
    public ServerResponse<User> login(String username, String password) {
        System.out.println("服务层"+username);
      User user=userRepository.findUserByUsernameAndPassword(username,password);

      User Euser=userRepository.findUserByEmailAndPassword(username,password);

      //如果结果都为空，说明密码错误或者用户名或者邮箱错误
      if(user==null&& Euser==null){
          return ServerResponse.createByErrorMessage("用户名或密码错误");
      }
      //如果user为空，说明用户使用邮箱登录
      if(user==null){
          if(Euser.getStatus().equals("0")){
              return ServerResponse.createByErrorMessage("用户未激活，请查看邮件进行激活");
          }
          if (Euser.getStatus().equals("2")){
              return ServerResponse.createByErrorMessage("已禁封账户");
          }
          else if(Euser.getStatus().equals("1")){
              return ServerResponse.createBySuccess("登录成功",Euser);
          }
          else {
              return ServerResponse.createByErrorMessage("用户状态异常");
          }
          //如果用户使用用户名密码登录
      }else {
          if(user.getStatus().equals("0")){
              return ServerResponse.createByErrorMessage("用户未激活，请查看邮件进行激活");
          }
          if (user.getStatus().equals("2")){
              return ServerResponse.createByErrorMessage("已禁封账户");
          }
          else if(user.getStatus().equals("1")){
              return ServerResponse.createBySuccess("登录成功",user);
          }
          else {
              return ServerResponse.createByErrorMessage("用户状态异常");
          }


      }


    }

    @Transactional
    @Override
    public ServerResponse<User> register(User user, HttpServletRequest request) {
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setRole("1");
        user.setStatus("0");
        user.setCode(UUIDUtils.getUUID());

        //查看邮箱是否注册
        if(userRepository.findUserByEmail(user.getEmail())!=null){
            return ServerResponse.createByErrorMessage("邮箱已经存在");
        }
        //查看用户名是否存在
        if(userRepository.findUserByUsername(user.getUsername())!=null){
            return ServerResponse.createByErrorMessage("用户名已经存在");
        }
        user=  userRepository.save(user);
    if(user==null){
        return ServerResponse.createByErrorMessage("参数为空或者错误");
    }else {


        //发送验证邮件
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Sender);
            message.setTo(user.getEmail()); //自己给自己发送邮件
            message.setSubject("来自WE的验证邮件");
            String url = "http://" + request.getServerName() //服务器地址
                    + ":"
                    + request.getServerPort()//端口号
                    + request.getContextPath()+"/users/register2";
            message.setText(user.getUsername()+" 欢迎注册we账号 :) 请点击下面链接完成激活 "+url+"?code="+user.getCode());
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
         return ServerResponse.createByErrorMessage("邮件发送失败");
        }

        return ServerResponse.createBySuccess("验证邮件发送成功，请查收",user);
    }

    }

    @Transactional
    @Override
    public ServerResponse<User> register2(String code) {
       User user = userRepository.findUserByCode(code);

       //如果没找到这个邮箱验证码
        if(user==null){
            return ServerResponse.createByErrorMessage("验证码错误");
        }else {
            //激活用户
            user.setStatus("1");
            User user1 =  userRepository.save(user);
            if(user1!=null){
                return ServerResponse.createBySuccess("激活成功，请登录",user1);
            }else {
                return ServerResponse.createByErrorMessage("激活失败,请重试");
            }
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
    public ServerResponse addGroup(User user,String checkcode) {
        user.setUpdatetime(new Date());


        Group2 group=groupRepository.findByGroupcheckAndStatus(checkcode,"1");


        if(group==null){
            return ServerResponse.createByErrorMessage("组验证码错误");
        }else {
            user.setGroupid(group.getId());
        }
        System.out.println(user.toString());
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
