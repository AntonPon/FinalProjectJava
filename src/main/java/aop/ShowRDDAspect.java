package aop;

import org.apache.spark.api.java.JavaRDD;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

import static configuration.ProcessConst.DEV;


@Component
@Aspect
@Profile(DEV)
public class ShowRDDAspect {

    @Before("@annotation(ShowRDDAtTheBeginning)")
    public void showRDDAtTheBeginningOfTheMethod(JoinPoint jp){
        JavaRDD<String> rdd = (JavaRDD<String>) jp.getArgs()[0];
        System.out.println("Aspect begins to print the rdd ...");
        printToConsole(jp, rdd);
        System.out.println("Aspect finished print the rdd");
    }

    private void printToConsole(JoinPoint jp, JavaRDD<String> rdd) {
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName =  jp.getSignature().getName();
        System.out.println("**************************************BEGIN*****" +className+ " "+methodName+ "******");
       List<String> list =  rdd.take(5);
       for (String s: list){
           System.out.println(s);
       }
        System.out.println("**************************************END*****" +className+ " "+methodName+ "******");
    }
}
