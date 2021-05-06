package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.repositories;

import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Service;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: Member Database with Hibernate
 * Developed by: Yousef Emadi
 * Date: APR 2021
 * Depends on: Maven, Spring, Hibernate, Thymeleaf, MySQL
 */


public interface IServiceRepository extends CrudRepository<Service, Integer> {
}
