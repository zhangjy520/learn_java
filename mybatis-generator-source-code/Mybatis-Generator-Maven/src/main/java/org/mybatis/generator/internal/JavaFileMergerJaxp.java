/*
 *  Copyright 2005 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mybatis.generator.internal;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.mybatis.generator.config.MergeConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mybatis.generator.api.dom.OutputUtilities.newLine;

/**
 * This class handles the task of merging changes into an existing XML file.
 *
 * @author Jeff Butler
 */
public class JavaFileMergerJaxp {
    public String getNewJavaFile(String newFileSource, String existingFileFullPath) throws FileNotFoundException {
        CompilationUnit newCompilationUnit = JavaParser.parse(newFileSource);
        CompilationUnit existingCompilationUnit = JavaParser.parse(new File(existingFileFullPath));
        return mergerFile(newCompilationUnit, existingCompilationUnit);
    }

    public String mergerFile(CompilationUnit newCompilationUnit, CompilationUnit existingCompilationUnit) {

        System.out.println("合并java代码");
        StringBuilder sb = new StringBuilder(newCompilationUnit.getPackageDeclaration().get().toString());
        newCompilationUnit.removePackageDeclaration();

        //合并imports
        NodeList<ImportDeclaration> imports = newCompilationUnit.getImports();
        imports.addAll(existingCompilationUnit.getImports());
        Set importSet = new HashSet<ImportDeclaration>();
        importSet.addAll(imports);

        NodeList<ImportDeclaration> newImports = new NodeList<ImportDeclaration>();
        newImports.addAll(importSet);
        newCompilationUnit.setImports(newImports);
        for (ImportDeclaration i : newCompilationUnit.getImports()) {
            sb.append(i.toString());
        }
        newLine(sb);
        NodeList<TypeDeclaration<?>> types = newCompilationUnit.getTypes();
        NodeList<TypeDeclaration<?>> oldTypes = existingCompilationUnit.getTypes();

        for (int i = 0; i < types.size(); i++) {
            //截取Class
            String classNameInfo = types.get(i).toString().substring(0, types.get(i).toString().indexOf("{") + 1);
            sb.append(classNameInfo);
            newLine(sb);
            newLine(sb);
            //合并fields
            List<FieldDeclaration> fields = types.get(i).getFields();
            List<FieldDeclaration> oldFields = oldTypes.get(i).getFields();
            List<FieldDeclaration> newFields = new ArrayList<FieldDeclaration>();
            HashSet<FieldDeclaration> fieldDeclarations = new HashSet<FieldDeclaration>();
            fieldDeclarations.addAll(fields);
            fieldDeclarations.addAll(oldFields);
            newFields.addAll(fieldDeclarations);
            for (FieldDeclaration f : newFields) {
                sb.append("\t" + f.toString());
                newLine(sb);
                newLine(sb);
            }

            //合并methods
            List<MethodDeclaration> methods = types.get(i).getMethods();
            List<MethodDeclaration> existingMethods = oldTypes.get(i).getMethods();

            for (MethodDeclaration f : methods) {
                String res = f.toString().replaceAll("\r\n", "\r\n\t");
                sb.append("\t" + res);
                newLine(sb);
                newLine(sb);
            }

            List<String> methodList = new ArrayList<String>();
            for (MethodDeclaration m : methods) {
                methodList.add(m.getName().toString());
            }
            methodList.add("toString");
            methodList.add("hashCode");
            methodList.add("equals");

            for (MethodDeclaration m : existingMethods) {
                if (methodList.contains(m.getName().toString())) {
                    continue;
                }

                boolean flag = true;
                for (String tag : MergeConstants.OLD_ELEMENT_TAGS) {
                    if (m.toString().contains(tag)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    String res = m.toString().replaceAll("\r\n", "\r\n\t");
                    sb.append("\t" + res);
                    newLine(sb);
                    newLine(sb);
                }
            }

            //判断是否有内部类
            types.get(i).getChildNodes();
            for (Node n : types.get(i).getChildNodes()) {
                if (n.toString().contains("static class")) {
                    String res = n.toString().replaceAll("\r\n", "\r\n\t");
                    sb.append("\t" + res);
                }
            }

        }

        return sb.append(System.getProperty("line.separator") + "}").toString();
    }

}
