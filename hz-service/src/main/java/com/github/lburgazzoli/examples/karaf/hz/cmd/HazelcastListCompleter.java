/**
 *
 * Copyright 2013 lb
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
package com.github.lburgazzoli.examples.karaf.hz.cmd;

import com.github.lburgazzoli.examples.karaf.hz.IHazelcastInstanceProvider;
import org.apache.karaf.shell.console.completer.StringsCompleter;

import java.util.List;

/**
 *
 */
public class HazelcastListCompleter extends AbstractHazelcastCompleter {

    @Override
    protected int doComplete(IHazelcastInstanceProvider service, String buffer, int cursor, List<String> candidates) {
        StringsCompleter delegate = new StringsCompleter();
        delegate.getStrings().add(HazelcastCommandConstants.LIST_MEMBERS);
        delegate.getStrings().add(HazelcastCommandConstants.LIST_PARTITIONS);
        delegate.getStrings().add(HazelcastCommandConstants.LIST_OBJECTS);

        return delegate.complete(buffer, cursor, candidates);
    }
}
