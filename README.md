## 실행 방법 🚀

언어 : `JAVA 17`

프레임 워크 : `Spring Boot 2.7.13`

Database : `H2 - MySQL Mode`

DB 구조: [`ERD`](/docs/requirements/DB설계.md)

요구사항 분석: [`요구사항 분석`](/docs/requirements/요구사항분석.md)

### Test 전화번호
- `phoneNumber` : "01012341234"

### API 정의

- API 공통 Response

| Element | Data Type | Parent | Description           |
|---------|-----------|--------|-----------------------|
| meta    | Object    |        | 응답 메타데이터              |
| code    | int       |        | 응답 코드 HttpStatus Base |
| message | Stirng    |        | API Message           |
| data    | Object    |        | 응답 Body               |

