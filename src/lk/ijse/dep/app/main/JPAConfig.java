package lk.ijse.dep.app.main;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@PropertySource("file:${user.dir}/settings/application.properties")
@EnableTransactionManagement

public class JPAConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagementFactory(DataSource ds, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcfb = new LocalContainerEntityManagerFactoryBean();
        lcfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcfb.setDataSource(ds);
        lcfb.setPackagesToScan("lk.ijse.dep.app.entity");
        return lcfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
        return jpaVendorAdapter;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUsername(env.getRequiredProperty("javax.persistence.jdbc.user"));
        bds.setPassword(env.getRequiredProperty("javax.persistence.jdbc.password"));
        bds.setUrl(env.getRequiredProperty("javax.persistence.jdbc.url"));
        bds.setInitialSize(8);
        bds.setMaxTotal(8);

//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUsername(env.getRequiredProperty("javax.persistence.jdbc.user"));
//        ds.setPassword(env.getRequiredProperty("javax.persistence.jdbc.password"));
//        ds.setUrl(env.getRequiredProperty("javax.persistence.jdbc.url"));
        return bds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }





}
