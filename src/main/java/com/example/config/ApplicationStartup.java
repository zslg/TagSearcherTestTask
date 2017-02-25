package com.example.config;

import com.example.database.dao.PlaceDao;
import com.example.database.dao.SettingDao;
import com.example.database.dao.TagDao;
import com.example.database.dao.UserDao;
import com.example.database.domain.PlaceDomain;
import com.example.database.domain.SettingDomain;
import com.example.database.domain.TagDomain;
import com.example.database.domain.UserDomain;
import com.example.elasticsearch.PlaceElasticSearchDao;
import com.example.service.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private TagDao tagDao;

    private PlaceDao placeDao;

    private UserDao userDao;

    private SettingDao settingDao;

    private PlaceElasticSearchDao placeElasticSearchDao;

    private Converter converter;

    public ApplicationStartup(TagDao tagDao, PlaceDao placeDao, UserDao userDao, SettingDao settingDao, PlaceElasticSearchDao placeElasticSearchDao, Converter converter) {
        this.tagDao = tagDao;
        this.placeDao = placeDao;
        this.userDao = userDao;
        this.settingDao = settingDao;
        this.placeElasticSearchDao = placeElasticSearchDao;
        this.converter = converter;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        init();
    }

    private void init() {

        Optional<SettingDomain> settings = settingDao.findAll().stream().findFirst();
        if (!settings.isPresent() || settings.get().getInitFlag()) {
            return;
        }

        List<TagDomain> tags = tagDao.findAll();

        int places = 500;
        int users = 100;
        int requiredTags = 12;
        int maxTags = Math.min(tags.size(),requiredTags);

        if (maxTags == 0) {
            return;
        }

        for (int i = 0; i < places; i++) {
            PlaceDomain placeDomain = new PlaceDomain();
            placeDomain.setName("Place "+(i+1));
            placeDomain.setTags(generateTags(tags,maxTags).stream().collect(Collectors.toSet()));
            placeDao.save(placeDomain);
            placeElasticSearchDao.save(converter.toPlaceDto(placeDomain));
        }

        for (int i = 0; i < users; i++) {
            UserDomain userDomain = new UserDomain();
            userDomain.setName("User "+(i+1));
            userDomain.setTags(generateTags(tags,maxTags).stream().collect(Collectors.toSet()));
            userDao.save(userDomain);
        }

        SettingDomain settingDomain = settings.get();
        settingDomain.setInitFlag(true);
        settingDao.save(settingDomain);

    }

    public int randomValue(int minimum,int maximum){
        return minimum + (int)(Math.random() * maximum);
    }

    public List<String> generateTags(List<TagDomain> tags,int maxTags){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < maxTags; i++) {
            int randomValue = randomValue(1, maxTags);
            result.add(tags.get(randomValue-1).getName());
        }
        return result;
    }
}
