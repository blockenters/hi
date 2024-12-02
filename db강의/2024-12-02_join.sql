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








