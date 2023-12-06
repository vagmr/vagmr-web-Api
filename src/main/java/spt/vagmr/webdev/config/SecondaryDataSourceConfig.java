package spt.vagmr.webdev.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/12/6-0:00
 * springBootProject
 * @Description
 */
@Configuration
// 配置次数据源的 Mapper 扫描路径
@MapperScan(basePackages = "spt.vagmr.webdev.mapper.secondary", sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class SecondaryDataSourceConfig {
    //次数据源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource secondDataSource(){
        return  DataSourceBuilder.create().build();
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondDataSource") DataSource secondDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(secondDataSource);
        // 设置 MyBatis 配置文件和 Mapper 文件的位置，根据实际情况调整路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/secondary/*.xml"));
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory secondarySqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(secondarySqlSessionFactory);
    }
}
