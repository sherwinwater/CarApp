package ca.sheridancollege.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DatabaseConfig {
	
	//Used in our DatabaseAccess class to submit JDBC Query Strings
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		System.out.println("constructor");
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	// conncet to mysql
	@Bean
	public DataSource dataSource() {
		System.out.println("connect");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/carapp?timeServerZone=UTC");
		dataSource.setUsername("sam");
		dataSource.setPassword("sam");
		return dataSource;
			
	}
//	public DataSource dataSource() {
//		System.out.println("connect");
//		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUrl("jdbc:h2:mem:testdb");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("");
//		return dataSource;
//		
//	}
	
	// execute sql while starting project
//	@Bean
//	public DataSource loadSchema() {
//		System.out.println("load");
//
//		return new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.H2)
//				.addScript("classpath:schema.sql")
//				.build();
//	}
	

}
