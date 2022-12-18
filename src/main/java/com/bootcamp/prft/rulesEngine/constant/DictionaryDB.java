package com.bootcamp.prft.rulesEngine.constant;

import lombok.Getter;
import java.util.Dictionary;
import java.util.Hashtable;

@Getter
public class DictionaryDB {

    private final Dictionary<String, TypeData> postgresDictionay;
    public DictionaryDB() {
        this.postgresDictionay = createPostgresDictionary();
    }

    private Dictionary<String, TypeData> createPostgresDictionary() {
        Dictionary<String, TypeData> dictionary = new Hashtable<>();
        dictionary.put("bigint", TypeData.Integer);
        dictionary.put("bigserial", TypeData.Integer);
        dictionary.put("boolean", TypeData.Boolean);
        dictionary.put("bool", TypeData.Boolean);
        dictionary.put("character", TypeData.String);
        dictionary.put("character varying", TypeData.String);
        dictionary.put("integer", TypeData.Integer);
        dictionary.put("date", TypeData.String);
        dictionary.put("text", TypeData.String);
        return dictionary;
    }
}