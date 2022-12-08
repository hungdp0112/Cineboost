package com.org.app.helper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

    //filePath la resources/images/ poster | profilepic 
    public static boolean saveImage(String filePath, String fileName, File file) {
        File dir = new File(filePath);
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, fileName);
        try {

            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    
    public static ImageIcon readImage(String filePath, String fileName) {
        File file = new File(filePath, fileName);
        return new ImageIcon(file.getAbsolutePath());
    }

    public static boolean deleteImage(String filePath, String fileName) {
        try {
            File file = new File(filePath, fileName);
            return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ImageIcon getPosterImage(String fileName) {
        try {
            String path = "/images/posters/";
            return new ImageIcon(ImageUtil.class.getResource(path+fileName));
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi đọc hình ảnh.");
        }
    }
    
    public static ImageIcon getDoAnImage(String fileName) {
        try {
            String path = "/images/food_drink/";
//            System.out.println("path:" + path + fileName);
            return new ImageIcon(ImageUtil.class.getResource(path+fileName));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi đọc hình ảnh.");
        }
    }
    
    public static ImageIcon getAvtImage(String fileName) {
        try {
            String path = "/images/avatars/";
            
//            System.out.println("filename "+fileName);
            ImageIcon i = new ImageIcon(ImageUtil.class.getResource(path + fileName));
            return i;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi đọc hình ảnh.");
        }
    }
}
