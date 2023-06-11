package dev.kybu.unicacity.fbi.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.function.Consumer;

public class ConfigurationService {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /*=-------------------------------------------------------=*/

    public ConfigurationService() {}

    /*=-------------------------------------------------------=*/

    public <T> T readConfiguration(final File file, final Class<T> clazz) {
        try(final FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, clazz);
        } catch(final Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public <T> void saveConfiguration(final File file, final T object) {
        try(final FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(object));
        } catch(final Exception exception) {
            exception.printStackTrace();
        }
    }

}
