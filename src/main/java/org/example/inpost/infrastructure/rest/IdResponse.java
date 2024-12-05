package org.example.inpost.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class IdResponse {
    UUID id;
}
