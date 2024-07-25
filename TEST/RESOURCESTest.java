import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

class RESOURCESTest {

    @Test
    void is_Older_Than() {
        Path file_path = Paths.get("C://Users//barto//Downloads//Sprawozdanie JA LAB1.pdf");
        boolean result = RESOURCES.is_Older_Than(file_path, 0);
        Assertions.assertTrue(result);
    }

    @Test
    void delete_file() {
        Path file_path = Paths.get("D:\\POLSL\\AMIAL\\kurs-pochodne\\domowe\\odp1.pdf");
        boolean result = RESOURCES.delete_file(file_path);
        Assertions.assertTrue(result);
    }
}