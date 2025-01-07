package com.fastcampus.ch3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class A1Dao {
    @Autowired
    DataSource dataSource;

    public int insert(int key, int value) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //connection = dataSource.getConnection();
            connection = DataSourceUtils.getConnection(dataSource);
            System.out.println("connection = " + connection); // TransactionManger 덕분에 같은 connection을 사용해서 하나가 insert 오류가 생기더라도 다른 것은 오류가 생기지 않고 진행됨
            preparedStatement = connection.prepareStatement("insert into A1(key, value) values(?, ?)");
            preparedStatement.setInt(1, key);
            preparedStatement.setInt(2, value);

            return preparedStatement.executeUpdate(); //실행
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; //예외를 던저야함 => 예외를 던져서 AlDaoTest에서 insertTest 함수에서 받음
        } finally {
//            close(connection, preparedStatement);
                close(preparedStatement);
                DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void deleteAll() throws Exception {
        Connection connection = dataSource.getConnection();

        String sql = "delete from A1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        close(preparedStatement);
    }

    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}
