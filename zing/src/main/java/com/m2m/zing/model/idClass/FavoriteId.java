package com.m2m.zing.model.idClass;

import com.m2m.zing.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteId implements Serializable {
    private Long user;
    private Long song;

    // Constructors, getters, setters và equals, hashCode
}
