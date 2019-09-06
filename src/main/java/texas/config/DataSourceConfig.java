package texas.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**    ---Rayn 固定写法，记就完事了   */
    @Bean(name = "texasDataSource")
    @Qualifier("texasDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.texas")
    public DataSource texasDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "texasDataSource")
    public JdbcTemplate texasJdbcTemplate(@Qualifier("texasDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
