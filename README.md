# 🔮 GradeCalculator
<img src = "https://user-images.githubusercontent.com/55652102/157683177-25b3ed31-5435-4b36-a9ff-40179bbc2b78.png" width="100%" height="100%">
2021년 1학기 데이터베이스설계 팀프로젝트 3팀 (정연희, 김지원, 박민영)
<br>
<br>

* **프로젝트 주제**
    
    학점계산기 어플리케이션

    * 선택 성적 입력 및 성적 자동 저장
    * 자동 학점 계산
    * 목표 졸업 학점에 따른 이수 학점 계산
    * 졸업 요건 충족 자동 관리
    * 평균 학점 그래프

<br>

* **프로젝트 개요**

    개인 성적 관리 및 졸업 요건 충족 여부를 한 번에 관리할 수 있으며, 학기 별, 취득학점, 성적 비율, 전공/교양에 대한 다양한 성적 통계 그래프를 제공하여 효과적으로 성적 분석을 할 수 있는 대학생활에 꼭 필요한 학점 관리 및 졸업 요건 관리를 편리하게 해줄 “학점 계산기“ 어플리케이션을 제작하였습니다.

<br>
<br>

# 주요 기능
### 🔓 로그인

* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157783227-b17b0e2e-6a6a-403b-8f51-d228f2ad907e.png" width="50%" height="50%">

    1. 학번 및 비밀번호 입력
    2. 로그인 버튼
        - 성공하면 '로그인에 성공하였습니다' 메시지를 출력하고, 메인 화면으로 이동합니다.
        - 실패하면 '로그인에 실패하였습니다' 메시지를 출력합니다.
    3. 회원가입 버튼

<br>

* 실행 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157788418-bccb3a64-9f2c-4b79-adbb-045b557431e7.gif" width="100%" height="100%">
<br>
<br>

### 🔐 회원가입
* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157783585-1545cabe-6486-4ff9-a339-328714ff5535.png" width="50%" height="50%">
    
    1. 회원 정보 입력
        * **항목** : 학번, 이름, 비밀번호, 입학 연도, 학적(n학년 n학기), 복부전 여부, 주전공, 선택 전공, 토익점수, 졸업논문 통과 여부
    2. 회원 정보 등록

<br>

* 실행 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157788419-de29281e-8db1-48ea-9295-70cc138dfcb9.gif" width="100%" height="100%">
<br>
<br>

### 📈 성적 비율 그래프 구현
* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157784028-5c12ce86-275a-48c3-b4de-0aff7a8d973c.png" width="50%" height="50%">
    
    1. 학년 및 학기 별 성적 그래프
        * 입력하지 않은 학기의 평균 학점 값은 0으로 나타납니다.
    2. 전체/전공별 성적 그래프
    3. 성적 비율 그래프
        * 등록된 성적을 A,B,C,D,F 비율대로 확인할 수 있습니다.
    
<br>

* 실행 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157788424-f45bcbf3-5143-4ab0-8b10-90434ff1fae3.gif" width="100%" height="100%">
<br>
<br>


### 📄 성적 조회
* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157784434-258e4c3b-baf4-47f4-a493-c374c785c887.png" width="50%" height="50%">
    
    1. 학년 및 학기 별 성적 조회
    2. 평균 자동 계산
        * 입력된 성적을 바탕으로 자동 계산된 전체 평균과 전공 평균을 조회할 수 있습니다.
    3. 성적 조회
        * 선택된 학년 및 학기에 따라 등록된 성적 정보(과목명, 과목, 과목 분류, 영역, 학점, 
        성적, 재수강 여부)를 조회할 수 있습니다.
    4. 성적 등록
        * 선택된 학년 및 학기 성적 등록 화면으로 이동합니다.

<br>
<br>


### ✏️ 성적 등록
* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157784603-0ae619b6-8ae2-4200-b406-f130925b6ad4.png" width="50%" height="50%">
    
    1. 성적 등록
        * 학년 및 학기 별 성적을 전공/선택전공/교양으로 나눠서 입력할 수 있습니다.
        * **항목** : 과목명, 교양/전공 , 전공 분류, 교양 영역, 학점(몇 학점짜리 강의인지), 성적, 재수강 여부
    2. 성적 등록 확인
        * 입력된 항목을 저장합니다.
    
<br>

* 실행 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157788422-c79996e6-aea9-467a-bf7d-d44f9408c8d2.gif" width="100%" height="100%">
<br>
<br>

### 👩‍ 졸업 여부 조회
* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157784912-33c0ee5f-4702-492a-99ff-5716e951e56c.png" width="50%" height="50%">
    
    1. 수강 과목 정보와 학생의 입학 연도, 주전공 정보, 선택 전공 여부를 활용하여 졸업 요건을 얼마나 충족했는지 여부를 한눈에 확인할 수 있습니다.
        * 학생이 이수한 학점 / 필수 이수 학점

<br>

* 실행 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157788426-45562394-c062-4d9b-acdd-26adbbed073c.gif" width="100%" height="100%">
<br>
<br>

### 💁 마이페이지 조회
> 마이페이지 (1)

* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157785198-ad22a97e-1dee-470f-a6f5-d2f468ac33f3.png" width="50%" height="50%">
    
    1. 사용자 정보 조회
        * 로그인 한 사용자의 ID를 조회할 수 있습니다.
    2. 내 정보 조회
        * 로그인한 사용자의 정보 조회 화면으로 이동합니다. 
    3.  로그 아웃
        * 로그아웃 확인 알림 창을 띄웁니다.
<br>
<br>

> 마이페이지 (2)
* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157785511-6cfd04b9-8029-4d93-bce3-e7240246c141.png" width="50%" height="50%">

    1. 마이페이지 정보 조회
        * 로그인 한 사용자의 정보를 조회할 수 있습니다.
    2. 수정
        * 로그인 한 사용자의 정보를 수정할 수 있습니다.
        * 수정 화면으로 이동합니다.
<br>
<br>

> 마이페이지 (3)
* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157785675-a187fd58-56d8-492b-a2ed-a098e10a9845.png" width="50%" height="50%">
    
    1. 마이페이지 수정
        * 로그인 한 사용자의 정보를 수정할 수 있습니다.
        * **항목** : 학적(학년/학기), 주전공, 복부전여부, 선택전공, 토익, 졸업논문 통과 여부
    2. 마이페이지 수정 확인
        * 입력된 항목으로 사용자의 정보를 수정합니다. 
<br>
<br>

> 로그아웃

* 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157785909-a4745363-b6ae-48d2-afc4-649abb68c4ee.png" width="50%" height="50%">
    
    1.  로그아웃 확인
        * 로그아웃을 진행한 뒤, 로그인 창으로 이동합니다.
    2.  로그아웃 취소
        * 로그아웃을 취소하고 마이페이지 화면으로 이동합니다.

<br>

* 실행 화면

    <img src = "https://user-images.githubusercontent.com/55652102/157788411-5f2b9ace-94f2-4beb-94ea-14cab074d195.gif" width="100%" height="100%">
<br>
<br>

# 📝 프로젝트 최종보고서
* [최종 보고서](https://drive.google.com/file/d/156W4rYj7kYKGSeQ1qc72AFkyEA5VuPKH/view?usp=sharing)

<br>

# 📹 프로젝트 영상
* [프로젝트 시연 영상](https://www.youtube.com/watch?v=abNEC-vWowY&t=99s)