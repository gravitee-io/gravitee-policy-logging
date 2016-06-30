/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.policy.logging;

import io.gravitee.gateway.api.buffer.Buffer;
import io.gravitee.gateway.api.stream.BufferedReadWriteStream;
import io.gravitee.gateway.api.stream.ReadWriteStream;
import io.gravitee.gateway.api.stream.SimpleReadWriteStream;
import io.gravitee.policy.api.annotations.OnRequestContent;
import io.gravitee.policy.api.annotations.OnResponseContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public class LoggingPolicy {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingPolicy.class);

    @OnRequestContent
    public ReadWriteStream onRequestContent() {
        return new BufferedReadWriteStream() {
            @Override
            public SimpleReadWriteStream<Buffer> write(Buffer content) {
                LOGGER.info("Request stream: {}", content);
                return super.write(content);
            }
        };
    }

    @OnResponseContent
    public ReadWriteStream onResponseContent() {
        return new BufferedReadWriteStream() {
            @Override
            public SimpleReadWriteStream<Buffer> write(Buffer content) {
                LOGGER.info("Response stream: {}", content);
                return super.write(content);
            }
        };
    }
}
