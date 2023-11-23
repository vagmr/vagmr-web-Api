package spt.vagmr.webdev.vo;

import lombok.Data;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/28-16:16
 * springBootProject
 * @Description
 */
@Data
public class LoginStatus {
    private boolean isLogin;
    private String message;
    private Acc acc;
}
