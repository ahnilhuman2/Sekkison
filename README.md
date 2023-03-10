# Sekkison
# JPA Repository / RestApi 구현 / Spring Security (BcrpitPasswordEncoder) / Kakao맵 구현 / 비동기 Api 구현

## 구현 완료
**Function** | **완료** | 
:------------ | :-------------|  
**회원가입 / 로그인 구현** | :heavy_check_mark: |  
**Spring Security** | :heavy_check_mark: |  
**BcryptPasswordEncoder 비밀번호 암호화** | :heavy_check_mark: |  
**커뮤니티 구현 (공개방, 비밀방, 모집중, 모집완료, 약속종료, 방찾기, 친구찾기, 약속만들기)** | :heavy_check_mark: |  
**약속 만들기(대면/비대면 약속, 나만의 약속), 수정, 삭제, 리스트,** | :heavy_check_mark: |  
**친구 정보(프로필 사진, 이름, 쪽지보내기 )** | :heavy_check_mark: | 
**마이페이지 구현** | :heavy_check_mark: | 
**Header, Footer(홈, 알림, 쪽지, 친구목록, 마이페이지)** | :heavy_check_mark: | 
**알림 페이지 구현(친구 추가 수락/거절, 약속 초대장 수락/거절, 정렬)** | :heavy_check_mark: | 
**쪽지 구현(쪽지 목록, 답장, 정렬)** | :heavy_check_mark: |
**친구 추가, 초대, 리스트** | :heavy_check_mark: | 
**쪽지, 초대 알림 구현** | :heavy_check_mark: |
**화면 UI 개발 (헤더, 풋터, 회원가입, home, 로그인, 약속만들기, 친구조회, 사용자조회, 친구초대, 약속초대)** | :heavy_check_mark: | 
**Gitlab CI & Crontab CD** | :heavy_check_mark: |  
**front 비동기 (Ajax)** | :heavy_check_mark: |  
**카카오맵 생성(KaKao Developer)** | :heavy_check_mark: |  
**마커 생성(KaKao Developer)** | :heavy_check_mark: | 
**좌표를 통해 위치 검색 구현(KaKao Developer)** | :heavy_check_mark: | 
**날짜 캘린더 생성** | :heavy_check_mark: |  



# ERD 다이어그램
<p align="center">
<img width="700" alt="erd8" src="https://user-images.githubusercontent.com/112387307/223662586-150f0bbd-d5fa-457d-a4f8-5bf6c059cf62.png">

### 테스트 전용 로그인 회원
**User**
> - ID : lion
>
> - PW : kk1234
<hr>

## 🔽 RestAPI EndPoint

| METHOD | URI                                | 기능               | RequestBody                                      |인증필요             |
| ------ | ---------------------------------- |---------------------------| ------------------------------------- |----------- |
| POST   | /api/v1/**users**/join                 | 회원가입                      | {"username": "string","password":"string"} |  | 
| POST   | /api/v1/**users**/login                | 로그인                       | {"username": "string","password":"string"} | | 
| POST   | /api/v1/**users**/{userId}/role/change | 회원 등급 변경(ADMIN 등급만 가능)    | { "role": "string" }                       |✔ | 
| GET    | /api/v1/**users**/{id}                      |회원 단건 조회(ADMIN 등급만 가능)  |                                           | ✔| 
| GET    | /api/v1/**users**/                      |회원 전체 조회(ADMIN 등급만 가능)  |                                           | ✔| 
| GET    | /api/v1/**posts**                      | 게시글 조회(최신 글 20개 페이징 처리)   |                                           | | 
| GET    | /api/v1/**posts**/{postId}             | 특정 게시글 상세 조회              |                                           | | 
| POST   | /api/v1/**posts**                      | 게시글 작성 (jwt 토큰 인증 필요) | { "title": "string" , "body": "string"}    |✔ | 
| PUT    | /api/v1/**posts**/{postId}             | 게시글 수정 (jwt 토큰 인증 필요) | { "title": "string" , "body": "string"}    |✔ | 
| DELETE | /api/v1/**posts**/{postId}             | 게시글 삭제 (Soft Delete 적용) |                                           | ✔| 
| GET | /api/v1/**posts**/my           | 내가 쓴 포스트 보기(최신순,20개) |                                           |✔ | 
| GET | /api/v1/**alarms**          | 알림 보기(최신순,20개) |                                           | | 
| POST | /api/v1/**posts**/{postId}/likes        | 게시글 좋아요 기능 (jwt 토큰 인증 필요) |                                           |✔ | 
| POST | /api/v1/{id}/**comments**            | 해당 게시글 댓글 달기 |  { "comment": "string" }                                          |✔ | 
| PUT | /api/v1/{id}/**comments**             | 해당 게시글 댓글 수정 |           { "comment": "string" }                                 |✔ | 
| DELETE | /api/v1/{id}/**comments**             | 해당 게시글 댓글 삭제 (Soft Delete 적용) |                                           |✔ | 
| GET | /api/v1/{id}/**comments**            | 해당 게시글 댓글 조회(페이징,최신순) |                                           | | 
