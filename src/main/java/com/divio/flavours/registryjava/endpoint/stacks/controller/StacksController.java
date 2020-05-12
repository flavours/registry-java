package com.divio.flavours.registryjava.endpoint.stacks.controller;

import com.divio.flavours.registryjava.endpoint.stacks.model.Stack;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
public class StacksController {
    // TODO: Move out of controller
    public static final Stack DEFAULT_STACK = new Stack("77bde934-5d73-4d25-9222-e74adb48ef3e", "gradle");

    @GetMapping(path = "/stacks/{id}/")
    public ResponseEntity<?> stacks(@NotBlank @PathVariable("id") final String id) {
        if (DEFAULT_STACK.getId().equalsIgnoreCase(id)) {
            return ResponseEntity.ok(DEFAULT_STACK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Identical to stacks
    @GetMapping(path = "/platforms/{id}/")
    public ResponseEntity<?> platforms(@NotBlank @PathVariable("id") final String id) {
        if (DEFAULT_STACK.getId().equalsIgnoreCase(id)) {
            return ResponseEntity.ok(DEFAULT_STACK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
