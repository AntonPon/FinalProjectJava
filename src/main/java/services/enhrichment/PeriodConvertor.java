package services.enhrichment;

import org.apache.spark.sql.api.java.UDF1;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RegisterUDF
public class PeriodConvertor implements UDF1<String, String>, Serializable {
    @Override
    public String call(String s) throws Exception {
        String[] splitted = s.split(":");
        Integer time = new Integer(splitted[0]);
        if(time.intValue() > 90){
            return "wrong time";
        }else if(time.intValue() < 45){
            return "first period";
        }
        return "second period";
    }
}
