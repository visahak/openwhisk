/*
 * Copyright 2015-2016 IBM Corporation
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

package system.rest;

import com.jayway.restassured.RestAssured
import common.WhiskProperties
import common.TestUtils

/**
 * Utilities for dealing with JSON schema
 *
 */
trait JsonSchema extends RestUtil {

    def validatorDir = WhiskProperties.getFileRelativeToWhiskHome("tools/json");

    /**
     * Check whether a JSON document (represented as a String) conforms to a JSON schema (also a String).
     * Invokes a python utility.
     *
     * @return true if the document is valid, false otherwise
     */
    def check(doc: String, schema: String): Boolean = {
        def result = TestUtils.runQuietly(TestUtils.DONTCARE_EXIT, validatorDir, WhiskProperties.python, "validate.py", doc, schema);
        return result.fst.trim() == "true";
    }
}
