package com.zjw.seatmanager.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@TableName("user")

@Data
@Repository
public class User implements Serializable {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name = "perms")
    private String perms;
//   // private String salt= "8d78869f470951332959580424d4bf4f";//加密密码的盐
//    private int state=0;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.3: 已上传认证资料未审核. 4: 已上传认证资料审核未通过
//    private String  remarks;//描述
//    private String  phone;//手机
//    private String  reason;//审核原因

    /**
     * 密码盐.
     * @return
     */
//    public String getCredentialsSalt(){
//        return this.name+this.salt;
//    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
