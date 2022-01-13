package com.envyful.papi.api;

import java.util.UUID;

public interface PlatformManager<T> {

    T convert(UUID uuid);

}
