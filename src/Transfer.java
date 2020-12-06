
import java.io.IOException;
import java.security.MessageDigest;

public class Transfer {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis() / 1000);

        String clearText = "IP:10.71.45.98:808";//明文

        String cipherText = "";//密文

        String cleanText = "";//解密后的

        //String key = "1234567890";//密钥，可自定义

        System.out.println("明文为：" + clearText);

        cipherText = Transfer.encrypt(clearText);

        System.out.println("加密后为：" + cipherText);

        cleanText = Transfer.decrypt(cipherText);

        System.out.println("解密后为：" + cleanText);

    }

    /**
    
     * 加密算法
    
     * */
    public static String encrypt(String txt) {
        String key="ksa5sa5klfhweif54saf4e8sda5f4ew8fa6sd";

        String encrypt_key = "0f9cfb7a9acced8a4167ea8006ccd098";

        int ctr = 0;

        String tmp = "";

        int i;

        for (i = 0; i < txt.length(); i++) {

            ctr = (ctr == encrypt_key.length()) ? 0 : ctr;

            tmp = tmp + encrypt_key.charAt(ctr) + (char) (txt.charAt(i) ^ encrypt_key.charAt(ctr));

            ctr++;

        }

        return base64_encode(key(tmp, key));

    }

    /**
    
     * 解密算法
    
     */
    public static String decrypt(String cipherText) {

// base64解码

        String key="ksa5sa5klfhweif54saf4e8sda5f4ew8fa6sd";
        cipherText = base64_decode(cipherText);

        cipherText = key(cipherText, key);

        String tmp = "";

        for (int i = 0; i < cipherText.length(); i++) {

            int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);

            String x = "" + (char) c;

            tmp += x;

            i++;

        }

        return tmp;

    }

    public static String key(String txt, String encrypt_key) {

        encrypt_key = strMD5(encrypt_key);

        int ctr = 0;

        String tmp = "";

        for (int i = 0; i < txt.length(); i++) {

            ctr = (ctr == encrypt_key.length()) ? 0 : ctr;

            int c = txt.charAt(i) ^ encrypt_key.charAt(ctr);

            String x = "" + (char) c;

            tmp = tmp + x;

            ctr++;

        }

        return tmp;

    }

    public static String base64_encode(String str) {

        return new sun.misc.BASE64Encoder().encode(str.getBytes());

    }

    public static String base64_decode(String str) {

        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

        if (str == null) {
            return null;
        }
        try {

            return new String(decoder.decodeBuffer(str));

        } catch (IOException e) {

            e.printStackTrace();

            return null;

        }

    }

    public static String strMD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

