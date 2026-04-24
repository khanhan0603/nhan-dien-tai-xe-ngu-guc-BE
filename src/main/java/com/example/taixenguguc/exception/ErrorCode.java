package com.example.taixenguguc.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    INVALID_PHONE(1001,"Số điện thoại phải 10 ký tự"),
    PHONE_NOT_EXIST(1002,"Số điện thoại không tồn tại"),
    INVALID_SIZE_PASSWORD(1003,"Mật khẩu ít nhất 6 ký tự"),
    UNCATEGORIZED_EXCEPTION(9999,"Lỗi server"),
    PHONE_EXISTED(1004,"Số điện thoại đã tồn tại"),
    INVALID_KEY(1005,"Invalid message key"),
    USER_NOT_EXIST(1006,"Số điện thoại không tồn tại"),
    UNAUTHENTICATED(1007,"Mật khẩu không chính xác"),
    USER_NOT_EXISTED(1008,"Người dùng không tồn tại"),
    ;
    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
