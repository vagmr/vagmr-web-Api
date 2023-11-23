package spt.vagmr.webdev.controller.bigEvent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spt.vagmr.webdev.util.AliOssUtil;
import spt.vagmr.webdev.vo.Result;

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
    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file){
        String url = "";
        try {
            String fileOriName = file.getOriginalFilename();
            String ext = "";
            if (Objects.requireNonNull(fileOriName).indexOf(".") > 0) {
               ext = fileOriName.substring(
                       fileOriName.lastIndexOf(".") + 1);
            }
            String fileName = UUID.randomUUID() + "." + ext;
            // file.transferTo(new File("H:\\springFile\\" + fileName));
            /*使用阿里云oss文件上传*/
          url = AliOssUtil.uploadFile(fileName,file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(url);
    }
}
