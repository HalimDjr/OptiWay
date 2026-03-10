package fr.uga.miage.l3.it.models;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;

public class EntityTestUtils {

    public static void assertFieldType(Class<?> clazz, String fieldName, Class<?> expectedType) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        assertEquals(expectedType, field.getType());
    }

    public static void assertAnnotationPresent(Class<?> clazz, String fieldName, Class<? extends Annotation> annotation) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        assertTrue(field.isAnnotationPresent(annotation));
    }
}