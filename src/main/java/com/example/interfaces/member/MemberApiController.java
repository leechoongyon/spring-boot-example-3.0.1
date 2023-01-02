package com.example.interfaces.member;

import com.example.domain.member.MemberService;
import com.example.interfaces.member.MemberDto.Create;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MemberDtoMapper memberDtoMapper;

    @PostMapping("/api/v1/members")
    public ResponseEntity<?> registerMember(@Valid @RequestBody Create create) {
        var memberCommand = memberDtoMapper.of(create);
        return ResponseEntity.ok(memberService.registerMember(memberCommand));
    }

    @PostMapping("/api/v2/members")
    public ResponseEntity<?> registerMemberV2(@Valid @RequestBody MemberDto.CreateV2 createV2) {
        var memberCommand = memberDtoMapper.of(createV2);
        return ResponseEntity.ok(memberService.registerMember(memberCommand));
    }

    @GetMapping("/api/v1/members/{id}")
    public ResponseEntity<?> getMember(@PathVariable("id") String id) {
        MemberDto.Base base = new MemberDto.Base(id, "test");
        return new ResponseEntity<>(base, HttpStatus.OK);
    }
}
