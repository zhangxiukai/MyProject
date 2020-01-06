package com.demo.download;


import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvDownload {

    public static void main(String[] args) throws IOException {
        /*<!--csv 下载依赖-->
        <dependency>
            <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>4.0</version>
        </dependency>*/

        // 准备数据
        String[] header = new String[]{"id", "name", "age"};
        String[] values1 = new String[]{"1", "张三", "25"};
        String[] values2 = new String[]{"2", "李四", "26"};
        String[] values3 = new String[]{"3", "王五", "28"};
        List<String[]> list = new ArrayList<>();
        list.add(values1);
        list.add(values2);
        list.add(values3);

        // 开始下载
        CSVWriter csvWriter = null;
        try {
            File uDir = new File("/home/kyle/Downloads");    //文件夹路径
            String filename = "testDownload";
            File file = File.createTempFile(filename, ".csv", uDir);    //文件所在位置
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, '"',"\n");
            // 写数据头部
            csvWriter.writeNext(header);
            // 写数据内容
            for (String[] value : list) {
                csvWriter.writeNext(value);
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("文件下载完成，文件位置为：" + file.getAbsolutePath());
        } catch (Exception exp) {
            if (csvWriter != null) {
                csvWriter.close();
            }
        }
    }

    }
