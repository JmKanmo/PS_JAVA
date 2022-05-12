package zerobase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {
    public static void main(String[] args) {
//        String output_file_path = "D:\\games\\video\\dest.txt";
//        String file_url = "blob:https://www.xvideos5.com/7ab91c98-7790-458d-b9be-73c91d3e614d";
//        method2(output_file_path, file_url);
//        test();

    }

    public static void method1(String output_file_path, String file_url) {
        try (InputStream in = new URL(file_url).openStream()) {
            Path imgPath = Paths.get(output_file_path);
            Files.copy(in, imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void method2(String output_file_path, String file_url) {
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(file_url).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(output_file_path)
        ) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead = 0;

            while ((bytesRead = bufferedInputStream.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        try {
            URL url;
            byte[] buf;
            int byteRead, byteWritten = 0;
            url = new URL("<iframe src=\"https://www.xvideos.com/embedframe/48528883\" frameborder=0 width=510 height=400 scrolling=no allowfullscreen=allowfullscreen></iframe>");
            BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream("D:\\games\\video\\test.txt"));

            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            buf = new byte[1024];
            while ((byteRead = is.read(buf)) != -1) {
                outStream.write(buf, 0, byteRead);
                byteWritten += byteRead;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
