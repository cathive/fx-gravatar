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

package com.cathive.fx.gravatar.viewer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import com.cathive.fx.gravatar.DefaultImage;
import com.cathive.fx.gravatar.FileTypeExtension;
import com.cathive.fx.gravatar.GravatarImageView;
import com.cathive.fx.gravatar.Rating;

/**
 * 
 * @author Benjamin P. Jung
 */
public class GravatarViewerController implements Initializable {

    @FXML private ComboBox<DefaultImage> defaultImageComboBox;
    @FXML private CheckBox forceDefaultImageCheckBox;
    @FXML private CheckBox forceHttpsCheckBox;
    @FXML private GravatarImageView gravatarImageView;
    @FXML private ComboBox<FileTypeExtension> imageTypeComboBox;
    @FXML private ComboBox<Rating> ratingComboBox;
    @FXML private ComboBox<Integer> gravatarSizeComboBox;
    @FXML private TextField emailAddressTextField;
    @FXML private Label statusLabel;

    @FXML
    private void loadGravatar() {
        gravatarImageView.update();
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        final List<DefaultImage> defaultImages = Arrays.asList(DefaultImage.values());
        defaultImageComboBox.setItems(FXCollections.observableArrayList(defaultImages));

        final List<Rating> ratings = Arrays.asList(Rating.values());
        ratingComboBox.setItems(FXCollections.observableArrayList(ratings));

        final List<FileTypeExtension> fileTypeExtensions = new ArrayList<>();
        for (FileTypeExtension ext: FileTypeExtension.values()) {
            if (ext.isImageFormat()) {
                fileTypeExtensions.add(ext);
            }
        }
        imageTypeComboBox.setItems(FXCollections.observableArrayList(fileTypeExtensions));

        gravatarSizeComboBox.setItems(FXCollections.observableArrayList(
                Integer.valueOf(16),
                Integer.valueOf(32),
                Integer.valueOf(64),
                Integer.valueOf(128),
                Integer.valueOf(256),
                Integer.valueOf(80),
                Integer.valueOf(160),
                Integer.valueOf(240)
        ));
        gravatarSizeComboBox.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
            @Override
            public ListCell<Integer> call(ListView<Integer> listView) {
                final ListCell<Integer> cell = new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        String text = null;
                        text = String.format("%d x %d", item, item);
                        if (item != null && item.intValue() == 80) {
                            text += " (default)";
                            setStyle("-fx-font-weight: bold;");
                        }
                        setText(text);
                    }
                };
                return cell;
            }
        });
        gravatarSizeComboBox.getSelectionModel().select(Integer.valueOf(80));

        // Bind all the values of the choice boxes and stuff to the GravatarImageView instance
        gravatarImageView.defaultImageProperty().bind(defaultImageComboBox.getSelectionModel().selectedItemProperty());
        gravatarImageView.fileTypeExtensionProperty().bind(imageTypeComboBox.getSelectionModel().selectedItemProperty());
        gravatarImageView.ratingProperty().bind(ratingComboBox.getSelectionModel().selectedItemProperty());
        gravatarImageView.forceDefaultProperty().bind(forceDefaultImageCheckBox.selectedProperty());
        gravatarImageView.secureProperty().bind(forceHttpsCheckBox.selectedProperty());
        gravatarImageView.sizeProperty().bind(gravatarSizeComboBox.getSelectionModel().selectedItemProperty());
        gravatarImageView.emailProperty().bind(emailAddressTextField.textProperty());

    }

    @FXML
    void exitApplication(final ActionEvent event) {
        Platform.exit();
    }

}
