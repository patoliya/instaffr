package com.pingbits;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class Connection {

    public static Entity addEntity(Schema schema) {

        Entity connection = schema.addEntity("Connection");
        connection.addIdProperty();
        connection.addStringProperty("name");
        connection.addStringProperty("email").unique();
        connection.addIntProperty("dpResource");
        return connection;
    }
}
