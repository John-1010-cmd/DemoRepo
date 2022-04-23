package com.example.protostuff.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Group {
    private String id;

    private String name;

    private User user;
}
