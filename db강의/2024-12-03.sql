select * 
from customers;

SELECT *
from orders;


-- 두개 테이블을 하나로 합쳐서 가져오시오.
SELECT *
from orders
join customers on orders.customer_id  =  customers.id ;



SELECT o.id as order_id, order_date, amount, customer_id, 
			first_name, last_name, email
from orders o
join customers c 
on o.customer_id = c.id;  

-- 위의 join은 , 두 개 테이블에 공통으로 둘다 들어있는 데이터만 합쳐서 가져온것이다.



-- 실습을 위해서, orders 테이블의 customers_id 가 2 인 사람의 데이터를 삭제하시오.
DELETE FROM orders
where customer_id = 2;

-- 회원가입은 했는데, 주문은 한번도 안한 사람도 있다.
-- 고객 테이블의 데이터를 모두 살리고, 오른쪽에 오더 테이블을 붙이고 싶다.
-- join 문에서, 왼쪽 테이블이 고객 테이블이다!
-- 즉, join의 왼쪽 테이블의 데이터는 모두 살리고, 오른쪽에 다른 테이블의 데이터를 합치는
-- 조인을 left join 이라고 한다.

SELECT *
from customers c
left join orders o
on  c.id = o.customer_id ;

SELECT *
from orders o
right join customers c
on o.customer_id = c.id;

-- 주문 금액이 600달러보다 큰 데이터를 가져오세요.
-- 주문 정보는 모두 보여주고, 주문한 사람의 이메일주소, 이름도 가져오세요.
SELECT o.*, c.email, c.first_name, c.last_name
from orders o
join customers c
on o.customer_id = c.id
WHERE o.amount > 600;

-- 위의 결과를, 주문날짜 내림차순으로 가져오세요. 
SELECT o.*, c.email, c.first_name, c.last_name
from orders o
join customers c
on o.customer_id = c.id
WHERE o.amount > 600
order by o.order_date desc;

-- 고객 아이디가 36인 사람의 주문내역을 가져오시오.
SELECT *
from orders o
join customers c
on o.customer_id = c.id
where customer_id = 36;

-- 회원가입은 했는데, 주문은 한번도 안한 고객의 이메일 주소를 가져오시오.
SELECT c.email
from customers c
left join orders o
on c.id = o.customer_id
where o.id is null;

-- 퍼스트 네임이 'Cobby' 인 사람의 주문내역을 가져오세요.
SELECT *
from customers c
join orders o
on c.id = o.customer_id
where first_name = 'Cobby';

-- 퍼스트 네임에 'ty'가 들어있는 사람의 주문내역을 가져오세요.
SELECT *
from customers c
join orders o
on c.id = o.customer_id
where first_name like '%ty%';

-- 주문 금액이 300달러 이상이고, 500달러 이하인 주문내역을 가져오세요.
-- 단, 주문한 사람의 이메일도 같이 나와야 합니다.

SELECT o.* , c.email
from orders o 
join customers c 
on o.customer_id = c.id
where amount BETWEEN 300 AND 500;

-- 각 고객별로, 주문 수를 나타내시오.
-- 주문을 한번도 안했으면, 0으로 나와야 한다.

SELECT c.id, c.first_name, c.last_name, c.email , count( o.id ) as order_cnt
from customers c
left join orders o 
on c.id = o.customer_id
group by c.id;

-- 각 고객별로 주문 금액 평균이 300달러 이상인 데이터만 가져오시오.
-- 고객의 이메일 주소도 같이 나오도록 해주세요.
SELECT  c.id, c.email, avg(o.amount) as avg_amount
from customers c
join orders o
on c.id = o.customer_id
group by c.id HAVING avg_amount >= 300;

 
-- 각 고객별로 주문 금액 최대값을 구하고,
-- 이 값이 600 달러 이상인 데이터만 가져와서, 내림차순으로 정렬하세요.
-- 이메일 주소도 나와야 합니다.
SELECT c.id, c.email, max( o.amount ) as max_amount
from customers c
join orders o
on c.id = o.customer_id 
GROUP BY c.id  HAVING  max_amount >= 600
order by max_amount desc;

