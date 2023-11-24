package com.example.service;

import com.example.db.api.IUserDao;
import com.example.db.entity.Tabs;
import com.example.db.entity.User;
import com.example.db.factory.UserFactory;
import com.example.service.api.IMainService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

import java.sql.SQLException;
import java.util.List;

public class MainService implements IMainService {
    private IUserDao userDao = UserFactory.getInstance();

    @Override
    public void addTabToUserLibrary(User user, Long tabIdentif){
        try {
            userDao.addTabToUserLibrary(user, tabIdentif);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tabs> showMyTabs(User user) {
        return userDao.showMyTabs(user);
    }

    @Override
    public List<Tabs> showSearchResult(String req) {
        return userDao.showSearchResult(req);
    }

    @Override
    public String displayTab(Tabs tab) {
        return userDao.displayTab(tab);
    }
}
