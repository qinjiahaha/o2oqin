package com.qinjia.o2oqin.util;



import ch.qos.logback.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathUtil {
    private static final Logger logger = LoggerFactory.getLogger(PathUtil.class);
    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        logger.debug("os.name: {}", os);
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "G:/guanlan";
        } else {
            basePath = "/home/artisan/o2o/image";
        }
        // 根据操作系统的不同，使用当前操作系统的路径分隔符替换掉，我们写的basePath中的路径分隔符，当然了也可以在basePath赋值的时候直接使用seperator
        basePath = basePath.replace("/", seperator);
        logger.debug("basePath: {}", basePath);
        return basePath;
    }
    public static String getShopImagePath(long shopId) {
        String shopImgPath = "/upload1/item1/shop/" + shopId + "/";
        shopImgPath = shopImgPath.replace("/", seperator);
        logger.debug("shopImgPath: {}", shopImgPath);
        return shopImgPath;
    }


}
