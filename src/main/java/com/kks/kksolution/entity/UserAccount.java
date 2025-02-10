package com.kks.kksolution.entity;

import com.kks.kksolution.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountId;
    private String accountPassword;
    private LocalDateTime changePasswordAt;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToOne
    private User user;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Override
    public boolean isAccountNonExpired() {
//        계정 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        계정 잠금 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        비밀번호 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() {
//        활성화 여부
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.accountPassword;
    }

    @Override
    public String getUsername() {
        return this.accountId;
    }
}
