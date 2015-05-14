package com.sohu.test;

/**
 * User: wujian (jianwu203076@sohu-inc.com)
 * Date: 05/14/2015 16:49
 */
public class DataUtil {
    public static int getInt(byte[] bytes, int index)
    {
//        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
//        return (0xff & bytes[index]) | (0xff00 & (bytes[index + 1] << 8)) | (0xff0000 & (bytes[index + 2] << 16)) | (0xff000000 & (bytes[index + 3] << 24));
        return (0xff & bytes[index + 3]) | (0xff00 & (bytes[index + 2] << 8)) | (0xff0000 & (bytes[index + 1] << 16)) | (0xff000000 & (bytes[index] << 24));
    }
}
