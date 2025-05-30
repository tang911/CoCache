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

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import me.ahoo.cache.api.CacheValue
import me.ahoo.cache.api.annotation.CoCache
import me.ahoo.cache.api.annotation.GuavaCache

/**
 * Guava Client Side Cache .
 *
 * @author ahoo wang
 */
class GuavaClientSideCache<V>(
    private val guavaCache: Cache<String, CacheValue<V>> = CacheBuilder.newBuilder().build(),
    override val ttl: Long = CoCache.DEFAULT_TTL,
    override val ttlAmplitude: Long = CoCache.DEFAULT_TTL_AMPLITUDE
) : ComputedClientSideCache<V> {

    override fun getCache(key: String): CacheValue<V>? {
        return guavaCache.getIfPresent(key)
    }

    override fun setCache(key: String, value: CacheValue<V>) {
        if (value.isExpired) {
            return
        }
        guavaCache.put(key, value)
    }

    override val size: Long
        get() = guavaCache.size()

    override fun evict(key: String) {
        guavaCache.invalidate(key)
    }

    override fun clear() {
        guavaCache.invalidateAll()
    }

    companion object {
        fun <V> GuavaCache.toClientSideCache(
            ttl: Long = CoCache.DEFAULT_TTL,
            ttlAmplitude: Long = CoCache.DEFAULT_TTL_AMPLITUDE
        ): GuavaClientSideCache<V> {
            val cacheBuilder = CacheBuilder.newBuilder()
            if (initialCapacity != GuavaCache.UNSET_INT) {
                cacheBuilder.initialCapacity(initialCapacity)
            }
            if (concurrencyLevel != GuavaCache.UNSET_INT) {
                cacheBuilder.concurrencyLevel(concurrencyLevel)
            }
            if (maximumSize != GuavaCache.UNSET_LONG) {
                cacheBuilder.maximumSize(maximumSize)
            }
            if (expireAfterWrite != GuavaCache.UNSET_LONG) {
                cacheBuilder.expireAfterWrite(expireAfterWrite, expireUnit)
            }
            if (expireAfterAccess != GuavaCache.UNSET_LONG) {
                cacheBuilder.expireAfterAccess(expireAfterAccess, expireUnit)
            }
            return GuavaClientSideCache(cacheBuilder.build(), ttl, ttlAmplitude)
        }
    }
}
