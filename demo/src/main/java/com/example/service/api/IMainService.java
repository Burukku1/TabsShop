package com.example.service.api;

import com.example.db.entity.Tabs;
import com.example.db.entity.User;

import java.util.List;

public interface IMainService {



     void addTabToUserLibrary(User user, Long tabIdentif);

     List<Tabs> showMyTabs(User user);

     List<Tabs> showSearchResult(String req);

     String displayTab(Tabs tab);
}
