package demo.jejuroadbackend.domain;

import demo.jejuroadbackend.dto.RestaurantRegister;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Restaurant {

    public enum Category {
        RESTAURANT, CAFFE
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Category category;

    @Column(nullable = false, length = 70)
    private String introduction;

    @Column(nullable = false, length = 50)
    private String wayToGo;

    @Embedded
    private Address address;

    @CreatedDate
    @Column(name = "create_datetime", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updatedAt;

    public static Restaurant of(RestaurantRegister request) {
        return builder()
            .name(request.getName())
            .category(Category.valueOf(request.getCategory().toUpperCase()))
            .introduction(request.getIntroduction())
            .wayToGo(request.getWayToGo())
            .address(new Address(
                request.getZipcode(),
                request.getState(),
                request.getCity(),
                request.getAddress(),
                request.getLatitude(),
                request.getLongitude()
            )).build();
    }

}
