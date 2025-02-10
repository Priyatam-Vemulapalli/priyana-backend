package com.priyana.model;


import com.priyana.ENUM.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String userName;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(unique = true, nullable = false)
    String emailId;

    // vulnerability
    String password; // find better ways of protecting the password

    @CreationTimestamp
    Date registeredOn; // only date - SQL

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<SongLists> bookings = new ArrayList<>();

}
