package com.demo.demo.controller;

import com.demo.demo.DemoApplication;
import com.demo.demo.entity.Dept;
import com.demo.demo.repository.DeptRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @Test
    public void findByIdTest() {
        Optional<Dept> deptOptional = deptRepository.findByDeptno(10);
        deptOptional.ifPresent(dept -> {
            System.out.println(dept.getDname());
        });
    }

    @Test
    public void queryByDnameTest() {
        List<Dept> list = deptRepository.queryByDname("ACCOUNTING");
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getLoc(), "NEW YORK");
    }

    @Test
    @Transactional
    public void saveAndFlushTest() {
        Dept dept = new Dept();
        dept.setDeptno(60);
        dept.setDname("ZXK");
        dept.setLoc("HE BI");
        deptRepository.saveAndFlush(dept);
        Optional<Dept> deptOptional = deptRepository.findByDeptno(50);
        deptOptional.ifPresent(dept1 -> {
            Assert.assertEquals(dept1.getLoc(), dept.getLoc());
        });
    }

    @Test
    @Transactional
    public void deleteByDnameTest() {
        long value = deptRepository.count();
        deptRepository.deleteAll();
        Assert.assertEquals(value, 4);
    }
}