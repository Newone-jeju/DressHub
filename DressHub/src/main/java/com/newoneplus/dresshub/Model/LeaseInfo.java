package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Data
@Entity
public class LeaseInfo {
    @Id
    private Integer id;
    private Integer product;
    private String leaser;
    private String leaseStart;
    private String leaseEnd;
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "leaseInfo")
    private Collection<LeaseInfoLog> log;
}
