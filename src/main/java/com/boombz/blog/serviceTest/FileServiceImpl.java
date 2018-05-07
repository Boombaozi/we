package com.boombz.blog.serviceTest;


import com.boombz.blog.domain.File;
import com.boombz.blog.domain.User;
import com.boombz.blog.repository.FileRepository;
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


/**
 * File 服务.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年7月30日
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    public FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;



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

    @Override
    public File saveImage(File file, HttpSession session, HttpServletRequest request) {
        return fileRepository.save(file);
    }

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
}
