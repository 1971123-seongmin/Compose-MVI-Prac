## Compose MVI Practice
<p> <b>Jetpack Compose MVI</b>와 <b>Orbit MVI</b>를 구글 소셜 로그인을 공부하기 위한 프로젝트</p>

## 개요
목적 : Jetpack Compose MVI, Orbit MVI, Google 소셜 로그인 학습
기간: 2024.11 ~ 12 (약 3주)

##  주요 학습 내용
1. Compose MVI
- State, Event, Effect 세 가지 요소를 기반으로 하는 단방향 데이터 흐름(UDF) 구조 학습
- SignInContract, SignUpContract와 같이 각 화면별로 Intent와 State로 관리하는 방식 학습

2. Orbit MVI
- Orbit의 Container를 통해 상태(State)와 사이드 이펙트(SideEffect)를 관리하는 법을 학습
- intent 블록 내에서 reduce(상태 변경)와 postSideEffect(일회성 이벤트)를 사용하여 가독성 높은 MVI 구조를 학습

3. Compose MVI vs Orbit MVI 비교
- 보일러플레이트: 순수 Compose MVI는 상태 업데이트 로직을 모두 직접 짜야 하지만, Orbit은 프레임워크 차원에서 reduce 등을 제공하여 반복적인 코드를 많이 줄일 수 있다.
- 사이드 이펙트 처리: 일반 MVI 방식의 경우 Channel이나 SharedFlow를 사용해 직접 만들어야 하는 번거로움이 있으나, Orbit은 자체 기능을 통해 토스트 메시지 출력, 화면 이동 등 일회성 이벤트를 간단하게 처리할 수 있다.

4. Google 소셜 로그인
- Google Credential을 활용한 로그인 구현

## 느낀 점
**MVI 패턴 구현과 Orbit 활용**
- 기존의 MVI 구조는 화면과 이벤트가 늘어날수록 정의해야 할 State와 연동 코드가 계속해서 증가하는 가독성과 유지보수의 어려움이라는 문제가 있었다. 하지만 Orbit MVI를 적용해 보며, 상태가 내부적으로 처리되는 방식 덕분에 훨씬 적은 양의 코드로 MVI 구조를 만들 수 있다고 느꼈다.
- reduce를 통한 상태 업데이트와 SideEffect를 활용한 일회성 이벤트 관리 방식을 공부하며 MVI 구조를 사용한다면 orbit을 도입해보면 좋을 것 같다고 느꼈다.

## Articles
* [Compose MVI 적용기 (1) - 기초 구조 설계](https://velog.io/@dssn1999/Compose-MVI-적용기-1)
* [Compose MVI 적용기 (2) - Orbit를 활용한 더 나은 상태 관리](https://velog.io/@dssn1999/Compose-MVI-적용기-2-orbit를-활용한-더-나은-상태-관리)
