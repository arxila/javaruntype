package io.arxila.javaruntype.typedef;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTypeDefs {
    
    
    @Test
    public void testForClass() throws Exception {
        assertEquals("java.lang.String",TypeDefs.forClass(String.class).getName());
    }
    
}
