package com.meme.shardingsphere;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.mode.repository.standalone.StandalonePersistRepositoryConfiguration;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionFactory {

    public static void main(String[] args) {

        String schemaName = "";
        ModeConfiguration modeConfig = new ModeConfiguration(
                "Standalone",
                new StandalonePersistRepositoryConfiguration("", new Properties()),
                false
        );

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc.mysql://localhost:3306/ds_1");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds_1", dataSource1);
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc.mysql://localhost:3306/ds_1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("ds_2", dataSource2);



//        Collection<RuleConfiguration> ruleConfigs = ;
//        Properties props = ;
//
//        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(schemaName, modeConfig, dataSourceMap, ruleConfigs, props);


    }
}
