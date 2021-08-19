package test.task.haulmont.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "CREDIT_OFFER")
public class CreditOffer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ID;

    @ManyToOne( cascade = CascadeType.MERGE )
    @NotNull
    private Client client;


    @ManyToOne( cascade = CascadeType.MERGE )
    @NotNull
    private Credit credit;

    @Column
    @NotNull
    private double creditAmount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creditOffer", cascade = CascadeType.REMOVE)
    private List<PaymentSchedule> paymentSchedule;

    public String getNameClient() {
        return client.getSurname();
    }
}
