package com.demo.demo.repository;

import com.demo.demo.entity.Dept;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {

    public Optional<Dept> findByDeptno(int id);

    @Override
    <S extends Dept> S saveAndFlush(S s);

    @Transactional
    public void deleteByDeptno(int id);

    @Query("select au from com.demo.demo.entity.Dept au where dname=:name")
    public List<Dept> queryByDname(@Param("name") String name);

    @Override
    long count();

    public long countByDeptno(int id);

    @Override
    void delete(Dept dept);
}
