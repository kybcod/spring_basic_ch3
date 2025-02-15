package com.fastcampus.ch3;

import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.jdbc.datasource.*;

import javax.sql.*;
import java.sql.*;

public class DBConnectionTest2 {

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
//        assertTrue(conn!=null);
    }
}
