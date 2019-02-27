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

    public static <X> ResponseEntity<X> wrapOrNotFound(final Optional<X> maybeResponse, final HttpHeaders header) {
        if (maybeResponse.isPresent()) {
            return ResponseEntity.ok().headers(header).body(maybeResponse.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}