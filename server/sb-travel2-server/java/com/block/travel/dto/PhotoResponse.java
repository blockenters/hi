package com.block.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoResponse {
    public Long id;
    public String photoUrl;
    public String description;
}
