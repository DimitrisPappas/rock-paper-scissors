package org.dspappas;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    public String name;
    public Choice hand;

    public Player(String name) {
        this.name = name;
    }
}


