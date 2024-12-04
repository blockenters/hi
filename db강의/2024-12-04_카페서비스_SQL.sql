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


-- 로그인 화면
SELECT *
from user
where email = 'abc@gmail.com';

-- 1. 카페 데이터를 먼저 넣고 진행합니다.
-- github.com 의 sql 폴더 안에 있는 19_카페서비스_카페.sql 파일을 실행
-- (커피숍 100개 데이터 인서트 합니다.)

-- 2. 유저 데이터를 인서트 합니다.
-- github.com 의 sql 폴더 안에 있는 19_카페서비스_유저.sql 파일을 실행

-- 3. 리뷰 데이터를 인서트 합니다. 
-- github.com 의 sql 폴더 안에 있는 19_카페서비스_리뷰.sql 파일을 실행

SELECT *
from cafe;


-- 리뷰 작성 화면에 사용할 SQL 작성

insert INTO review (user_id, cafe_id, rating, content) 
values ( 1, 50, 4, '분위기 좋아요.');

SELECT *
from review;

-- 리뷰 수정 화면에 사용할 SQL

UPDATE review
set rating = 4 , content = '분위기 좋긴 좋아요.'
where id = 15;

SELECT *
from review;


-- 카페 리스트 가져오는 화면 SQL 작성

SELECT c.id,c.name,c.address,c.description,IFNULL(  avg( r.rating ) , 0 ) avg_rating
from cafe c 
left join review r 
on c.id = r.cafe_id
group by c.id
order by c.id
limit 0,20;

-- 상세화면 SQL 
-- 유저가 카페아이디 2번을 눌렀다. 

SELECT c.id, c.name, c.address, c.operating_days , 
		SUBSTR(opening_hour, 1, 5) opening_hour, 
		SUBSTR(closing_hour, 1, 5) closing_hour,
		IFNULL( avg(r.rating) , 0 ) avg_rating
from cafe c
left join review r 
on c.id = r.cafe_id
where c.id = 2;

SELECT r.id, u.nickname, r.created_at, r.content, r.rating
from review r
join user u
on r.user_id = u.id
where cafe_id = 2
order by r.created_at desc;

-- 가정 : 내 아이디는 1이다.
-- 내가 작성한 리뷰 화면 SQL 개발
SELECT r.id, r.cafe_id, c.name, r.created_at, r.content, r.rating
from review r
join cafe c 
on r.cafe_id = c.id
where user_id = 1
order by r.created_at desc;


-- 리뷰 삭제 SQL
DELETE from review
where user_id = 1 and cafe_id = 6;




