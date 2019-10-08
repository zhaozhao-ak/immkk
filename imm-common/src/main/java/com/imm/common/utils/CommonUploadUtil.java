package com.imm.common.utils;

import com.imm.common.result.UploadInfo;
import com.imm.common.result.UploadResult;
import com.imm.common.utils.images.UploadUtil;
import com.imm.common.utils.time.DateUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 公共上传文件工具类
 *
 * @author rjyx_huxinsheng
 */
public class CommonUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUploadUtil.class);

    /**
     * 公共上传文件
     *
     * @param storeDir   存储目录
     * @param dir        子目录
     * @param file       上传的文件
     * @param isFullPath 是否全路径(true:http://www.xxx.com/项目名/子目录/文本名.jpg;false:/项目名/子目录/文本名.jpg)
     * @return UploadResult
     */
    public static UploadResult upload(String storeDir, String dir, MultipartFile file, boolean isFullPath) {

        String dateString = DateUtils.formatShort(new Date());
        String separator = "/";
        dir = dir + separator + dateString;
        String storePath = storeDir + separator + dir;
        logger.info("save file path = {}", storePath);
        File directory = new File(storePath);
        boolean state = directory.mkdirs();
        String original = file.getOriginalFilename();

        String suffix;
        int index;
        assert original != null;
        if ((index = original.lastIndexOf(".")) != -1) {
            suffix = original.substring(index).toLowerCase();
        } else {
            return UploadResult.error("upload file error.");
        }

        String fileName = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes())
                .substring(8, 24);
        //生成文件名
        String name = fileName + suffix;
        String path = directory.getAbsolutePath() + separator + name;
        String thumbnailsName = fileName + "_thumb" + suffix;
        String thumbnailsPath = directory.getAbsolutePath() + separator + thumbnailsName;
        logger.info("upload path = {}", path);
        logger.info("thumbnailsPath path = {}", thumbnailsPath);

        // 不同的文件处理
        String resPath;
        if (".pdf".equals(suffix) || ".doc".equals(suffix) || ".docx".equals(suffix)) {
            // 暂时是pdf 文件
            resPath = separator + "res" + separator + "file";
        } else {
            resPath = separator + "res" + separator + "image";
        }
        String url = null;
        String thumbnailsUrl = null;
        try {
            File locFile = new File(path);
            UploadUtil.copyFile(file, locFile);
            if (isFullPath) {
                url = UploadUtil.getFullUrl(resPath, dir, locFile);
            } else {
                url = UploadUtil.getUrl(resPath, dir, locFile);
            }
            logger.info("resource url = {}", url);
            Thumbnails.of(path).scale(1f).outputQuality(0.25f).toFile(thumbnailsPath);
            thumbnailsUrl = url.replace(name, thumbnailsName);
        } catch (IOException e) {
            if (!StringUtils.isEmpty(url)) {
                UploadUtil.delete(url, storeDir);
            }
            e.printStackTrace();
        }
        UploadInfo info = new UploadInfo();
        info.setPath(url);
        info.setThumbPath(thumbnailsUrl);
        return UploadResult.ok(info);
    }

    /**
     * 公共上传文件,返回全路径
     * http://www.xxx.com/项目名/子目录/文本名.jpg
     *
     * @param storeDir 存储目录
     * @param dir      子目录
     * @param file     上传的文件
     * @return UploadResult
     */
    public static UploadResult upload(String storeDir, String dir, MultipartFile file) {
        return upload(storeDir, dir, file, true);
    }
}
