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
public class VoucherByPrice {
    @Id
    private int voucherId;

    private double lowerbound;

    @OneToOne
    @MapsId
    @JoinColumn(name = "voucherId")
    private Voucher voucher;

    // Getters and Setters
}
