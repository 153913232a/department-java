package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("user")
public class UserController {
    private List<String> sessionArray = new ArrayList<String>();

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result<User> login(@RequestBody Map<String, Object> user, HttpServletRequest request, HttpServletResponse response){

        Result<User> ret = new Result<>();
        List<User> userList = userService.getUsers(user);

        String userName = user.get("userName").toString();
        String passWord = user.get("passWord").toString();

        if (userList.size() == 1) {
            if (sessionArray.indexOf(userName) !=-1) {
                ret.setCode(-4);
                ret.setTotal(0);
                ret.setMessage("no repeat login");
                return ret;
            }
            ret.setCode(1);
            ret.setTotal(1);
            ret.setMessage("ok");
            request.getSession().setAttribute(userName, passWord); // 设置session
            sessionArray.add(userName);
            Cookie cookie = new Cookie(userName,passWord);  // 前端设置cookie
            cookie.setPath("/");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
        } else {
            ret.setCode(-1);
            ret.setTotal(0);
            ret.setMessage("userName or passWord error");
        }
        return ret;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies =  request.getCookies();
        if(cookies.length > 0){
            for(Cookie cookie : cookies){
                String userName = cookie.getName();

                for (int i=0; i < sessionArray.size(); i++) {
                    if (sessionArray.get(i).equals(userName)) {
                        request.getSession().removeAttribute(userName);  // 移除session
                        sessionArray.remove(i);
                        return userName;
                    }
                }

            }
        }
        return "";
    }
    @RequestMapping("/auth")
    public Result<User> auth(@RequestBody Map<String, Object> user, HttpServletRequest request){
        Result<User> ret = new Result<>();
        Cookie[] cookies =  request.getCookies();
        if(cookies.length > 0){
            for(Cookie cookie : cookies){
                String userName = cookie.getName();
                String passWord = cookie.getValue();
                String ypassWord = (String)request.getSession().getAttribute(userName);
                if (ypassWord == null) {
                } else if (ypassWord.equals(passWord)) {
                    ret.setCode(1);
                    ret.setTotal(1);
                    ret.setMessage("ok");
                    return ret;
                }
            }
        } else {  // 时间超时或没有登录时
            ret.setCode(-2);
            ret.setTotal(0);
            ret.setMessage("time is exceed or no auth");
            String name = user.get("userName").toString();
            if (request.getSession().getAttribute(name)!=null) {  // 有session清空
                request.getSession().removeAttribute(name);
            }
            return ret;
        }
        ret.setCode(-3);  // -3没有权限
        ret.setTotal(1);
        ret.setMessage("time is exceed or no auth");
        String name = user.get("userName").toString();
        if (request.getSession().getAttribute(name)!=null) {  // 有session清空
            request.getSession().removeAttribute(name);
        }
        return ret;
    }

    @RequestMapping("/getUsers")
    public Result<User> getUsers(){
        Result<User> retResult = new Result<>();
        List<User> user = userService.getUsers(new HashMap());
        retResult.setData(user);
        retResult.setCode(0);
        retResult.setMessage("ok");
        retResult.setTotal(user.size());
        return retResult;
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody Map<String, Object> user) {
        try{
            userService.insert(user);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @RequestMapping("/update")
    public void update(@RequestBody Map<String, Object> user) {
        try{
            userService.update(user);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody List<User> user) {
        try{
            for (int i=0; i < user.size(); i++) {
                userService.delete(user.get(i).getId());
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
