package io.arxila.javaruntype.type.testtypes;

import io.arxila.javaruntype.type.Type;
import io.arxila.javaruntype.type.Types;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTypeFromJavaLangReflectWithSelfBoundedTypeVariables {
    public interface X<T extends Comparable<T>> {
    }

    @Test public void test() throws Exception {
        Map<String, Type<?>> vars = new HashMap<String, Type<?>>();
        vars.put("T", Types.forJavaLangReflectType(Integer.class));

        Type<?> t = Types.forJavaLangReflectType(X.class.getTypeParameters()[0], vars);

        assertEquals(Integer.class, t.getRawClass());
    }
}
