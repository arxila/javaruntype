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
package io.arxila.javaruntype.type.testtypes;


@SuppressWarnings("unchecked")
public class TType <Z,Y extends Number&Comparable<Float>,X extends Z> extends TParentType<Y>
        implements Comparable {


    private static final long serialVersionUID = 5957929133996499746L;


    public TType() {
        super();
    }


    public int compareTo(Object o) {
        return 0;
    }
    
}
