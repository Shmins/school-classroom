package com.school.main.controllers;

import java.util.ArrayList;
import java.util.List;

import com.school.main.entity.Coordinator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.main.entity.dto.CoordinatorDto;
import com.school.main.services.CoordinatorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.RolesAllowed;

@Controller
@RestController
@RequestMapping("coordinator")
@RolesAllowed("COORDINATOR")
public class CoordinatorControllers {
        private final CoordinatorService coordinatorService;

        public CoordinatorControllers(CoordinatorService coordinatorService) {
                this.coordinatorService = coordinatorService;
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "request OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CoordinatorDto.class)) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @PostMapping(value = "/", consumes = "application/json")
        @Operation(summary = "Create teacher using schema CoordinatorDto and return status (200)", tags = "Coordinator")
        public ResponseEntity<CoordinatorDto> createCoordinator(@RequestBody @Validated CoordinatorDto data) {
                return ResponseEntity.status(200).body(this.coordinatorService.createCoordinator(data).convert());
        }

        @GetMapping(value = "/getAll")
        @Operation(summary = "Search all Coordinators and return TeacherDto", tags = "Coordinator")
        public ResponseEntity<List<CoordinatorDto>> getAll() {
                List<CoordinatorDto> coordinatorDto = new ArrayList<>();
                for (Coordinator i : this.coordinatorService.getAllCoordinators()) {
                        coordinatorDto.add(i.convert());
                }
                return new ResponseEntity<>(coordinatorDto, HttpStatus.valueOf(200));
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "request OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CoordinatorDto.class)) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/{id}")
        @Operation(summary = "Search Coordinator by id and return CoordinatorDto", tags = "Coordinator")
        public ResponseEntity<CoordinatorDto> getCoordinatorById(@PathVariable String id) {
                return ResponseEntity.status(200).body(this.coordinatorService.getCoordinatorById(id).convert());
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "request OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CoordinatorDto.class)) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/cpf/{cpf}")
        @Operation(summary = "Search Coordinator by cpf and return CoordinatorDto", tags = "Coordinator")
        public ResponseEntity<CoordinatorDto> getCoordinatorByCpf(@PathVariable String cpf) {
                return ResponseEntity.status(200).body(this.coordinatorService.getCoordinatorByCpf(cpf).convert());
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "request OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CoordinatorDto.class)) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/name/{name}")
        @Operation(summary = "Search Coordinator by name and return CoordinatorDto", tags = "Coordinator")
        public ResponseEntity<CoordinatorDto> getCoordinatorByName(@PathVariable String name) {
                return ResponseEntity.status(200).body(this.coordinatorService.getCoordinatorByName(name).convert());
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "request OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CoordinatorDto.class)) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @PutMapping(value = "/")
        @Operation(summary = "Update Coordinator and return status (200)", tags = "Coordinator")
        public ResponseEntity<CoordinatorDto> updateCoordinator(@RequestBody CoordinatorDto data) {
                return ResponseEntity.ok().body(this.coordinatorService.updateCoordinator(data).convert());
        }

        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "request OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = CoordinatorDto.class)) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @Operation(summary = "Delete Coordinator and return status (200)", tags = "Coordinator", responses = {
                        @ApiResponse(responseCode = "200", description = "Delete Coordinator", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - coordinator only", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error - possible cause: id of Coordinator not found", content = @Content) })
        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable String id) {
                this.coordinatorService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}
