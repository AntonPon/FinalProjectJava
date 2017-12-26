package services.validation;


import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("time")
public class TimeValidation implements  Validation, Serializable{
    @Override
    public void validateRDD(String[] matchInfo) {

        for(String s: matchInfo){
            String[] st = s.split("=");

            if(st[0].equals("eventTime")){
                Integer i = new Integer(st[1].split(":")[0]);
                if (i.intValue() >= 90){
                    System.out.println("alarm!!! event time "+i.intValue() + " is bigger than 90. Check your data, or you don't know the football rules");
                }
            }
        }

    }
}
