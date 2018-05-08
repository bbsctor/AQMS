
package com.ly.shiro;

import javax.transaction.Transactional;

import org.apache.shiro.authc.AccountException;  
import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.SimpleAuthenticationInfo;  
import org.apache.shiro.authz.AuthorizationInfo;  
import org.apache.shiro.authz.SimpleAuthorizationInfo;  
import org.apache.shiro.realm.AuthorizingRealm;  
import org.apache.shiro.subject.PrincipalCollection;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
  


import com.ly.entity.UserInfo;
import com.ly.service.UserService;
import com.ly.shiro.UsernamePasswordUsertypeToken; 
/** 
 * 登录Realm 
 * @author caihz 
 * 
 */  
public class ShiroAndEduRealm extends AuthorizingRealm {  
    final static Logger log = LoggerFactory.getLogger(ShiroAndEduRealm.class);  
      
    @Autowired  
    private UserService userService;  
    private UserInfo UserInfo;
    /** 
     * 登录认证回调函数,登录时调用. 
     * @param authcToken 登录页面参数，用户名和密码等 
     */  
    @Override  
    @Transactional
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
        UsernamePasswordUsertypeToken token = (UsernamePasswordUsertypeToken) authcToken;  
        String username = token.getUsername();  
        if (username == null){  
            throw new AccountException("账号不能为空");  
        }  
        //如果用户中心没有用户，则请求登录接口并查询用户中心对应的用户  
        String password = String.valueOf(token.getPassword());  
        //password = StringUtil.md5(password);  
        password = password.toLowerCase();  
     
		UserInfo=userService.checkLogin(token.getUsername(), password, token.getUsertype());  
        if (UserInfo != null){  
            return new SimpleAuthenticationInfo(UserInfo, UserInfo.getPassword(), getName());  
        }else {  
            return null;  
        }  
          
    }  
  
    /** 
     * 登录认证通过后的权限查询函数, 由于目前用户中心前台页面不需要权限控制，所以没写，以后如果需要可扩展 
     *  
     * @see org.apache.shiro.authz.AuthorizationInfo 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
  
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
  
        return info;  
    }  
}  