

import com.sun.tracing.ProbeName;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.lang.reflect.Array;
import java.util.*;

@Configuration
@ComponentScan("services")
@ComponentScan("configuration")
@ComponentScan("aop")
@PropertySource("classpath:codes.properties")
@PropertySource("classpath:log4j.properties")
@PropertySource("classpath:football_columns.properties")
@PropertySource("classpath:teams.properties")
@PropertySource("classpath:countries.properties")
@EnableAspectJAutoProxy
public class Config {

    @Autowired
    private SparkConf sparkConf;


    @Autowired
    private Environment env;


    @Bean
    public String[] countries(){
        List<String>count =  env.getProperty("countries", List.class);
        String[] countries = count.toArray(new String[count.size()]);
        return countries;
    }

    @Bean
    public JavaSparkContext sc() {
        return new JavaSparkContext(sparkConf);
    }

    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(sc());
    }

    @Bean
    public Set<String> teams() {
        Set<String> st = new TreeSet<>();
        for (String country: countries()){
            List<String> count = env.getProperty(country, List.class);
            st.addAll(count);
        }

        return st;
    }

    @Bean
    public List<String> process() {
        List<String> process = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            process.add(env.getProperty(Integer.toString(i)));
        }
        return process;
    }

}
