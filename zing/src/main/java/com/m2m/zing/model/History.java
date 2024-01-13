package com.m2m.zing.model;

import com.m2m.zing.model.idClass.FavoriteId;
import com.m2m.zing.model.idClass.HistoryId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "histories")
@IdClass(HistoryId.class)
public class History {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    private LocalDateTime listenDate;
}
