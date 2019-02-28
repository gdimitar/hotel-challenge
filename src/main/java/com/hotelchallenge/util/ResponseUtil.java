package com.hotelchallenge.util;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static <X> ResponseEntity<X> wrapOrNotFound(final Optional<X> maybeResponse) {
        return wrapOrNotFound(maybeResponse, null);
    }

    private static <X> ResponseEntity<X> wrapOrNotFound(final Optional<X> maybeResponse, final HttpHeaders header) {
        return maybeResponse.map(x -> ResponseEntity.ok().headers(header).body(x))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}