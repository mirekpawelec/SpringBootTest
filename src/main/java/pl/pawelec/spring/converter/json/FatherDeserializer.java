/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.converter.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Date;
import pl.pawelec.spring.model.Father;
import pl.pawelec.spring.model.PersonData;

/**
 *
 * @author mirek
 */
public class FatherDeserializer extends StdDeserializer<Father>{    
    public FatherDeserializer() {this(null);}
    public FatherDeserializer(Class<?> vc) {super(vc);}
    
    @Override
    public Father deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        long id = node.get("id").asLong();
        Long personDataId = node.get("personData").asLong();
        Long birthDateNo = node.get("birthDate").asLong();
        PersonData personData = new PersonData(personDataId, "", "", "");
        Date birthDate = new Date(birthDateNo);
        return new Father(id, personData, birthDate);
    }
    
}
