package com.boombz.blog.service;


import com.boombz.blog.domain.Day;
import com.boombz.blog.domain.File;
import com.boombz.blog.domain.Image;
import com.boombz.blog.domain.User;
import com.boombz.blog.repository.DayRepository;
import com.boombz.blog.repository.FileRepository;
import com.boombz.blog.repository.ImageRepository;
import com.boombz.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

//文件相关服务

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    public FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DayRepository dayRepository;

    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void removeFile(String id) {
        fileRepository.delete(id);
    }

    @Override
    public File getFileById(String id) {
        return fileRepository.findOne(id);
    }

    @Override
    public List<File> findAllFile(int pageIndex, int pageSize) {
        Page<File> page = null;
        List<File> list = null;

        Sort sort = new Sort(Direction.DESC, "uploadDate");
        Pageable pageable = new PageRequest(pageIndex, pageSize, sort);

        page = fileRepository.findAll(pageable);
        list = page.getContent();
        return list;
    }
    //上传相册图片
    @Override
    @Transactional
    public File saveImage(File file, HttpSession session, HttpServletRequest request) {
        String url = "http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()//端口号
                + request.getContextPath()+"/file/view/"   ;   //项目名称

        File f = fileRepository.save(file);
        System.out.println("file" + f.toString());
        User user = (User) session.getAttribute("user");
        Image image=new Image();
        image.setCreatetime(new Date());
        image.setUpdatetime(new Date());
        image.setAuthorid(user.getId());
        image.setImage(url + f.getId());
        image.setStatus("1");
        image.setIschecked("1");
        image.setGroupid(user.getGroupid());
        System.out.println("更新的信息 :" + image);
        Image image1 = imageRepository.save(image);
        return f;
    }
    //头像上传
    @Override
    @Transactional
    public File saveUserImage(File file, HttpSession session,HttpServletRequest request) {

        String url = "http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()//端口号
                + request.getContextPath()+"/file/view/"   ;   //项目名称

        File f = fileRepository.save(file);
        System.out.println("file" + f.toString());
        User user = (User) session.getAttribute("user");
        user.setImage(url + f.getId());
        user.setUpdatetime(new Date());
        System.out.println("更新的信息 :" + user);
        User user1 = userRepository.save(user);
        return f;
    }
    //纪念日上传
    @Override
    @Transactional
    public File saveDayImage(File file, HttpSession session,
                             HttpServletRequest request,
                             String title,
                             String content,
                             Date time) {
        String url = "http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()//端口号
                + request.getContextPath()+"/file/view/"   ;   //项目名称

        File f = fileRepository.save(file);
        System.out.println("file" + f.toString());
        User user = (User) session.getAttribute("user");
        Day day =new Day();
        day.setCreatetime(new Date());
        day.setUpdatetime(new Date());
        day.setStatus("1");
        day.setIschecked("1");
        day.setImage(url + f.getId());
        day.setTitle(title);
        day.setTime(time);
        day.setContent(content);
        day.setGroupid(user.getGroupid());
        day.setAuthorid(user.getId());
       System.out.println("更新的信息 :" + day.toString());
        Day day1=  dayRepository.save(day);
        return f;
    }
}
