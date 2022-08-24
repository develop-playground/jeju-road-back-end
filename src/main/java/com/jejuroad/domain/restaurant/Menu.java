package com.jejuroad.domain.restaurant;

import com.jejuroad.dto.RestaurantRequest;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PACKAGE;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, length = 30)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String image;

    @NonNull
    @Column(nullable = false)
    private int price;

    @CreatedDate
    @Column(name = "create_datetime", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updatedAt;

    public void update(final RestaurantRequest.UpdateMenu request) {
        if (request.getName() != null) {
            this.name = request.getName();
        }
        if (request.getImage() != null) {
            this.image = request.getImage();
        }
        if (request.getPrice() != null) {
            this.price = request.getPrice();
        }
    }

}
