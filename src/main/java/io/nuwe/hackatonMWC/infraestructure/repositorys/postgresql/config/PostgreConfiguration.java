package io.nuwe.hackatonMWC.infraestructure.repositorys.postgresql.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * To Activate PostgreSQL database activate this configuration,
 * enable @Component annotation in PostgreUserRepository and
 * remove profile from main application.properties
 */

//@Configuration
//@EnableJpaAuditing
public class PostgreConfiguration {

}