-- 2019년 12월 20일부터 2020년 1월 10일 사이에 주문한 사람은 몇명입니까?
SELECT count( DISTINCT  customer_id )
from orders
where order_date >= '2019-12-20' and order_date <= '2020-01-10';

-- 2019년 12월 20일부터 2020년 1월 10일 사이의 주문 데이터에서,
-- 고객별 주문 금액 평균이 300달러 이상인 사람의
-- 평균 금액을 가져오세요.  이메일도 나오게 해주세요.
SELECT customer_id , avg(amount) as avg_amount, c.email
from orders o
join customers c
on o.customer_id = c.id
WHERE order_date BETWEEN '2019-12-20' and '2020-01-10'
group by customer_id HAVING avg_amount >= 300
order by avg_amount desc;

INSERT INTO students (first_name) VALUES 
('Caleb'), ('Samantha'), ('Raj'), ('Carlos'), ('Lisa');

INSERT INTO papers (student_id, title, grade ) VALUES
(1, 'My First Book Report', 60),
(1, 'My Second Book Report', 75),
(2, 'Russian Lit Through The Ages', 94),
(2, 'De Montaigne and The Art of The Essay', 98),
(4, 'Borges and Magical Realism', 89);

SELECT *
from students;

SELECT *
from papers;

SELECT s.first_name, p.title, p.grade
from papers p
join students s
on p.student_id = s.id
order by grade desc;


SELECT s.first_name, p.title, p.grade
from students s
left join papers p
on s.id = p.student_id;

SELECT s.first_name,  ifnull( p.title , 'MISSING' ) title , IFNULL( p.grade , 0 ) grade
from students s
left join papers p
on s.id = p.student_id;


SELECT   s.first_name,   IFNULL(avg( grade ) , 0 )  average
from students s
left join papers p
on s.id = p.student_id
group by s.id
order by average desc;




SELECT   s.first_name,   IFNULL(avg( grade ) , 0 )  average,  
			if( IFNULL(avg( grade ) , 0 ) >= 75  , 'PASSING'   , 'FAILING'  )  passing_status  
from students s
left join papers p
on s.id = p.student_id
group by s.id
order by average desc;

use test8;

INSERT INTO series (title, released_year, genre) VALUES
   ('Archer', 2009, 'Animation'),
   ('Arrested Development', 2003, 'Comedy'),
   ("Bob's Burgers", 2011, 'Animation'),
   ('Bojack Horseman', 2014, 'Animation'),
   ("Breaking Bad", 2008, 'Drama'),
   ('Curb Your Enthusiasm', 2000, 'Comedy'),
   ("Fargo", 2014, 'Drama'),
   ('Freaks and Geeks', 1999, 'Comedy'),
   ('General Hospital', 1963, 'Drama'),
   ('Halt and Catch Fire', 2014, 'Drama'),
   ('Malcolm In The Middle', 2000, 'Comedy'),
   ('Pushing Daisies', 2007, 'Comedy'),
   ('Seinfeld', 1989, 'Comedy'),
   ('Stranger Things', 2016, 'Drama');
 
 
INSERT INTO reviewers (first_name, last_name) VALUES
   ('Thomas', 'Stoneman'),
   ('Wyatt', 'Skaggs'),
   ('Kimbra', 'Masters'),
   ('Domingo', 'Cortes'),
   ('Colt', 'Steele'),
   ('Pinkie', 'Petit'),
   ('Marlon', 'Crafford');
  
 
INSERT INTO reviews(series_id, reviewer_id, rating) VALUES
   (1,1,8.0),(1,2,7.5),(1,3,8.5),(1,4,7.7),(1,5,8.9),
   (2,1,8.1),(2,4,6.0),(2,3,8.0),(2,6,8.4),(2,5,9.9),
   (3,1,7.0),(3,6,7.5),(3,4,8.0),(3,3,7.1),(3,5,8.0),
   (4,1,7.5),(4,3,7.8),(4,4,8.3),(4,2,7.6),(4,5,8.5),
   (5,1,9.5),(5,3,9.0),(5,4,9.1),(5,2,9.3),(5,5,9.9),
   (6,2,6.5),(6,3,7.8),(6,4,8.8),(6,2,8.4),(6,5,9.1),
   (7,2,9.1),(7,5,9.7),
   (8,4,8.5),(8,2,7.8),(8,6,8.8),(8,5,9.3),
   (9,2,5.5),(9,3,6.8),(9,4,5.8),(9,6,4.3),(9,5,4.5),
   (10,5,9.9),
   (13,3,8.0),(13,4,7.2),
   (14,2,8.5),(14,3,8.9),(14,4,8.9);


