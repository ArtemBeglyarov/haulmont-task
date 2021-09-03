package test.task.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;



@Getter
@Setter
@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(generator ="uuid")
    @GenericGenerator(name ="uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String surname;
    @Column
    @NotNull
    private String patronymic;
    @Column
    @NotNull
    private long numberPhone;
    @Column
    @NotNull
    private String email;
    @NotNull
    @Column
    private String passportID;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name="bank_clients",
            joinColumns={@JoinColumn(name="client_id")},
            inverseJoinColumns={@JoinColumn(name="bank_id")})
    private Set<Bank> banks = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade ={ CascadeType.MERGE, CascadeType.REMOVE })
    private Set<CreditOffer> creditOffers;

    public String viewNameAllBank(Set<Bank> banks) {
        StringBuilder sb = new StringBuilder();
        for (Bank bank : banks) {
            sb.append(bank.getName());
            sb.append("; ");

        }
        return String.valueOf(sb);
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", numberPhone=" + numberPhone +
                ", email='" + email + '\'' +
                ", passportID=" + passportID +
                '}';
    }
}
