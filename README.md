# What To Eat 🍽️

오늘 먹을 메뉴를 랜덤으로 골라주는 서비스

## 핵심 기능

1. **랜덤 메뉴 추천** — 카테고리, 식사 시간 조건에 따라 랜덤 추천
2. **메뉴 관리** — 메뉴 등록 / 수정 / 삭제
3. **추천 히스토리** — 오늘 추천받은 메뉴 기록 (반복 추천 방지 옵션)
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/2aa7ede8-5599-4037-9c23-8a7ab0ad9936" width="230"/></td>
    <td><img src="https://github.com/user-attachments/assets/2e6b6272-1b9c-49e4-8547-406d7969d4ec" width="230"/></td>
    <td><img src="https://github.com/user-attachments/assets/be7ee144-8f49-4115-b91b-027dad6c497d" width="230"/></td>
  </tr>
</table>

---

## 기술 스택

- **Backend**: Spring Boot 3.5, Kotlin, Spring MVC
- **View**: Thymeleaf
- **DB**: MySQL + Spring Data JPA

---

## 도메인 설계

### Member (회원) — 추후 로그인 기능 추가 시

| 필드      | 타입          | 설명     |
| --------- | ------------- | -------- |
| id        | Long          | PK       |
| email     | String        | 이메일   |
| password  | String        | 비밀번호 |
| createdAt | LocalDateTime | 가입일시 |

### Visitor (익명 사용자)

| 필드        | 타입                  | 설명                   |
| ----------- | --------------------- | ---------------------- |
| id          | Long                  | PK                     |
| cookieId    | UUID (UNIQUE)         | 쿠키에 저장되는 식별자 |
| member      | Member (FK, nullable) | 로그인 연동 시 사용    |
| createdAt   | LocalDateTime         | 최초 방문일시          |
| lastVisitAt | LocalDateTime         | 마지막 방문일시        |

> 쿠키 만료/브라우저 변경 시 새 Visitor가 생성되지만, 소규모 서비스 특성상 별도 정리 로직 없이 운영.

### Category (카테고리)

| 필드 | 타입   | 설명                                 |
| ---- | ------ | ------------------------------------ |
| id   | Long   | PK                                   |
| name | String | 한식 / 중식 / 일식 / 양식 / 분식 ... |

### MealType (식사 시간)

| 필드 | 타입   | 설명                          |
| ---- | ------ | ----------------------------- |
| id   | Long   | PK                            |
| name | String | 아침 / 점심 / 저녁 / 간식 ... |

### Menu (메뉴)

| 필드      | 타입          | 설명      |
| --------- | ------------- | --------- |
| id        | Long          | PK        |
| name      | String        | 메뉴 이름 |
| category  | FK → Category | 카테고리  |
| mealType  | FK → MealType | 식사 시간 |
| createdAt | LocalDateTime | 등록일시  |
| updatedAt | LocalDateTime | 수정일시  |

### RecommendHistory (추천 이력)

| 필드          | 타입         | 설명                 |
| ------------- | ------------ | -------------------- |
| id            | Long         | PK                   |
| visitor       | FK → Visitor | 추천받은 익명 사용자 |
| menu          | FK → Menu    | 추천된 메뉴          |
| recommendedAt | LocalDate    | 추천 날짜            |
| memo          | String       | 메모 (선택)          |

### ERD 관계

```
Member ──○── Visitor ──<── RecommendHistory ──>── Menu
                                                    │
                                              Category, MealType
```

---

## API 설계

### MVC (화면)

| Method | URL                  | 설명                                     |
| ------ | -------------------- | ---------------------------------------- |
| `GET`  | `/`                  | 메인 — 랜덤 추천 버튼 + 오늘의 추천 결과 |
| `GET`  | `/menus`             | 메뉴 목록                                |
| `GET`  | `/menus/new`         | 메뉴 등록 폼                             |
| `POST` | `/menus`             | 메뉴 등록 처리                           |
| `GET`  | `/menus/{id}/edit`   | 메뉴 수정 폼                             |
| `POST` | `/menus/{id}`        | 메뉴 수정 처리                           |
| `POST` | `/menus/{id}/delete` | 메뉴 삭제                                |
| `GET`  | `/histories`         | 추천 이력 목록                           |

