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

package me.ahoo.cache.example.controller;

import me.ahoo.cache.CoherentCache;
import me.ahoo.cache.example.model.User;
import me.ahoo.cosid.IdGenerator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * TestController .
 *
 * @author ahoo wang
 */
@RestController
@RequestMapping("test")
public class TestController {
    private final CoherentCache<Long, User> userCaching;
    private final IdGenerator idGenerator;
    
    public TestController(IdGenerator idGenerator,
                          @Qualifier("userCache") CoherentCache<Long, User> userCaching) {
        this.userCaching = userCaching;
        this.idGenerator = idGenerator;
    }
    
    @GetMapping
    public User get() {
        return userCaching.get(1L);
    }
    
    @PutMapping
    public void set() {
        User user = new User();
        user.setId(1L);
        user.setName(idGenerator.generateAsString());
        userCaching.set(user.getId(), user);
    }
}
