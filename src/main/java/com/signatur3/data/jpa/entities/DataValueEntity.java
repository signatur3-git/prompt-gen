package com.signatur3.data.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "datavalue")
@Getter
public class DataValueEntity {

    @SuppressWarnings("unused" /* Managed by JPA */)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "datatype_id",
            foreignKey = @ForeignKey(name = "fk_datavalue_datatype")
    )
    DataTypeEntity dataType;

    @Setter
    @Column(nullable = false)
    private String value;

    @SuppressWarnings("unused" /* Used by JPA */)
    protected DataValueEntity() {
    }

    public DataValueEntity(DataTypeEntity dataType, String value) {
        this.dataType = dataType;
        this.value = value;
    }
}