SELECT *
from reviewers;

SELECT *
from series;

SELECT *
from reviews;

SELECT s.title , r.rating
from series s
join reviews r
on s.id = r.series_id;

SELECT s.title , avg(r.rating) avg_rating
from series s
join reviews r
on s.id = r.series_id
group by s.id
order by avg_rating asc ;



SELECT first_name, last_name, rating
from reviewers r
join reviews rv
on r.id = rv.reviewer_id;






SELECT s.title
from series s
left join reviews r
on  s.id = r.series_id
WHERE r.id is null  ;


SELECT s.genre, avg( rating ) avg_rating
from series s
join reviews r
on s.id = r.series_id
group by s.genre;


SELECT  r.first_name, r.last_name  , count(rv.id) COUNT, 
			IFNULL( min(rv.rating) , 0    )  MIN , 
			IFNULL( max(rv.rating)  , 0  )  MAX, 
			IFNULL( avg(rv.rating) , 0 )  AVG ,
			if( count(rv.id) != 0 , 'ACTIVE'  , 'INACTIVE'  ) STATUS
from reviewers r
left join reviews rv
on r.id = rv.reviewer_id
group by r.id;



SELECT s.title, r.rating,  concat( rv.first_name, ' ' , rv.last_name ) as reviewer
from series s
join reviews  r
on s.id = r.series_id
join reviewers rv 
on r.reviewer_id = rv.id
order by title asc;







use test9;

-- customers table data
INSERT INTO customers (customer_name, email, address) VALUES
('김철수', 'kimchulsoo@example.com', '서울시 강남구'),
('이영희', 'leeyounghee@example.com', '서울시 마포구'),
('박준혁', 'parkjunhyuk@example.com', '경기도 수원시'),
('최수진', 'choisujin@example.com', '부산시 해운대구'),
('정우진', 'jungwoojin@example.com', '대구시 수성구'),
('한지수', 'hanjisu@example.com', '서울시 성동구'),
('김지훈', 'kimjihun@example.com', '인천시 남동구'),
('이민영', 'leeminyoung@example.com', '경기도 고양시'),
('장서현', 'jangseohyun@example.com', '서울시 종로구'),
('박다연', 'parkdayeon@example.com', '전라북도 전주시'),
('윤성민', 'yoonseongmin@example.com', '강원도 원주시'),
('신예린', 'shinyeorin@example.com', '서울시 관악구'),
('오지훈', 'ohjihoon@example.com', '서울시 구로구'),
('김서준', 'kimseojun@example.com', '경기도 파주시'),
('이하은', 'leehaneun@example.com', '서울시 동작구'),
('문찬영', 'moonchanyeong@example.com', '경기도 용인시'),
('임수연', 'imsuyeon@example.com', '울산시 남구'),
('유진우', 'yoojinu@example.com', '서울시 서초구'),
('정하영', 'jeonghayoung@example.com', '경기도 성남시'),
('최민준', 'choiminjun@example.com', '서울시 송파구');

-- orders table data (reflecting multiple purchases by some users and no purchases by others)
INSERT INTO orders (customer_id, order_date, total_amount) VALUES
(1, '2023-10-01', 2500000),
(2, '2023-10-03', 1800000),
(1, '2023-10-10', 2200000),
(3, '2023-10-12', 3000000),
(4, '2023-10-15', 800000),
(2, '2023-10-18', 1400000),
(5, '2023-10-20', 1800000),
(6, '2023-10-25', 1600000),
(1, '2023-10-27', 1900000),
(3, '2023-10-29', 2000000),
(7, '2023-11-01', 2200000),
(8, '2023-11-05', 2500000),
(9, '2023-11-08', 800000),
(10, '2023-11-11', 1300000),
(2, '2023-11-15', 1500000),
(4, '2023-11-20', 1700000),
(11, '2023-11-22', 2000000),
(12, '2023-11-25', 2100000),
(6, '2023-11-28', 1800000),
(8, '2023-11-30', 1600000);

