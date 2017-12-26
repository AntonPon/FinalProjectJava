package services;

import aop.ShowDataFrameAtTheBeginning;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.bpp.AutowiredBroadcast;
import services.validation.Validation;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class GameProcessingImpl implements GameProcessing, Serializable {

   @AutowiredBroadcast
   private Broadcast<Mapper> broadcast;

    @Value("${columnNames}")
    private String[] columnNames;


    @Override
    @ShowDataFrameAtTheBeginning
    public JavaRDD<Row> getGameProcessed(JavaRDD<String> rdd) {

        JavaRDD<Row> moments = rdd.map((String line) ->{
            String[] elements = line.split(";");

            for(String key: broadcast.value().getValidationMap().keySet()){
                this.broadcast.value().getValidationMap().get(key).validateRDD(elements);
            }
            List<String>  columns = Arrays.asList(columnNames);
            String realColumns[] = new String[columns.size()];


            for(int i=0; i < columns.size(); i++){
                if (columns.contains(elements[i].split("=")[0])){
                    realColumns[i] = elements[i].split("=")[1];
                }

            }

            return RowFactory.create(realColumns);
        });
        //DataFrame df =sqc.emptyDataFrame();
        return moments;
    }
}
