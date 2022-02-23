package cn.sll.installapk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String adbPath = "adb.bat";// new File("adb/adb.bat").getAbsolutePath();
    //private static final String adbPath =new File("adb/adb.bat").getAbsolutePath();

    public static void main(String[] args) {
        String apkPath = "";//D:\Download\pandownload\596[PRO].apk
        String deviceID = "";
        if (args.length != 1) {
            System.out.println("请输入apk路径：");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) apkPath = scanner.next();
        } else {
            apkPath = args[0];
        }
        String devices = ProcessUtils.run(adbPath + " devices").data;
        System.out.println("devices:" + devices);
        String[] devicesSplit = devices.split("\n");
        List<String> deviceIDs = new ArrayList<>();
        for (String d : devicesSplit) {
            if (d.startsWith("List")) continue;
            int index = d.indexOf("\tdevice");
            if (index == -1) continue;
            String device = d.substring(0, index);
            deviceIDs.add(device);
        }
        if (deviceIDs.size() > 1) {
            System.out.println("\n######Device list#######");
            for (int i = 0; i < deviceIDs.size(); i++) {
                System.out.println(String.format("%s.deviceID:%s  model:%s", i, deviceIDs.get(i), ProcessUtils.run(String.format("%s -s %s shell getprop ro.product.model", adbPath, deviceIDs.get(i))).data));
            }
            System.out.println("Please choose device:");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                int s = scanner.nextInt();
                deviceID = deviceIDs.get(s);
            } else {
                return;
            }
        } else {
            deviceID = deviceIDs.get(0);
        }
        System.out.println("start install apk...");
        File tempApk=toTempApkFile(apkPath);
        if(tempApk==null)return;
        String data = ProcessUtils.run(String.format("%s -s %s install -r %s", adbPath, deviceID, tempApk.getAbsolutePath())).data;
        System.out.println(data);
        System.out.println("please input any key to exit....");
        Scanner scanner = new Scanner(System.in);
        scanner.next();

    }
    private static File toTempApkFile(String apkPath){
        try {
            File tempFile = new File(System.getProperty("java.io.tmpdir"), "_" + System.currentTimeMillis() + ".apk");
            tempFile.createNewFile();
            tempFile.deleteOnExit();
            StreamUtils.copy(new FileInputStream(apkPath),new FileOutputStream(tempFile));
            return tempFile;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
