package com.boombz.blog.controller;




import com.boombz.blog.domain.User;
import com.boombz.blog.util.ServerResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.boombz.blog.util.ServerResponse.createBySuccessMessage;


/**
 * 用户控制器.
 *
 */
@RestController
@RequestMapping("/")
public class HelloController {

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView index(Model model,HttpSession session) {
		//如果用户未登录，返回登录界面
		if(session.getAttribute("user")==null){
			model.addAttribute("msg","请登录或注册");
			return new ModelAndView("users/login","Model",model);
		}
		//如果登录，转到主页
		return new ModelAndView("index/index", "Model", model);
	}



	@GetMapping("about")
	public ModelAndView about() {
		return new ModelAndView("index/about");
	}



	@GetMapping("test")
	public ModelAndView test() {
		return new ModelAndView("index/test");
	}

}
