package com.pingbits;


import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Todo {
    public static Entity addEntity(Schema schema) {

        Entity todo = schema.addEntity("Todo");
        todo.addIdProperty();
        todo.addStringProperty("title");
        todo.addStringProperty("addedBy");
        todo.addBooleanProperty("done");
        return todo;
    }
}
