package test.task.haulmont.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "BANK")
public class Bank {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ID;

    private String name;
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bank", cascade = {CascadeType.MERGE , CascadeType.REMOVE})
//    private Set<Client> clients;

//    private Set<Credit> credits;

}
