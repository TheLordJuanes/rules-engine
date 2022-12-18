package com.bootcamp.prft.rulesEngine.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

public class ComparisonDeserializer extends JsonDeserializer<Comparison> {
    @Override
    public Comparison deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = mapper.readTree(jsonParser);
        Class<? extends Comparison> comparisonClass;
        if (checkConditionsForColumnComparison(jsonParser.getParsingContext().toString()))
            comparisonClass = ColumnComparison.class;
        else
            comparisonClass = ValueComparison.class;
        return mapper.treeToValue(root, comparisonClass);
    }

    private boolean checkConditionsForColumnComparison(String parsingContext) {
        return parsingContext.equals("{\"column\"}");
    }
}