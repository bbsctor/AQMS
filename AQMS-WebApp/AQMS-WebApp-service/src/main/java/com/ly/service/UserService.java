
package com.ly.service;

import com.ly.entity.UserInfo;

public interface UserService
{
	 public UserInfo checkLogin(String username,String password,String Permission);
	 
	 public int UpdateUser(UserInfo user);
	 
	 public UserInfo getUserByName(String name);  

}