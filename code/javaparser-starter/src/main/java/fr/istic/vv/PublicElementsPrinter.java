package fr.istic.vv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;

// This class visits a compilation unit and
// prints all public enum, classes or interfaces along with their public methods
public class PublicElementsPrinter extends VoidVisitorWithDefaults<Void> {
    List<String>publicMethods=new ArrayList<>();
    List<String>fielsTab=new ArrayList<>();
    String retour="";
    String retour1="";
    
    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for (TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {
        if (!declaration.isPublic())
            return;
        System.out.println(declaration.getFullyQualifiedName().orElse("[Anonymous]"));
        for (MethodDeclaration method : declaration.getMethods()) {
            method.accept(this, arg);
        }
        // Printing nested types in the top level
        for (BodyDeclaration<?> member : declaration.getMembers()) {
                member.accept(this, arg);
        }
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(EnumDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(FieldDeclaration fieldDeclaration, Void arg) {
        String fieldName=fieldDeclaration.getVariable(0).getNameAsString();
        if (!fieldDeclaration.isPublic()) {
            fielsTab.add(fieldName);
            for(int j=0;j<publicMethods.size();j++){
                if(publicMethods.get(j).equals("get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1))){
                   
                    retour1 += ("Private Field: " + fieldName +
                        " in class: "
                        + fieldDeclaration.findAncestor(ClassOrInterfaceDeclaration.class).orElse(null)
                                .getNameAsString()
                        +
                        " in package: " + fieldDeclaration.findAncestor(CompilationUnit.class).orElse(null)
                                .getPackageDeclaration().map(p -> p.getNameAsString()).orElse("[Default Package]"))+" has public getter\n\t";
                    
                }    
            }
         
        }
        else
            System.out.println("There is not private fileds in this file!");

        }


    @Override
    public void visit(MethodDeclaration declaration, Void arg) {
        if (!declaration.isPublic())
            return;
        else{
        publicMethods.add(declaration.getNameAsString());
        //System.out.println(publicMethods.toString() );
        }
    }

    public String getReport() {
    retour+="this is the list of private fields"+fielsTab.toString()+",only:\n\t"+retour1; 
        return retour;
    }

}
