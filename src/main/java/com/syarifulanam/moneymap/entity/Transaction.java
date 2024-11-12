package com.syarifulanam.moneymap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

// table transactions ini untuk simpan data pemasukan dan pengeluaran

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "type")
    private String type; // in, out

    @Column(name = "amount")
    private BigDecimal amount;

    //    @JsonProperty("balances_id")
//    @Column(name = "balances_id")
//    private Long balances_id;
    @JsonProperty("balance_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balance_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Balance balance;

    //@Column(name = "user_id")
    //private Long user_id;

    @JsonProperty("user_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "notes")
    private String notes;
}
