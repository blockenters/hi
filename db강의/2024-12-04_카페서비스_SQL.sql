-- 카페 리뷰 서비스 화면 기획서를 보고
-- 데이터베이스 테이블 설계하세요.

-- 데이터베이스는 cafe_db 라고 만드세요.
-- 이 데이터베이스 안에 테이블을 자유롭게 만드세요. 


-- cafe_db2 에 저랑 같이 테이블 만들겠습니다.
create database cafe_db2;

use cafe_db2;

-- user 테이블

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_unique` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- cafe 테이블

CREATE TABLE `cafe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `operating_days` varchar(100) DEFAULT NULL,
  `opening_hour` time DEFAULT NULL,
  `closing_hour` time DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- review 테이블

CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `cafe_id` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 각 화면에 필요한 SQL문을 작성합니다.



-- 1. 회원가입 화면에 필요한 SQL
INSERT INTO user (email, password, nickname)
values ( 'bbb@naver.com', '5678', '하이미디어' );

SELECT *
from user;


SELECT *
from cafe;

insert INTO cafe 
 ( name, address, operating_days, opening_hour, closing_hour, phone_number, 
   description)
values ('아로마 카페', '성남시 중원구 22', '월-금', '08:30', '21:00', '031-111-2222',
	'정통 카페입니다. 좋아요');

SELECT *
from cafe;


















