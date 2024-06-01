package ru.kislyakow.user.wallet.service.api.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    @NotNull
    private Long id;

    @NotEmpty
    @JsonProperty("full_name")
    private String fullName;

    @NotEmpty
    private String email;
}
