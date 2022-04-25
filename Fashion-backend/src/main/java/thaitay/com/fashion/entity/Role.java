package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "ROLES")
@Proxy(lazy = false)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ROLES")
    @SequenceGenerator(name = "S_ROLES", sequenceName = "ROLES_SEQ", allocationSize = 1)
    @Column(name = "ROLE_ID", unique=true, updatable = false, insertable=false)
    @NaturalId(mutable=true)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "ROLE_NAME", length = 60)
    private RoleName roleName;

    public Role() {
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
