# NBE5-7-1-Team01

## 커피를 찾을 수 없습니다, <span style="color: red;">404</span> Coffee Not Found
**404 Coffee Not Found**는 카페 메뉴의 효율적인
CRUD(Create, Read, Update, Delete) 운영 시스템을 구축하고,

고객 주문 데이터를 이메일 기준으로 통합 관리할 수 있는 플랫폼을 구현한 프로젝트입니다 !

![Image](https://github.com/user-attachments/assets/9fb40579-df74-4981-8de7-67d273249f9c)


---

## 🗓️ 개발기간
**2024.04.23 ~ 2024.04.28**

---

## 😈 팀원 소개
| 이름 | 주요 역할 | 추가 세부 역할 | 파트 |
|:----:|:---------:|:--------------:|:----:|
| [황성철](https://github.com/HwuanPage) | PM(프로젝트 매니저), UI 개발자 | 프로젝트 전체 관리 및 계획 수립, 전체 UI 개발 담당 | 전체 관리 |
| [신현우](https://github.com/newname99) | UI 개발자 | 사용자인터페이스(UI) 화면 개발 및 최종 화면 완성 | 메인 페이지, 관리자 페이지 |
| [진주열](https://github.com/JuYul-Jin) | 서버 개발자 | 주요 비즈니스 로직 보조 개발 및 API 구현 지원 | ITEM 클래스 |
| [정미광](https://github.com/mipangg) | 서버 개발자 | 주요 비즈니스 로직 보조 개발 및 API 구현 지원 | ORDERITEMS 클래스 |
| [고영민](https://github.com/dbogym) | 서버 개발자 | 주요 비즈니스 로직 보조 개발 및 DB 설계 및 연동 | ORDER 클래스 |

---

## 📍 주요 기능
| 구분 | 기능명 | 설명 |
|------|-------|------|
| 공통 | 상품 목록 조회 | 전체 상품 목록을 조회 (+ 카테고리별 필터링 기능 제공) |
| 고객 | 장바구니 관리 | 상품을 장바구니에 추가/삭제하고 수량 조절 및 총합 금액 확인 가능 |
| 고객 | 주문하기 | 이메일, 주소, 우편번호 입력 후 원하는 상품과 수량을 선택하여 주문 수행 |
| 고객 | 주문 내역 확인 | "결제하기" 클릭 시 주문 완료 알림창 확인 |
| 고객 | 주문 내역 조회 | 본인의 이메일을 기반으로 주문 내역 및 주문 상세 조회 가능 |
| 관리자 | 상품 등록 | 새로운 상품을 이름, 가격, 이미지, 카테고리와 함께 등록 가능 |
| 관리자 | 상품 수정 | 기존 상품의 정보 수정 가능 (ex. 상품의 이름/가격/설명/이미지 변경) |
| 관리자 | 상품 삭제 | 기존 상품 삭제 가능 |
| 관리자 | 주문 전체 조회 | 고객들의 주문 전체 리스트를 조회하고 상세 주문 내용 확인 가능 |
| 관리자 | 주문 상태 변경 | 주문 상태를 준비 중, 배송 중 등으로 변경 가능 (선택 사항) |

---

## 🔀 Flow Chart
![Image](https://github.com/user-attachments/assets/0b2f6918-bf4b-4e13-adf9-f1cd55de1b29)

---

## 💽 ERD
![Image](https://github.com/user-attachments/assets/e5938e48-36d3-4c2a-ba9f-5e433325e3d5)

---

## 🧩 Class Diagram
![Image](https://github.com/user-attachments/assets/8b98beb1-a665-4916-b4d5-59ee2379a080)

---

## 📄 API 명세서
![Image](https://github.com/user-attachments/assets/6626bf1c-6699-4694-8071-152e1aef2a19)

---

## 🛠️ 기술 스택
![Image](https://github.com/user-attachments/assets/27a7ae2b-f72b-4680-9536-c185f16b8eea)

---

## 🛒 주요 기능별 화면

### 📌 주문 내역 작성 (고객)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/de435ecf-55e4-43d4-9152-3457b7f3081e" />

### 📌 주문하기 (고객)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/ae609ee9-285d-458c-a5d6-bbdb53a75685" />

### 📌 주문 내역 조회 (고객)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/d31b6f3d-d9d1-4c40-8ab3-7897860bfc7f" />

### 📌 주문 내역 수정 (고객)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/96b64872-bfa9-41f6-be28-9d95fbb8d5e6" />

---

### 📌 메뉴 아이템 추가 (관리자)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/3ec212f0-afa7-4186-98cf-2de939baa881" />

### 📌 메뉴 아이템 관리 (관리자)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/18966609-638e-4ff3-bdf0-e7149dae0b2d" />

### 📌 메뉴 아이템 수정 (관리자)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/a5dac0a7-256f-4ef2-9275-58dfbd3ca5d9" />

### 📌 주문 내역 조회 (관리자)
<img width="1608" alt="Image" src="https://github.com/user-attachments/assets/b737166d-8302-4d52-b908-d6a2f00e6a19" />