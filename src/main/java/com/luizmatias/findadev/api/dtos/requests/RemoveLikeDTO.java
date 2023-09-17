package com.luizmatias.findadev.api.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RemoveLikeDTO(
        @NotNull
        @Min(1)
        @Max(Long.MAX_VALUE)
        Long fromId,
        @NotNull
        @Min(1)
        @Max(Long.MAX_VALUE)
        Long toId
) {

}
