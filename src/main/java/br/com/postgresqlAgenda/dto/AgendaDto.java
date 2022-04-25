package br.com.postgresqlAgenda.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaDto {
    
    @NotBlank
    @Size(max = 70)
    private String nome;

    @NotBlank
    @Size(max = 15)
    private String fone;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 70)
    private String nome;
    @Column(name = "phone", nullable = false, length = 15)
    private String fone;
    @Column(name = "birth_dt", nullable = false)
    private LocalDate birthDate;
}
