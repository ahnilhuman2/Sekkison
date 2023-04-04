# Sekkison
# JPA Repository / RestApi 구현 / Spring Security (BcryptPasswordEncoder) / Kakao맵 구현 / 비동기 Api 구현

프론트 배포 주소 : https://kdjdj77.github.io/Sekkison_Front/

<p align="">
<img width="500" alt="erd8" src="https://user-images.githubusercontent.com/112387307/224238495-a9c011b0-d17a-46ba-9db7-474046386001.png">

### 📌 프로젝트 기술스택
- 에디터 : Intellij Ultimate
- 개발 툴 : SpringBoot 2.7.8
- 자바 : JAVA 11
- 빌드 : Gradle 7.6
- 서버 : AWS EC2
- 데이터베이스 : MySql
- 필수 라이브러리 : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security

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
**google, kakao, naver API 로그인 구현** | :heavy_check_mark: |  
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
> - ID : user1
> - PW : 1234
<hr>

## 🔽 RestAPI EndPoint

| METHOD | URI                                | 기능                                                     |
| ------ | ---------------------------------- |--------------------------- |
| POST   | /**users**                | 회원가입                        | 
| POST   | /**users**/login                | 로그인                       | 
| GET   | /**users**/{userId}       | User객체 반환                      | 
| PUT    | /**users**/{userId}                      | 회원정보수정                                            |
| DELETE | /**users**/{userId}                     |회원탈퇴                                         | 
| GET    | /**users**/duplicated/{parameter}      | 아이디, 별명, 전화번호 중복체크                           | 
| GET    | /**users**/my_list/{userId}/{parameter}  | 친구 초대 리스트, 약속 초대 리스트                                 | 
| PUT   | /**users**/{param}/{userId}     | 별명, content 정보수정    |
| GET    | /**users**/search/invite/{userId}/{appointId}{postId}| 약속에 초대할 유저 검색     |
| POST | /**upload**/upload             | 프로필 업로드                                           |
| GET | /**upload**/{userId}           | 프로필 불러오기                                          |
| POST | /**appoints**/{userId}          | 약속 만들기 |                      
| GET | /**appoints**/{userId}/{appointId}        | 약속 가져오기                                          |
| PUT | /**appoints**/{appointId}/{userId}             | 약속 수정                                       | 
| DELETE | /**appoints**/{userId}/{appointId}             | 약속 삭제                                |
| GET | /**appoints**/members/{appointId}| 멤버 별명 가져오기                                        |
| DELETE | /**appoints**/members/{appoint_id}/{from_id}/{to_id} | 약속 멤버 강퇴                | 
| GET | /**appoints**/search/{is_public}/{is_recruit}/{page} | 약속 최대인원 수정                | 
| PUT | /**appoints**/setCount/{appoint_id}/{user_id}/{count} | 약속 멤버 강퇴                |  
| GET | /**appoints**/list/{userId}/{page}| 내약속목록 가져오기               | 
| DELETE | /**friends**/{friendId} | 친구초대 거절              | 
| POST | /**friends**/ | 친구초대 보내기               |  
| POST | /**friends**/accept/{friendId} | 친구초대 수락                | 
| GET | /**friends**/list{userId} | 친구목록                | 
| DELETE | /**invites**/{inviteId} | 약속초대 거절                | 
| POST | /**invites**//{appointId}/{fromId}/{toId} | 초대보내기                | 
| GET | /**messages**/list/{userId} | 쪽지 목록                | 
| DELETE | /**messages**/{userId} | 쪽지 삭제           | 
| POST | /**messages** | 쪽지 보내기                | 
| GET | /**myAppoints**/is_master/{userId}/{appointId} | 방장 여부               | 
| POST | /**myAppoints**/{userId}/{appointId} | 약속 참가              | 
| DELETE | /**myAppoints**/{userId}/{appointId} | 약속 나가기           | 
