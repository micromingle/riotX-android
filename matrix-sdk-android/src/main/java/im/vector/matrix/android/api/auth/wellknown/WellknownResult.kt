/*
 * Copyright (c) 2020 New Vector Ltd
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

package im.vector.matrix.android.api.auth.wellknown

import im.vector.matrix.android.api.auth.data.WellKnown

/**
 * Ref: https://matrix.org/docs/spec/client_server/latest#well-known-uri
 */
sealed class WellknownResult {
    /**
     * The provided matrixId is no valid. Unable to extract a domain name.
     */
    object InvalidMatrixId : WellknownResult()

    /**
     * Retrieve the specific piece of information from the user in a way which fits within the existing client user experience,
     * if the client is inclined to do so. Failure can take place instead if no good user experience for this is possible at this point.
     */
    data class Prompt(val homeServerUrl: String,
                      val identityServerUrl: String?,
                      val wellKnown: WellKnown) : WellknownResult()

    /**
     * Stop the current auto-discovery mechanism. If no more auto-discovery mechanisms are available,
     * then the client may use other methods of determining the required parameters, such as prompting the user, or using default values.
     */
    object Ignore : WellknownResult()

    /**
     * Inform the user that auto-discovery failed due to invalid/empty data and PROMPT for the parameter.
     */
    object FailPrompt : WellknownResult()

    /**
     * Inform the user that auto-discovery did not return any usable URLs. Do not continue further with the current login process.
     * At this point, valid data was obtained, but no homeserver is available to serve the client.
     * No further guess should be attempted and the user should make a conscientious decision what to do next.
     */
    object FailError : WellknownResult()
}