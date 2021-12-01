package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
public class Account implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Size(min=2,message="The user name isn't empty")
    private String userName;
    @Size(min=2,message = "The password isn't empty")
    private String password;
}
