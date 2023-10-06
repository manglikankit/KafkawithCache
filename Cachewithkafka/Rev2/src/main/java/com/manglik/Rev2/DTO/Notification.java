package com.manglik.Rev2.DTO;

import com.manglik.Rev2.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification <T>{
    private T data;
    private NotificationType notificationType;
    private String pid;
}
