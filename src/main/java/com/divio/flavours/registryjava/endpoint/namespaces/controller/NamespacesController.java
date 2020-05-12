package com.divio.flavours.registryjava.endpoint.namespaces.controller;

import com.divio.flavours.registryjava.endpoint.namespaces.model.Namespace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
public class NamespacesController {
    // TODO: Move out of controller
    public static final Namespace DEFAULT_NAMESPACE = new Namespace("380ca58e-32dc-4a90-831d-b63a57a8f621", "gradle", List.of());

    @GetMapping(path = "/namespaces/{id}/")
    public ResponseEntity<?> resolveNamespaces(@NotBlank @PathVariable("id") final String id) {
        if (DEFAULT_NAMESPACE.getId().equalsIgnoreCase(id)) {
            return ResponseEntity.ok(DEFAULT_NAMESPACE);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
