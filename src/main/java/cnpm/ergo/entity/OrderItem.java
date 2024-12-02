package cnpm.ergo.entity;

import cnpm.ergo.DAO.implement.ProductDaoImpl;
import cnpm.ergo.configs.JPAConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    private Order order; 

    @Id
    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "typeId", nullable = false)
    private ProductType productType; 

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    public static void main(String[] args) {

    }
}
