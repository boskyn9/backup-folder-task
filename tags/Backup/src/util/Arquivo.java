package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arquivo {

    public static void main(String[] args) {
        try {
            apagarArquivosZipDaPasta("F:\\Program Files (x86)\\Minecraft", 34 * 60l);
        } catch (ParseException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void copyFolder(File src, File dest) throws IOException {

        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
                //System.out.println("Directory copied from " + src + "  to " + dest);
            }
            String files[] = src.list();

            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                copyFolder(srcFile, destFile);
            }

        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            //System.out.println("File copied from " + src + " to " + dest);
        }
    }

    public static String apagarArquivosZipDaPasta(String path, Long tempo) throws ParseException {
        if (path == null) {
            return null;
        }

        StringBuilder stringBuffer = new StringBuilder("");
        Date agora = new Date();

        File dir = new File(path);
        File[] files = dir.listFiles(new FileZipBackup());

        for (int i = 0; i < files.length; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            Date data = sdf.parse(files[i].getName().split("\\.")[0]);

            long difSec = (agora.getTime() - data.getTime()) / 1000;

            if (difSec > tempo) {
                stringBuffer.append(files[i].getName()).append("\n");
                files[i].delete();
            }
        }
        return stringBuffer.toString();
    }
}