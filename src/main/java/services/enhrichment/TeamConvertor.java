package services.enhrichment;

import org.apache.spark.sql.api.java.UDF1;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

//@Component
//@RegisterUDF
public class TeamConvertor implements UDF1<String, String>, Serializable {

//@Autowired
private String[] countreis;

private Map<String, List<String>> playerMap;

//@PostConstruct
private void init() throws IOException {
    Properties properties = new Properties();
    InputStream stream = new FileInputStream("teams.properties");
    properties.load(stream);
    playerMap = new HashMap<>();
    for (String country: countreis){

        System.out.println(properties.getProperty(country));
    }

}


    @Override
    public String call(String s) throws Exception {


        return "not defined";
    }
}
