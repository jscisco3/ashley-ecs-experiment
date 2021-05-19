package com.jscisco.lom.application.services;

import com.jscisco.lom.domain.event.level.Generated;
import com.jscisco.lom.domain.repository.LevelRepository;
import com.jscisco.lom.domain.repository.ZoneRepository;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * ZoneService: Domain service for interacting with the Zone root aggregate.
 */
@Service
@Transactional
public class ZoneService {

    private static final Logger logger = LoggerFactory.getLogger(ZoneService.class);
    private final ZoneRepository zoneRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public ZoneService(LevelRepository levelRepository, ZoneRepository zoneRepository) {
        this.levelRepository = levelRepository;
        this.zoneRepository = zoneRepository;
    }

    public Zone getZone(Long zoneId) {
        return zoneRepository.getById(zoneId);
    }

    public Zone createZone() {
        Zone zone = new Zone();
        return zoneRepository.save(zone);
    }

    public Level createLevel(Long zoneId, int width, int height, LevelGeneratorStrategy.Strategy strategy) {
        Zone zone = zoneRepository.getById(zoneId);
        Level level = new Level(width, height);
        Generated event = new Generated();
        event.setStrategy(strategy);
        event.setSeed(0xDEADBEEFL);
        level.addEvent(event);
        level.processEvents();
        zone.addLevel(level);
        return level;
    }

    public Level saveLevel(Level level) {
        logger.info("saving level with # entites: " + level.getEntities().size());
        logger.info("saving level with # items: " + level.getItems().size());
        return levelRepository.save(level);
    }

    public Level loadLevel(UUID levelId) {
        logger.info("Loading level with id: " + levelId);
        Level level = levelRepository.findById(levelId).get();
        Hibernate.initialize(level.getItems());
        // Run through all events
        level.processEvents();
        // Initialize all entity positions
        level.getEntities().forEach(e -> e.move(e.getPosition()));
//        level.getItems().forEach(i -> {
//            logger.info(i.toString());
//            assert i.getPosition() != null;
//            level.getTileAt(i.getPosition()).addItem(i);
//        });
        return level;
    }

}