-- products table data
INSERT INTO products (product_name, price) VALUES
('노트북', 1200000),
('스마트폰', 800000),
('태블릿', 600000),
('헤드폰', 200000),
('이어폰', 100000),
('스마트워치', 300000),
('모니터', 700000),
('마우스', 50000),
('키보드', 80000),
('USB 메모리', 20000),
('외장하드', 100000),
('파워뱅크', 150000),
('충전기', 40000),
('케이블', 10000),
('프린터', 500000),
('책상', 100000),
('의자', 200000),
('모니터 받침대', 50000),
('스피커', 300000),
('마이크', 60000);

-- order_items table data (reflecting multiple items per order)
INSERT INTO order_items (order_id, product_id, quantity) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 3, 1),
(2, 4, 3),
(3, 5, 2),
(3, 1, 1),
(4, 6, 2),
(5, 7, 1),
(5, 3, 1),
(6, 8, 2),
(7, 9, 4),
(8, 10, 1),
(9, 1, 1),
(9, 6, 1),
(10, 2, 2),
(10, 5, 1),
(11, 3, 3),
(12, 4, 1),
(13, 5, 1),
(14, 8, 2),
(15, 1, 2),
(15, 5, 1),
(16, 2, 3),
(16, 3, 1),
(17, 4, 2),
(17, 6, 1),
(18, 7, 1),
(18, 8, 4),
(19, 9, 2),
(20, 3, 2),
(20, 5, 3);





SELECT *
FROM orders;

-- 고객 아이디 1000, order_date 은 2024-11-12, 토탈금액은 200000원
-- 이 데이터를 추가하세요. orders 테이블에 

INSERT INTO orders (customer_id, order_date, total_amount)
values ( 1000, '2024-11-12', 200000 );

SELECT *
from orders;







-- 고객 이름과 주문 날짜를 조회하세요.
SELECT c.customer_name , o.order_date
from customers c
join orders o 
on c.id = o.customer_id ;

-- 각 주문에 대해 주문 번호, 고객 이름, 주문 총액을 조회하세요
SELECT o.id, c.customer_name, o.total_amount
from orders o
join customers c
on o.customer_id = c.id;

-- 제품 이름과 가격을 조회하세요.
SELECT product_name, price
from products;

-- 주문한 고객의 이름과 해당 주문에서 구매한 제품의 이름을 조회하세요.
SELECT c.customer_name, p.product_name
from order_items oi
join orders  o
on oi.order_id = o.id
join customers c 
on o.customer_id = c.id
join products p 
on oi.product_id = p.id;


-- 특정 주문(예: 주문 번호 15)에 포함된 제품 이름과 수량을 조회하세요.

SELECT p.product_name, oi.quantity
from orders o
join order_items oi
on o.id = oi.order_id
join products p
on oi.product_id = p.id
WHERE o.id = 15;

-- 총액이 200만 원 이상인 주문의 고객 이름과 주문 총액을 조회하세요.
SELECT c.customer_name, o.total_amount
from orders o
join customers  c 
on o.customer_id = c.id
WHERE total_amount >= 2000000;

-- 모든 주문에 대해 고객의 이름과 해당 고객이 주문한 제품 수량을 조회하세요
SELECT c.customer_name, oi.quantity
from orders o
join customers c 
on o.customer_id = c.id
join order_items oi 
on o.id = oi.order_id;

-- 각 주문에 포함된 총 제품 수량을 조회하세요.
SELECT o.id, sum(oi.quantity) sum_quantity
from orders o 
join order_items oi 
on o.id = oi.order_id
group by o.id;

-- 제품 이름과, 해당 제품이 포함된 주문의 총 주문 횟수를 조회하세요.
SELECT  p.product_name , count(*) as cnt
from order_items oi 
join products  p 
on oi.product_id = p.id
group by p.product_name;

--  2023년 10월에 주문한 고객의 이름과 주문 총액을 조회하세요.
SELECT c.customer_name, o.total_amount
from orders o
join customers  c 
on o.customer_id = c.id
where  YEAR(order_date) = 2023  and   MONTH( order_date )  = 10  ;

