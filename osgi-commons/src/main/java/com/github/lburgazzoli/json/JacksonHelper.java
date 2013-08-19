/**
 *
 * Copyright 2013 lb
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
package com.github.lburgazzoli.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.undercouch.bson4jackson.BsonFactory;

/**
 *
 */
public class JacksonHelper {
    public static final ObjectMapper DEFAULT_JSON = newJsonObjectMapper();
    public static final ObjectMapper DEFAULT_BSON = newBsonObjectMapper();

    // *************************************************************************
    //
    // *************************************************************************

    /**
     *
     * @return
     */
    public static ObjectMapper newJsonObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.disable(SerializationFeature.INDENT_OUTPUT);
        om.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return om;
    }

    /**
     *
     * @return
     */
    public static ObjectMapper newBsonObjectMapper() {
        return new ObjectMapper(new BsonFactory());
    }

}
