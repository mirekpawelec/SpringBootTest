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
import pl.pawelec.spring.model.Family;
import pl.pawelec.spring.model.Father;

/**
 *
 * @author mirek
 */
public class FamilyDeserializer extends StdDeserializer<Family>{
    public FamilyDeserializer() {this(null);}
    public FamilyDeserializer(Class<?> vc) {super(vc);}

    @Override
    public Family deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Long id = node.get("id").asLong();
        Long fatherId = node.get("father").asLong();
        Father father = new Father();
        father.setId(fatherId); 
        return new Family(id, father);
    }
    
}
