package spt.vagmr.webdev.service.bigEvent.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import spt.vagmr.webdev.mapper.bigEvent.bigEventMapper;
import spt.vagmr.webdev.service.bigEvent.userService;
import spt.vagmr.webdev.util.Md5Util;
import spt.vagmr.webdev.util.ThreadLocalUtil;
import spt.vagmr.webdev.vo.bigEvent.User;

import java.util.Map;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/12-11:52
 * springBootProject
 * @Description
 */
@Service
public class userServiceImpl implements userService {


    @Resource
    private bigEventMapper bigEventMapper;
    @Override
    public User findByUserName(String username) {
        return bigEventMapper.getByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        //加密
        String newPassword = Md5Util.getMD5String(password);
        //注册
        bigEventMapper.addUser(username,newPassword);
    }

    @Override
    public void updateUser(User user) {
        //更新数据
        bigEventMapper.putUser(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
       Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        bigEventMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String new_pwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        String psd = Md5Util.getMD5String(new_pwd);
        bigEventMapper.updatePwd(psd,id);
    }

}
