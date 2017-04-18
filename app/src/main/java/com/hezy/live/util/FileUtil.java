package com.hezy.live.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {

    public static String getFormatFilePath(String path) {
        if (TextUtils.isEmpty(path)) {
            return "";
        }
        if (path.startsWith("file://")) {
            return path;
        }
        return "file://" + path;
    }

    // 拷贝文件夹
    public static boolean copyFolder(String file1, String file2) {
        File in = new File(file1);
        File out = new File(file2);
        if (!in.exists()) {
            System.out.println(in.getAbsolutePath() + "源文件路径错误！！！");
            return false;
        }
        /*
         * else{ System.out.println("源文件路径"+in.getAbsolutePath());
		 * System.out.println("目标路径"+out.getAbsolutePath()); }
		 */
        if (!out.exists()) {
            out.mkdirs();
        }
        File[] file = in.listFiles();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile())
                try {
                    fis = new FileInputStream(file[i]);
                    fos = new FileOutputStream(new File(file2 + "\\" + file[i].getName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            int c;
            byte[] b = new byte[1024 * 5];

            try {
                while ((c = fis.read(b)) != -1) {
                    fos.write(b, 0, c);
                }

                fis.close();
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;

    }

    // 重命名文件
    public static void renameFile(String oldPath, String newPath, String oldName, String newName) {
        if (!oldName.equals(newName)) {
            File oldFile = new File(oldPath + "/" + oldName);
            File newFile = new File(newPath + "/" + newName);
            if (newFile.exists()) {
                System.out.println(newName + "已经存在");
            } else {
                oldFile.renameTo(newFile);
            }

        }
    }

    // 拷贝文件
    public static boolean copyFile(File in, File out) {
        if (!in.exists()) {
            System.out.println(in.getAbsolutePath() + "源文件路径错误！！！");
            return false;
        }
		/*
		 * else{ System.out.println("源文件路径"+in.getAbsolutePath());
		 * System.out.println("目标路径"+out.getAbsolutePath()); }
		 */
        // if (!out.exists()) {
        // out.mkdirs();
        // }
        // File[] file = in.listFiles();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        // File file = new File(out.getAbsolutePath());
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            fis = new FileInputStream(in);
            fos = new FileOutputStream(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        int c;
        byte[] b = new byte[1024 * 5];

        try {
            while ((c = fis.read(b)) != -1) {
                fos.write(b, 0, c);
            }
            fis.close();
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void forChannel(File f1, File f2) {
        try {
            // Create channel on the source
            FileInputStream fis = new FileInputStream(f1);
            FileOutputStream fos = new FileOutputStream(f2);
            FileChannel srcChannel = fis.getChannel();
            // Create channel on the destination
            FileChannel dstChannel = fos.getChannel();
            // Copy file contents from source to destination
            // dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
            srcChannel.transferTo(0, srcChannel.size(), dstChannel);
            // Close the channels
            srcChannel.close();
            dstChannel.close();
            fos.flush();
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(File f1, File f2) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f1));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f2));
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = bis.read(buff, 0, 1024)) != -1) {
                bos.write(buff, 0, len);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy1(File f1, File f2) {
        try {
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(f2);
            PrintWriter pw = new PrintWriter(fw, true);
            String line;
            while ((line = br.readLine()) != null) {
                pw.println(line);
            }
            pw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy2(File f1, File f2) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f1));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f2));
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = bis.read(buff, 0, 1024)) != -1) {
                bos.write(buff, 0, len);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 验证字符串是否为正确路径名的正则表达式
    // private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
    private static boolean flag;

    // 通过 sPath.matches(matches) 方法的返回值判断是否正确
    // sPath 为路径字符串

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String sPath) {
        flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    public static boolean dirIsExist(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return true;// false
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if external storage is built-in or removable.
     *
     * @return True if external storage is removable (like an SD card), false
     * otherwise.
     */
    public static boolean isExternalStorageRemovable() {
        return Environment.isExternalStorageRemovable();
    }

    /**
     * Get the external app cache directory.
     *
     * @param context The context to use
     * @return The external cache dir
     */
    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    /**
     * Get a usable cache directory (external if available, internal otherwise).
     *
     * @param context    The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public static File getDiskCacheDir(Context context, String subPath, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir otherwise use internal cache dir
        final String cachePath = getDiskCacheDirPath(context);
        File file = new File(cachePath + File.separator + subPath + File.separator + uniqueName);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static String getDiskCacheDirPath(Context context) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir otherwise use internal cache dir
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(
                context).getPath()
                : context.getCacheDir().getPath();
        return cachePath;
    }

    public static String getDiskCacheDirPath(Context context, String subPath) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir otherwise use internal cache dir
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(
                context).getPath()
                : context.getCacheDir().getPath();
        return cachePath + File.separator + subPath;
    }

    /**
     * A hashing method that changes a string (like a URL) into a hash suitable
     * for using as a disk filename.
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
