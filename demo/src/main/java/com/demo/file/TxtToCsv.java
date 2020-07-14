package com.demo.file;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TxtToCsv {

    private static String txtFile;
    private static String csvFile;
    private static String filePath;
    private final static String TXT_SPLIT = " ";
    private final static char CSV_SPLIT = ',';

    private static void readTxt() throws IOException {
        filePath =
            Thread.currentThread().getContextClassLoader().getResource(".").getPath() + "/file";
        txtFile = filePath + "/testChinese.txt";
        csvFile = filePath + "/testCsv.csv";
        File file = new File(csvFile);
        if (file.exists()) {
            file.delete();
        }

        Writer fileWriter = new FileWriter(csvFile);
        CSVWriter writer = new CSVWriter(fileWriter, CSV_SPLIT);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(txtFile)));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            writer.writeNext(line.split(TXT_SPLIT));
        }
        writer.flush();
        bufferedReader.close();
        writer.close();
        System.out.println("转化完成");
    }

    public static void main(String[] args) throws Exception {
//        readTxt();
//        System.out.println(Math.ceil((1530 * 1.0) / (6.0)));
//        String s = "/dsfds/dsafdsf/dsfsdf.txt";
//        System.out.println(s.substring(s.lastIndexOf("/")));
//        testCreateTempFile();
//        createZip();
//        test();
//            String sss = "name\tkecheng\tfensu";
//            String[]  split = sss.split("\t");
//        for (String s : split) {
//            System.out.println(s);
//        }
//        splitFile();
//        csvTab();
        String split = "\\|";
        String sss = "\"id\"|\"name,age\"|\"ete\"";
        for (String s : sss.split(split)) {
            System.out.println(s);
        }
    }

    private static void csvTab() throws IOException {
        String ss="\t";
        filePath =
            Thread.currentThread().getContextClassLoader().getResource(".").getPath() + "/file";
        String path = filePath + "/test.csv";
        InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
        CSVReader csvReader = new CSVReader(reader, '\t');
        while (true) {
            String[] atgs = csvReader.readNext();
            System.out.println(atgs.length);
            for (String atg : atgs) {
                System.out.println(atg);
            }
        }

    }

    private static void testCreateTempFile() throws IOException {
        filePath =
            Thread.currentThread().getContextClassLoader().getResource(".").getPath() + "/file";
        File tempFile = File.createTempFile("tempFile", ".txt", new File(filePath));
        boolean result = tempFile.renameTo(new File(filePath + "/zxk.txt"));
        System.out.println(result);
    }

    private static void createZip() throws Exception {
        filePath = Thread.currentThread().getContextClassLoader().getResource(".").getPath() + "/file";
        try {
            File zipFile = new File(filePath + "/test.zip");
            if (zipFile.exists()) {
                zipFile.delete();
            }

            ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
            //校验
//            zos.setMethod(ZipOutputStream.DEFLATED);
//            zos.setLevel(Deflater.BEST_COMPRESSION);
            byte[] buf = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(
                new File("/home/kyle/Pictures/2000_0.xls"));
            zos.putNextEntry(new ZipEntry("/home/kyle/Pictures/2000_0.xls"));
            while ((len = in.read(buf)) > 0) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }


//        FileInputStream fos = new FileInputStream("/tmp/linkoop-studio-local-dir/ADMIN/export/2000_0.xls");
//        BufferedInputStream bis = new BufferedInputStream(fos);
//        int len;
//        byte[] buf = new byte[1024];
//        while((len=bis.read(buf, 0, 1024)) != -1) {
//            zos.write(buf, 0, len);
//        }
//        zos.closeEntry();
//        bis.close();
//        fos.close();
    }

    private static void  test() throws IOException {
        String path = "/home/kyle/Pictures";
        File excelFile = new File("/home/kyle/Pictures/2000_0.xls");

        String fileName = excelFile.getName();
        FileInputStream fileInputStream = null;
        ZipOutputStream zipOutputStream = null;
        File file = new File(
            path  + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip");
        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        fileInputStream = new FileInputStream(excelFile);
        //放入压缩zip包中;
        zipOutputStream.putNextEntry(new ZipEntry(path + "/" + fileName));
        byte[] buf = new byte[1024];
        int len = 0;
        //读取文件;
        while ((len = fileInputStream.read(buf)) > 0) {
            zipOutputStream.write(buf, 0, len);
        }
        //关闭;
        zipOutputStream.closeEntry();
        if (fileInputStream != null) {
            fileInputStream.close();
        }
    }

    private static void splitFile() throws IOException {
        filePath = Thread.currentThread().getContextClassLoader().getResource(".").getPath() + "/file";
        File file = new File(filePath + "/sql.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        String s = "\t";
        while ((line = bufferedReader.readLine()) != null){
            String[] args = line.split(s);
            for (String arg : args) {
                System.out.println(arg);
            }
        }
    }
}
