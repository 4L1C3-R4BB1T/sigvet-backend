ALTER TABLE vaccinations DROP COLUMN date_time;

ALTER TABLE vaccinations ADD COLUMN "date" DATE NULL;
ALTER TABLE vaccinations ADD COLUMN "hour" TIME NULL;