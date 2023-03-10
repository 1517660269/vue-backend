package com.example.vuebackend.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Farm {

    @Id
    private String id;

    private String name;

    @Column(name = "money", scale = 3, precision = 13)
    private BigDecimal money;

    @Column(columnDefinition = "DATE")
    private String date;

    @Column(name = "spring_area", scale = 3, precision = 13)
    private BigDecimal springArea;

    @Column(name = "spring_money", scale = 3, precision = 13)
    private BigDecimal springMoney;

    @Column(name = "summer_area", scale = 3, precision = 13)
    private BigDecimal summerArea;

    @Column(name = "summer_money", scale = 3, precision = 13)
    private BigDecimal summerMoney;

    @Column(name = "autumn_area", scale = 3, precision = 13)
    private BigDecimal autumnArea;

    @Column(name = "autumn_money", scale = 3, precision = 13)
    private BigDecimal autumnMoney;

    @Column(name = "winner_area", scale = 3, precision = 13)
    private BigDecimal winnerArea;

    @Column(name = "winner_money", scale = 3, precision = 13)
    private BigDecimal winnerMoney;

    private Date addDate;

    private Date updateDate;
}
