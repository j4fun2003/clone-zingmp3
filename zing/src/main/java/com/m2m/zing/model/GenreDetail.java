package com.m2m.zing.model;

import com.m2m.zing.model.idClass.GenreDetailId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre_details")
@IdClass(GenreDetailId.class)
public class GenreDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Id
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;
}
