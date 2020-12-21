package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.annotations.ProtoClass;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
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

    public TypeMirror getProtoClassFromAnnotation(ProtoClass protoClassAnnotation) {
        try {
            //A hack. It always throws the exception, and this is the easiest way to get the TypeMirror of the class
            protoClassAnnotation.protoClass();
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
