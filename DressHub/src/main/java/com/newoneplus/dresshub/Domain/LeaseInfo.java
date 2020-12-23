package com.newoneplus.dresshub.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class LeaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer product;
    private String leaser;
    private String leaseStart;
    private String leaseEnd;
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "leaseInfo")
    private Collection<LeaseInfoLog> log;
}
