package fr.uga.miage.l3.it.utils;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {

    public static <T> void verifyAnnotationClassFiledHaveProperty(
            Class<?> classz,
            String fieldClass,
            Class<? extends Annotation> annotationClass,
            String annotationFieldName,
            T value
    ) throws Exception {
        assertionAnnotationClassFiledHaveProperty(classz, fieldClass, annotationClass, annotationFieldName)
                .as("Dans la classe %s, l'attribut %s n'est pas configuré correctement, la propriété %s de l'annotation @%s est incorrecte.",
                        classz.getSimpleName(), fieldClass, annotationFieldName, annotationClass.getSimpleName())
                .isEqualTo(value);
    }

    public static <T> void verifyClassHaveAnnotationField(
            Class<?> classz,
            Class<? extends Annotation> annotationClass,
            String annotationField,
            T value
    ) throws Exception {
        assertionClassHaveAnnotationField(classz, annotationClass, annotationField)
                .as("L'attribut %s de l'annotation @%s de la classe %s est incorrect",
                        annotationField, annotationClass.getSimpleName(), classz.getSimpleName())
                .isEqualTo(value);
    }

    public static void verifyClassHaveAnnotation(Class<?> classz, Class<? extends Annotation> annotationClass) {
        assertThat(classz.getAnnotation(annotationClass))
                .as("La classe %s doit avoir l'annotation @%s", classz.getSimpleName(), annotationClass.getSimpleName())
                .isNotNull();
    }

    public static void verifyClassNotHaveAnnotation(Class<?> classz, Class<? extends Annotation> annotationClass) {
        assertThat(classz.getAnnotation(annotationClass))
                .as("La classe %s ne doit pas avoir l'annotation @%s", classz.getSimpleName(), annotationClass.getSimpleName())
                .isNull();
    }

    public static void verifyAllFieldIsDeclared(Class<?> classz, int nbFields) {
        assertThat(classz.getDeclaredFields())
                .as("Tous les champs doivent être déclarés.")
                .hasSize(nbFields);
    }

    public static void verifyClassFieldHaveAnnotation(
            Class<?> classz,
            String fieldClass,
            Class<? extends Annotation> annotationClass
    ) throws Exception {
        assertThat(classz.getDeclaredField(fieldClass).getAnnotation(annotationClass))
                .as("Dans la classe %s, l'attribut %s doit avoir l'annotation @%s",
                        classz.getSimpleName(), fieldClass, annotationClass.getSimpleName())
                .isNotNull();
    }

    public static void verifyClassFieldNotHaveAnnotation(
            Class<?> classz,
            String fieldClass,
            Class<? extends Annotation> annotationClass
    ) throws Exception {
        assertThat(classz.getDeclaredField(fieldClass).getAnnotation(annotationClass))
                .as("Dans la classe %s, l'attribut %s ne doit pas avoir l'annotation @%s",
                        classz.getSimpleName(), fieldClass, annotationClass.getSimpleName())
                .isNull();
    }

    public static void verifyMethodIsDeclared(Class<?> clazz, String methodNameContaining) {
        assertThat(Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase()))
                .toList())
                .as("La méthode %s devrait être définie dans la classe %s",
                        methodNameContaining, clazz.getSimpleName())
                .isNotEmpty();
    }

    public static void verifyMethodHaveAnnotation(
            Class<?> clazz,
            String methodNameContaining,
            Class<? extends Annotation> annotationClass
    ) {
        assertionMethodHaveAnnotation(clazz, methodNameContaining, annotationClass)
                .as("La méthode %s devrait avoir l'annotation @%s dans la classe %s",
                        methodNameContaining, annotationClass.getSimpleName(), clazz.getSimpleName())
                .isNotNull();
    }

    private static ObjectAssert<? extends Annotation> assertionMethodHaveAnnotation(
            Class<?> clazz,
            String methodNameContaining,
            Class<? extends Annotation> annotationClass
    ) {
        return assertThat(findMethod(clazz, methodNameContaining).getAnnotation(annotationClass));
    }

    public static <T> void verifyMethodAnnotationField(
            Class<?> clazz,
            String methodNameContaining,
            Class<? extends Annotation> annotationClass,
            String annotationField,
            T value
    ) throws Exception {
        Method methodInInterface = findMethod(clazz, methodNameContaining);
        Annotation annotation = methodInInterface.getAnnotation(annotationClass);

        Method annotationMethod = annotation.annotationType().getMethod(annotationField);

        assertThat(annotationMethod.invoke(annotation))
                .as("Dans la méthode %s, la propriété %s de l'annotation @%s devrait être égale à [%s]",
                        methodInInterface.getName(), annotationField, annotationClass.getSimpleName(), value)
                .isEqualTo(value);
    }

    public static void verifyMethodHaveParam(
            Class<?> clazz,
            String methodNameContaining,
            String parameterNameContaining
    ) {
        Method methodInInterface = findMethod(clazz, methodNameContaining);
        assertThat(Arrays.stream(methodInInterface.getParameters())
                .filter(parameter -> parameter.getName().toLowerCase().contains(parameterNameContaining.toLowerCase()))
                .toList())
                .as("La méthode %s devrait avoir un paramètre %s",
                        methodInInterface.getName(), parameterNameContaining)
                .isNotEmpty();
    }

    public static void verifyMethodParamHaveAnnotation(
            Class<?> clazz,
            String methodNameContaining,
            String parameterNameContaining,
            Class<? extends Annotation> annotationClass
    ) {
        Method methodInInterface = findMethod(clazz, methodNameContaining);
        assertThat(findParameter(methodInInterface, parameterNameContaining).getAnnotation(annotationClass))
                .as("Le paramètre %s de la méthode %s devrait avoir l'annotation @%s",
                        parameterNameContaining, methodInInterface.getName(), annotationClass.getSimpleName())
                .isNotNull();
    }

    public static <T> void verifyMethodParamAnnotationField(
            Class<?> clazz,
            String methodNameContaining,
            String parameterNameContaining,
            Class<? extends Annotation> annotationClass,
            String annotationField,
            T value
    ) throws Exception {
        Method methodInInterface = findMethod(clazz, methodNameContaining);
        Annotation annotation = findParameter(methodInInterface, parameterNameContaining).getAnnotation(annotationClass);

        assertThat(annotation.annotationType().getMethod(annotationField).invoke(annotation))
                .as("La valeur %s de l'annotation @%s du paramètre %s de la méthode %s est incorrecte",
                        annotationField, annotationClass.getSimpleName(), parameterNameContaining, methodInInterface.getName())
                .isEqualTo(value);
    }

    public static void verifyApiResponse(
            Class<?> clazz,
            String methodNameContaining,
            int numberOfApiResponse
    ) {
        Method methodInInterface = findMethod(clazz, methodNameContaining);
        ApiResponse[] responses = methodInInterface.getAnnotationsByType(ApiResponse.class);

        assertThat(responses)
                .as("La méthode %s doit avoir %s annotation(s) @ApiResponse",
                        methodInInterface.getName(), numberOfApiResponse)
                .hasSize(numberOfApiResponse);
    }

    public static <T> void verifyApiResponseField(
            Class<?> clazz,
            String methodNameContaining,
            int index,
            String fieldName,
            T value
    ) throws Exception {
        Method methodInInterface = findMethod(clazz, methodNameContaining);
        ApiResponse[] responses = methodInInterface.getAnnotationsByType(ApiResponse.class);

        assertThat(responses)
                .as("La méthode %s doit avoir au moins %s annotation(s) @ApiResponse",
                        methodInInterface.getName(), index + 1)
                .hasSizeGreaterThan(index);

        Method apiResponseMethod = responses[index].annotationType().getMethod(fieldName);

        assertThat(apiResponseMethod.invoke(responses[index]))
                .as("Dans la classe %s, l'annotation @ApiResponse[%s] de la méthode %s devrait avoir la propriété %s égale à [%s]",
                        clazz.getSimpleName(), index, methodInInterface.getName(), fieldName, value)
                .isEqualTo(value);
    }

    public static ObjectAssert<Object> assertionClassHaveAnnotationField(
            Class<?> classz,
            Class<? extends Annotation> annotationClass,
            String annotationField
    ) throws Exception {
        return assertThat(
                Arrays.stream(classz.getAnnotation(annotationClass)
                                .annotationType()
                                .getMethods())
                        .filter(method -> method.getName().equals(annotationField))
                        .findFirst()
                        .orElseThrow()
                        .invoke(classz.getAnnotation(annotationClass))
        );
    }

    public static ObjectAssert<Object> assertionAnnotationClassFiledHaveProperty(
            Class<?> classz,
            String fieldClass,
            Class<? extends Annotation> annotationClass,
            String annotationFieldName
    ) throws Exception {
        return assertThat(
                Arrays.stream(
                                Arrays.stream(
                                                Arrays.stream(classz.getDeclaredFields())
                                                        .filter(field -> field.getName().equals(fieldClass))
                                                        .findFirst()
                                                        .orElseThrow(() -> new Exception(
                                                                String.format("L'attribut %s n'est pas déclaré dans la classe", fieldClass)))
                                                        .getAnnotationsByType(annotationClass)
                                        )
                                        .findFirst()
                                        .orElseThrow(() -> new Exception(
                                                String.format("L'attribut %s n'a pas l'annotation @%s",
                                                        fieldClass, annotationClass.getSimpleName())))
                                        .annotationType()
                                        .getMethods()
                        )
                        .filter(method -> method.getName().equals(annotationFieldName))
                        .findFirst()
                        .orElseThrow()
                        .invoke(classz.getDeclaredField(fieldClass).getAnnotation(annotationClass))
        );
    }

    public static EntityType<?> getEntityTypeByEntityManager(EntityManager entityManager, String entity) {
        return entityManager.getMetamodel()
                .getEntities()
                .stream()
                .filter(entityType -> entityType.getName().toLowerCase().contains(entity.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("La classe contenant %s n'a pas été trouvée", entity)));
    }

    public static void verifyRepositoryExtendJpaRepositoryWith(
            Class<?> clazz,
            Class<?> entityClass,
            Class<?> idClass
    ) {
        Arrays.stream(clazz.getGenericInterfaces())
                .filter(type -> type instanceof ParameterizedType parameterizedType
                        && parameterizedType.getRawType() == JpaRepository.class)
                .map(type -> ((ParameterizedType) type).getActualTypeArguments())
                .forEach(repoTypes -> {
                    assertThat(repoTypes).hasSize(2);
                    assertThat(repoTypes[0].getTypeName())
                            .as("Le type de l'entité n'est pas correct dans le repository [%s]", clazz.getSimpleName())
                            .isEqualTo(entityClass.getName());
                    assertThat(repoTypes[1].getTypeName())
                            .as("Le type de l'id de l'entité n'est pas correct dans le repository [%s]", clazz.getSimpleName())
                            .isEqualTo(idClass.getName());
                });
    }

    private static Method findMethod(Class<?> clazz, String methodNameContaining) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().toLowerCase().contains(methodNameContaining.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("La méthode contenant [%s] est introuvable dans %s",
                                methodNameContaining, clazz.getSimpleName())));
    }

    private static java.lang.reflect.Parameter findParameter(Method method, String parameterNameContaining) {
        return Arrays.stream(method.getParameters())
                .filter(parameter -> parameter.getName().toLowerCase().contains(parameterNameContaining.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Le paramètre contenant [%s] est introuvable dans la méthode %s",
                                parameterNameContaining, method.getName())));
    }
}