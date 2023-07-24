
![](https://img.shields.io/badge/Last_Upadate-2023--07--20-blue)
![](https://img.shields.io/badge/Sprint-2-green)


# 📜 TODO 리스트 프로젝트
- 2023 코드스쿼드 마스터즈 Max에서 진행한 "TODO 리스트"를 구현하는 그룹 프로젝트
- 미션 기간: `2023-07-10 ~ 2023-07-21`
- <a href="https://github.com/masters2023-2nd-project-traveler/todo-max/wiki"><img src="https://img.shields.io/badge/Todo-black?logo=Wikipedia"></a>

<br/>

## 🧑🏻‍💻팀원 소개
|                                                    프론트엔드                                                    |                                                    프론트엔드                                                    |                                                          백엔드                                                          |                                                       백엔드                                                       |                                                      백엔드                                                      |                                                       백엔드                                                       |
|:-----------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------:|
| <a href="https://github.com/gunoc"><img src = "https://avatars.githubusercontent.com/gunoc" width="120px;"> | <a href="https://github.com/lolWK"><img src = "https://avatars.githubusercontent.com/lolWK" width="120px;"> | <a href="https://github.com/chunghye98"><img src = "https://avatars.githubusercontent.com/chunghye98" width="120px;"> | <a href="https://github.com/won4885"><img src = "https://avatars.githubusercontent.com/won4885" width="120px;"> | <a href="https://github.com/sudago"><img src = "https://avatars.githubusercontent.com/sudago" width="120px;"> | <a href="https://github.com/CDBchan"><img src = "https://avatars.githubusercontent.com/CDBchan" width="120px;"> |                                         |                                         |
|                                     [**푸반**](https://github.com/gunoc)                                      |                                     [**아티**](https://github.com/lolWK)                                      |                                        [**시오**](https://github.com/chunghye98)                                        |                                     [**Sully**](https://github.com/won4885)                                     |                                      [**지안**](https://github.com/sudago)                                      |                                    [**Charile**](https://github.com/CDBchan)                                    |


## 🖥️ 동작 화면


<br/>

<br/>

## 🔧️ 개발 환경
### Front-End

![](https://img.shields.io/badge/-ReactJs-61DAFB?logo=react&logoColor=white&style=flat)
![](https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white&style=flat)
![](https://camo.githubusercontent.com/a91f29fbfde227665b0cd5a447c0b035180e8a285bfef1ec8d91c8ba80fcaa20/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f547970657363726970742d3331373843363f7374796c653d666c6174266c6f676f3d54797065536372697074266c6f676f436f6c6f723d7768697465)
![](https://camo.githubusercontent.com/e3883202fdd9cb44fd6a62f35730342d5cd477c3d76a2140aa38aa87eac6b224/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f2d56697375616c25323053747564696f253230436f64652d3030374143433f7374796c653d666c6174266c6f676f3d56697375616c25323053747564696f253230436f6465266c6f676f436f6c6f723d7768697465)

- VITE
- Styled-Components
- React
- TypeScript

### Back-End
![](https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white)
![](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white)
![](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white)
![](https://img.shields.io/badge/Amazon_AWS-232F3E?style=flat&logo=amazonaws)

- Java: `JDK 11`
- SpringBoot: `ver. 2.7.13`
- MySQL: `ver. 8.0.32`
- Amazon AWS: `EC2`, `RDS`

<br/>

## ⚙️ 인프라 구조
### Back-End
`Github` -> `AWS EC2` -> `AWS RDS (MySQL)`

<br/>

## 💾 ERD
![](https://user-images.githubusercontent.com/62871026/254807800-fa460514-f837-4734-aba7-64251e7d6682.png)

<br/>

## 🌎 API 명세서
- [상세 명세서(Postman)](https://documenter.getpostman.com/view/26597299/2s946bAu7a)

| 기능            | HTTP Method | URL                       |
|:--------------|:------------|:--------------------------|
| todolist 가져오기 | `GET`       | /api/                     |
| 카드 등록         | `POST`      | /api/task                 |
| 카드 삭제         | `DELETE`    | /api/task/:taskId         |
| 카드 수정         | `PATCH`     | /api/task/:taskId         |
| 카드 이동         | `PATCH`     | /api/task/process/:taskId |
| 칼럼 생성         | `POST`      | /api/process              |
| 칼럼 수정         | `PATCH`     | /api/process/:processId   |
| 칼럼 삭제         | `DELETE`    | /api/process/:processId   |
| 활동 기록 조회      | `GET`       | /api/history              |
| 활동 기록 삭제      | `DELETE`    | /api/history              |

<br/>

## 🗂️ WIKI 문서
### 🤝 협업 전략
- [그라운드 룰](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C-%EB%A3%B0)
- [스크럼](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/%EC%8A%A4%ED%81%AC%EB%9F%BC)
- [커밋 컨밴션](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/%EC%BB%A4%EB%B0%8B-%EC%BB%A8%EB%B0%B4%EC%85%98)
- [브랜치 전략](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/%EB%B8%8C%EB%9E%9C%EC%B9%98-%EC%A0%84%EB%9E%B5)
- [참고 링크](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/%EC%B0%B8%EA%B3%A0-%EB%A7%81%ED%81%AC)

### 💾 DB
- [ERD](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/ERD)
- [Scheme](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/Scheme)

### 📓 API
- [Postman](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/Postman)
- [Swagger](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/Swagger)
- [Status](https://github.com/masters2023-2nd-project-traveler/todo-max/wiki/Status)
