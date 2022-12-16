package com.bootcamp.prft.rulesEngine.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class ComparisonDeserializer extends JsonDeserializer<Comparison> {
    @Override
    public Comparison deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jsonParser);
        Class<? extends Comparison> comparisonClass = null;
        if(checkConditionsForColumnComparison(jsonParser.getParsingContext().toString())) {
            comparisonClass = ColumnComparison.class;
        } else {
            comparisonClass = ValueComparison.class;
        }
        if (comparisonClass == null){
            return null;
        }
        return mapper.treeToValue(root, comparisonClass);
    }

    private boolean checkConditionsForColumnComparison(String parsingContext){
        if(parsingContext.equals("{\"column\"}"))
            return true;
        return false;
    }
}
