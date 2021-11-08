package com.investment.apiinvestiment.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class EntityModel implements Serializable {

    /**
     * Serial number.
     */
    private static final long serialVersionUID = 8899373186154863589L;

    /**
     * Chave primaria da entidade.
     */
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Armazena a data e hora em que o registro foi criado.
     */
    @CreationTimestamp
    protected Date createdAt;

    /**
     * Armazena a data e hora da ultima vez em que o registro sofreu alguma alteracao.
     */
    @UpdateTimestamp
    protected Date updatedAt;

    /**
     * Indica se um registro da entidade esta excluido.
     * Esta campo eh usado para excluir de forma logica um registro da
     * entidade quando o mesmo nao puder ser excluido fisicamente.
     */
    protected Date deletedAt;
}


