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

import io.arxila.javaruntype.util.Utils;



/**
 * <p>
 * Represents an inner type definition variable containing a parameterized type.
 * </p>
 * <p>
 * For example: <tt>X extends <b>List&lt;Integer&gt;</b></tt>.
 * </p>
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public final class InnerParameterizedTypeTypeDefVariable 
        implements InnerTypeDefVariable {

    private static final long serialVersionUID = 7978451943834222992L;
    
    private final Class<?> componentClass;
    private final InnerTypeDefVariable[] variables;
    private final int arrayDimensions;
    
    private final String stringRepresentation;
    
    private final int hashCode;
    
    
    
    private static String createStringRepresentation(final Class<?> componentClass,
            final InnerTypeDefVariable[] variables, final int arrayDimensions) {
        
        final StringBuilder str = new StringBuilder();
        str.append(componentClass.getName());
        str.append("<");
        str.append(Utils.join(variables, ","));
        str.append(">");
        for (int i = 0; i < arrayDimensions; i++) {
            str.append("[]");
        }
        return str.toString();
        
    }
    
    
    
    InnerParameterizedTypeTypeDefVariable(final Class<?> componentClass,
            final InnerTypeDefVariable[] variables, final int arrayDimensions) {
        this.componentClass = componentClass;
        this.variables = variables;
        this.arrayDimensions = arrayDimensions;
        this.stringRepresentation = 
            createStringRepresentation(
                    componentClass, variables, arrayDimensions);
        this.hashCode = this.stringRepresentation.hashCode();
    }


    /**
     * <p>
     * Returns the component class
     * </p>
     * 
     * @return the component class
     */
    public Class<?> getComponentClass() {
        return this.componentClass;
    }


    /**
     * Returns the type parameters (variables) applied to the component class.
     * 
     * @return the type parameters
     */
    public InnerTypeDefVariable[] getVariables() {
        return this.variables.clone();
    }
    
    
    /**
     * <p>
     * Returns the number of array dimensions applied to the component class.
     * </p>
     * 
     * @return the array dimensions.
     */
    public int getArrayDimensions() {
        return this.arrayDimensions;
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
        final InnerParameterizedTypeTypeDefVariable other = (InnerParameterizedTypeTypeDefVariable) obj;
        return this.stringRepresentation.equals(other.stringRepresentation);
    }


    @Override
    public String toString() {
        return this.stringRepresentation;
    }

    
}
