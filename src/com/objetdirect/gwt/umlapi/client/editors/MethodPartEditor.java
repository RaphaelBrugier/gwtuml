/**
 * 
 */
package com.objetdirect.gwt.umlapi.client.editors;
import com.google.gwt.user.client.Window;
import com.objetdirect.gwt.umlapi.client.UMLDrawerException;
import com.objetdirect.gwt.umlapi.client.analyser.LexicalAnalyser;
import com.objetdirect.gwt.umlapi.client.analyser.MethodSyntaxAnalyser;
import com.objetdirect.gwt.umlapi.client.artifacts.classArtifactComponent.ClassMethodsArtifact;
import com.objetdirect.gwt.umlapi.client.artifacts.classArtifactComponent.ClassPartArtifact;
import com.objetdirect.gwt.umlapi.client.umlcomponents.Method;
import com.objetdirect.gwt.umlapi.client.webinterface.UMLCanvas;
/**
 * @author  fmounier
 */
public class MethodPartEditor extends FieldEditor {

    Method methodToChange;

    public MethodPartEditor(UMLCanvas canvas, ClassMethodsArtifact artifact, Method methodToChange) {
        super(canvas, artifact);
        this.methodToChange = methodToChange;
    }
    @Override
    protected boolean updateUMLArtifact(String newContent) {
        if(newContent.equals("")) {
            ((ClassMethodsArtifact) artifact).remove(methodToChange);
            ((ClassMethodsArtifact) artifact).getClassArtifact().rebuildGfxObject();
            return false;
        }

        LexicalAnalyser lex = new LexicalAnalyser(newContent);
        try {
            MethodSyntaxAnalyser ma = new MethodSyntaxAnalyser();
            ma.process(lex, null);
            Method newMethod = ma.getMethod();
            methodToChange.setVisibility(newMethod.getVisibility());
            methodToChange.setName(newMethod.getName());
            methodToChange.setReturnType(newMethod.getReturnType());
            methodToChange.setParameters(newMethod.getParameters());
            ((ClassMethodsArtifact) artifact).getClassArtifact().rebuildGfxObject();
        } catch (UMLDrawerException e) {
            Window.alert(e.getMessage());
        }
        return true;

    }
    @Override
    protected void next() {		
        ((ClassPartArtifact) artifact).edit();
    }
}
