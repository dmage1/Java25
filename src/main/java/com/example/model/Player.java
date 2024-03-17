package com.example.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Comparable is an interface defining a strategy of comparing an object with other objects of the same type.
 * This is called the class’s “natural ordering.”
 *
 * In order to be able to sort, we must define our Player object as comparable by implementing the Comparable
 * interface:
 */
public class Player implements Comparable<Player> {
    private int ranking;
    private String name;
    private int age;

    public Player(int ranking, String name, int age) {
        this.ranking = ranking;
        this.name = name;
        this.age = age;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Player otherPlayer) {
        return Integer.compare(getRanking(), otherPlayer.getRanking());
    }

    public static List<Player> getPlayerList() {
        return Arrays.asList(
                new Player(59, "John", 20),
                new Player(67, "Roger", 22),
                new Player(45, "Steven", 24));
    }

    public static void main(String[] args) {
        List<Player> footballTeam = Player.getPlayerList();

        System.out.println("Before Sorting : " + footballTeam);
        Collections.sort(footballTeam);
        System.out.println("After Sorting : " + footballTeam);
    }
}
