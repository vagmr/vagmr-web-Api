package spt.vagmr.webdev.controller.bigEvent;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spt.vagmr.webdev.service.bigEvent.userService;
import spt.vagmr.webdev.util.JwtUtil;
import spt.vagmr.webdev.util.Md5Util;
import spt.vagmr.webdev.util.ThreadLocalUtil;
import spt.vagmr.webdev.vo.Result;
import spt.vagmr.webdev.vo.bigEvent.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/12-11:29
 * springBootProject
 * @Description
 */
@RestController
@Validated
@RequestMapping("/bigEvent/user")
public class userController {
    @Autowired
    private userService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /*注册功能实现*/
    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public Result getUser(@Pattern(regexp = "^\\S{5,12}$",
            message = "用户名格式不正确,需要为5-12位的数字或者字母")
            String username,
            @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z]).{5,12}",
            message = "密码格式不正确,需要为5-12位，且同时包含数字和字母")
            String password) {
            //查询用户
            User u = userService.findByUserName(username);
            if (u == null) {
                //表明未注册
                //注册
                userService.register(username, password);
                return Result.success();
            }
            else return Result.error("用户已存在");

    }
    /*登录功能接口*/
    @PostMapping("/login")
    public Result<String> loginApi(@Pattern(regexp = "^\\S{5,12}$",
            message = "用户名格式不正确,需要为5-12位的数字或者字母")
                          String username,
                          @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-Z]).{5,12}",
                                  message = "密码格式不正确,需要为5-12位，且同时包含数字和字母")
                          String password) {
        //查找用户是否存在
        User u = userService.findByUserName(username);
        if(password == null) return Result.error("密码不能为空");
        if(u == null) return Result.error("用户不存在");
        //校验密码是否正确
        if(Md5Util.checkPassword(password,u.getPassword())){
            //生成token
            Map<String,Object> map = new HashMap<>();
            map.put("username",u.getUsername());
            map.put("id",u.getId());
            var jwt = JwtUtil.genToken(map);
            // return之前将token先存入redis,并设置过期时间为1小时
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(jwt,jwt,1, TimeUnit.HOURS);
            return  Result.success(jwt);
        }
        return Result.error("密码错误");
    }
    /*获取用户信息*/
    @GetMapping("/info")
    public Result<User> getUserInfo(/*@RequestHeader(value = "vagmr_key")
    String token*/){
//        var map = JwtUtil.parseToken(token);
        //改为从ThreadLocal变量中取数据
        Map<String,Object> map = ThreadLocalUtil.get();
      String username  = (String) map.get("username");
      User user = userService.findByUserName(username);
      return Result.success(user);
    }
    /*更新用户信息*/
    @PutMapping("/info")
    public Result<String> updateUserInfo(@Validated @RequestBody  User user){
        userService.updateUser(user);
        return Result.success("更新成功");
     }
     /*更新用户头像*/
    @PatchMapping("/info/avatar")
    public Result<String> updateAvatar
    (@RequestParam @URL(message = "必须是合法url")
     @NotEmpty(message = "不能为空") String avatarUrl )
    {
        userService.updateAvatar(avatarUrl);
        return Result.success("更新头像成功");
    }
    /*更新用户密码*/
    @PatchMapping("/info/password")
    public Result<String> updatePwd(@RequestBody Map<String,Object> pwdMap,
    @RequestHeader("vagmr_key") String token){
        String old_pwd = (String) pwdMap.get("oldPwd");
        String new_pwd = (String) pwdMap.get("newPwd");
        String re_pwd = (String) pwdMap.get("rePwd");
        /*必要参数有为空的*/
        if(! (StringUtils.hasLength(old_pwd) || StringUtils.hasLength(new_pwd)||
        StringUtils.hasLength(re_pwd))
        ){
            return Result.error("缺失必要参数");
        }
        /*密码不正确的*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username =(String) map.get("username");
        User user = userService.findByUserName(username);
        if(!Md5Util.checkPassword(old_pwd,user.getPassword())){
            return Result.error("密码不正确");
        }
        /*两次密码不一致*/
        if(!new_pwd.equals(re_pwd)){
            return Result.error("两次密码不一致");
        }
        userService.updatePwd(new_pwd);
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        return Result.success("更新密码成功");
    }
}
