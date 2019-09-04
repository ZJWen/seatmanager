package com.zjw.seatmanager.config;

import javax.annotation.Resource;

import com.zjw.seatmanager.entity.User;
import com.zjw.seatmanager.service.UserService;
import com.zjw.seatmanager.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
//    权限配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        User user  = (User)principals.getPrimaryPrincipal();
//        for(SysRole role:user.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
//        //CacheUntil.setCacheTree(authorizationInfo);
        //1.从principalCollection中来获取登录用户的信息
        //Object principal=principals.getPrimaryPrincipal();


        //2.利用登录的用户的信息来获得当前用户的角色或权限（可能需要查询数据库）
       // Set<String> roles=new HashSet<>();
        ///roles.add("user");
       // if ("admin".equals(principal)){
          //  roles.add("admin");
     //   }

        //3.创建SimpleAuthorizationInfo,并设置器reles属性
       // SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo(roles);

        // 4.返回SimpleAuthorizationInfo对象
        //-------------------------------------
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //添加资源的授权字符串
        //info.addStringPermission("user:add");
        Subject subject=SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        info.addStringPermission(user.getPerms());

        return info;

    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        System.out.println("ShiroRealm.doGetAuthenticationInfo()");
       log.info("验证当前subject时获取到token为"+token.toString());
       String username=token.getUsername();
       User user=userService.findUserByName(username);
       if(user!=null){
           //若存在，将此用户存放到登录认证info中，无需自己做密码对比，shiro会为我们进行密码对比校验
           return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
       }
       return null;

    }



}
