# 🛡️ Spring Boot 로그인 · 회원가입 · 게시판 예제

Spring Boot 기반 웹 애플리케이션에서 **로그인/회원가입 기능**과 **게시판 기능**, 그리고 **비밀번호 암호화(BCrypt)**를 적용한 예제 프로젝트입니다.

---

## 🔐 주요 기능

### ✅ 회원가입 기능
- 이름, 이메일, 비밀번호 입력 
- 비밀번호는 `BCryptPasswordEncoder`로 암호화되어 DB에 저장
- 중복 이메일 / 이름 체크

### ✅ 로그인 기능
- 이메일, 비밀번호 입력으로 로그인
- 비밀번호는 암호화된 값과 `matches()`로 비교
- 로그인 시 `HttpSession`을 통해 사용자 인증 처리

### ✅ 게시판 기능
- 게시글 작성/수정/삭제/목록/상세보기
- 작성자만 자신의 게시글을 수정/삭제 가능 (아직 x)
- 타인이 게시글 열람 시 비밀번호 확인 후 접근 (x)

---

## 🛠 사용 기술

| 기술 | 설명 |
|------|------|
| Java | 17 |
| Spring Boot | 3.x |
| Spring Security | 비밀번호 암호화용으로만 사용 |
| Thymeleaf | 뷰 템플릿 |
| Spring Data JPA | ORM, DB 연동 |
| H2 / MySQL | 내장 또는 외부 DB |
| Gradle or Maven | 빌드 도구 선택 가능 |

---

## ✨ 핵심 코드 예시

### 🔒 비밀번호 암호화 (회원가입 시)

```java
// MemberService.java
String encodedPassword = passwordEncoder.encode(member.getPassword());
member.setPassword(encodedPassword);
memberRepository.save(member);
