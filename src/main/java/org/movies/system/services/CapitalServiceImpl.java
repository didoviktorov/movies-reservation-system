package org.movies.system.services;

import org.movies.system.models.entities.Capital;
import org.movies.system.repositories.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
@Transactional
public class CapitalServiceImpl implements CapitalService {

    private CapitalRepository capitalRepository;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }


    @Override
    public Long capitalCount() {
        return this.capitalRepository.count();
    }

    @Override
    public void save(Capital capital) {
        this.capitalRepository.save(capital);
    }

    @Override
    public void seedCapitals() {
        String directory = System.getProperty("user.dir");

        String filePath =
                directory +
                        File.separator + "src" +
                        File.separator + "main" +
                        File.separator + "resources" +
                        File.separator + "capitals" +
                        File.separator + "capitals.txt";
        System.out.println(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] capitalArgs = currentLine.split("\\,");
                String capitalName = capitalArgs[0];
                double longitude = Double.parseDouble(capitalArgs[1]);
                double latitude = Double.parseDouble(capitalArgs[2]);
                Capital capital = new Capital();
                capital.setName(capitalName);
                capital.setLongitude(longitude);
                capital.setLatitude(latitude);
                this.capitalRepository.save(capital);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
