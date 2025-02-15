package com.fastcampus.ch3;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TxServiceTest {

    @Autowired
    TxService txService;

    @Test
    public void insertA1WithoutTx() throws Exception {
        txService.insertA1WithoutTx();
        txService.insertA1WithTxFail();
        txService.insertA1WithTxSucess();
    }
}