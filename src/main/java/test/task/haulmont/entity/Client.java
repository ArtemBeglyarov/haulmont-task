package test.task.haulmont.entity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private String email;
    @NotNull
    @Column
    private long passportID;

}
