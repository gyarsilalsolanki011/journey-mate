package com.gyarsilalsolanki011.journeymate.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class TripDataLoader implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public TripDataLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Integer count = null;
        try {
            count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM trips", Integer.class);
        } catch (Exception e) {
            System.out.println("⚠️ Trips table not found, will create and insert data.");
        }

        if (count != null && count > 0) {
            System.out.println("✅ Trips already exist (" + count + " rows), skipping load.");
            return;
        }

        Resource resource = new ClassPathResource("setup/trips_db.sql");
        String sql = new String(resource.getInputStream().readAllBytes());

        for (String statement : sql.split(";")) {
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement.trim());
            }
        }

        System.out.println("✅ trips loaded successfully from trips_db.sql");
    }
}

