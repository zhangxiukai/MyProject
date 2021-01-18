package com.demo.file;

import com.alibaba.fastjson.JSONObject;
import com.demo.reflect.Person;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectToFile {

    public static void main(String[] args) throws IOException {
        writeFile();
//        readFile();
        readObjectFromFile();
    }

    private static void writeFile() throws IOException {
        String filePath = "/home/kyle/package/test.txt";
        FileOutputStream outputStream = new FileOutputStream(filePath);
        ObjectOutputStream objOut=new ObjectOutputStream(outputStream);
        Person person = new Person("ARRAY[1.0,2.0]", 22, true);
        objOut.writeObject(person);
        objOut.writeObject(person);
        objOut.flush();
        objOut.close();
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFile() {
        String filePath = "/home/kyle/package/test.txt";
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(filePath));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                Person person = JSONObject.parseObject(tempString, Person.class);
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    private static void readObjectFromFile()
    {
        Person temp = null;
        File file =new File("/home/kyle/package/test.txt");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            while (in.available() != 0) {
                temp = (Person) objIn.readObject();
                System.out.println(temp);
            };
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
