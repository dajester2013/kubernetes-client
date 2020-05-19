/**
 * Copyright (C) 2015 Red Hat, Inc.
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
package io.fabric8.kubernetes.client.dsl;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public interface Waitable<T, P> {

  long DEFAULT_INITIAL_BACKOFF_MILLIS = 5;
  double DEFAULT_BACKOFF_MULTIPLIER = 2;

  T waitUntilReady(long amount, TimeUnit timeUnit) throws InterruptedException;

  T waitUntilCondition(Predicate<P> condition, long amount, TimeUnit timeUnit) throws InterruptedException;

  /**
   * Configure the backoff strategy to use when waiting for conditions, in case the watcher encounters a retryable error.
   * @param initialBackoff the value for the initial backoff on first error
   * @param backoffUnit the TimeUnit for the initial backoff value
   * @param backoffMultiplier what to multiply the backoff by on each subsequent error
   * @return
   */
  Waitable<T, P> withWaitRetryBackoff(long initialBackoff, TimeUnit backoffUnit, double backoffMultiplier);
}
