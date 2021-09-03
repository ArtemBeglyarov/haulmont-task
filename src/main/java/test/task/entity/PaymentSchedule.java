package test.task.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "PAYMENT_SCHEDULE")
public class PaymentSchedule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ID;

    @Column
    @NotNull
    private Date payDay;

    @Column
    @NotNull
    private double  paymentSum;

    @Column
    @NotNull
    private double  paymentBodyCredit;

    @Column
    @NotNull
    private double  paymentPercent;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "credit_offer_id", nullable = false)
    private CreditOffer creditOffer;
}
