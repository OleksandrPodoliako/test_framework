package csv.csvControllers;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.log4j.Logger;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvGeneric<E> {

    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private final Class<E> entityClass;
    private String pathToCsv;
    private Reader reader = null;

    public CsvGeneric(Class<E> entityClass, String pathToCsv) {
        this.entityClass = entityClass;
        this.pathToCsv = pathToCsv;
    }

    public Reader getReader() {
        try {
            return Files.newBufferedReader(Paths.get(pathToCsv));
        } catch (Exception e) {
            LOGGER.info("File does not exist");
            return reader;
        }
    }

    public List<E> getCsvBeanList() {
        return new CsvToBeanBuilder(getReader())
                .withType(entityClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
    }

    public Boolean isExisting() {
        try {
            Files.newBufferedReader(Paths.get(pathToCsv));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
