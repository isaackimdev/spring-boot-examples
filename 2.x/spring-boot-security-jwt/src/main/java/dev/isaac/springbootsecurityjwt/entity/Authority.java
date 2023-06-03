package dev.isaac.springbootsecurityjwt.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}
