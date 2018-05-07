package com.boombz.blog.controller;

import com.boombz.blog.domain.File;
import com.boombz.blog.repository.FileRepository;
import com.boombz.blog.serviceTest.FileService;
import com.boombz.blog.util.FileUtil;
import com.boombz.blog.util.MD5Util;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05
 **/
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;


    @GetMapping
    public ModelAndView goUploadImg() {
        //跳转到
        return new ModelAndView("users/upload");
    }
    /**
     * 上传头像
     *
     * @param file
     * @param
     * @return
     */
    @PostMapping
    public String userImageUpdate(@RequestParam("file") MultipartFile file,
                                   HttpServletRequest request,
                                   HttpSession session) {

        try {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),
                    new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            fileService.saveUserImage(f,session,request);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return "redirect:/file";
        }
        return "redirect:/file";
    }


    @PostMapping("/image")
    public String imageUpdate(@RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request,
                                  HttpSession session) {

        try {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),
                    new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            fileService.saveImage(f,session,request);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Your " + file.getOriginalFilename() + " is wrong!");
            return "redirect:/file";
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/file";
    }
    /**
     * 显示文件
     *
     * @param id
     * @return
     */
    @GetMapping("/view/{id}")
    @ResponseBody
    public ResponseEntity<Object> serveFileOnline(@PathVariable String id) {

        File file = fileService.getFileById(id);

        if (file!=null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.getName() + "\"")
                    .body(file.getContent().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }

    }

}
