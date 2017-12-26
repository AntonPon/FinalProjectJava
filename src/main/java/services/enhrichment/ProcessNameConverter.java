package services.enhrichment;

import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import services.bpp.AutowiredBroadcast;


import java.io.Serializable;
import java.util.List;

@Component
@RegisterUDF
public class ProcessNameConverter implements UDF1<String, String>, Serializable{

    @Autowired
    private List<String> list;
    @Override
    public String call(String s) throws Exception {
        //return this.broadcast.getValue().get(new Integer(s).intValue());
        return list.get(new Integer(s).intValue()-1);
    }
}
