package com.ly.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ly.entity.UserInfo;
import com.ly.service.UserService;
import com.ly.shiro.UsernamePasswordUsertypeToken;

@Controller

@Scope(value="prototype")  //@Scope(“prototype”)表示将Action的范围声明为原型
						   //利用容器的scope=”prototype”来保证每一个请求有一个单独的Action来处理，避免struts中Action的线程安全问题。

@SessionAttributes("UserInfo")//@SessionAttributes可以直接把model中的UserInfo(也就key)放入其中这样保证了session中存在UserInfo这个对象
@RequestMapping("/user")
//RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
public class UserInfoController {
	private static Logger logger = Logger.getLogger(UserInfoController.class);  
	    @Resource
	    private UserService UserInfoService;

	    @RequestMapping(value="/login",method=RequestMethod.POST)
	   public String login(UserInfo UserInfo,Model model) throws Exception {
	    	
	   try {
		   UserInfo=UserInfoService.checkLogin(UserInfo.getUsername(), UserInfo.getPassword(),UserInfo.getUsertype());

		     logger.info(JSON.toJSONString(UserInfo));  
		        if(UserInfo!=null){
		            model.addAttribute("UserInfo",UserInfo);
		            UserInfo.setIsonline("on");
		            UserInfoService.UpdateUser(UserInfo);
		            if(UserInfo.getUsertype().equals("普通用户"))
		            	return "main/usermain";
		            return "main/main";// 路径 WEB-INF/JSP/welcome.jsp            
		        }
	} catch (Exception e) {
		// TODO: handle exception
		  System.out.println("连接失败");
	}
	   
	        return "fail";
	       
	    }
	    
	    @RequestMapping(value="/shiro-login",method=RequestMethod.POST)
	    public ModelAndView login1(@RequestParam("username") String username, 
	            @RequestParam("password") String password,@RequestParam("permission") String permission){
	    	ModelAndView mv= new ModelAndView();
	        Subject currentUser = SecurityUtils.getSubject();  
            UsernamePasswordUsertypeToken token = new UsernamePasswordUsertypeToken(username,password, false,permission);  
	        try {
	            //执行认证操作. 
	        	currentUser.login(token);
	        	Session session = currentUser.getSession();
	        	mv.setViewName("welcome");
	        	return mv;
	        }catch (AuthenticationException ae) {
	            System.out.println("登陆失败: " + ae.getMessage());
	            ae.printStackTrace();
	            mv.setViewName("fail");
	            return mv;
	        }
	    
	    }

	    @RequestMapping("/outLogin")
	    public String outLogin(HttpSession session,UserInfo UserInfo){
	         UserInfo.setIsonline("off");
	        //通过session.invalidata()方法来注销当前的session
	        session.invalidate();
	        return "login";
	    }
	    
	  
	    
}
