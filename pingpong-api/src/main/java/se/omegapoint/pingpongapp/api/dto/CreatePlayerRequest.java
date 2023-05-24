package se.omegapoint.pingpongapp.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePlayerRequest (@NotBlank String name){
}
