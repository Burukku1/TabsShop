package com.example.db.api;


import com.example.db.entity.Tabs;
import com.example.db.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserDao {
    void createUser(User user);
    Optional<User> checkUserLog(User user);
    boolean checkUserPass(User user);
    void printAllUserTabs();

    List<Tabs> showMyTabs (User user);

    List<Tabs> showSearchResult(String req);

    void addTabToUserLibrary(User user, Long tabIdentif) throws SQLException;
}
