package com.financy.exception;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class ErrorMessage {
    private String code ;
    private List<Map<String, String >> messages ;
    private String message ;
}