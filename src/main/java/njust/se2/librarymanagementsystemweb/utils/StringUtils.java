package njust.se2.librarymanagementsystemweb.utils;

import java.util.Random;

public class StringUtils {
    /**
     * 生成 length 位随机数
     *
     * @param length 随机数长度
     * @return 随机数字符串
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            stringBuilder.append(base.charAt(number));
        }
        return stringBuilder.toString();
    }
}
