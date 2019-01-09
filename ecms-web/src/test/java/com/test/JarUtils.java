package com.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

/**
 * uploadToLinux
 *
 * @author Gray.Z
 * @date 2019/1/5 0005.
 */
public class JarUtils {

    public static void writeToJar(String classPathStr, String jarPathStr, String jarName) throws Exception {
        Path classPath = Paths.get(classPathStr);
        Path jarPath = Paths.get(jarPathStr, jarName);

        Manifest manifest = getManifest(jarPath.toString());

        try (JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(jarPath.toFile()),
            modifyManifest(manifest, "ecms", "1.0"))) {

            Files.walkFileTree(classPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("==>" + dir.toString());

                    String name = dir.toString().split("classes\\\\")[1].replace("\\", "/");
                    if (!name.isEmpty()) {
                        if (!name.endsWith("/")) {
                            name += "/";
                        }
                        JarEntry entry = new JarEntry(name);
                        entry.setTime(dir.toFile().lastModified());
                        jarOutputStream.putNextEntry(entry);
                        jarOutputStream.closeEntry();
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("Adding " + file.toString() + " to jar");
                    JarEntry entry = new JarEntry(file.toString().replace("\\", "/").split("classes/")[1]);
                    entry.setTime(file.toFile().lastModified());
                    //表示将该entry写入jar文件中 也就是创建该文件夹和文件
                    jarOutputStream.putNextEntry(entry);
                    JarUtils.writeJar(file, jarOutputStream);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    private static void writeJar(Path file, JarOutputStream jarOutputStream) throws IOException {
        byte [] buffer = new byte[4096];
        // Write file to archive
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file.toFile()))) {
            while (true) {
                int len = in.read(buffer, 0, buffer.length);
                if (len == -1) {
                    break;
                }
                jarOutputStream.write(buffer, 0, len);
            }
        }
        jarOutputStream.closeEntry();
    }


    public static void listJarFile(String jarPathStr) throws IOException{
        JarFile jarFile = new JarFile(Paths.get(jarPathStr).toFile());

        System.out.println(jarFile.stream().count());
        Enumeration<JarEntry> jarEntrys = jarFile.entries();
        int index = 0;
        while(jarEntrys.hasMoreElements()){
            ZipEntry entryTemp = jarEntrys.nextElement();
            System.out.println(index+"-->>>"+entryTemp.getName()+"==============>"+entryTemp.getSize()+"==>>>"+entryTemp.getCompressedSize());
            index++;
        }
        jarFile.close();
    }

    /**
     * 获得Manifest对象
     *
     * @param original 源文件名称
     * @return Manifest对象
     */
    private static Manifest getManifest(String original) {
        try {
            JarInputStream jis = new JarInputStream(new FileInputStream(original));
            return jis.getManifest() == null ? new Manifest() : jis.getManifest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Manifest();
    }

    /**
     * 修改jar文件参数
     *
     * @param mf 原文件名称
     * @param agent 代理商代号
     * @param version 版本号
     */
    private static Manifest modifyManifest(Manifest mf, String agent, String version) {
        try {
            Attributes attrs = mf.getMainAttributes();
            //attrs.putValue("Agent-Name", agent);
            if (version != null) {
                attrs.put(Attributes.Name.MANIFEST_VERSION, version);
            }
            return mf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mf;
    }

    public static void main(String[] args) throws Exception {
    /*    JarUtils.writeToJar(
            "D:\\workspace\\maitian\\mm_stat\\build\\classes\\cn\\maitian\\stat",
            "D:\\workspace\\maitian\\maimai\\classes\\artifacts\\maimai\\WEB-INF\\lib\\","mm_stat-1.10.jar"
            );
*/
        JarUtils.listJarFile("C:\\Users\\Administrator\\mm_stat-1.0.jar");
        JarUtils.listJarFile("D:\\workspace\\xx\\classes\\artifacts\\xx\\WEB-INF\\lib\\xx-1.10.jar");


    }
}
