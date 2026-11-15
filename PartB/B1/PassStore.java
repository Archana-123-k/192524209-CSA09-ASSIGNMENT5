import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class PassStore {
    private final Path file;

    PassStore(Path file) {
        this.file = file;
    }

    public void add(String passId, String regNo, String name, int route, double amount) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(String.join(",", passId, regNo, name,
                    String.valueOf(route), String.valueOf(amount)));
            writer.newLine();
        }
    }

    public String[] find(String passId) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] fields = line.split(",", -1);
                if (fields.length != 5) continue;

                if (passId.equals(fields[0].trim())) {
                    return Arrays.copyOf(fields, fields.length);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Path temp = Files.createTempFile("passes", ".csv");
        PassStore store = new PassStore(temp);

        store.add("P1001", "192311001", "Arun", 12, 4500);
        Files.writeString(temp, "P1002,DAMAGED LINE\n", StandardOpenOption.APPEND);
        store.add("P1003", "192311003", "Divya", 7, 3800);

        System.out.println(Arrays.toString(store.find("P1003")));
        System.out.println(Arrays.toString(store.find("P1002")));

        Files.deleteIfExists(temp);
    }
}
