package com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.dto;

import com.emadi.yoseph.memeber_product_on_spring_tymeleaf_mysql.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {

    public Member member;
}
