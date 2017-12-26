package services.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.util.*;

@Service("player")
public class PlayerValidation implements Validation, Serializable{

    @Autowired
    Set<String> players;

    @Override
    public void validateRDD(String[] matchInfo) {
       String  to = null;
       String from =  null;
       Integer code = null;


       for ( String s: matchInfo){

           String[] st = s.split("=");

           if(st[0].equals("code")){
               code = new Integer(st[1]);

           }else if (st[0].equals("from")){
               from = st[1];

           }else if (st[0].equals("to")){
               to = st[1];

           }
       }

       if (code.intValue() <3){

           if (players.contains(from)){


               System.out.println("wrong input! for code "+ code.intValue() + " need only 1 player");

           }
       else{
               if (!players.contains(from) ||!players.contains(to)){
                   System.out.println("wrong input!" + "such a player cannot do an operation at the field, because he doesn't exist");
               }
           }
       }
    }
}
