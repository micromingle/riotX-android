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

package im.vector.matrix.android.internal.session.identity.db

import im.vector.matrix.android.internal.session.identity.data.IdentityData
import im.vector.matrix.android.internal.session.identity.data.IdentityPendingBinding

internal object IdentityMapper {

    fun map(entity: IdentityDataEntity): IdentityData {
        return IdentityData(
                identityServerUrl = entity.identityServerUrl,
                token = entity.token,
                hashLookupPepper = entity.hashLookupPepper,
                hashLookupAlgorithm = entity.hashLookupAlgorithm.toList()
        )
    }

    fun map(entity: IdentityPendingBindingEntity): IdentityPendingBinding {
        return IdentityPendingBinding(
                clientSecret = entity.clientSecret,
                sendAttempt = entity.sendAttempt,
                sid = entity.sid
        )
    }
}
