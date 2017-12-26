package services.validation;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;

import java.io.Serializable;

public interface Validation {
    void validateRDD(String[] matchInfo);
}
