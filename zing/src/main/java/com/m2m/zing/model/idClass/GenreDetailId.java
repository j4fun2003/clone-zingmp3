package com.m2m.zing.model.idClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDetailId implements Serializable {
    private Long genre;
    private Long song;
}
