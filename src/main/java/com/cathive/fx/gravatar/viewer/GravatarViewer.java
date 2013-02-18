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
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SceneBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

/**
 *
 * @author Benjamin P. Jung
 */
public class GravatarViewer extends Application {

    private ResourceBundle resources;

    @Override
    public void init() throws Exception {
        super.init();
        resources = ResourceBundle.getBundle(GravatarViewer.class.getName());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        final Parent root = FXMLLoader.load(getClass().getResource("GravatarViewer.fxml"), resources);
        final MouseEventHandler meh = new MouseEventHandler(root);
        root.setOnMousePressed(meh);
        root.setOnMouseDragged(meh);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        StageBuilder.create()
                .title(resources.getString("APP_NAME"))
                .resizable(false)
                .scene(SceneBuilder.create()
                        .fill(Color.TRANSPARENT)
                        .root(root)
                        .stylesheets("/com/cathive/fx/gravatar/viewer/GravatarViewer.css")
                        .build())
                .applyTo(primaryStage);

        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    static class MouseEventHandler implements EventHandler<MouseEvent> {
        final Node node;
        double initialX;
        double initialY;
        MouseEventHandler(final Node node) {
            super();
            this.node = node;
        }
        @Override
        public void handle(MouseEvent me) {
            final EventType<?> eventType = me.getEventType();
            if (eventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                node.getScene().getWindow().setX(me.getScreenX() - initialX);
                node.getScene().getWindow().setY(me.getScreenY() - initialY);   
            } else  if (eventType.equals(MouseEvent.MOUSE_PRESSED)) {
                initialX = me.getSceneX();
                initialY = me.getSceneY();
            }
        }
    }

}
