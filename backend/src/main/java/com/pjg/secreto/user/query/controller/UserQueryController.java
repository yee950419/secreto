package com.pjg.secreto.user.query.controller;

import com.pjg.secreto.common.response.SuccessResponse;
import com.pjg.secreto.user.common.dto.UserInfo;
import com.pjg.secreto.user.query.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserQueryController {

    @PostMapping("/users/log-in")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto){
        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", "iasdoifsajndfoidsa");
        result.put("accessToken", "aposdfjasdpofmjasdf");
        result.put("tokenType", "bearer");
        result.put("userInfo", new UserInfo(
                "",
                "jdragonkee@naver.com",
                "이재용",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnamu.wiki%2Fw%2F%25EC%2586%2590%25ED%259D%25A5%25EB%25AF%25BC&psig=AOvVaw3hwk1U_g8hmvtI9YGXxndF&ust=1706258699363000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCKCTz46T-IMDFQAAAAAdAAAAABAE"
        ));


        SuccessResponse response = new SuccessResponse(HttpStatus.OK, "로그인에 성공하였습니다.", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users/log-out")
    public ResponseEntity<?> logout(@RequestHeader String Type,
                                    @RequestHeader String AccessToken,
                                    @RequestBody LogOutRequestDto dto){
        SuccessResponse response = new SuccessResponse(HttpStatus.OK, "로그아웃 하셨습니다.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> detail(@RequestHeader String Type,
                                    @RequestHeader String AccessToken,
                                    @PathVariable String userId){

        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", new UserInfo(
                "",
                "jdragonkee@naver.com",
                "이재용",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnamu.wiki%2Fw%2F%25EC%2586%2590%25ED%259D%25A5%25EB%25AF%25BC&psig=AOvVaw3hwk1U_g8hmvtI9YGXxndF&ust=1706258699363000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCKCTz46T-IMDFQAAAAAdAAAAABAE"
        ));

        SuccessResponse response = new SuccessResponse(HttpStatus.OK, "정상적으로 반환하였습니다.", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/password/{certCode}")
    public ResponseEntity<?> allowChangePassword(@PathVariable String certCode,
                                                 @RequestBody AllowChangePaswordRequestDto dto){
        SuccessResponse response = new SuccessResponse(HttpStatus.OK, "비밀번호를 변경하는 페이지로 이동합니다.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cert")
    public ResponseEntity<?> validateCertCode(@RequestBody ValidateCertRequestDto dto){
        SuccessResponse response = new SuccessResponse(HttpStatus.OK, "이메일이 정상적으로 검증되었습니다.");
        return ResponseEntity.ok(response);
    }
}
