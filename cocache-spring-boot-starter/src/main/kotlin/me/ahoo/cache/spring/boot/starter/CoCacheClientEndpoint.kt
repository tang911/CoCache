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

package me.ahoo.cache.spring.boot.starter

import me.ahoo.cache.CacheFactory
import me.ahoo.cache.api.CacheValue
import me.ahoo.cache.api.client.ClientSideCache
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation
import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.boot.actuate.endpoint.annotation.Selector

@Endpoint(id = "cocacheClient")
class CoCacheClientEndpoint(override val cacheFactory: CacheFactory) : AbstractCoCacheEndpoint() {

    private fun String.clientSideCache(): ClientSideCache<Any>? {
        return coherentCache()?.clientSideCache
    }

    @ReadOperation
    fun getSize(@Selector name: String): Long? {
        return name.clientSideCache()?.size
    }

    @ReadOperation
    fun get(@Selector name: String, @Selector key: String): CacheValue<*>? {
        val coherentCache = name.coherentCache() ?: return null
        val clientCacheKey = coherentCache.keyConverter.toStringKey(key)
        return coherentCache.clientSideCache.getCache(clientCacheKey)
    }

    @DeleteOperation
    fun clear(@Selector name: String) {
        name.clientSideCache()?.clear()
    }
}
