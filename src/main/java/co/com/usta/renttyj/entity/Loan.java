package co.com.usta.renttyj.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "loans")
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_days")
    private Short timeDays;

    @Column(name = "max_days")
    private String maxDays;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "daily_loans")
    private Integer dailyLoans;

    @Column(name = "penalty_fee")
    private Double penaltyFee;

    private Double amount;

    private String state;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Client client;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Product product;

    @PrePersist
    private void createdDate() {
        this.loanDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    private void updateDate() {
        this.updateDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Loan loan = (Loan) o;
        return id != null && Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
