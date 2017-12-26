package services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.enhrichment.PeriodConvertor;
import services.enhrichment.ProcessNameConverter;
import services.enhrichment.TeamConvertor;

import java.util.Arrays;
import java.util.List;


import static org.apache.spark.sql.functions.*;

@Service
public class CreatingFrameImpl implements CreatetingFrame {
    @Autowired
    private JavaSparkContext sc;

    @Value("${columnNames}")
    private String[] columnNames;

    @Autowired
    private GameProcessing gmp;

    @Autowired
    private SQLContext sqlContext;



    @Override
    public DataFrame createDF(String path) {


        JavaRDD<Row> rd = gmp.getGameProcessed(sc.textFile(path));
        //System.out.println(rd.take(5));
        List<String> columns = Arrays.asList(columnNames);

        StructField[] fields = new  StructField[columns.size()];
        for (int i = 0; i < fields.length; i++){
            fields[i] = DataTypes.createStructField(columns.get(i), DataTypes.StringType, false);
        }

        StructType schema = DataTypes.createStructType(fields);
        DataFrame dataFrame = sqlContext.createDataFrame(rd, schema);
        dataFrame = dataFrame.withColumn("process", callUDF(ProcessNameConverter.class.getName(), col("code")));
        dataFrame = dataFrame.withColumn("period", callUDF(PeriodConvertor.class.getName(), col("eventTime")));
       // dataFrame = dataFrame.withColumn("from team", callUDF(TeamConvertor.class.getName(), col("from")));
        //dataFrame.show();
        return dataFrame;
    }
}
