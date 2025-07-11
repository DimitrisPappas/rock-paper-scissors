package org.dspappas;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Player {

    public String name;
    public Choice hand;
    public int score = 0;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Choice hand) {
        this.name = name;
        this.hand = hand;
    }
}


