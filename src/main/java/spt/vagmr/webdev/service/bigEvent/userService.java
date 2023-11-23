package spt.vagmr.webdev.service.bigEvent;


import spt.vagmr.webdev.vo.bigEvent.User;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/12-11:41
 * springBootProject
 * @Description
 */
public interface userService {

    User findByUserName(String username);

    void register(String username, String password);

    void updateUser(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String new_pwd);
}
