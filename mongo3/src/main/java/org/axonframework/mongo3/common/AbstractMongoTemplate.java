/*
 * Copyright (c) 2010-2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.mongo3.common;

import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;

/**
 * Abstract implementation for Mongo templates. Mongo templates give access to the collections in a Mongo Database used
 * by components of the Axon Framework. The AbstractMongoTemplate takes care of the authentication against the
 * Mongo database.
 *
 * @author Allard Buijze
 * @since 2.0
 */
public abstract class AbstractMongoTemplate {

    private static final String DEFAULT_AXONFRAMEWORK_DATABASE = "axonframework";

    private final MongoDatabase database;

    /**
     * Initializes the MongoTemplate to connect using the given <code>mongo</code> instance and a database with default
     * name "axonframework". The given <code>userName</code> and <code>password</code>, when not <code>null</code>, are
     * used to authenticate against the database.
     *
     * @param mongoClient    The MongoClient instance configured to connect to the Mongo Server
     */
    protected AbstractMongoTemplate(MongoClient mongoClient) { // NOSONAR
        this(mongoClient, DEFAULT_AXONFRAMEWORK_DATABASE);
    }

    /**
     * Initializes the MongoTemplate to connect using the given <code>mongo</code> instance and the database with given
     * <code>databaseName</code>. The given <code>userName</code> and <code>password</code>, when not <code>null</code>,
     * are used to authenticate against the database.
     *
     * @param mongoClient   The Mongo instance configured to connect to the Mongo Server
     * @param databaseName  The name of the database containing the data
     */
    protected AbstractMongoTemplate(MongoClient mongoClient, String databaseName) { // NOSONAR
        database = mongoClient.getDatabase(databaseName);
    }

    /**
     * Returns a reference to the Database with the configured database name. If a username and/or password have been
     * provided, these are used to authenticate against the database.
     * <p/>
     * Note that the configured <code>userName</code> and <code>password</code> are ignored if the database is already
     * in an authenticated state.
     *
     * @return a MongoDatabase instance, referring to the database with configured name.
     */
    protected MongoDatabase database() {
        return database;
    }
}