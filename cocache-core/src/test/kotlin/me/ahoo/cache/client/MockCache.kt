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

package me.ahoo.cache.client

import me.ahoo.cache.ComputedCache
import me.ahoo.cache.api.annotation.CaffeineCache
import me.ahoo.cache.api.annotation.CoCache
import me.ahoo.cache.api.annotation.GuavaCache
import java.util.concurrent.TimeUnit

@CoCache(ttl = 1000, ttlAmplitude = 10)
@GuavaCache
interface MockDefaultGuavaClientCache : ComputedCache<String, String>

@CoCache
@GuavaCache(
    initialCapacity = 1,
    maximumSize = 2,
    concurrencyLevel = 3,
    expireUnit = TimeUnit.SECONDS,
    expireAfterAccess = 4,
    expireAfterWrite = 5
)
interface MockCustomizeGuavaClientCache : ComputedCache<String, String>

@CoCache(ttl = 1000, ttlAmplitude = 10)
@CaffeineCache
interface MockDefaultCaffeineClientCache : ComputedCache<String, String>

@CoCache
@CaffeineCache(
    initialCapacity = 1,
    maximumSize = 2,
    expireUnit = TimeUnit.SECONDS,
    expireAfterAccess = 4,
    expireAfterWrite = 5
)
interface MockCustomizeCaffeineClientCache : ComputedCache<String, String>
