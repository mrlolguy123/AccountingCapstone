package com.team7.dfa;

/**
 * This class is the main class that runs the application.
 * It calls the main method from the TemplateTestApplication class but without an explicit extension of Application so that packaging the application as a JAR file is possible.
 */
public class Main {
    public static void main(String[] args)  throws Exception {
        TemplateTestApplication.main(args);
    }
}
