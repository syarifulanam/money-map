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

@Entity
@Table(name = "balances") // DOMPET kita, atau ATM kita
@Data // di sini itu sebenarnya sudah ada setter getter
public class Balance {

    @Id // untuk menandakan dia PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // otomatis bikin ID
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount")
    private BigDecimal amount;

    // NOTE: Bidirectional: Both entities are aware of the relationship.
    // NOTE: Unidirectional: Only one entity knows about the relationship. (we use this)
    // NOTE: LAZY: Fetches the related entity only when it’s accessed.
    // NOTE: OnDeleteAction.CASCADE: if a parent entity is deleted, all its related child entities are automatically deleted
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @JsonProperty("created_at")
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp // bikin data waktu create otomatis
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    @UpdateTimestamp // bikin data waktu update otomatis
    private Timestamp updatedAt;

    @Column(name = "notes")
    private String notes;
}
