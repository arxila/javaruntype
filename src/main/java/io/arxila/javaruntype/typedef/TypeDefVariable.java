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

import java.io.Serializable;


/**
 * <p>
 * This interface represents type definition variables. These can be of one of two types (each
 * of them represented by one of this interface's implementing classes):
 * </p>
 * <ul>
 *   <li>Named (<kbd>NamedTypeDefVariable</kbd>): <kbd>E</kbd></li>
 *   <li>Bounded (<kbd>BoundedTypeDefVariable</kbd>): <kbd>E extends T</kbd></li>
 * </ul>
 * <p>
 * Some examples:
 * </p>
 * <ul>
 *   <li><kbd>app.pack.ClassOne</kbd> contains no <kbd>TypeDefVariable</kbd>s.</li>
 *   <li><kbd>app.pack.ClassThree&lt;E, T&gt;</kbd> contains two <kbd>TypeDefVariable</kbd>s:
 *       <ul>
 *         <li><kbd>E</kbd> (named)</li>
 *         <li><kbd>T</kbd> (named)</li>
 *       </ul> 
 *   </li>
 *   <li><kbd>app.pack.ClassEight&lt;E, T, X extends E &amp; java.util.Collection&lt;? extends T&gt;&gt;</kbd> contains three <kbd>TypeDefVariable</kbd>s:
 *       <ul>
 *         <li><kbd>E</kbd> (named)</li>
 *         <li><kbd>T</kbd> (named)</li>
 *         <li><kbd>X extends E &amp; java.util.Collection&lt;? extends T&gt;</kbd> (bounded)</li>
 *       </ul> 
 *   </li>
 * </ul>
 * 
 * <p>
 * As can be seen above, <i>every</i> type definition variable has, at least, a <b>name</b>: <kbd>E</kbd> ,<kbd>T</kbd> and <kbd>X</kbd>
 * in the above examples. Also, it can be noted that bounded variables can create relations among variables, like in 
 * <kbd>X extends E</kbd>.
 * </p>
 * 
 * @since 1.0
 *
 */
public sealed interface TypeDefVariable
        extends Serializable
        permits BoundedTypeDefVariable, NamedTypeDefVariable {
    
    /**
     * Returns the variable name.
     * 
     * @return the variable name.
     */
    public String getVariableName();
    
}
