package com.boombz.blog.service;

import com.boombz.blog.domain.Image;
import com.boombz.blog.util.ServerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05-05 16
 **/
public interface ImageService {

    public ServerResponse addImage(Image image, HttpSession session);

    public ServerResponse<Page<Image>> findAllImageByGroupId(Integer integer, Pageable pageable);

    public ServerResponse  deleteImagebyId(Integer id);

    public ServerResponse  realDeleteImagebyId(Integer id);

    public ServerResponse  recoverImage(Integer id);

    public ServerResponse<Page<Image>>  findAllImage(Pageable pageable);
}
