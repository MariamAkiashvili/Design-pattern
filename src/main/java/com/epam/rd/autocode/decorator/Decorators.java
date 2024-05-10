package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {

        List<String> targetList = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); i += 2) {
            targetList.add(sourceList.get(i));
        }


        return targetList;
    }


}
