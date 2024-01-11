package com.m2m.zing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "users_role")
@Data
@AllArgsConstructor
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

    public UserRole() {
    }

    public UserRole(Long userId, Long id, Long roleId, String roleName) {
        // Khởi tạo các trường với các giá trị được truyền vào
        this.user = new User();  // Nếu cần, bạn có thể set giá trị cho user
        this.user.setUserId(userId);
        this.id = id;
        this.role = new Role();  // Tương tự, bạn có thể set giá trị cho role
        this.role.setId(roleId);
        this.role.setName(roleName);
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
