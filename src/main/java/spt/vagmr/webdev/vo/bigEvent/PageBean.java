package spt.vagmr.webdev.vo.bigEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/21-21:36
 * springBootProject
 * @Description 封装分页数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    public long total;//总条数
    public List<T> items;//分页数据
}
