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

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Benjamin P. Jung
 */
public final class GravatarImageView extends ImageView {

    // Executor to load images in the background
    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    // The Gravatar URL builder instance
    final GravatarUrlBuilder urlBuilder = GravatarUrlBuilder.create();

    // Properties that can be set to define the characteristics of the gravatar image being loaded
    private final IntegerProperty size = new SimpleIntegerProperty(80);
    private final ObjectProperty<DefaultImage> defaultImage = new SimpleObjectProperty<>();
    private final ObjectProperty<FileTypeExtension> fileTypeExtension = new SimpleObjectProperty<>(FileTypeExtension.PNG);
    private final BooleanProperty forceDefault = new SimpleBooleanProperty(false);
    private final ObjectProperty<Rating> rating = new SimpleObjectProperty<>(Rating.GENERAL_AUDIENCES);
    private final BooleanProperty secure = new SimpleBooleanProperty(false);

    /** Call {@link #update()} after this property has been set to update the image view */ 
    private final StringProperty email = new SimpleStringProperty(null);

    public GravatarImageView() {
        super();
    }


    /**
     * Update the Gravatar image in the background using one of JavaFX's shiny {@link Task task objects}.
     * Make sure to call this method once all the properties of the desired Gravatar image
     * are set up to actually update the view.
     */
    public void update() {
        final URL gravatarUrl = getGravatarUrl();
        final Task<Image> task = new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                return new Image(gravatarUrl.openStream());
            }
            @Override
            protected void succeeded() {
                super.succeeded();
                GravatarImageView.this.setImage(this.getValue());
                fitWidthProperty().set(getSize());
                fitHeightProperty().set(getSize());
            }
        };
        executorService.execute(task);
    }

    public URL getGravatarUrl() {
        urlBuilder.size(getSize());
        urlBuilder.fileTypeExtension(getFileTypeExtension());
        urlBuilder.defaultImage(getDefaultImage());
        urlBuilder.forceDefault(isForceDefault());
        urlBuilder.rating(getRating());
        urlBuilder.secure(isSecure());
        urlBuilder.email(getEmail());
        return urlBuilder.build();
    }


    // ---- GETTERS, SETTERS AND PROPERTIES ----------------------------------------------------------------------------

    public int getSize() {
        return this.size.get();
    }

    public void setSize(Integer size) {
        this.size.set(size);
    }

    public IntegerProperty sizeProperty() {
        return this.size;
    }

    public DefaultImage getDefaultImage() {
    	return this.defaultImage.get();
    }

    public void setDefaultImage(DefaultImage defaultImage) {
        this.defaultImage.set(defaultImage);
    }

    public ObjectProperty<DefaultImage> defaultImageProperty() {
        return this.defaultImage;
    }

    public FileTypeExtension getFileTypeExtension() {
        return this.fileTypeExtension.get();
    }

    public void setFileTypeExtension(FileTypeExtension fileTypeExtension) {
        this.fileTypeExtension.set(fileTypeExtension);
    }

    public ObjectProperty<FileTypeExtension> fileTypeExtensionProperty() {
        return this.fileTypeExtension;
    }

    public Rating getRating() {
        return this.rating.get();
    }

    public void setRating(Rating rating) {
        this.rating.set(rating);
    }

    public ObjectProperty<Rating> ratingProperty() {
        return this.rating;
    }

    public boolean isForceDefault() {
        return this.forceDefault.get();
    }

    public void setForceDefault(boolean forceDefault) {
        this.forceDefault.set(forceDefault);
    }

    public BooleanProperty forceDefaultProperty() {
        return this.forceDefault;
    }

    public boolean isSecure() {
    	return this.secure.get();
    }

    public void setSecure(boolean secure) {
    	this.secure.set(secure);
    }

    public BooleanProperty secureProperty() {
    	return this.secure;
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return this.email;
    }

}
