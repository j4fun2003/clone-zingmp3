package com.m2m.zing.model;

import com.m2m.zing.model.idClass.FavoriteId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorites")
@IdClass(FavoriteId.class)
public class Favorite {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    private LocalDateTime likeDate;
}
