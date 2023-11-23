package spt.vagmr.webdev.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/30-22:17
 * springBootProject
 * @Description
 */

@Data
public class PostsData {

    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;
}
