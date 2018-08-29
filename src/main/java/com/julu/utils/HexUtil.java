package com.julu.utils;
public class HexUtil {
    /**
     * 将Byte数组转换成16进制
     *
     * @param bytes
     *            需转换的byte数组
     * @return 返回16进制的字符串
     */
    public final static String bytesToHexString(byte[] bytes) {
        String hex = "";
        if (bytes != null) {
            StringBuilder buf = new StringBuilder(bytes.length);
            for (byte temp : bytes) {
                int value = temp & 0xFF;
                if (value < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(value));
            }
            hex = buf.toString();
        }
        return hex;
    }

    public static byte[] hexToBinary(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);

            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;

    }

}
