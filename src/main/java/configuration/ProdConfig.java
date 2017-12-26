package configuration;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static configuration.ProcessConst.PROD;

@Profile(PROD)
@Configuration
public class ProdConfig {

    @Bean
    public SparkConf sparkConf (){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("football_descpt");
        return sparkConf;
    }
}
