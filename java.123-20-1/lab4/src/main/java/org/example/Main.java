import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Клас для керування записом та зчитуванням університету у форматі JSON
class JsonManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Запис університету у файл у форматі JSON
    public static void saveUniversityToJson(University university, String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(university, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Зчитування університету з файлу у форматі JSON
    public static University loadUniversityFromJson(String fileName) {
        try (Reader reader = new FileReader(fileName)) {
            Type universityType = new TypeToken<University>() {}.getType();
            return gson.fromJson(reader, universityType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

// Клас для тестування
class UniversityTest {
    public void testUniversitySerialization() {
        University oldUniversity = createTypicalUniversity();
        JsonManager.saveUniversityToJson(oldUniversity, "university.json");
        University newUniversity = JsonManager.loadUniversityFromJson("university.json");
        assert oldUniversity.equals(newUniversity);
    }
}

