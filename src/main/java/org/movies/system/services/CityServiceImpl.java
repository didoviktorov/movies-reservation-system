package org.movies.system.services;

import org.movies.system.models.entities.City;
import org.movies.system.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public Long cityCount() {
        return this.cityRepository.count();
    }

    @Override
    public void save(City city) {
        this.cityRepository.save(city);
    }

    @Override
    public void seedCities() {
        String directory = System.getProperty("user.dir");

        String filePath =
                directory +
                        File.separator + "src" +
                        File.separator + "main" +
                        File.separator + "resources" +
                        File.separator + "cities" +
                        File.separator + "cities.txt";
        System.out.println(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] cityArgs = currentLine.split("\\,");
                String cityName = cityArgs[0];
                City city = new City();
                city.setName(cityName);
                this.cityRepository.save(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
