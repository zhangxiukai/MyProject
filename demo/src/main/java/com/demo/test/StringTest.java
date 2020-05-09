package com.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class StringTest {

    public static void main(String[] args) {
        String name = "con";
        String field = "\"ID\",\"NAME\",\"age\"";
        String condition = "id > 10 and name <> PREV(con.age, 4) and name < FIRST(id)";
        List<String> fields = Arrays.asList(field.split(","));
        List<String> conditionWord = new ArrayList<>();
        List<String> conditions = Arrays.asList(condition.split("and"));
        conditions.forEach(con -> {
            String[] cons = con.split("AND");
            for (String cond : cons) {
                List<String> condList = new ArrayList<>();
                String[] words = cond.split(" ");
                for (String word : words) {
                    for (String column : fields) {
                        if (word.contains("\"")) {
                            if (word.contains(column)) {
                                if (word.contains("(") && !word.contains(".")) {
                                    word = word.replace(column, name + "." + column);
                                }
                                if (!word.contains(".")) {
                                    word = name + "." + word;
                                }
                            }
                        } else {
                            column = column.replaceAll("\"", "");
                            if (word.toUpperCase().contains(column)) {
                                if (word.contains("(") && !word.contains(".")) {
                                    word = word.replace(column.toLowerCase(), name + "." + column.toLowerCase());
                                }
                                if (!word.contains(".")) {
                                    word = name + "." + word;
                                }
                            } else if (word.toLowerCase().contains(column)) {
                                if (word.contains("(") && !word.contains(".")) {
                                    word = word.replace(column.toLowerCase(), name + "." + column.toLowerCase());
                                }
                                if (!word.contains(".")) {
                                    word = name + "." + word;
                                }
                            }
                        }
                    }
                    condList.add(word);
                }
                conditionWord.add(StringUtils.join(condList, " "));
            }
        });
        String sql = StringUtils.join(conditionWord, " AND ");
        System.out.println(sql);
    }
}
