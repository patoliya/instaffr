package com.pingbits;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;

class Main {

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1, "com.pingbits.greendao");

        Connection.addEntity(schema);

        Todo.addEntity(schema);

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
