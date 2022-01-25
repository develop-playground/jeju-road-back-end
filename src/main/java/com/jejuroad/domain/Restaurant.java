package com.jejuroad.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "RESTAURANT_CATEGORY",
        joinColumns = @JoinColumn(name = "RESTAURANT_ID"),
        inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private List<Category> categories = new ArrayList<>();

    @Column(nullable = false, length = 70)
    private String introduction;

    @Column(nullable = false, length = 50)
    private String wayToGo;

    @Embedded
    private Address address;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "RESTAURANT_TIP",
        joinColumns = @JoinColumn(name = "RESTAURANT_ID"),
        inverseJoinColumns = @JoinColumn(name = "TIP_ID")
    )
    private List<Tip> tips = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "RESTAURANT_ID")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "RESTAURANT_ID")
    private List<OpenTime> openTimes = new ArrayList<>();

    @CreatedDate
    @Column(name = "create_datetime", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updatedAt;

}
