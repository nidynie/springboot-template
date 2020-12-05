//package com.meiliyaya.web.component;
//
//import com.meiliyaya.web.enums.FileTypesEnum;
//import com.meiliyaya.web.util.IdUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.DigestUtils;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//
//@Slf4j
//@Component
//public class FSComponent {
//
//    @Value("${fs.base-path}")
//    private String base_path;
//
//
//    @Value("${fs.host-path}")
//    private String hostPath;
//
//
//    /**
//     * 保存文件
//     *
//     * @param inputStream 文件流
//     * @param fileName    文件名称
//     * @param type        类型
//     * @return
//     */
//    public String saveFile(InputStream inputStream, String fileName, String type) {
//
//        if (inputStream == null || StringUtils.isBlank(fileName) || StringUtils.isBlank(type)) {
//            throw new NullPointerException("入参不能为空");
//        }
//
//        //基础路径/类型名称/日期
//        //String folderPath = base_path + "/"++ + new SimpleDateFormat("yyyy-mm-dd").format(new Date());
//        String folderPath = String.format("%s/%s/%s", base_path, type,
//                new SimpleDateFormat("yyyyMMdd").format(new Date()));
//        if (!new File(folderPath).exists()) {
//            new File(folderPath).mkdirs();
//        }
//        String targetName = IdUtil.getId() + "_" + DigestUtils.md5DigestAsHex(getShortName(fileName).getBytes()) + "." + getFileType(fileName);
//        String filePath = folderPath + "/" + targetName;
//        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
//            IOUtils.copy(inputStream, fileOutputStream);
//            fileOutputStream.flush();
//            return hostPath + filePath;
//        } catch (Exception e) {
//            log.error("上传文件出错，错误信息:{}", e.getMessage(), e);
//        }
//        return null;
//    }
//
//
//    private String getShortName(String fileName) {
//        int indexPoint = fileName.indexOf(".");
//        return fileName.substring(0, indexPoint);
//    }
//
//    private String getFileType(String fileName) {
//        int indexPoint = fileName.indexOf(".");
//        return fileName.substring(indexPoint + 1);
//    }
//
//
//}
