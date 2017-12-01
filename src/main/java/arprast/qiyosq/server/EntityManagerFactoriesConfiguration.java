package arprast.qiyosq.server;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class EntityManagerFactoriesConfiguration {
//	@Autowired
//	private DataSource dataSource;
//
//	@Bean(name = "entityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean emf() {
//		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//		emf.setDataSource(dataSource);
//		emf.setPackagesToScan(new String[] { "arprast.qiyosq" });
//		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//		return emf;
//	}
}