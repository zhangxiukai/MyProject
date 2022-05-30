package com.demo.reflect;


import java.io.Serializable;
import org.apache.log4j.Logger;

public class Person implements PersonInterface, Serializable {

    public static  final   Logger logger = Logger.getLogger(Person.class);

    public static String PERSON_NAME = "person";
    private static String PERSON_DESCRIPTION = "description";

    public String name;
    public int age;
    public boolean sex;
    private String description;

    public Person() {
    }

    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    private Person(String name, int age, boolean sex, String description) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", sex=" + sex +
            ", description='" + description + '\'' +
            '}';
    }

    public static void main(String[] args) {
        logger.info(" start the main method ! ");
    }

    public String getPerson(String name, int age) {
        String personMsg = "the person's name is :"  + name +  ", and age is :" + age;
        logger.info(personMsg);
        return  personMsg;
    }

    @Override
    public void createPerson() {
        System.out.println("---------------接口测试");
    }
}
