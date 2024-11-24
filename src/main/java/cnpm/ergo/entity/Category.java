package cnpm.ergo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryId;

    @Column(name = "categoryName", columnDefinition = "NVARCHAR(200) NOT NULL")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public static void main(String[] args) {

    }
}
