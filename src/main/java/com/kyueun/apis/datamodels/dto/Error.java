package com.kyueun.apis.datamodels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Error {
    String errorName;
    String errorMessage;
}
