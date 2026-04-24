package com.example.taixenguguc.dbo.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data //Tự tạo getter, setter, đồng thời có thêm các phương thức khác như toString...
@NoArgsConstructor
@AllArgsConstructor
@Builder //builder class cho 1 cái dbo
@FieldDefaults(level = AccessLevel.PRIVATE)//mặc định các biến là private
public class UserResponse {
    String id;
    String phone;
    String fullname;
}
