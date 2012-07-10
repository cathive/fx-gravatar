/*
 * Copyright (C) 2012 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.fx.gravatar;

/**
 *
 * @author Benjamin P. Jung
 */
public enum DefaultImage {

    /** do not load any image if none is associated with the email hash, instead return an HTTP 404 (File Not Found) response */
    NONE("404"),

    /** a simple, cartoon-style silhouetted outline of a person (does not vary by email hash) */
    MYSTERY_MAN("mm"),

    /** a geometric pattern based on an email hash */
    IDENTICON("identicon"),

    /** a generated 'monster' with different colors, faces, etc */
    MONSTER_ID("monsterid"),

    /** generated faces with differing features and backgrounds */
    WAVATAR("wavatar"),

    /** awesome generated, 8-bit arcade-style pixelated faces */
    RETRO("retro");


    private final String stringValue;

    private DefaultImage(final String stringValue) {
        this.stringValue = stringValue;
    }

    public String stringValue() {
        return this.stringValue;
    }

}
