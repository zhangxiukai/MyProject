package com.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


public class ReflectionTest {

    public static void main(String[] args)
        throws ClassNotFoundException, NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class cls = Class.forName("com.demo.reflect.Person");
        Field[] fields = cls.getFields();   // 只获取 public 的属性
        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
        }
        System.out.println("--------------");
        Field field = cls.getField("name");
        System.out.println(field.getName());
        String clsString = cls.toString();
        System.out.println(clsString);
        clsString = cls.toGenericString();
        System.out.println(clsString);
        System.out.println(cls.isAnnotation()); // 是否为注解类
        System.out.println(cls.isArray());  // 是否为数组类
        System.out.println(cls.isInterface());  //是否是接口
        System.out.println(cls.getPackage());   // 获取包路径
        System.out.println("------------------");
        Class[] interfaces = cls.getInterfaces();   // 获取接口信息
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.isInterface());
            System.out.println(anInterface.getName());
        }
        System.out.println("------------------");
        Constructor[] cs = cls.getConstructors();   //获取构造方法
        Object obj = cls.getConstructor(String.class, int.class, boolean.class).newInstance("张三", 1, true); // 根据构造方法创建一个类的实列
        for (Constructor c : cs) {
            System.out.println(c.getParameterCount());
            System.out.println(c.getName());

            Class[] ls = c.getParameterTypes();
            for (Class l : ls) {
                System.out.println(l.getName());
            }
        }
        System.out.println(cls.getSimpleName());
        System.out.println("----------------");
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            System.out.println(method.getParameterCount());
            System.out.println(method.getName());
            if (("createPerson").equals(method.getName())) {
                method.invoke(obj);   // 调用类中的方法，先初始化一个类的实列。
            }
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getType().getName());
            }
        }
        System.out.println("------------");


    }
}
