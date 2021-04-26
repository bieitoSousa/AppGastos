package com.bieitoProyects.AppGastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({ "com.bieitoProyect.appGastos.base"})
@EnableTransactionManagement
@EnableScheduling 
public class ServerApplication {

	@Bean
	public PasswordEncryptHelper passwordEncryptHelper(){
		return new PasswordEncryptHelper();
	}

	private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

	private static final String PROPERTY_SPRING_RESORUCES_STATIC_LOCATIONS = "spring.resources.static-locations";

	private static String checkProperty(String property, boolean isMandatory) {
		String value = System.getProperty(property);
		if (value == null && !isMandatory) {
			logger.warn(String.format("System property '%s' not found.", property));
		} else if (value == null) {
			logger.error(String.format("System property '%s' not found.", property));
			System.exit(-1);
		} else {
			logger.info(String.format("System property '%s' = %s", property, value));
		}
		return value;
	}

	public static void main(String[] args) {
		// Check properties
		checkProperty(PROPERTY_SPRING_RESORUCES_STATIC_LOCATIONS, false);
		SpringApplication.run(ServerApplication.class, args);
	}

}
