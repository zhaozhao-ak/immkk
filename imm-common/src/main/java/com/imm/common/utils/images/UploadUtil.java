package com.imm.common.utils.images;

import com.imm.common.web.HttpContext;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

/**
 * 图片工具类
 *
 * @author HuXinsheng
 */
public class UploadUtil {

    private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    public static String DEFAULT_PREVFIX = "thumb_";
    /**
     * 建议该值为false
     */
    public static Boolean DEFAULT_FORCE = false;

    /**
     * 获取最终图片访问路径
     *
     * @param dir  子目录
     * @param file 文件名
     * @return 地址
     */
    public static String getFullUrl(String path, String dir, File file) {
        String name = file.getName();
        if (!StringUtils.isEmpty(dir)) {
            name = dir + "/" + name;
        }
        String url = HttpContext.getUrlRoot() + path + "/get/" + name;
        logger.info("getUrl{}", url);
        return url;
    }

    /**
     * 获取最终图片访问路径
     *
     * @param dir  子目录
     * @param file 文件名
     * @return 地址
     */
    public static String getUrl(String path, String dir, File file) {
        String name = file.getName();
        if (!StringUtils.isEmpty(dir)) {
            name = dir + "/" + name;
        }
        String url = HttpContext.getContextPath() + path + "/get/" + name;
        logger.info("getUrl{}", url);
        return url;
    }

    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 </p>
     *
     * @param imagePath 原图片路径
     * @param w         缩略图宽
     * @param h         缩略图高
     * @param prevfix   生成缩略图的前缀
     * @param force     是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public static String thumbnailImage(String imagePath, String folder, int w, int h, String prevfix, boolean force) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if (imgFile.getName().contains(".")) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || !types.toLowerCase().contains(suffix.toLowerCase())) {
                    logger.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return "";
                }
                logger.debug("target image's size, width:{}, height:{}.", w, h);
                Image img = ImageIO.read(imgFile);
                if (!force) {
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if ((width * 1.0) / w < (height * 1.0) / h) {
                        if (width > w) {
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                            logger.debug("change image's height, width:{}, height:{}.", w, h);
                        }
                    } else {
                        if (height > h) {
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                            logger.debug("change image's width, width:{}, height:{}.", w, h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
                // 将图片保存在原目录并加上前缀
                String pathName = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prevfix + imgFile.getName();
                File newFile = new File(pathName);
                ImageIO.write(bi, suffix, newFile);
                logger.debug("缩略图在原路径下生成成功");
                return getUrl("/res/image", folder, newFile);
            } catch (IOException e) {
                logger.error("generate thumbnail image failed.", e);
            }
        } else {
            logger.warn("the image is not exist.");
        }
        return "";
    }

    /**
     * 获取图片宽度
     *
     * @param file 图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is;
        BufferedImage src;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            //得到源图宽
            ret = src.getWidth(null);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * 获取图片高度
     *
     * @param file 图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is;
        BufferedImage src;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            // 得到源图高
            ret = src.getHeight(null);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void copyFile(MultipartFile file, File locFile) throws IOException {
        InputStream input = file.getInputStream();
        OutputStream output = new FileOutputStream(locFile);
        IOUtils.copy(input, output);
        output.close();
        input.close();
    }

    public static boolean delete(String requestUri, String fileDir) {
        String requestPath = "get";
        String path = requestUri.substring(requestUri.indexOf(requestPath) + 3);
        File directory = new File(fileDir);
        String tempPath = directory.getAbsolutePath() + path;
        File deleteFile = new File(tempPath);
        if (deleteFile.exists()) {
            return deleteFile.delete();
        }
        return false;
    }
}
