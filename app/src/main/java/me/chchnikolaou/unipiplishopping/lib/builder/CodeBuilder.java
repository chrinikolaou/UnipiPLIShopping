package me.chchnikolaou.unipiplishopping.lib.builder;

import java.util.UUID;

public class CodeBuilder {

    public static String generate(int number){
        if(number > 16) number = 16;

        return UUID.randomUUID().toString().substring(0, number+1);

    }

}
