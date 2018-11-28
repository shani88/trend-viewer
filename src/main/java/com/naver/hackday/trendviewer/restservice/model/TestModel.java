package com.naver.hackday.trendviewer.restservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author SaeHan Kim
 * @since 2018-11-28
 */
@Entity
@Table(name = "test")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class TestModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
