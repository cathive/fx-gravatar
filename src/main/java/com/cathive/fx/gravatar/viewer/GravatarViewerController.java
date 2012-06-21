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

package com.cathive.fx.gravatar.viewer;

import com.cathive.fx.gravatar.DefaultImage;
import com.cathive.fx.gravatar.FileTypeExtension;
import com.cathive.fx.gravatar.GravatarUrlBuilder;
import com.cathive.fx.gravatar.Rating;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Benjamin P. Jung
 */
public class GravatarViewerController implements Initializable {

    @FXML
    private ChoiceBox<DefaultImage> defaultImageChoiceBox;

    @FXML
    private CheckBox forceDefaultImageCheckBox;

    @FXML
    private CheckBox forceHttpsCheckBox; // Value injected by FXMLLoader

    @FXML
    private ImageView gravatarImageView;

    @FXML
    private ChoiceBox<FileTypeExtension> imageTypeChoiceBox;

    @FXML
    private ChoiceBox<Rating> ratingChoiceBox;

    @FXML
    private TextField emailAddressTextField;

    @FXML
    private Label statusLabel;

    private final GravatarUrlBuilder urlBuilder = GravatarUrlBuilder.create();

    
    @FXML
    public void loadGravatar() {
        final URL gravatarUrl = urlBuilder.build();
        statusLabel.setText(gravatarUrl.toExternalForm());
        gravatarImageView.setImage(new Image(gravatarUrl.toExternalForm()));
        
    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        final List<DefaultImage> defaultImages = Arrays.asList(DefaultImage.values());
        defaultImageChoiceBox.setItems(FXCollections.observableArrayList(defaultImages));
        defaultImageChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DefaultImage>() {
            @Override
            public void changed(ObservableValue<? extends DefaultImage> observableValue, DefaultImage oldValue, DefaultImage newValue) {
                urlBuilder.defaultImage(newValue);
            }
        });

        final List<FileTypeExtension> fileTypeExtensions = new ArrayList<>();
        for (FileTypeExtension ext: FileTypeExtension.values()) {
            if (ext.isImageFormat()) {
                fileTypeExtensions.add(ext);
            }
        }
        imageTypeChoiceBox.setItems(FXCollections.observableArrayList(fileTypeExtensions));
        imageTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FileTypeExtension>() {
            @Override
            public void changed(ObservableValue<? extends FileTypeExtension> observableValue, FileTypeExtension oldValue, FileTypeExtension newValue) {
                urlBuilder.fileTypeExtension(newValue);
            }
        });

        final List<Rating> ratings = Arrays.asList(Rating.values());
        ratingChoiceBox.setItems(FXCollections.observableArrayList(ratings));
        ratingChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rating>() {
            @Override
            public void changed(ObservableValue<? extends Rating> observableValue, Rating oldValue, Rating newValue) {
                urlBuilder.rating(newValue);
            }
        });

        forceDefaultImageCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                urlBuilder.forceDefault(newValue);
            }
        });

        forceHttpsCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                urlBuilder.secure(newValue);
            }
        });

        emailAddressTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                urlBuilder.email(newValue);
            }
        });

    }

}

