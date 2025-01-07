package com.fastcampus.ch3;

import junit.framework.TestCase;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {

    @Autowired
    A1Dao a1Dao;

    @Autowired
    DataSource ds;

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Test
    public void insertTest() throws Exception {
        // TxManager를 생성
        // PlatformTransactionManager transactionManager = new DataSourceTransactionManager(ds); // DataSourceTransactionManager 빈등록
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100); //성공
            a1Dao.insert(1, 200); // 실패
            transactionManager.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status); // 하나만 성공하고 하나는 실패하면 둘다 insert 안된다.
        } finally {
        }
    }

}