package br.com.postgresqlAgenda.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contato")
@AllArgsConstructor
@NoArgsConstructor
public class Contato implements Serializable {

    private static final long serialVersionUID = 7916533899715748375L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 70)
    private String name;
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;
    @Column(name = "birth_dt", nullable = false)
    private LocalDate birthDate;
}
