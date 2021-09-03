package test.task.entity;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    @JoinTable (name="bank_clients", joinColumns={@JoinColumn (name="bank_id")}, inverseJoinColumns={@JoinColumn(name="client_id")})
    private Set<Client> clients ;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bank", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Credit> credits;

    public String viewNameAllClients() {
        StringBuilder sb = new StringBuilder();
        for (Client k : clients) {
            sb.append(k.getName());
            sb.append("; ");

        }
        return String.valueOf(sb);
    }
    public String viewNameAllCredits() {
        StringBuilder sb = new StringBuilder();
        for (Credit k : credits) {
            sb.append(k.getName());
            sb.append("; ");

        }
        return String.valueOf(sb);
    }
    @Override
    public String toString() {
        return "Bank{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", clients=" + clients +
                '}';
    }
}
