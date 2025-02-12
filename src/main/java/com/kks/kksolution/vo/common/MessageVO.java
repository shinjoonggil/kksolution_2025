package com.kks.kksolution.vo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
public class MessageVO implements Serializable {
    private MessageType type;
    private String message;
    @Serial
    private static final long serialVersionUID = 1L; // 직렬화 버전 명시

    public static MessageVO DEFAULT(String message) {
        return new MessageVO(MessageType.DEFAULT, message);
    }

    public static MessageVO SUCCESS(String message) {
        return new MessageVO(MessageType.SUCCESS, message);
    }

    public static MessageVO ERROR(String message) {
        return new MessageVO(MessageType.ERROR, message);
    }

    public static MessageVO INFO(String message) {
        return new MessageVO(MessageType.INFO, message);
    }

    public static MessageVO WARN(String message) {
        return new MessageVO(MessageType.WARN, message);
    }


    public enum MessageType {
        SUCCESS,
        INFO,
        ERROR,
        WARN,
        DEFAULT
    }
}

