package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
public class InformationAccount implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Size(min=5,message="The user name is more length than 5")
    private String username_infor;
    @Size(min=5,message = "the password is more length than 5")
    private String password_infor;
    private String mail;
    @Size(min=3,message = "the full name is more length than 3")
    private String fullName;
    private String address;
    private String phoneNumber;
}
