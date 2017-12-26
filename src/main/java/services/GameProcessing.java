package services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;

import java.io.Serializable;

public interface GameProcessing {

    JavaRDD<Row> getGameProcessed(JavaRDD<String> rdd);
}
