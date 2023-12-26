package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class EntityOfEmission {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Emission emission;

}
