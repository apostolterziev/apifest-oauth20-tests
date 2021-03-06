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

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Test cases for access token generation with client credentials.
 *
 * @author Rossitsa Borissova
 *
 */
public class ClientCredentialsTokenFlowTest extends OAuth20BasicTest {

    @BeforeTest
    public void setup() {
        registerDefaultScope();
        String newClientResponse = registerDefaultClient();
        clientId = extractClientId(newClientResponse);
        clientSecret = extractClientSecret(newClientResponse);
        updateClientAppStatus(clientId, 1);
    }

//    @Test
//    public void when_valid_client_id_and_client_secret_in_Authorization_Header_issue_token() throws Exception {
//        // WHEN
//        String accessToken = obtainClientCredentialsAccessToken(clientId, clientSecret, DEFAULT_SCOPE, false);
//
//        // THEN
//        assertNotNull(accessToken);
//        assertTrue(!accessToken.contains("{\"error\""));
//    }

    @Test
    public void when_invalid_client_id_and_client_secret_in_Authorization_Header_return_error() throws Exception {
        // WHEN
        String accessToken = obtainClientCredentialsAccessToken("invalid_clientId", clientSecret, DEFAULT_SCOPE, true);

        // THEN
        assertTrue(accessToken.contains("{\"error\": \"invalid client_id\"}"));
    }

    @Test
    public void when_no_Authorization_Header_and_invalid_client_id_return_error() throws Exception {
        // WHEN
        String accessToken = obtainClientCredentialsAccessToken(clientId + "invalid", clientSecret, DEFAULT_SCOPE, false);

        // THEN
        assertTrue(accessToken.contains("{\"error\": \"invalid client_id/client_secret\"}"));
    }
}
