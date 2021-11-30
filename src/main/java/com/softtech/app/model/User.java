package com.softtech.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by N.Bimeri on 23/08/2021.
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 2, message = "firstName must be greater than two character")
    private String firstName;
    @Size(min = 2, message = "middleName must be greater than two character")
    private String middleName;
    @Size(min = 2, message = "lastName must be greater than two character")
    private String lastName;

    @Size(min = 6, message = "user name should be greater than 6 character")
    @NotNull
    private String userName;

    @Column(unique = true)
    @Email
    private String email;
    private int age;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @NotNull
    @Column(length = 500)
    private String password;

}
