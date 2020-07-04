package ml.sadriev.security.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ml.sadriev.security.enums.RoleType;

@Data
@NoArgsConstructor
@Entity
@Table(name = "table_roles")
public class Role {

    @Id
    @GeneratedValue(generator = "system-uuid")
    private UUID id;

    @ManyToOne
    private Users user;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleEnum = RoleType.ADMIN;
}
