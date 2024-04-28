package com.isaac.demo.app.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // JsonIgnore 설명 : 필드 레벨에서 무시 될 수 있는 속성을 표시하는데 사용
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

}
