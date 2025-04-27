package constants;

import java.nio.file.Paths;

public interface PATH {
    String RESOURCES = Paths.get("src", "main", "resources").toFile().getAbsolutePath();
    String SCHEMA_VALIDATION = Paths.get("src", "main", "resources", "schema_validation").toFile().getAbsolutePath();
    String TEST_DATA = Paths.get("src", "main", "resources", "test_data").toFile().getAbsolutePath();
}