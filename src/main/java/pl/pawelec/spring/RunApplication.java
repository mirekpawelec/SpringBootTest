/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.pawelec.spring.repository.PersonDataRepository;
import pl.pawelec.spring.repository.impl.PersonDataRepositoryImpl;

/**
 *
 * @author mirek
 */
@SpringBootApplication(scanBasePackages = {"pl.pawelec.spring"})
//@EnableTransactionManagement
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }
//    
//    @Bean
//    public PersonDataRepository personDataRepository(){
//        return new PersonDataRepositoryImpl();
//    }
    
}
