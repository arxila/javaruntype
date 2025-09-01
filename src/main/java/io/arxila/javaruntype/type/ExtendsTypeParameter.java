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

import java.io.Serial;

/**
 * <p>
 * Represents a type parameter which contains an "extends" clause.
 * </p>
 * <p>
 * For example, the type <kbd>List&lt;? extends Set&lt;String[]&gt;&gt;</kbd>,
 * would have an <kbd>ExtendsTypeParameter</kbd>
 * containing the <kbd>? extends Set&lt;String[]&gt;</kbd> parameter.
 * </p>
 * 
 * @since 1.0
 *
 */
public final class ExtendsTypeParameter<T> extends TypeParameter<T> {

    @Serial
    private static final long serialVersionUID = -3422656676188437147L;
    

    private final Type<T> type;
    
    private final String stringRepresentation;
    
    private final int hashCode;
    
    
    
    private static <T> String createStringRepresentation(final Type<T> type) {
        return  ("? extends " + type.getName()).intern();
    }
    
    
    ExtendsTypeParameter(final Type<T> type) {
        super();
        this.type = type;
        this.stringRepresentation = createStringRepresentation(type);
        this.hashCode = this.stringRepresentation.hashCode();
    }


    @Override
    public Type<T> getType() {
        return this.type;
    }


    @Override
    boolean isAssignableFrom(final TypeParameter<?> typeParameter) {

        if (this.equals(typeParameter)) {
            return true;
        }
        if ((typeParameter instanceof WildcardTypeParameter) || 
            (typeParameter instanceof SuperTypeParameter<?>)) {
            return false;
        } 
        return this.type.isAssignableFrom(typeParameter.getType());
        
    }

    

    @Override
    public int hashCode() {
        return this.hashCode;
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExtendsTypeParameter<?> other = (ExtendsTypeParameter<?>) obj;
        return this.stringRepresentation.equals(other.stringRepresentation);
    }


    @Override
    public String toString() {
        return this.stringRepresentation;
    }
    
    
    
}
