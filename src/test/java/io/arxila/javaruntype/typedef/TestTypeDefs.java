package io.arxila.javaruntype.typedef;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTypeDefs {

    public static class NoGenerics {}

    public static class One<E> {}

    public static class Two<E,T> {}

    // T extends E
    public static class Bounded1<E,T extends E> {}

    // T extends Serializable
    public static class Bounded2<E,T extends Serializable> {}

    // E extends Serializable, and T extends E
    public static class Bounded3<E extends Serializable, T extends E> {}

    // X extends Collection<T> (avoid multiple bounds after a type variable)
    public static class Bounded4<E,T,X extends Collection<T>> {}

    // X extends Collection<? extends T> (avoid multiple bounds after a type variable)
    public static class Bounded5<E,T,X extends Collection<? extends T>> {}

    public static class GenericBound1<E, T extends List<E>> {}

    public static class GenericBound2<T extends List<? extends Number>> {}

    public static class ArrayInBound1<T extends List<String[]>> {}

    public static class ArrayInBound2<T extends Collection<int[][]>> {}



    @Test
    public void testForClass() throws Exception {
        assertEquals("java.lang.String",TypeDefs.forClass(String.class).getName());
    }

    @Test
    public void testNoGenerics() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.NoGenerics.class);
        assertEquals(io.arxila.javaruntype.typedef.TestTypeDefs.NoGenerics.class, td.getComponentClass());
        assertEquals(0, td.getVariables().length);
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$NoGenerics", td.getName());
    }

    @Test
    public void testSingleTypeVariable() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.One.class);
        assertEquals(io.arxila.javaruntype.typedef.TestTypeDefs.One.class, td.getComponentClass());
        assertEquals(1, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$One<E>", td.getName());
    }

    @Test
    public void testTwoTypeVariables() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.Two.class);
        assertEquals(io.arxila.javaruntype.typedef.TestTypeDefs.Two.class, td.getComponentClass());
        assertEquals(2, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("T", td.getVariables()[1].getVariableName());
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$Two<E,T>", td.getName());
    }

    @Test
    public void testBoundedExtendsVariable() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.Bounded1.class);
        // T extends E
        assertEquals(2, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("T", td.getVariables()[1].getVariableName());
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$Bounded1<E,T extends E>", td.getName());
    }

    @Test
    public void testBoundedExtendsSerializable() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.Bounded2.class);
        // T extends java.io.Serializable
        assertEquals(2, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("T", td.getVariables()[1].getVariableName());
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$Bounded2<E,T extends java.io.Serializable>", td.getName());
    }

    @Test
    public void testIntersectionBound() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.Bounded3.class);
        // E extends java.io.Serializable, T extends E
        assertEquals(2, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("T", td.getVariables()[1].getVariableName());
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$Bounded3<E extends java.io.Serializable,T extends E>", td.getName());
    }

    @Test
    public void testInnerParameterizedInBound() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.Bounded4.class);
        // X extends java.util.Collection<T>
        assertEquals(3, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("T", td.getVariables()[1].getVariableName());
        assertEquals("X", td.getVariables()[2].getVariableName());
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$Bounded4<E,T,X extends java.util.Collection<T>>", td.getName());
    }

    @Test
    public void testWildcardInBound() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.Bounded5.class);
        // X extends java.util.Collection<? extends T>
        assertEquals(3, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("T", td.getVariables()[1].getVariableName());
        assertEquals("X", td.getVariables()[2].getVariableName());
        assertEquals("io.arxila.javaruntype.typedef.TestTypeDefs$Bounded5<E,T,X extends java.util.Collection<? extends T>>", td.getName());
    }

    // Additional tests requested (generic bounds and arrays inside bounds)

    @Test
    public void testGenericTypeInExtendsClause_simple() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.GenericBound1.class);
        // T extends java.util.List<E>
        assertEquals(2, td.getVariables().length);
        assertEquals("E", td.getVariables()[0].getVariableName());
        assertEquals("T", td.getVariables()[1].getVariableName());
        assertEquals(
                "io.arxila.javaruntype.typedef.TestTypeDefs$GenericBound1<E,T extends java.util.List<E>>",
                td.getName()
        );
    }

    @Test
    public void testGenericTypeInExtendsClause_withWildcardExtends() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.GenericBound2.class);
        // T extends java.util.List<? extends Number>
        assertEquals(1, td.getVariables().length);
        assertEquals("T", td.getVariables()[0].getVariableName());
        assertEquals(
                "io.arxila.javaruntype.typedef.TestTypeDefs$GenericBound2<T extends java.util.List<? extends java.lang.Number>>",
                td.getName()
        );
    }

    @Test
    public void testArrayMentionedInsideGenericExtendsClause_stringArray() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.ArrayInBound1.class);
        // T extends java.util.List<java.lang.String[]>
        assertEquals(1, td.getVariables().length);
        assertEquals("T", td.getVariables()[0].getVariableName());
        assertEquals(
                "io.arxila.javaruntype.typedef.TestTypeDefs$ArrayInBound1<T extends java.util.List<java.lang.String[]>>",
                td.getName()
        );
    }

    @Test
    public void testArrayMentionedInsideGenericExtendsClause_primitiveMultiDimArray() {
        final TypeDef td = TypeDefs.forClass(io.arxila.javaruntype.typedef.TestTypeDefs.ArrayInBound2.class);
        // T extends java.util.Collection<int[][]>
        assertEquals(1, td.getVariables().length);
        assertEquals("T", td.getVariables()[0].getVariableName());
        assertEquals(
                "io.arxila.javaruntype.typedef.TestTypeDefs$ArrayInBound2<T extends java.util.Collection<int[][]>>",
                td.getName()
        );
    }

    @Test
    public void testArrayClassRejected() {
        assertThrows(IllegalArgumentException.class, () -> TypeDefs.forClass(String[].class));
    }

    @Test
    public void testNullRejected() {
        assertThrows(IllegalArgumentException.class, () -> TypeDefs.forClass(null));
    }
}