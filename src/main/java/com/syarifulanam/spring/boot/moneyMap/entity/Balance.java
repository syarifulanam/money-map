package com.syarifulanam.spring.boot.moneyMap.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "balances") // DOMPET kita, atau ATM kita
@Data
public class Balance {

    @Id // untuk menandakan dia PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // otomatis bikin ID
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount")
    private BigDecimal amount;

    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Long userId;

    @JsonProperty("created_at")
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp // bikin data waktu create otomatis
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    @UpdateTimestamp // bikin data waktu update otomatis
    private Timestamp updatedAt;

}
