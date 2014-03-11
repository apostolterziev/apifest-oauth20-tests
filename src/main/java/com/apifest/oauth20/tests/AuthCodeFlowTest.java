/*
* Copyright 2013-2014, ApiFest project
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

package com.apifest.oauth20.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 * Test cases for obtaining auth code.
 *
 * @author Rossitsa Borissova
 */
public class AuthCodeFlowTest extends OAuth20BasicTest {

    public AuthCodeFlowTest() {
    }

    @Test
    public void when_auth_code_obtained_with_invalid_client_id_return_error() throws Exception {
        // WHEN
        String response = obtainAuthCode(clientId + "_invalid", REDIRECT_URI);

        // THEN
        assertEquals(response,  "{\"error\": \"invalid client_id\"}");
        System.out.println("response: " + response);
    }

    @Test
    public void when_auth_code_obtained_with_invalid_response_type_return_error() throws Exception {
        // WHEN
        String response = obtainAuthCode(clientId, REDIRECT_URI, "unknown_type");

        // THEN
        assertEquals(response,  "{\"error\": \"unsupported_response_type\"}");
        System.out.println("response: " + response);
    }

    @Test
    public void when_auth_code_obtained_with_invalid_redirect_uri_return_error() throws Exception {
        // WHEN
        String response = obtainAuthCode(clientId, "htp:/example.com");

        // THEN
        assertEquals(response,  "{\"error\": \"invalid redirect_uri\"}");
        System.out.println("response: " + response);
    }

    @Test
    public void when_auth_code_obtained_with_valid_redirect_uri_return_code() throws Exception {
        // WHEN
        String response = obtainAuthCode(clientId, "http://example.com");

        // THEN
        assertTrue(!response.contains("error"));
        log.info(response);
    }
}