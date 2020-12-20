package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.annotations.ProtoClass;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

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

    public boolean isList(TypeMirror fieldType) {
        TypeElement listTypeElement = processingEnv.getElementUtils().getTypeElement("java.util.List");
        Types typeUtil = processingEnv.getTypeUtils();
        DeclaredType listType = typeUtil.getDeclaredType(listTypeElement, typeUtil.getWildcardType(null, null));

        return processingEnv.getTypeUtils().isAssignable(fieldType, listType);
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
}
