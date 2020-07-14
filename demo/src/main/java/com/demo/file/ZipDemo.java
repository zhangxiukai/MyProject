package com.demo.file;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip压缩文件实例 add by 周海涛
 *
 * @author Administrator
 */
public class ZipDemo {

    /**
     * @param args
     * @throws IOException

     */
    public static void main(String[] args)
        throws IOException {
        String path = "/home/kyle/Pictures";
        //创建文件夹;
//        createFile(path);
        //创建Excel文件;
//        createExcelFile(path);
        //生成.zip文件;
        craeteZipPath(path);
        //删除目录下所有的文件;
//        File file = new File(path);
//        //删除文件;
//        deleteExcelPath(file);
//        //重新创建文件;
//        file.mkdirs();
    }

    /**
     * 创建文件夹;
     *
     * @param path
     * @return
     */
    public static String createFile(String path) {
        File file = new File(path);
        //判断文件是否存在;
        if (!file.exists()) {
            //创建文件;
            boolean bol = file.mkdirs();
            if (bol) {
                System.out.println(path + " 路径创建成功!");
            } else {
                System.out.println(path + " 路径创建失败!");
            }
        } else {
            System.out.println(path + " 文件已经存在!");
        }
        return path;
    }

//    /**
//     * 在指定目录下创建Excel文件;
//     *
//     * @param path
//     * @throws IOException
//     * @throws WriteException
//     * @throws RowsExceededException
//     */
//    public static void createExcelFile(String path)
//        throws IOException, RowsExceededException, WriteException {
//        for (int i = 0; i < 3; i++) {
//            //创建Excel;
//            WritableWorkbook workbook = Workbook.createWorkbook(new File(
//                path + "/" + new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date()) + "_" + (i
//                    + 1) + ".xls"));
//            //创建第一个sheet文件;
//            WritableSheet sheet = workbook.createSheet("导出Excel文件", 0);
//            //设置默认宽度;
//            sheet.getSettings().setDefaultColumnWidth(30);
//
//            //设置字体;
//            WritableFont font1 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
//                UnderlineStyle.NO_UNDERLINE, Colour.RED);
//
//            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
//            //设置背景颜色;
//            cellFormat1.setBackground(Colour.BLUE_GREY);
//            //设置边框;
//            cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
//            //设置自动换行;
//            cellFormat1.setWrap(true);
//            //设置文字居中对齐方式;
//            cellFormat1.setAlignment(Alignment.CENTRE);
//            //设置垂直居中;
//            cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
//            //创建单元格
//            Label label1 = new Label(0, 0, "第一行第一个单元格(测试是否自动换行!)", cellFormat1);
//            Label label2 = new Label(1, 0, "第一行第二个单元格", cellFormat1);
//            Label label3 = new Label(2, 0, "第一行第三个单元格", cellFormat1);
//            Label label4 = new Label(3, 0, "第一行第四个单元格", cellFormat1);
//            //添加到行中;
//            sheet.addCell(label1);
//            sheet.addCell(label2);
//            sheet.addCell(label3);
//            sheet.addCell(label4);
//
//            //给第二行设置背景、字体颜色、对齐方式等等;
//            WritableFont font2 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD,
//                false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE2);
//            WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
//            cellFormat2.setAlignment(Alignment.CENTRE);
//            cellFormat2.setBackground(Colour.PINK);
//            cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
//            cellFormat2.setWrap(true);
//
//            //创建单元格;
//            Label label11 = new Label(0, 1, "第二行第一个单元格(测试是否自动换行!)", cellFormat2);
//            Label label22 = new Label(1, 1, "第二行第二个单元格", cellFormat2);
//            Label label33 = new Label(2, 1, "第二行第三个单元格", cellFormat2);
//            Label label44 = new Label(3, 1, "第二行第四个单元格", cellFormat2);
//
//            sheet.addCell(label11);
//            sheet.addCell(label22);
//            sheet.addCell(label33);
//            sheet.addCell(label44);
//
//            //写入Excel表格中;
//            workbook.write();
//            //关闭流;
//            workbook.close();
//        }
//    }

    /**
     * 生成.zip文件;
     *
     * @param path
     * @throws IOException
     */
    public static void craeteZipPath(String path) throws IOException {
        ZipOutputStream zipOutputStream = null;

        File file = new File(
            path + "/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip");
        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        zipOutputStream.setMethod(ZipOutputStream.DEFLATED);
        zipOutputStream.setLevel(Deflater.BEST_COMPRESSION);
        File[] files = new File(path).listFiles();
        FileInputStream fileInputStream = null;
        byte[] buf = new byte[1024];
        int len = 0;
        if (files != null && files.length > 0) {
            for (File excelFile : files) {
                String fileName = excelFile.getName();
                fileInputStream = new FileInputStream(excelFile);
                //放入压缩zip包中;
                zipOutputStream.putNextEntry(new ZipEntry(path + "/" + fileName));

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
        }

        if (zipOutputStream != null) {
            zipOutputStream.close();
        }
    }

    /**
     * 删除目录下所有的文件;
     *
     * @param
     */
    public static boolean deleteExcelPath(File file) {
        String[] files = null;
        if (file != null) {
            files = file.list();
        }

        if (file.isDirectory()) {
            for (int i = 0; i < files.length; i++) {
                boolean bol = deleteExcelPath(new File(file, files[i]));
                if (bol) {
                    System.out.println("删除成功!");
                } else {
                    System.out.println("删除失败!");
                }
            }
        }
        return file.delete();
    }
}

