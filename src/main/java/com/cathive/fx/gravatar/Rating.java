/**
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
public enum Rating {

    /**
     * <b>g:</b> suitable for display on all websites with any audience type.
     */
    GENERAL_AUDIENCES("g"),

    /**
     * <b>pg:</b> may contain rude gestures, provocatively dressed individuals,
     * the lesser swear words, or mild violence.
     */
    PARENTAL_GUIDANCE("pg"),

    /**
     * <b>r:</b> may contain such things as harsh profanity, intense violence,
     * nudity, or hard drug use.
     */
    RESTRICTED("r"),

    /**
     * <b>x:</b> may contain hardcore sexual imagery or extremely disturbing
     * violence.
     */
    X_RATED("x");


    private final String stringValue;

    private Rating(final String stringValue) {
        this.stringValue = stringValue;
    }
    public String stringValue() {
        return this.stringValue;
    }

}
