package com.wang.jk.common.util;

import com.wang.jk.common.prop.JkProperties;
import com.wang.jk.pojo.po.Image;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class Uploads {
    public static JkProperties.Upload upload;
    static {
        upload = JkProperties.get().getUpload();
    }

    public static String uploadVideo(MultipartFile multipartFile) throws Exception {
        // 目录（相对）
        String dirPath = upload.getVideoRelativePath();
        return uploadFile(multipartFile, dirPath);
    }

    public static Image uploadImage(MultipartFile multipartFile) throws Exception {
        // 目录（相对）
        String dirPath = upload.getImageRelativePath();
        String filepath = uploadFile(multipartFile, dirPath);
        if (filepath == null) return null;
        // 返回图片对象
        Image image = new Image();
        image.setUri(filepath);
        return image;
    }

    private static String uploadFile(MultipartFile multipartFile, String dir) throws Exception {
        if (multipartFile == null || multipartFile.getSize() == 0) return null;
        // 文件扩展名
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        // 文件名
        String filename = UUID.randomUUID() + "." + extension;
        // 文件路径（相对）
        String filepath = dir + filename;
        // 文件路径（绝对）
        String fullFilepath = upload.getBasePath() + filepath;
        File file = new File(fullFilepath);
        // 创建文件夹
        FileUtils.forceMkdirParent(file);
        // 剪切
        multipartFile.transferTo(file);
        // 返回图片对象
        return filepath;
    }

    public static void remove(String uri) {
        if (StringUtils.isEmpty(uri)) return;
        String path = upload.getBasePath() + uri;
        FileUtils.deleteQuietly(new File(path));
    }

    public static void remove(Image image) {
        if (image == null) return;
        remove(image.getUri());
    }

    public static void remove(List<Image> images) {
        if (images == null) return;
        for (Image image : images) {
            remove(image);
        }
    }
}
