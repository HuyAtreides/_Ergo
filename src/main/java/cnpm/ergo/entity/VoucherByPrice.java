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
public class VoucherByPrice extends Voucher {
    private double lowerbound;

    // Getters and Setters
}
