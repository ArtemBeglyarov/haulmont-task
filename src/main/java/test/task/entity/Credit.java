package test.task.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;



@Getter
@Setter
@Table(name = "CREDIT")
@Entity
public class Credit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ID;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private double creditLimit;
    @Column
    @NotNull
    private double creditPercent;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "credit", cascade ={ CascadeType.MERGE, CascadeType.REMOVE })
    private Set<CreditOffer> creditOffers;

    public String getBankName() {

        String bankName = bank.getName();
    return bankName;
    }

}
