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

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.util.Builder;

/**
 *
 * @author Benjamin P. Jung
 */
public final class GravatarUrlBuilder implements Builder<URL> {

    public static final String BASE_REQUEST_HTTP = "http://www.gravatar.com/avatar/";
    public static final String BASE_REQUEST_HTTPS = "https://www.gravatar.com/avatar/";

    // Some default values and constraints.
    public static final int SIZE_MINIMUM = 1;
    public static final int SIZE_MAXIMUM = 512;
    public static final String DEFAULT_IMAGE_DEFAULT = null;
    public static final boolean FORCE_DEFAULT_DEFAULT = false;
    public static final String RATING_DEFAULT = null;
    public static final boolean SECURE_DEFAULT = false;
    public static final String FILE_TYPE_EXTENSION_DEFAULT = null;

    private String emailHash = null;
    private String fileTypeExtension = FILE_TYPE_EXTENSION_DEFAULT;
    /**
     * Default size is {@link #SIZE_DEFAULT 80px}.
     * <p>Minimum value: {@link #SIZE_MINIMUM 1px}, maximum value: {@link MAX_SIZE 512px}.</p>
     */
    private Integer size = null;
    private String defaultImage = DEFAULT_IMAGE_DEFAULT;
    private boolean forceDefault = FORCE_DEFAULT_DEFAULT;
    private String rating = RATING_DEFAULT;
    /**
     * This flag indicates, whether HTTPS should be used instead of HTTP to
     * retrieve Gravatar images. Default value:
     * <code>false</code>
     */
    private boolean secure = SECURE_DEFAULT;

    private GravatarUrlBuilder() {
        super();
    }

    public static GravatarUrlBuilder create() {
        return new GravatarUrlBuilder();
    }

    @Override
    public URL build() {

        final StringBuilder sb = new StringBuilder();
        sb.append(secure ? BASE_REQUEST_HTTPS : BASE_REQUEST_HTTP);
        sb.append(emailHash);
        if (fileTypeExtension != null) {
            sb.append(fileTypeExtension);
        }

        int getParamCount = 0;
        if (size != null) {
            sb.append(String.format("?s=%d", this.size));
            getParamCount += 1;
        }
        if (defaultImage != null) {
            sb.append(getParamCount > 0 ? '&' : '?');
            sb.append(String.format("d=%s", defaultImage));
            getParamCount += 1;
        }
        if (forceDefault) {
            sb.append(getParamCount > 0 ? '&' : '?');
            sb.append("f=true");
            getParamCount += 1;
        }
        if (rating != null) {
            sb.append(getParamCount > 0 ? '&' : '?');
            sb.append(String.format("r=%s", rating));
            getParamCount += 1;
        }

        try {
            final URL gravatarUrl = new URL(sb.toString());
            return gravatarUrl;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    public GravatarUrlBuilder email(String email) {
        this.emailHash = md5Hex(email);
        return this;
    }

    public GravatarUrlBuilder fileTypeExtension(FileTypeExtension fileTypeExtension) {
        this.fileTypeExtension = fileTypeExtension == null ? null : fileTypeExtension.stringValue();
        return this;
    }

    public GravatarUrlBuilder fileTypeExtension(String fileTypeExtension) {
        this.fileTypeExtension = fileTypeExtension;
        return this;
    }
    
    public GravatarUrlBuilder size(int size) {
        if (size < SIZE_MINIMUM || size > SIZE_MAXIMUM) {
            throw new IllegalArgumentException(String.format("Size must be a value between %d and %d", SIZE_MINIMUM, SIZE_MAXIMUM));
        }
        this.size = Integer.valueOf(size);
        return this;
    }

    public GravatarUrlBuilder defaultImage(DefaultImage defaultImage) {
        this.defaultImage = defaultImage == null ? null : defaultImage.stringValue();
        return this;
    }

    public GravatarUrlBuilder defaultImage(String defaultImage) {
        try {
            this.defaultImage = URLEncoder.encode(defaultImage, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public GravatarUrlBuilder forceDefault(boolean forceDefault) {
        this.forceDefault = forceDefault;
        return this;
    }

    public GravatarUrlBuilder rating(Rating rating) {
        this.rating = rating == null ? null : rating.stringValue();
        return this;
    }

    public GravatarUrlBuilder rating(String rating) {
        this.rating = rating;
        return this;
    }

    public GravatarUrlBuilder secure(boolean secure) {
        this.secure = secure;
        return this;
    }

    /**
     * Helper method to create MD5 encoded <code>String</code> objects.
     * @param message   message to be encoded
     * @return  An MD5 encoded version of the input string
     */
    static String md5Hex(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
