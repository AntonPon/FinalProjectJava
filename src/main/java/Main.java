

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CreatetingFrame;

import static configuration.ProcessConst.DEV;
import static configuration.ProcessConst.PROD;

public class Main {

    public static void main(String ... args){
        System.setProperty("spring.profiles.active", DEV);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CreatetingFrame gm = context.getBean(CreatetingFrame.class);
        gm.createDF("data/rawData.txt");
        }

    }
