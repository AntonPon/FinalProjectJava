package services.enhrichment;

import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UDFRegistratorListener implements ApplicationListener<ContextRefreshedEvent>{


//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private SQLContext sqlContext;
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
//        Collection<Object> udfObjects = applicationContext.getBeansWithAnnotation(RegisterUDF.class).values();
//        for(Object o: udfObjects){
//            sqlContext.udf().register(o.getClass().getName(),(UDF1<?, ?>) o, DataTypes.StringType);
//        }
//    }
@Autowired
private ApplicationContext context;

    @Autowired
    private SQLContext sqlContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Collection<Object> udfObjects = context.getBeansWithAnnotation(RegisterUDF.class).values();
        for (Object udfObject : udfObjects) {

            sqlContext.udf().register(udfObject.getClass().getName(),
                    (UDF1<?, ?>) udfObject,
                    DataTypes.StringType);
        }
    }
}
