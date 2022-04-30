package co.com.usta.renttyj.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user_auth")
public class UserAuth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "join_date")
    private LocalDateTime joinLocalDateTime;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "last_login_display")
    private LocalDateTime lastLoginDisplay;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_not_locked")
    private Boolean isNotLocked;

    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "user_authorities")
    @ToString.Exclude
    private List<Authority> authorities;

    @PrePersist
    private void createdDate() {
        this.joinLocalDateTime = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    private void updateDate() {
        this.updateDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAuth userAuth = (UserAuth) o;
        return id != null && Objects.equals(id, userAuth.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
