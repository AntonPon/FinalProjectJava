package services;

import org.apache.spark.sql.DataFrame;

public interface CreatetingFrame {

    DataFrame createDF(String path);
}
