package team.lte.businessquery;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DruidStarterConfigTest {

    @Resource
    private DataSource dataSource;

    @Test
    public void Test() {
        DruidDataSource druidDataSource = (DruidDataSource)dataSource;
        System.out.println(druidDataSource.getDriverClassName());
        System.out.println(druidDataSource.getUrl());
        System.out.println(druidDataSource.getUsername());
        System.out.println(druidDataSource.getPassword());
    }
}