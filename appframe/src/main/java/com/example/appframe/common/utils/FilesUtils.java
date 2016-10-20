package com.example.appframe.common.utils;

import java.io.File;
import java.io.IOException;

/**
 * @FileName: com.example.appframe.common.utils.FilesUtils.java
 * @author: Alien
 * @date: 2016/10/20
 */
public class FilesUtils {
    private FilesUtils(){ };

    /**
     * Get file by filepath
     * 根据路径获取文件
     * @param filePath
     * @return
     */
    public static File getFileByPath(String filePath){
        return filePath==null?null:new File(filePath);
    }

    /**
     * Determine whether the file exists
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isFileExists(String filePath){
        return isFileExists(getFileByPath(filePath));
    }
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    /**
     * Determine whether it is a dir
     * 判断是否是目录
     * @param dirPath
     * @return
     */
    public static boolean isDir(String dirPath) {
        return isDir(getFileByPath(dirPath));
    }
    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    /**
     * Determine whether it is a file
     * 判断是否是文件
     * @param filePath
     * @return
     */
    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }
    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    /**
     * Determine whether the directory exists, if it does not exist, return is created successfully
     * 判断目录是否存在，如果不存在，返回目录是否创建成功
     * @param dirPath
     * @return
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }
    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * Determine whether the file exists, if it not exist, to determine whether to create a file successfully
     * 判断文件是否存在，如果不存在，判断文件是否创建成功
     * @param file
     * @return
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null) return false;
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
