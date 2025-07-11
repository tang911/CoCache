/*
 * Copyright [2021-present] [ahoo wang <ahoowang@qq.com> (https://github.com/Ahoo-Wang)].
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.ahoo.cache.api.annotation

import me.ahoo.cache.api.annotation.GuavaCache.Companion.UNSET_INT
import me.ahoo.cache.api.annotation.GuavaCache.Companion.UNSET_LONG
import java.lang.annotation.Inherited
import java.util.concurrent.TimeUnit

/**
 * 配置 GuavaCache 作为客户端缓存。
 *
 * @see me.ahoo.cache.api.client.ClientSideCache
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Inherited
@MustBeDocumented
@Suppress("LongParameterList")
annotation class CaffeineCache(
    val initialCapacity: Int = UNSET_INT,
    val maximumSize: Long = UNSET_LONG,
    val expireUnit: TimeUnit = TimeUnit.SECONDS,
    val expireAfterWrite: Long = UNSET_LONG,
    val expireAfterAccess: Long = UNSET_LONG
)
