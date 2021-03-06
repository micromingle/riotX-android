/*
 * Copyright 2020 New Vector Ltd
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
 *
 */

package im.vector.matrix.android.internal.session.profile

import im.vector.matrix.android.api.MatrixCallback
import im.vector.matrix.android.api.session.profile.ProfileService
import im.vector.matrix.android.api.util.Cancelable
import im.vector.matrix.android.api.util.JsonDict
import im.vector.matrix.android.api.util.Optional
import im.vector.matrix.android.internal.task.TaskExecutor
import im.vector.matrix.android.internal.task.configureWith
import javax.inject.Inject

internal class DefaultProfileService @Inject constructor(private val taskExecutor: TaskExecutor,
                                                         private val getProfileInfoTask: GetProfileInfoTask) : ProfileService {

    override fun getDisplayName(userId: String, matrixCallback: MatrixCallback<Optional<String>>): Cancelable {
        val params = GetProfileInfoTask.Params(userId)
        return getProfileInfoTask
                .configureWith(params) {
                    this.callback = object : MatrixCallback<JsonDict> {
                        override fun onSuccess(data: JsonDict) {
                            val displayName = data[ProfileService.DISPLAY_NAME_KEY] as? String
                            matrixCallback.onSuccess(Optional.from(displayName))
                        }

                        override fun onFailure(failure: Throwable) {
                            matrixCallback.onFailure(failure)
                        }
                    }
                }
                .executeBy(taskExecutor)
    }

    override fun getAvatarUrl(userId: String, matrixCallback: MatrixCallback<Optional<String>>): Cancelable {
        val params = GetProfileInfoTask.Params(userId)
        return getProfileInfoTask
                .configureWith(params) {
                    this.callback = object : MatrixCallback<JsonDict> {
                        override fun onSuccess(data: JsonDict) {
                            val avatarUrl = data[ProfileService.AVATAR_URL_KEY] as? String
                            matrixCallback.onSuccess(Optional.from(avatarUrl))
                        }

                        override fun onFailure(failure: Throwable) {
                            matrixCallback.onFailure(failure)
                        }
                    }
                }
                .executeBy(taskExecutor)
    }

    override fun getProfile(userId: String, matrixCallback: MatrixCallback<JsonDict>): Cancelable {
        val params = GetProfileInfoTask.Params(userId)
        return getProfileInfoTask
                .configureWith(params) {
                    this.callback = matrixCallback
                }
                .executeBy(taskExecutor)
    }
}
