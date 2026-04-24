package com.example.taixenguguc.dbo.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String fullname;

    @Size(max=10,message="INVALID_PHONE")
    String phone;

    @Size(min=6, message = "INVALID_SIZE_PASSWORD")
    String password;
}
