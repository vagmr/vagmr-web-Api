package spt.vagmr.webdev.vo;

import lombok.Data;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/26-22:44
 * springBootProject
 * @Description
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
