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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/12/5-23:39
 * springBootProject
 * @Description
 */
@Configuration
@MapperScan(basePackages = "spt.vagmr.webdev.mapper.main", sqlSessionTemplateRef = "SqlSessionTemplate")
public class PrimaryDataSourceConfig {
    //主数据源
    @Bean
    @Primary //主数据库，
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 设置 MyBatis 配置文件和 Mapper 文件的位置，根据实际情况调整路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/main/*.xml"));
        Objects.requireNonNull(bean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Bean(name = "SqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(SqlSessionFactory);
    }
}
