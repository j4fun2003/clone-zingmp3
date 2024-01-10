package com.m2m.zing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "singer")
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "singer_id")
    Integer singerId;

    @Column(name = "singer_full_name")
    String singerFullName;

    @Column(name = "image")
    String image;

    @Column(name = "singer_birthday")
    Date singerBirthday;

    @Column(name = "singer_email")
    String singerEmail;

    @Column(name = "singer_description")
    String singerDescription;

    @OneToMany(mappedBy = "singer")
    @JsonIgnore
    List<Song> songs;
}
