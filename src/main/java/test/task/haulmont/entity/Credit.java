package test.task.haulmont.entity;

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


//    private Bank bank;

//    private Set<CreditOffer> creditOffers;
}
