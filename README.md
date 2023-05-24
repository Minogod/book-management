## 📌 프로젝트 소개
- 도서관리 API 개인 미니 프로젝트
- 사용자가 도서관에서 책을 빌리는 서비스입니다. 

## 📌 주요 기능

### 회원가입 및 로그인
* 비밀번호 암호화 저장
* JWT 토큰을 이용하여 인증/인가
### 도서관 검색기능
* 모든 도서관 조회 (Page)  
* 도서관 디테일 조회  
  * 해당 도서관에서 대여가능한 책 리스트
### 책 검색기능
* 모든 책 조회 (Page)
* 책 디테일 조회  
  * 해당 책을 대여할수있는 도서관 리스트
### 책 대여 및 반납
* 조건에따른 확인로직  
  * 1인당 최대 5권의 책만 대여가능  
  * 대여기간은 14일, 대여기간 초과시 초과한일수만큼 대여불가능상태로 변경  
  * 사용자의 대여 히스토리는 영구저장  
  * 도서관에 등록된 재고가 모두 대여중인경우 대여불가능 상태로 변경
### 대여불가능 기간 만료 체크 스케줄러  
* 매일 정오 12시 사용자의 대여 패널티 만료기간을 체크하여 상태값 업데이트
### Redis를 사용한 캐시화
* 자주 사용되는 책,도서관 List Page Data를 캐시화하여 DB서버 부하분산

## 📌 ERD
![book_management](https://github.com/Minogod/book-management/assets/93550624/a2a2db52-e7c3-4a4d-9638-4fc8bf6c1bd6)

### 🔧 BackEnd
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=springdata&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/JWT-181717?style=for-the-badge&logo=jwt&logoColor=white"><img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"><img src="https://img.shields.io/badge/Map Struct-F1A54F?style=for-the-badge&logo=mapstruct&logoColor=white"><img src="https://img.shields.io/badge/Jasypt-364161?style=for-the-badge&logo=jasypt&logoColor=white">

