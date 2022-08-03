package com.jejuroad.domain.restaurant;

import com.jejuroad.common.BusinessException;
import com.jejuroad.dto.RestaurantRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_DUPLICATED_MENU_NAME;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PACKAGE;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@RequiredArgsConstructor(access = PACKAGE)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, length = 30)
    private String name;

    @NonNull
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "RESTAURANT_CATEGORY",
        joinColumns = @JoinColumn(name = "RESTAURANT_ID"),
        inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private List<Category> categories;

    @NonNull
    @Column(nullable = false, length = 70)
    private String introduction;

    @NonNull
    @Column(nullable = false, length = 50)
    private String wayToGo;

    @NonNull
    @Embedded
    private Address address;

    @NonNull
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "RESTAURANT_TIP",
        joinColumns = @JoinColumn(name = "RESTAURANT_ID"),
        inverseJoinColumns = @JoinColumn(name = "TIP_ID")
    )
    private List<Tip> tips;

    @NonNull
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "RESTAURANT_ID")
    private List<Menu> menus = new ArrayList<>();

    @NonNull
    @ElementCollection
    @CollectionTable(name = "OPEN_TIME", joinColumns = @JoinColumn(name = "RESTAURANT_ID"))
    private List<OpenTime> openTimes;

    @NonNull
    @ElementCollection
    @CollectionTable(name = "RESTAURANT_IMAGE", joinColumns = @JoinColumn(name = "RESTAURANT_ID"))
    private List<String> images;

    @CreatedDate
    @Column(name = "create_datetime", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updatedAt;

    public void addMenus(final RestaurantRequest.RegisterMenu registerDto) {
        if (menus.stream().anyMatch(menu -> menu.getName().equals(registerDto.getName())))
            throw new BusinessException(RESTAURANT_RESPONSE_DUPLICATED_MENU_NAME);

        final Menu newMenu = new Menu(
            registerDto.getName(),
            registerDto.getImage(),
            registerDto.getPrice()
        );
        menus.add(newMenu);
    }

    public void update(final Restaurant updateRestaurant) {
        this.name = updateRestaurant.getName();
        this.categories = updateRestaurant.getCategories();
        this.introduction = updateRestaurant.getIntroduction();
        this.wayToGo = updateRestaurant.getWayToGo();
        this.address = updateRestaurant.getAddress();
        this.tips = updateRestaurant.getTips();
        this.openTimes = updateRestaurant.getOpenTimes();
        this.images = updateRestaurant.getImages();
    }

}
