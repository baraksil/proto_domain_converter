package org.silbertb.proto.domainconverter;

import org.silbertb.proto.domainconverter.annotations.OneofField;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LangModelUtil {

    private ProcessingEnvironment processingEnv;

    public LangModelUtil(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    public TypeMirror getClassFromAnnotation(Runnable classGetterFromAnnotation) {
        try {
            //A hack. It always throws the exception, and this is the easiest way to get the TypeMirror of the class
            classGetterFromAnnotation.run();
        }
        catch( MirroredTypeException mte ) {
            return mte.getTypeMirror();
        }
        return null;
    }

    public TypeMirror getDomainClassFromAnnotation(OneofField oneofFieldAnnotation) {
        try {
            //A hack. It always throws the exception, and this is the easiest way to get the TypeMirror of the class
            oneofFieldAnnotation.domainClass();
        }
        catch( MirroredTypeException mte ) {
            return mte.getTypeMirror();
        }
        return null;
    }

    public boolean isList(TypeMirror typeMirror) {
        return isAssignedFrom(typeMirror, List.class);
    }

    public boolean isMap(TypeMirror typeMirror) {
        return isAssignedFrom(typeMirror, Map.class);
    }

    public boolean isAssignedFrom(TypeMirror typeMirror, Class<?> supposedSuperClass) {
        String superClassName = supposedSuperClass.getName();
        TypeElement superTypeElement = processingEnv.getElementUtils().getTypeElement(superClassName);
        Types typeUtil = processingEnv.getTypeUtils();

        return typeUtil.isAssignable(typeMirror, typeUtil.erasure(superTypeElement.asType()));
    }

    public boolean isConcreteType(Element element) {
        Element e = processingEnv.getTypeUtils().asElement(element.asType());
        return !isAbstractType(e) && !isInterfaceType(e);
    }

    public boolean isAbstractType(Element element) {
        return element.getModifiers().contains(Modifier.ABSTRACT);
    }

    public boolean isInterfaceType(Element element) {
        return element.getKind() == ElementKind.INTERFACE;
    }

    public boolean isSameType(TypeMirror typeMirror, Class<?> clazz) {
        TypeElement otherTypeElement = processingEnv.getElementUtils().getTypeElement(clazz.getName());
        Types typeUtil = processingEnv.getTypeUtils();
        return typeUtil.isSameType(typeMirror, otherTypeElement.asType());
    }

    public boolean isByteArray(TypeMirror typeMirror) {
        if (typeMirror.getKind() == TypeKind.ARRAY) {
            return ((ArrayType)typeMirror).getComponentType().getKind().equals(TypeKind.BYTE);
        }
        return false;
    }

    public <A extends Annotation> A getAnnotation(TypeMirror typeMirror, Class<A> annotationClass) {
        Types typeUtils = processingEnv.getTypeUtils();
        TypeElement typeElement = (TypeElement) typeUtils.asElement(typeMirror);
        return typeElement.getAnnotation(annotationClass);
    }

    public List<? extends TypeMirror> getGenericsTypes(TypeMirror typeMirror) {
        if(!typeMirror.getKind().equals(TypeKind.DECLARED)) {
            return Collections.emptyList();
        }

        DeclaredType fieldDeclaredType = (DeclaredType)typeMirror;
        return fieldDeclaredType.getTypeArguments();
    }

    private void info(String msg) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
    }
}
