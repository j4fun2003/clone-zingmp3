package com.m2m.zing.model.idClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryId implements Serializable {
    private Long user;
    private Long song;

}
