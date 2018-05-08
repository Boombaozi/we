/**
 * 
 */
package com.boombz.blog.service;


import com.boombz.blog.domain.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


public interface FileService {
	/**
	 * 保存文件
	 * @param File
	 * @return
	 */
	File saveFile(File file);
	
	/**
	 * 删除文件
	 * @param File
	 * @return
	 */
	void removeFile(String id);
	
	/**
	 * 根据id获取文件
	 * @param File
	 * @return
	 */
	File getFileById(String id);

	/**
	 * 分页查询，按上传时间降序
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<File> findAllFile(int pageIndex, int pageSize);

	//保存用户相册图片
	File saveImage(File file, HttpSession session, HttpServletRequest request);

	//保存用户头像
	File saveUserImage(File file,HttpSession session,HttpServletRequest request);

	File saveDayImage(File file,HttpSession session,
					  HttpServletRequest request,
					  String title,
					  String content,
					  Date time);
}
