package com.proj.assgn;

import org.apache.spark.sql.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App2 {
    public static void main(String[] args) throws Exception {
        String path = args[0];
        String features = args[1];
        SparkSession sparkSession = SparkSession.builder().appName("csvtoDf")
                .master("local")
                .config("spark.master", "local")
                .config("spark.executor.memory", "2g")
                .config("spark.driver.memory", "2g")
                .config("spark.driver.offHeap.enabled", true)
                .config("spark.driver.offHeap.size", "16g")
                .config("spark.testing.memory", "2147480000")
                .getOrCreate();
        sparkSession.sparkContext().setLogLevel("OFF");

        Dataset<Row> df = sparkSession.read().format("csv")
                .option("handler", "true")
                .option("multiline", true)
                .option("seperator", ",")
                .option("inferschema", false)
                .option("header", "false")
                .load(path);

        df.show();

        List<String> positionsSource = Arrays.stream(features.split(",")).map(String::trim).collect(Collectors.toList());
        Dataset<Row> dataset = sparkSession.emptyDataFrame();
        for (String name : positionsSource) {
            if (dataset.isEmpty()) {
                String amount = df.filter("_c2='" + name + "' OR _c3='" + name + "' OR _c4='" + name + "'").agg(functions.min("_c1")).first().mkString();
                dataset = df.filter("_c2='" + name + "' OR _c3='" + name + "' OR _c4='" + name + "'").filter("_c1='" + amount + "'").distinct();
            } else {
                String amount = df.filter("_c2='" + name + "' OR _c3='" + name + "' OR _c4='" + name + "'").agg(functions.min("_c1")).first().mkString();
                dataset = dataset.union(df.filter("_c2='" + name + "' OR _c3='" + name + "' OR _c4='" + name + "'").filter("_c1='" + amount + "'").distinct());
            }
        }
        String sum = dataset.agg(functions.sum("_c1")).first().mkString();
        List<String> plan = dataset.select(dataset.col("_c0")).map(row -> row.mkString(), Encoders.STRING()).collectAsList();
        System.out.println(sum + "," + String.join(",", plan));
    }
}


