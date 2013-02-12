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
public enum FileTypeExtension {

    /**
     * PNG image file format.
     */
    PNG(".png", true),

    /**
     * JPG/JPEG image file format.
     */
    JPEG(".jpg", true),

    /**
     * JSON Profile Data.
     * See {@link "http://en.gravatar.com/site/implement/profiles/json/"} for details
     */
    JSON(".json", false),

    /**
     * XML Profile Data.
     * See {@link "http://en.gravatar.com/site/implement/profiles/xml/"} for details
     */
    XML(".xml", false),

    /**
     * PHP Profile Data.
     * See {@link "http://en.gravatar.com/site/implement/profiles/php/"} for details
     */
    PHP(".php", false),

    /**
     * VCF/vCard.
     * See {@link "http://en.gravatar.com/site/implement/profiles/vcf/"} for details
     */
    VCF(".vcf", false),

    /**
     * QR Codes.
     * Produces a QR Code image in PNG format that will link directly to the
     * Gravatar profile page of the requested user when scanned by a
     * QR-compatible device.
     * See {@link "http://en.gravatar.com/site/implement/profiles/qr/"} for details
     */
    QR_CODE(".qr", true);

    private final String stringValue;
    private final boolean imageFormat;

    private FileTypeExtension(final String stringValue, final boolean imageFormat) {
        this.stringValue = stringValue;
        this.imageFormat = imageFormat;
    }
    public String stringValue() {
        return this.stringValue;
    }

    /**
     * Checks whether this file type extension can be used to retrieve encoded
     * image data, that can be used to construct a JAVA FX UI <code>Image</code>
     * object or not.
     * @return  <code>true</code> if the format indicates encoded image data,
     *          <code>false</code> otherwise.
     */
    public boolean isImageFormat() {
        return this.imageFormat;
    }

}
