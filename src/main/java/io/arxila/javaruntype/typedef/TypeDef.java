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
package io.arxila.javaruntype.typedef;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;

import io.arxila.javaruntype.util.Utils;

/**
 * <p>
 * This class represents a Type Definition. A <i>Type Definition</i> specifies which type parameters
 * (if any) can a class take, and the relation that exists among them. It corresponds to the way
 * a class can be defined in its .java file.
 * </p>
 * <p>
 * Examples of type definitions are:
 * </p>
 * <ul>
 *   <li><kbd>app.pack.ClassOne</kbd></li>
 *   <li><kbd>app.pack.ClassTwo&lt;E&gt;</kbd></li>
 *   <li><kbd>app.pack.ClassThree&lt;E, T&gt;</kbd></li>
 *   <li><kbd>app.pack.ClassFour&lt;E, T extends E&gt;</kbd></li>
 *   <li><kbd>app.pack.ClassFive&lt;E, T extends java.io.Serializable&gt;</kbd></li>
 *   <li><kbd>app.pack.ClassSix&lt;E, T extends E &amp; java.io.Serializable&gt;</kbd></li>
 *   <li><kbd>app.pack.ClassSeven&lt;E, T, X extends E &amp; java.util.Collection&lt;T&gt;&gt;</kbd></li>
 *   <li><kbd>app.pack.ClassEight&lt;E, T, X extends E &amp; java.util.Collection&lt;? extends T&gt;&gt;</kbd></li>
 *   <li>etc...</li>
 * </ul>
 * <p>
 * These type definitions serve as a template for creating <b>types</b> ({@link io.arxila.javaruntype.type.Type}
 * class), which resolve all type variables present in the type definition (for example, for a <kbd>TypeDef</kbd>
 * object <kbd>java.util.Collection&lt;E&gt;</kbd> we can get a <kbd>Type</kbd> object
 * <kbd>java.util.Collection&lt;java.lang.String&gt;</kbd>
 * </p>
 * <p>
 * Objects of this class are never created directly. To obtain a <kbd>TypeDef</kbd> object,
 * the diverse methods in the {@link TypeDefs} class should be used.
 * </p>
 * <p>
 * Objects of this class are <b>immutable</b>, and thus <b>thread-safe</b>. Also, in order
 * to avoid excessive memory usage, an internal synchronized cache exists which 
 * prevents the same <kbd>TypeDef</kbd> from being instantiated more than once (so, if two 
 * TypeDef objects are equal, this will mean that they are the same object).
 * </p>
 * 
 * @since 1.0
 *
 */
public final class TypeDef implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 3446428616087989437L;

    private static final String NAME_TYPE_VARIABLES_START = "<";
    private static final String NAME_TYPE_VARIABLES_END = ">";
    private static final String NAME_TYPE_VARIABLES_SEPARATOR = ",";
    
    private final Class<?> componentClass;
    private final TypeDefVariable[] variables;
    private final String name;
    
    // Class is immutable, so hashCode can be precomputed
    private final int hashCode;


    private static String createName(final Class<?> componentClass, 
            final TypeDefVariable[] variables) {
        
        final StringBuilder str = new StringBuilder();
        str.append(componentClass.getName());
        if (variables.length > 0) {
            str.append(NAME_TYPE_VARIABLES_START);
            str.append(Utils.join(variables, NAME_TYPE_VARIABLES_SEPARATOR));
            str.append(NAME_TYPE_VARIABLES_END);
        }
        return str.toString();
        
    }
    
    
    TypeDef(final Class<?> componentClass, final TypeDefVariable[] variables) {
        
        Utils.validateNotNull(componentClass, 
                "Component class cannot be null");
        Utils.validateNotNull(variables, 
                "Type parameters cannot be null");

        this.componentClass = componentClass;
        this.variables = variables.clone(); 
        this.name = createName(componentClass, variables);
        this.hashCode = this.name.hashCode();
        
    }
    
    
    /**
     * 
     * Returns the component class of the type definition. For example, if the type definition
     * is <kbd>java.util.Collection&lt;E&gt;</kbd>, this method will return
     * <kbd>java.lang.Class&lt;java.util.Collection&gt;</kbd>
     * 
     * @return the component class.
     */
    public Class<?> getComponentClass() {
        return this.componentClass;
    }
    
    
    /**
     * Returns the variables of the type definition.
     * 
     * @return the type definition variables.
     */
    public TypeDefVariable[] getVariables() {
        return this.variables.clone();
    }
    
    
    /**
     * Returns the name of the type definition. For example: "java.util.Collection&lt;E&gt;"
     * 
     * @return the name of the type definition.
     */
    public String getName() {
        return this.name;
    }

    
    @Override
    public String toString() {
        return getName();
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
        final TypeDef other = (TypeDef) obj;
        // Comparing name will be enough and quicker than other comparisons
        return (this.name.equals(other.name));
    }
    
    
    @SuppressWarnings("unused")
    private Object readResolve() throws ObjectStreamException {
        final TypeDefRegistry typeDefRegistry = TypeDefRegistry.getInstance();
        return typeDefRegistry.forClass(this.componentClass);
    }

    
}
