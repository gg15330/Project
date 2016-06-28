package com.company;

import java.io.File;

/**
 * Created by george on 22/06/16.
 */
public class Parser {

    private File f;

    public Parser(File f) {
        System.out.println("Parser created.");
        this.f = f;
    }

    public void printMethods(File f) {
        System.out.println("Methods:");
    }

}
