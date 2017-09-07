package com.github.yeriomin.tokendispenser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.nio.file.*;
import java.io.File;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.ipAddress;
import static spark.Spark.notFound;
import static spark.Spark.port;

public class Server {

    static public final Logger LOG = LoggerFactory.getLogger(Server.class.getName());

    //static private final String CONFIG_FILE = "/config.properties";

    static final String PROPERTY_SPARK_HOST = "spark-host";
    static final String PROPERTY_SPARK_PORT = "spark-port";
    static final String PROPERTY_STORAGE = "plaintext";
    static final String PROPERTY_STORAGE_PLAINTEXT_PATH = "storage-plaintext-path";
    static final String PROPERTY_MONGODB_HOST = "mongodb-host";
    static final String PROPERTY_MONGODB_PORT = "mongodb-port";
    static final String PROPERTY_MONGODB_USERNAME = "mongodb-username";
    static final String PROPERTY_MONGODB_PASSWORD = "mongodb-password";
    static final String PROPERTY_MONGODB_DB = "mongodb-databaseNameStorage";
    static final String PROPERTY_MONGODB_COLLECTION = "mongodb-collectionName";
    static final String PROPERTY_EMAIL_RETRIEVAL = "enable-email-retrieval";

    static public final String STORAGE_MONGODB = "mongodb";
    static public final String STORAGE_PLAINTEXT = "plaintext";

    static PasswordsDbInterface passwords;

    public static void main(String[] args) {
        String host = "0.0.0.0";
        int port = Integer.valueOf(System.getenv("DISPENSER_PORT"));
        ipAddress(host);
        port(port);
        notFound("Not found");
        before((req, res) -> LOG.info(req.requestMethod() + " " + req.url()));
        after((req, res) -> res.type("text/plain"));
        Server.passwords = new PasswordsDbPlaintext(); //PasswordsDbFactory.get(config);
        get("/token/email/:email", (req, res) -> new TokenResource().handle(req, res));
        get("/token-ac2dm/email/:email", (req, res) -> new TokenAc2dmResource().handle(req, res));
        get("/email", (req, res) -> new EmailResource().handle(req, res));
    }

}
