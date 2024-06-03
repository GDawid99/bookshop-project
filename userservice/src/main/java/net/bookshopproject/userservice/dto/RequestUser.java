package net.bookshopproject.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RequestUser {
    private List<Long> ids;
}
