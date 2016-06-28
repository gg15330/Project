package com.company;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        Main cp = new Main();
        cp.run();

    }

    private void run() {
        // creates an input stream for the file to be parsed
        FileInputStream in;
        try {
            in = new FileInputStream("src/com/company/FileToParse.java");
        }
        catch(Exception e) {
            in = null;
            e.printStackTrace();
        }

        CompilationUnit cu;
        // parse the file
        try {
            cu = JavaParser.parse(in);
            in.close();
        } catch(Exception e) {
            cu = null;
            e.printStackTrace();
        }

        MethodVisitor mv = new MethodVisitor();
        mv.visit(cu, null);

        int count = 0;
        int i = 0;
        while(i < 10) {
            count++;
            i++;
        }
        if(i < count) { System.out.println("Not matching."); }
        System.out.println("Loop entered " + count + " times.\n");
    }

    private class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this
            // CompilationUnit, including inner class methods
            System.out.println("NAME: " + n.getName());
            System.out.println("DECL: " + n.getDeclarationAsString());
            BlockStmt bs = n.getBody();
            for(Statement s : bs.getStmts()) {
                process(s);
            }
            super.visit(n, arg);
        }

        private void process(Statement s) {
            if(s instanceof ForStmt) {
                System.out.println("FOR STATEMENT: ");
                System.out.println("INIT: " + ((ForStmt) s).getInit());
                System.out.println("COMPARE: " + ((ForStmt) s).getCompare());
                System.out.println("UPDATE: " + ((ForStmt) s).getUpdate());
                System.out.println(
                        "STATEMENT: \n" +
                                "Begin line: " + s.getBeginLine() + "\n" +
                                s + "\n" +
                                "End line: " + s.getEndLine()
                );
            }
            else {
                System.out.println(
                        "STATEMENT: \n" +
                                "Begin line: " + s.getBeginLine() + "\n" +
                                s + "\n" +
                                "End line: " + s.getEndLine()
                );
            }
        }
    }
}
