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

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SceneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

/**
 *
 * @author Benjamin P. Jung
 */
public class GravatarViewer extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        final ResourceBundle resources = ResourceBundle.getBundle(GravatarViewer.class.getName());
        final Parent root = FXMLLoader.load(getClass().getResource("GravatarViewer.fxml"), resources);

        StageBuilder.create()
                .title(resources.getString("APP_TITLE"))
                .resizable(false)
                .scene(SceneBuilder.create()
                        .root(root)
                        .build())
                .applyTo(primaryStage);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.show();

    }

}
