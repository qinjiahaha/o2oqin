package com.qinjia.o2oqin.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    private static  String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();//获取classpath的绝对路径
    private static  final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r=new Random();
    public static String generateThumbnail(InputStream thumbnailInputStream,String fileName,String targetAddr){
        String realFileName=getRandomFileName();
        String extension=getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
        try{
            Thumbnails.of(thumbnailInputStream).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+ "/watermark.jpg")),0.25f).outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        return realFileName;
    }
    public static String getRandomFileName(){
        int rannum=r.nextInt(89999)+10000;
        String nowTimeStr=sDateFormat.format(new Date());
        return nowTimeStr+rannum;

    }

    private static String getFileExtension(String fileName){
        String extension = fileName.substring(fileName.lastIndexOf("."));
        logger.debug("extension: {}", extension);
        return extension;
    }
    private static void makeDirPath(String targetAddr){
        String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
        File dirPath=new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }
    public static void main(String[] args) throws IOException {



            Thumbnails.of(new File("G:\\guanlan\\xiandao.jpg")).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+ "/watermark.jpg")),0.25f).outputQuality(0.8f).toFile("G:\\guanlan\\xiandao1.jpg");

    }

}
