package spt.vagmr.webdev.controller.bigEvent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spt.vagmr.webdev.util.AliOssUtil;
import spt.vagmr.webdev.vo.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/11/21-23:04
 * springBootProject
 * @Description
 */
@RestController
@RequestMapping("/bigEvent")
public class uploadFileController {
    /*获取文件名*/
    public String getFileName(MultipartFile file) {
        String fileOriName = file.getOriginalFilename();
        String ext = "";
        if (Objects.requireNonNull(fileOriName).indexOf(".") > 0) {
            ext = fileOriName.substring(
                    fileOriName.lastIndexOf(".") + 1);
        }
        // file.transferTo(new File("H:\\springFile\\" + fileName));
        return  UUID.randomUUID() + "." + ext;
    }
    public String getFileName(MultipartFile file, String dirName) {
        String fileOriName = file.getOriginalFilename();
        String ext = "";
        if (Objects.requireNonNull(fileOriName).indexOf(".") > 0) {
            ext = fileOriName.substring(
                    fileOriName.lastIndexOf(".") + 1);
        }
        // file.transferTo(new File("H:\\springFile\\" + fileName));
        return  dirName + UUID.randomUUID() + "." + ext;
    }

    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file){
        String url = "";
        try {
            String fileName = getFileName(file);
            /*使用阿里云oss文件上传*/
          url = AliOssUtil.uploadFile(fileName,file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(url);
    }
    /*处理多文件上传*/
    @PostMapping("/uploads")
    public Result<Map<String,Object>> uploadFiles(MultipartFile[] files){
        String url;
        Map<String,Object> map = new HashMap<>();
        try {
            for (MultipartFile file : files) {
                String fileName = getFileName(file,"fruit/");
                /*使用阿里云oss文件上传*/
                url = AliOssUtil.uploadFile(fileName,file.getInputStream());
                map.put(fileName,url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.error("检查上传的类型和名称，类型要为file,名字要为files");
        }
        return Result.success(map);
    }
}
