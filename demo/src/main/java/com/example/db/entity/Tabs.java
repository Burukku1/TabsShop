package com.example.db.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tabs", schema = "public")
public class Tabs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tab_id")
    private Long tabId;

    @Column(name = "song_name")
    private String songName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "link_path")
    private String linkPath;

    @ManyToMany(mappedBy = "tabs")
    private Set<User> users = new HashSet<>();

    public Tabs() {

    }

    public Tabs(Long tabId) {
        this.tabId = tabId;
    }

    public Long getTabId() {
        return tabId;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setTabId(Long tabId) {
        this.tabId = tabId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Tabs(Long tabId, String songName, String authorName, int difficulty, String linkPath) {
        this.tabId = tabId;
        this.songName = songName;
        this.authorName = authorName;
        this.difficulty = difficulty;
        this.linkPath = linkPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tabs)) return false;
        Tabs tabs = (Tabs) o;
        return difficulty == tabs.difficulty && Objects.equals(tabId, tabs.tabId) && Objects.equals(songName, tabs.songName) && Objects.equals(authorName, tabs.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tabId, songName, authorName, difficulty);
    }

    @Override
    public String toString() {
        return "Tabs{" +
                "tabId=" + tabId +
                ", songName='" + songName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
