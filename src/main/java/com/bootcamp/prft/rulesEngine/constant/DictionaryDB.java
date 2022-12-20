package com.bootcamp.prft.rulesEngine.constant;

import lombok.Getter;
import java.util.Dictionary;
import java.util.Hashtable;

@Getter
public class DictionaryDB {

    private final Dictionary<String, TypeData> postgresqlDictionary;
    public DictionaryDB() {
        this.postgresqlDictionary = createPostgresqlDictionary();
    }

    private Dictionary<String, TypeData> createPostgresqlDictionary() {
        Dictionary<String, TypeData> dictionary = new Hashtable<>();

        dictionary.put("bigint", TypeData.Integer);
        dictionary.put("int8", TypeData.Integer);
        dictionary.put("bigserial", TypeData.Integer);
        dictionary.put("serial8", TypeData.Integer);
        dictionary.put("bit", TypeData.String);
        dictionary.put("bit varying", TypeData.String);
        dictionary.put("varbit", TypeData.String);
        dictionary.put("boolean", TypeData.Boolean);
        dictionary.put("bool", TypeData.Boolean);
        dictionary.put("box", TypeData.String);
        dictionary.put("bytea", TypeData.String);
        dictionary.put("character", TypeData.String);
        dictionary.put("character varying", TypeData.String);
        dictionary.put("cidr", TypeData.String);
        dictionary.put("circle", TypeData.String);
        dictionary.put("date", TypeData.String);
        dictionary.put("double precision", TypeData.Double);
        dictionary.put("float8", TypeData.Double);
        dictionary.put("inet", TypeData.String);
        dictionary.put("integer", TypeData.Integer);
        dictionary.put("int", TypeData.Integer);
        dictionary.put("int4", TypeData.Integer);
        dictionary.put("interval", TypeData.String);
        dictionary.put("json", TypeData.String);
        dictionary.put("jsonb", TypeData.String);
        dictionary.put("line", TypeData.String);
        dictionary.put("lseg", TypeData.String);
        dictionary.put("macaddr", TypeData.String);
        dictionary.put("macaddr8", TypeData.String);
        dictionary.put("money", TypeData.Double);
        dictionary.put("numeric", TypeData.Integer);
        dictionary.put("path", TypeData.String);
        dictionary.put("pg_lsn", TypeData.String);
        dictionary.put("pg_snapshot", TypeData.String);
        dictionary.put("point", TypeData.String);
        dictionary.put("polygon", TypeData.String);
        dictionary.put("real", TypeData.Double);
        dictionary.put("float4", TypeData.Double);
        dictionary.put("smallint", TypeData.Integer);
        dictionary.put("int2", TypeData.Integer);
        dictionary.put("smallserial", TypeData.Integer);
        dictionary.put("serial2", TypeData.Integer);
        dictionary.put("serial", TypeData.Integer);
        dictionary.put("serial4", TypeData.Integer);
        dictionary.put("text", TypeData.String);
        dictionary.put("time", TypeData.String);
        dictionary.put("time with time zone", TypeData.String);
        dictionary.put("timetz", TypeData.String);
        dictionary.put("timestamp", TypeData.String);
        dictionary.put("timestamp with time zone", TypeData.String);
        dictionary.put("timestamptz", TypeData.String);
        dictionary.put("tsquery", TypeData.String);
        dictionary.put("tsvector", TypeData.String);
        dictionary.put("txid_snapshot", TypeData.String);
        dictionary.put("uuid", TypeData.String);
        dictionary.put("xml", TypeData.String);
        return dictionary;
    }
}