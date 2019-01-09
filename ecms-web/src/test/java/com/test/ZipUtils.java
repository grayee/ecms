package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * uploadToLinux
 *
 * @author Gray.Z
 * @date 2019/1/4 0004.
 */
public class ZipUtils {


    /**
     * Returns a zip file system 创建基于zip和jar文件的文件系统的方式有两种：一种是使用模式为“jar”的URI来调用FileSystems类的newFileSystem方法；
     * 另一种是使用Path接口的实现对象来调用newFileSystem方法。如果文件路径的后缀是“.zip”或“.jar”， 会自动创建对应的zip/jar文件系统实现
     *
     * @param zipFilename to construct the file system from
     * @return a zip file system
     */
    public static FileSystem createZipFileSystem(String zipFilename)
        throws IOException {
        // convert the filename to a URI
        final Path path = Paths.get(zipFilename);
        final URI uri = URI.create("jar:file:" + path.toUri().getPath());
        final Map<String, String> env = new HashMap<>();
        env.put("create", String.valueOf(Files.notExists(path)));
        env.put("encoding", "UTF-8");
        return FileSystems.newFileSystem(uri, env);
    }

    /**
     * Unzips the specified zip file to the specified destination directory. Replaces any files in the destination, if
     * they already exist.
     *
     * @param zipFilename the name of the zip file to extract
     * @param destDirname the directory to unzip to
     */
    public static void unzip(String zipFilename, String destDirname)
        throws IOException {

        final Path destDir = Paths.get(destDirname);
        //if the destination doesn't exist, create it
        if (Files.notExists(destDir)) {
            System.out.println(destDir + " does not exist. Creating...");
            Files.createDirectories(destDir);
        }

        try (FileSystem zipFileSystem = createZipFileSystem(zipFilename)) {
            final Path root = zipFileSystem.getPath("/");

            //walk the zip file tree and copy files to the destination
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    final Path destFile = Paths.get(destDir.toString(), file.toString());
                    System.out.printf("Extracting file %s to %s\n", file, destFile);
                    Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    final Path dirToCreate = Paths.get(destDir.toString(), dir.toString());
                    if (Files.notExists(dirToCreate)) {
                        System.out.printf("Creating directory %s\n", dirToCreate);
                        Files.createDirectory(dirToCreate);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    /**
     * Creates/updates a zip file.
     *
     * @param zipFilename the name of the zip to create
     * @param filenames list of filename to add to the zip
     */
    public static void create(String zipFilename, String... filenames) throws IOException {

        try (FileSystem zipFileSystem = createZipFileSystem(zipFilename)) {
            final Path root = zipFileSystem.getPath("/");

            //iterate over the files we need to add
            for (String filename : filenames) {
                final Path src = Paths.get(filename);

                //add a file to the zip file system
                if (!Files.isDirectory(src)) {
                    final Path dest = zipFileSystem.getPath(root.toString(), src.toString());
                    final Path parent = dest.getParent();
                    if (Files.notExists(parent)) {
                        System.out.printf("Creating directory %s\n", parent);
                        Files.createDirectories(parent);
                    }
                    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    //for directories, walk the file tree
                    Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            final Path dest = zipFileSystem.getPath(root.toString(), file.toString().split("artifacts")[1]);
                            Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                            throws IOException {
                            final Path dirToCreate = zipFileSystem.getPath(root.toString(), dir.toString().split("artifacts")[1]);
                            if (Files.notExists(dirToCreate)) {
                                System.out.printf("Creating directory %s\n", dirToCreate);
                                Files.createDirectories(dirToCreate);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                }
            }
            System.out.println("zip finished!");
        }
    }

    /**
     * List the contents of the specified zip file
     */
    public static void list(String zipFilename) throws IOException {
        System.out.printf("Listing Archive:  %s\n", zipFilename);

        //create the file system
        try (FileSystem zipFileSystem = createZipFileSystem(zipFilename)) {

            final Path root = zipFileSystem.getPath("/");

            //walk the file tree and print out the directory and filenames
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file,
                    BasicFileAttributes attrs) throws IOException {
                    print(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                    BasicFileAttributes attrs) throws IOException {
                    print(dir);
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * prints out details about the specified path
                 * such as size and modification time
                 * @param file
                 * @throws IOException
                 */
                private void print(Path file) throws IOException {
                    final DateFormat df = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
                    final String modTime = df.format(new Date(
                        Files.getLastModifiedTime(file).toMillis()));
                    System.out.printf("%d  %s  %s\n",
                        Files.size(file),
                        modTime,
                        file);
                }
            });
        }
    }



    public void addFileToZip(File zipFile, File fileToAdd) throws IOException {
        File tempFile = File.createTempFile(zipFile.getName(), null);
        tempFile.delete();
        zipFile.renameTo(tempFile);
        try (ZipInputStream input = new ZipInputStream(new FileInputStream(tempFile));
            ZipOutputStream output = new ZipOutputStream(new FileOutputStream(zipFile))) {
            ZipEntry entry = input.getNextEntry();
            byte[] buf = new byte[8192];
            while (entry != null) {
                String name = entry.getName();
                if (!name.equals(fileToAdd.getName())) {
                    output.putNextEntry(new ZipEntry(name));
                    int len = 0;
                    while ((len = input.read(buf)) > 0) {
                        output.write(buf, 0, len);
                    }
                }
                entry = input.getNextEntry();
            }
            try (InputStream newFileInput = new FileInputStream(fileToAdd)) {
                output.putNextEntry(new ZipEntry(fileToAdd.getName()));
                int len = 0;
                while ((len = newFileInput.read(buf)) > 0) {
                    output.write(buf, 0, len);
                }
                output.closeEntry();
            }
        }
        tempFile.delete();
    }

}
