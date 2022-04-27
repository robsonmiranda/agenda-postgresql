package br.com.postgresqlAgenda.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContatoDto {

    private String name;
    private String phone;

    @NotBlank
    @Size(max = 70)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Size(max = 15)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
