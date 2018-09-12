package com.sk.sps.base.utils.jni;

/**
 * Created by zhangruigang on 2017/9/13.
 */
public class FingerprintExtractor {
    static {
        try {
            System.load("/opt/xxx/sps/conf/so/FingerprintExtractor.so");
        } catch (Throwable e) {
            System.out.println("system load so file error" + e.getMessage());
        }
    }

    native String getPeVersionMd5(String pePath);

    native String getPeVersion(String pePath);

    native int[] getAppSignatureString(String pePath, int len);

    public static void main(String[] args) {
        try {
            FingerprintExtractor fingerprintExtractor = new FingerprintExtractor();
            String pePath = args[0];
            System.out.println("pePath:" + pePath);
            String versionMd5 = fingerprintExtractor.getPeVersionMd5(pePath);
            System.out.println("versionMd5:" + versionMd5);
            String version = fingerprintExtractor.getPeVersion(pePath);
            System.out.println("version:" + version);
            int[] signature = fingerprintExtractor.getAppSignatureString(pePath, 6);
            System.out.println("signaure:" + signature);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
