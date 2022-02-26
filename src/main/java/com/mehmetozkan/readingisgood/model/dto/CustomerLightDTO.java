package com.mehmetozkan.readingisgood.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerLightDTO {

    private String name;
    private String surname;
    private String email;

}
