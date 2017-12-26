package services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.validation.Validation;

import java.io.Serializable;
import java.util.Map;
@Data
@Service
public class Mapper implements Serializable {
    @Autowired
    private Map<String, Validation> validationMap;
}
