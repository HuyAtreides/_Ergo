package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.RoleDAOImpl;
import cnpm.ergo.DAO.interfaces.RoleDAO;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class VoucherType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    @Column(nullable = false, unique = true)
    private String typeName;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Voucher> vouchers;

    // Getters and Setters
}
