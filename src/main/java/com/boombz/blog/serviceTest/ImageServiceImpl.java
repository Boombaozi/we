package com.boombz.blog.serviceTest;

import com.boombz.blog.domain.Image;
import com.boombz.blog.repository.ImageRepository;
import com.boombz.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05
 **/
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Override
    public ServerResponse addImage(Image image, HttpSession session) {
        return null;
    }

    @Override
    public ServerResponse<Page<Image>> findAllImageByGroupId(Integer integer, Pageable pageable) {
     Page<Image>  images= imageRepository.findAllBygroupidAndStatusOrderByTimeDesc(integer,"1",pageable);
       if(images!=null){
           return ServerResponse.createBySuccess(images);
       }else {
           return ServerResponse.createByErrorMessage("错误");
       }
    }

    @Override
    public ServerResponse deleteImagebyId(Integer id) {
        return null;
    }

    @Override
    public ServerResponse realDeleteImagebyId(Integer id) {
        return null;
    }

    @Override
    public ServerResponse recoverImage(Integer id) {
        return null;
    }

    @Override
    public ServerResponse<Page<Image>> findAllImage(Pageable pageable) {
        return null;
    }
}
