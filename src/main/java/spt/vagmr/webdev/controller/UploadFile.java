package spt.vagmr.webdev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author vagmr
 * @version 0.0.1
 *          2023/10/29-18:34
 *          springBootProject
 * @Description
 */
@RestController
public class UploadFile {
    @PostMapping("/upload")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        {
            System.out.println("开始处理上传文件");
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> fileMap = new HashMap<>();
            try {
                if (!file.isEmpty()) {
                    fileMap.put("上传文件名称", file.getName());
                    long size = file.getSize();
                    if (size > 1024 * 1024) {
                        size = size / 1024 / 1024;
                        fileMap.put("上传文件大小", size + "M");
                    } else if (size > 1024) {
                        size = size / 1024;
                        fileMap.put("上传文件大小", size + "K");
                    } else if (size > 0) {
                        fileMap.put("上传文件大小", size + "B");
                    }
                    fileMap.put("上传文件类型", file.getContentType());
                    var ext = "";
                    String fileName = file.getOriginalFilename();
                    if (Objects.requireNonNull(fileName).indexOf(".") > 0) {
                        ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                    }
                    // 生成服务器所用的文件名称
                    String svFileName = UUID.randomUUID() + "." + ext;
                    /*
                     * 部署到服务器时改为服务器上的路径
                     * String filePath = directory + File.separator + "file.txt";
                     */
                    String path = "H:/springFile/" + svFileName; // 存储文件的路径

                    fileMap.put("服务器文件名称", svFileName);
                    file.transferTo(new java.io.File(path)); // 将文件保存到磁盘上
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("msg", "响应成功");
            map.put("code", 200);
            map.put("data", fileMap);
            return map;
        }
    }

    // 多文件上传
    @SuppressWarnings({ "all" })
    @PostMapping("/uploads")
    public Map<String, Object> uploadFiles(@RequestParam("file") MultipartFile[] files) {
        System.out.println("开始处理上传文件");
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> fileMapList = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                // 必须在循环内实例化，不然数据会被覆盖
                Map<String, Object> fileMap = new HashMap<>();
                if (!file.isEmpty()) {
                    fileMap.put("上传文件名称", file.getName());
                    // 根据文件大小显示不同单位
                    long size = file.getSize();
                    if (size > 1024 * 1024) {
                        size = size / 1024 / 1024;
                        fileMap.put("上传文件大小", size + "M");
                    } else if (size > 1024) {
                        size = size / 1024;
                        fileMap.put("上传文件大小", size + "K");
                    } else if (size > 0) {
                        fileMap.put("上传文件大小", size + "B");
                    }
                    fileMap.put("上传文件类型", file.getContentType());
                    var ext = "";
                    String fileName = file.getOriginalFilename();
                    if (Objects.requireNonNull(fileName).indexOf(".") > 0) {
                        ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                    }
                    // 生成服务器所用的文件名称
                    String svFileName = UUID.randomUUID() + "." + ext;
                    /*
                     * 部署到服务器时改为服务器上的路径
                     * String filePath = directory + File.separator + "file.txt";
                     */
                    String path = "H:/springFile/" + svFileName; // 存储文件的路径

                    fileMap.put("服务器文件名称", svFileName);
                    file.transferTo(new java.io.File(path)); // 将文件保存到磁盘
                    // 把集合数据添加到list中
                    fileMapList.add(fileMap);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("msg", "响应成功");
        map.put("code", 200);
        map.put("data", fileMapList);
        return map;
    }
}
