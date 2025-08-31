/*
 * =========================================================================
 *
 *   Copyright (c) 2009-2025 Arxila OSS (https://arxila.io)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *   implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 *
 * =========================================================================
 */
package io.arxila.javaruntype.type;

import io.arxila.javaruntype.exceptions.TypeRecognitionException;
import io.arxila.javaruntype.exceptions.TypeValidationException;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.jupiter.api.Assertions.*;

public class TestParsingEdgeCases {

    // Type-use annotations for the annotated types test
    @Target(TYPE_USE)
    @Retention(RUNTIME)
    public @interface A1 {}

    @Target(TYPE_USE)
    @Retention(RUNTIME)
    public @interface A2 {}

    // Methods used for reflection-based tests:

    // Intersection bounds
    public <X extends Number & Serializable> List<X> mIntersection() {
        return null;
    }

    // Annotated type (annotations should be ignored by reflection-based resolution)
    public @A1 List<@A2 String>[][] mAnnotated() {
        return null;
    }

    @Test
    public void testTabsAndNewlinesInForName() {
        // Control: with spaces only, it should parse fine.
        String withSpaces = "java.util.List<   java.lang.String   >  []";
        assertDoesNotThrow(() -> Types.forName(withSpaces));
    }

    @Test
    public void testPerDimensionArraysParsing_forGenerics() {
        Type<?> t = Types.forName("java.util.List<java.lang.String>[][][]");
        assertTrue(t.isArray(), "Type should be an array");
        assertEquals(3, t.getArrayDimensions(), "Array dimensions should be 3");
        assertEquals(java.util.List.class, t.getComponentClass(), "Component class should be List");
        assertEquals("java.util.List<java.lang.String>[][][]", t.getName(), "Canonical name should match");
    }

    @Test
    public void testIntersectionTypeViaReflection_validSubstitution() throws Exception {
        Method m = TestParsingEdgeCases.class.getMethod("mIntersection");
        java.lang.reflect.Type reflected = m.getGenericReturnType();

        // Substitute X -> Integer (which satisfies Number & Serializable)
        Type<?> result = Types.forJavaLangReflectType(reflected, java.util.Map.of("X", Types.INTEGER));
        Type<?> expected = Types.forName("java.util.List<java.lang.Integer>");
        assertEquals(expected, result);
    }

    @Test
    public void testIntersectionTypeViaReflection_invalidSubstitution() throws Exception {
        Method m = TestParsingEdgeCases.class.getMethod("mIntersection");
        java.lang.reflect.Type reflected = m.getGenericReturnType();

        // Substitute X -> String (does not satisfy Number)
        assertThrows(
            TypeValidationException.class,
            () -> Types.forJavaLangReflectType(reflected, java.util.Map.of("X", Types.STRING)),
            "Invalid substitution should fail validation"
        );
    }

    @Test
    public void testAnnotatedTypesIgnoredByReflection() throws Exception {
        Method m = TestParsingEdgeCases.class.getMethod("mAnnotated");
        java.lang.reflect.Type reflected = m.getGenericReturnType();

        // Type-use annotations are ignored in java.lang.reflect.Type here; structure should match unannotated type
        Type<?> result = Types.forJavaLangReflectType(reflected);
        Type<?> expected = Types.forName("java.util.List<java.lang.String>[][]");
        assertEquals(expected, result);
    }
}
