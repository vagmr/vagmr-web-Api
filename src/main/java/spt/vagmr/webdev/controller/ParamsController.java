package spt.vagmr.webdev.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spt.vagmr.webdev.mapper.main.AccMapper;
import spt.vagmr.webdev.vo.Acc;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/27-16:51
 * springBootProject
 * @Description
 */
@RestController
public class ParamsController {
    @Resource
    private AccMapper accMapper;

    @GetMapping("/params/ph")
    public String getParams(Integer id, String name){
        return "参数id为%s,参数name为%s".formatted(id,name);
    }
/*通过requestParam接收参数,默认required为true*/
    @GetMapping("/params/re")
    public String getParams2(@RequestParam(value = "id") Integer id,
     @RequestParam(value = "name",required = false,defaultValue = "vagmr") String name)
    {
        return "参数id为%s,参数name为%s".formatted(id,name);
    }
    //通过对象接收参数
    @GetMapping("/params/ac")
    public String getParams3(Acc user){
        return "账户信息为:%s".formatted(user.toString());
    }
    //获取请求头的信息
    @GetMapping("params/hd")
    public String getParams4(@RequestHeader("Connection") String header){
        return "方法4，获取到的头信息为%s".formatted(header);
    }
    //接收请求体数据
    @PostMapping("/params/bd")
    public String getParams5(@RequestBody Acc user){
        return "账户信息为:id为%d,名字是%s,密码是%s"
                .formatted(user.getId(),user.getUsername(),user.getPassword());
    }
    //利用reader,inputStream读取请求体的数据
    @PostMapping("/params/json")
    public String getParams6(Reader reader){
        StringBuilder content = new StringBuilder();
        try(BufferedReader read = new BufferedReader(reader)){
            var line = "";
            while ((line = read.readLine())!= null){
                content.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return content.toString();
    }
    //接收同名参数转成数组,参数要和形参同名
    @GetMapping("/params/arr")
    public String getParams7(Integer[] id){
        List<Integer> params = Arrays.stream(id).toList();
        return "方法7，接收同名参数作为数组%s".formatted(params.toString());
    }
    /*带验证的bean，返回验证结果,下面是新增操作*/
    @PostMapping("/params/vd")
    public Map<String,Object> postParams(@Validated @RequestBody Acc acc, BindingResult res){
        Map<String,Object> map = new HashMap<>();
        if (res.hasErrors()){
         List<FieldError> errors = res.getFieldErrors();
         for(int i = 0;i<errors.size();i++){
            var field =  errors.get(i);
            map.put(i + "-" +field,field.getDefaultMessage());
         }
        }else {
          int row =  accMapper.insert(acc);
          Acc[] datas = accMapper.selectAll();
          Acc data = datas[datas.length - 1];
          map.put("code",200);
          map.put("msg","新增成功%d".formatted(row));
          map.put("data",data);
        }
        return map;
    }
/*下面是编辑操作*/
    @PostMapping("/params/ed")
public Map<String,Object> editParams(@Validated(Acc.EditGroup.class) @RequestBody Acc acc, BindingResult res){
        Map<String,Object> map = new HashMap<>();
        if (res.hasErrors()){
            List<FieldError> errors = res.getFieldErrors();
            Map<String,Object> errorMap = new LinkedHashMap<>();
            for(int i = 0;i<errors.size();i++){
                var field =  errors.get(i);
                errorMap.put("error-%d: %s".formatted(i,field.getField()),field.getDefaultMessage());
                map.put("code",500);
                map.put("msg","编辑失败");
                map.put("data",errorMap);
            }
        }else {
           int row = accMapper.updateInfo(acc.getUsername(),acc.getPassword(),acc.getId());
           map.put("code",200);
           map.put("source","vagmr");
           map.put("msg","编辑成功了%d行数据，id为%d".formatted(row,acc.getId()));
           map.put("data",acc);
        }
        return map;
    }
}

