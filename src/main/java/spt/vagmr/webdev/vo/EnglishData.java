package spt.vagmr.webdev.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/31-14:00
 * springBootProject
 * @Description 每日英文名句的实体类
 */

@Data
public class EnglishData {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("content")
    private String content;
    @JsonProperty("note")
    private String note;
    @JsonProperty("author")
    private String author;
    @JsonProperty("date")
    private Date date;
}
