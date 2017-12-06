package ru.spbau.group202.sharkova.zipfile;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class finds .zip files in a directory
 * and extracts all files with names that match
 * a regular expression to that same directory.
 */
public class ZipFinder {

    /**
     * This wrapper method extracts files with names that
     * match the regex from ./zip files in given directory.
     * The directory is not traversed recursively,
     * so any files in inner directories will not be found.
     * @param filepath path to the directory in which to search
     * @param regex regular expression for file names
     */
    public static void zipFiles(@NotNull String filepath, @NotNull String regex) {
        extractFilesByRegex(findZipFiles(filepath), regex, filepath);
    }

    /**
     * This private method finds .zip files in a given directory.
     * @param filepath path to directory
     *                 in which the search is performed
     * @return array of file descriptors of found .zip files
     */
    @NotNull
    private static File[] findZipFiles(@NotNull String filepath) {
        File dir = new File(filepath);

        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Incorrect filepath provided");
        }

        return dir.listFiles((file, s) -> s.endsWith(".zip"));
    }

    /**
     * This private method extracts files from .zip archives
     * if the files' names match the provided regex
     * to a specified path.
     * @param filenames array of File objects
     *                  corresponding to .zip files
     * @param regex regular expression for filenames
     * @param path files will be extracted to that path
     */
    private static void extractFilesByRegex(@NotNull File[] filenames,
                                            @NotNull String regex,
                                            @NotNull String path) {

        for (File f : filenames) {
            try (ZipInputStream in = new ZipInputStream(
                    new BufferedInputStream(new FileInputStream(f)))) {

                byte[] buffer = new byte[9000];

                ZipEntry entry = null;

                while ((entry = in.getNextEntry()) != null) {
                    String filename = entry.getName();

                    if (filename.matches(regex)) {
                        // an extra separator won't have any effect so it's ok
                        FileOutputStream out = new FileOutputStream(path
                                + File.separator + filename);

                        int len = -1;
                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }

                        out.close();
                    }
                }

            } catch (IOException e) {
                System.out.println("Incorrect filepath provided.");
            } catch (PatternSyntaxException e) {
                System.out.println("Incorrect regular expression.");
            }
        }

    }

}

