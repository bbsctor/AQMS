package com.ly.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ly.entity.UserInfo;
import com.ly.service.UserService;
import com.ly.dao.UserInfoMapper;

@Service("UserInfoService")
@Transactional
public class UserServiceImpl implements UserService {
	    @Resource
	    private UserInfoMapper UserInfoMapper;
	    /* 登陆验证 */
	    public UserInfo checkLogin(String username,String password,String Permission) {
	    	 UserInfo UserInfo=null;
	    try {
	    	 UserInfo = UserInfoMapper.getUserByName(username);
		        if (UserInfo != null && UserInfo.getPassword().equals(password)&& UserInfo.getUsertype().equals(Permission))	        	
		        {
		            return UserInfo;
		        }
		        else
		        	return null;		       
		} catch (Exception e) {
			// TODO: handle exception
		}
	        //根据用户名实例化用户对象
	        return UserInfo;
	    }

		public UserInfo getUserByName(String name) {
			// TODO Auto-generated method stub
			return this.UserInfoMapper.getUserByName(name);
		}

		public int UpdateUser(UserInfo user) {
			// TODO Auto-generated method stub
			return this.UserInfoMapper.updateByPrimaryKey(user);
		}

	
		
}

