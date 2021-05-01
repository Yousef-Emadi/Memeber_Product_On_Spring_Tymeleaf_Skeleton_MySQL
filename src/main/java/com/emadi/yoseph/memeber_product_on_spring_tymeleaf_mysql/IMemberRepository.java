package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql;

import org.springframework.data.repository.CrudRepository;

public interface IMemberRepository extends CrudRepository<Member, Integer> {
}