-- 2023년 10월에 구매한 고객의 이름과 주문 횟수를 조회하세요.
SELECT  c.customer_name , count(*) as cnt
from orders o
join customers c 
on o.customer_id = c.id
where YEAR(order_date) = 2023 and MONTH(order_date) = 10 
group by c.id;

-- 각 고객별로, 총 주문 금액(total_amount 의 sum)과 고객의 이름이 나오도록 
-- 조회하세요. 단 고객별 총 주문금액(total_amount 의 sum)이 200만원 이상인 
-- 고객만 나오도록 해주세요.
SELECT c.customer_name , sum( total_amount )
from orders o
join customers c 
on o.customer_id = c.id
group by customer_id;

-- 각 고객이 구매한 총 제품 수량을 조회하세요. 결과는 고객 이름별로 정렬하세요.
SELECT   c.customer_name , sum(oi.quantity) as sum_quantity
from customers c 
join orders o 
on c.id = o.customer_id
join order_items oi 
on o.id = oi.order_id
group by c.id
order by c.customer_name asc;


-- 각 주문에 대해 고객 이름, 주문일, 해당 주문에서 구매한 제품의 총 금액을 조회하세요.
SELECT c.customer_name, o.order_date, o.total_amount
from customers c
join orders o 
on c.id = o.customer_id;

-- 가장 많이 주문된 제품의 이름과 총 주문 수량을 조회하세요.
SELECT p.product_name  ,  sum( oi.quantity ) sum_quantity
from products p
join order_items oi 
on p.id = oi.product_id
group by p.id;


select  max( sum_quantity )
from (  SELECT p.product_name  ,  sum( oi.quantity ) sum_quantity
			from products p
			join order_items oi 
			on p.id = oi.product_id
			group by p.id   ) sub_table; 




select * 
from ( SELECT p.product_name  ,  sum( oi.quantity ) sum_quantity
			from products p
			join order_items oi 
			on p.id = oi.product_id
			group by p.id ) sub_table
where sum_quantity = (select  max( sum_quantity )
								from (  SELECT p.product_name  ,  sum( oi.quantity ) sum_quantity
											from products p
											join order_items oi 
											on p.id = oi.product_id
											group by p.id   ) sub_table)   ;

-- 주문한 제품 종류가 3개 이상인 고객의 이름과 해당 고객의 주문 횟수를 조회하세요.
SELECT c.customer_name  , count( DISTINCT o.id )
from order_items oi 
join orders o
on oi.order_id = o.id
join customers c 
on o.customer_id = c.id
GROUP BY c.id HAVING count( DISTINCT oi.product_id) >= 3 ;


-- 2023년 10월에 주문한 제품의 이름과 총 판매 수량을 조회하세요.
SELECT product_name , sum(oi.quantity)
from orders o
join order_items oi 
on o.id = oi.order_id
join products p 
on oi.product_id = p.id
where order_date BETWEEN '2023-10-01' and '2023-10-31' 
group by oi.product_id ;

-- 가장 많이 구매한 고객의 이름과 해당 고객이 구매한 총 제품 수량을 조회하세요.
SELECT c.customer_name , sum( oi.quantity ) as sum_quantity
from customers c
join orders o
on c.id = o.customer_id
join order_items oi 
on o.id = oi.order_id
group by c.id
order by sum_quantity desc;


SELECT max(sum_quantity)
from  (SELECT c.customer_name , sum( oi.quantity ) as sum_quantity
			from customers c
			join orders o
			on c.id = o.customer_id
			join order_items oi 
			on o.id = oi.order_id
			group by c.id
			order by sum_quantity desc) as sub_table ; 

SELECT * 
from (SELECT c.customer_name , sum( oi.quantity ) as sum_quantity
			from customers c
			join orders o
			on c.id = o.customer_id
			join order_items oi 
			on o.id = oi.order_id
			group by c.id
			order by sum_quantity desc) as sub_table
where sum_quantity = (SELECT max(sum_quantity)
									from  (SELECT c.customer_name , sum( oi.quantity ) as sum_quantity
												from customers c
												join orders o
												on c.id = o.customer_id
												join order_items oi 
												on o.id = oi.order_id
												group by c.id
												order by sum_quantity desc) as sub_table) ; 















