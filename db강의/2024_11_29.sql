use test6;

select  REPLACE( title , ' ' , '->' ) title
from books;

SELECT author_lname as forwards ,  REVERSE( author_lname ) as backwards 
from books;

select  UPPER(  CONCAT(  author_fname, ' '  ,author_lname )  ) as 'full name in caps'   
from books;

select  CONCAT( title, ' was released in ' ,released_year ) as blurb
from books;


SELECT title , CHAR_LENGTH(title) as 'character count'
from books;


SELECT  CONCAT(  SUBSTR( title , 1, 10  ) , '...'  ) as 'short title'  , 
			CONCAT(  author_lname, ',' ,author_fname    ) as author, 
			CONCAT( stock_quantity , ' in stock' ) as quantity
from books;


INSERT INTO employees (name, position, email, phone_number, salary) VALUES
('김철수', 'Manager', 'chulsoo.kim@example.com', '010-1234-5678', 5000000),
('이영희', 'Developer', 'younghee.lee@example.com', '010-2345-6789', 4000000),
('박준혁', 'Designer', 'junhyuk.park@example.com', '010-3456-7890', 3500000),
('최수진', 'Tester', 'sujin.choi@example.com', '010-4567-8901', 3200000),
('정우진', 'HR', 'woojin.jung@example.com', '010-5678-9012', 3000000);


SELECT * 
from employees;

insert INTO employees (name, position, email, phone_number, salary)
values ('한지수', 'Developer', 'jisu.han@example.com', '010-6789-0123', 3800000);

SELECT name, email,  
		  REPLACE( phone_number,    SUBSTR( phone_number, 5, 4 )  , '****'    ) as  phone_number      
from employees;


SELECT name, position 
from employees
WHERE salary >= 3500000;



SELECT *
from employees;

UPDATE employees
set position = 'Senior Developer' , salary = 4500000
where name = '이영희';


DELETE from employees
WHERE salary < 3000000;


SELECT  CONCAT( name, ' - '  ,position ) as info
from employees;

SELECT   SUBSTRING( name , 2, 2 ) as name
from employees;



SELECT  CHAR_LENGTH( email ) as email_length
from employees;




INSERT INTO subscriptions (user_name, plan_name, start_date, end_date, status, price) VALUES
('김철수', 'Premium', '2023-01-01', '2023-12-31', 'Active', 12000),
('이영희', 'Basic', '2023-06-01', '2023-11-30', 'Expired', 6000),
('박준혁', 'Standard', '2023-07-01', '2023-12-31', 'Active', 9000),
('최수진', 'Premium', '2023-01-01', '2023-12-31', 'Active', 12000),
('정우진', 'Basic', '2023-02-01', '2023-07-31', 'Expired', 6000),
('한지수', 'Standard', '2023-03-01', '2023-08-31', 'Expired', 9000),
('김지훈', 'Premium', '2023-04-01', '2023-12-31', 'Active', 12000),
('이민영', 'Basic', '2023-05-01', '2023-10-31', 'Expired', 6000),
('장서현', 'Standard', '2023-06-01', '2023-12-31', 'Active', 9000),
('박다연', 'Premium', '2023-01-01', '2023-12-31', 'Active', 12000);


SELECT *
from subscriptions;

INSERT INTO subscriptions (user_name, plan_name, start_date, end_date, status, price)
values ('유진우', 'Standard', '2023-09-01', '2024-08-31', 'Active', 9000);

select user_name, plan_name, end_date
from subscriptions
WHERE status = 'Expired';

SELECT user_name, price
from subscriptions
WHERE price >= 8000 ;


SELECT *
from subscriptions;

UPDATE subscriptions
SET  status = 'Active' , end_date = '2023-12-31'
where user_name = '이영희';

UPDATE subscriptions
set price = 13000
where plan_name = 'Premium';


DELETE FROM subscriptions
where status = 'Expired';




SELECT  CONCAT(  user_name, ' - '  , plan_name ) as name_plan
from subscriptions;


SELECT  SUBSTR( plan_name , 1, 3 ) as plan
from subscriptions;







insert into books
(title, author_fname, author_lname, released_year, stock_quantity,
pages)
values
('10% Happier', 'Dan', 'Harris', 2014, 29, 256),
('fake_book', 'Freida', 'Harris', 2001, 287, 428),
('Lincoln In The Bardo', 'George', 'Saunders', 2017, 111, 388);


SELECT *
from books;


-- MySQL에서 사용하는 키워드 ( 키워드는 함수가 아니다)
-- author_lname 컬럼의 데이터는 중복 데이터가 있습니다.
-- 중복을 제거해서, 유니크 하게 가져오자. distinct 키워드
SELECT DISTINCT author_lname 
from books;

SELECT DISTINCT  concat( author_fname, ' ' ,author_lname ) as full_name
from books;

-- 정렬하는 키워드. order by 

-- author_lname 으로 정렬해서 가져오기 - 오름차순 정렬
SELECT * 
from books
order by author_lname;

SELECT * 
from books
order by author_lname ASC;

SELECT * 
from books
order by author_lname DESC;

-- full_name 으로 내림차순 정렬
SELECT *,  concat( author_fname,' ', author_lname ) as full_name
from books
order by full_name desc;

-- 출간년도 내림차순으로 정렬하여서, 책제목,출간년도,페이지수를 가져오세요.
 SELECT title, released_year, pages
 from books
order by released_year desc;

-- 정렬은, 여러개가 가능하다.
-- author_lname 으로 정렬하되, lname 이 같으면,  author_fname으로 정렬하자.
SELECT *
from books
order by author_lname ,  author_fname;


-- 정렬은, 여러개가 가능하다.
-- author_lname은 내림차순으로 정렬하되, 
-- lname 이 같으면,  author_fname은 오름차순 으로 정렬하자.

SELECT *
from books
order by author_lname desc , author_fname asc; 

-- 데이터를 끊어서 가져오는 방법 : limit 키워드  
-- books 테이블의 데이터를, 5개만 가져오세요.
SELECT *
from books
limit 5;

-- offset !!  offset 이란, 데이터를 가져오는 시작점!
-- books 테이블의 데이터를, 처음부터 5개 가져오세요.
-- 0 , 5 에서 처음의 0은, 처음부터라는 뜻!  두번째의 5는, 5개씩 가져오라는 뜻.
SELECT *
from books
limit 0 , 5 ;
-- 그 다음의 5개도 가져오시오.
SELECT *
from books
limit 5, 5;

-- 그 다음의 5개도 가져오시오.
SELECT *
from books
limit 10, 5;


SELECT *
from books
limit 15, 5;

-- 출간년도 내림차순으로 정렬하여, 처음부터 7개의 데이터를 가져오시오.
SELECT *
from books
order by released_year DESC 
limit 14, 7;

-- 문자열 안에, 원하는 문자열이 들어있는지 검색 하는 키워드 : like
-- 책 제목에 the 가 들어있는 책 데이터를 가져오시오.
SELECT *
from books
where title like '%the%';

-- 책 제목에 the 로 시작하는 책 데이터를 가져오시오.
SELECT *
from books
where title like 'the%';

-- 책 제목이 e 로 끝나는 책 데이터를 가져오시오.
SELECT *
from books
where title like '%e';

-- stock_quantity 의 숫자가, 두자리 수인 데이터만 가져오시오.
SELECT *
from books
where stock_quantity like '__';

-- 실습문제
SELECT title
from books
where title like '%stories%';

SELECT title, pages
from books
order by pages desc
limit 1;

SELECT  CONCAT(  title, ' - ' ,released_year  ) as summary
from books
order by released_year desc
limit 3;

SELECT title, author_lname
from books
where author_lname like '% %'   ;


SELECT title, released_year, stock_quantity
from books
order by stock_quantity asc
limit 3;


SELECT title, author_lname
from books
order by author_lname asc, title asc ;


SELECT  UPPER( concat( 'my favorite author is '  ,author_fname, ' ' ,author_lname ) )  as yell        
from books
order by author_lname asc;

-- 숫자 처리

-- 데이터의 갯수를 세는 함수 : count() 
-- books 테이블에 책 데이터가 들어있다. 책은 총 몇권인가?

SELECT  count( * )  
from books;

SELECT * 
from books;

-- (author_lname) 책 쓴사람은 총 몇명이냐.
SELECT   COUNT(  DISTINCT author_lname )
from books;


-- 책 제목에 the 가 들어간 책은 몇권입니까?
SELECT  count( * ) 
from books
where title like '%the%';

-- ~ 별로 묶어서 처리하는 경우 : 집계! => group by 키워드 

-- author_lname 별로 몇권의 책을 썼는지,
-- 작가의author_lname과 책의갯수를 보여주세요.

SELECT  author_lname  , COUNT(*) as book_cnt
FROM books
group by  author_lname ;

-- 년도별로 각각 몇권의 책이 출간되었는지,
-- 년도와 그년도의 출간된 책의 갯수를 보여주세요.
SELECT  released_year  , count(*) as book_cnt
from books
group by released_year
order by released_year desc;

-- 값을 더해주는 함수 sum() 
-- books 테이블의 모든 책의 페이지수를 다 더하면?
SELECT  SUM( pages )
from books;

-- 각 작가별로 자신이 쓴 책의 페이지수를 다 더하면??
SELECT  author_lname  , sum( pages )
from books
group by author_lname ;

-- 평균 구하는 함수 avg()
-- books 테이블의 페이지수 평균은??
SELECT avg( pages )
from books;

-- 각 작가별로 자신이 쓴책의 평균 페이지수를 알고 싶다.
SELECT author_lname, AVG( pages ) as page_avg
from books
group by author_lname
order by  page_avg desc;

use test6;

select * 
from books;

-- 최대값을 구하는 함수  :  max() 
-- 페이지수가 가장 많은 책은, 몇페이지인가?

SELECT max( pages )
from books;

-- 최소값을 구하는 함수 : min()
-- 페이지 컬럼에서, 가장 적은 페이지수는??
SELECT  min( pages )
from books;

-- 페이지 컬럼의 최대값, 최소값, 토탈값, 평균값을 보여주세요.
SELECT max( pages ) as max ,  
			min( pages ) as min,  
			sum(  pages ) as sum , 
			avg( pages) as avg
from books;

-- 페이지수가 가장 긴 책의,  제목, 작가이름, 페이지수 를 보여주세요.

-- 해결책 1 : 정렬해서 해결 
SELECT  pages, title, author_fname, author_lname 
from books
order by pages desc
limit 1;

-- 해결책 2 : pages 의 max 값을 구해서, Sub Query 해서 해결한다. 
SELECT *
from books;

-- 1. 페이지 컬럼의 맥스값을 찾고  => 634
SELECT max( pages )
from books;

-- 2. pages 컬럼이, 위의 맥스값은 634 인 데이터를 가져오시오.
SELECT title, author_fname, author_lname, pages
from books
where pages = 634;

-- 위의 2개의 문장을 한개의 문장으로 만드는거! : Sub Query 
SELECT title, author_fname, author_lname, pages
from books
where pages = (SELECT max( pages ) from books) ;


-- 재고 (stock_quantity) 가 가장 적은 책의, 책 이름과 발행년도를 보여주세요. 
SELECT title, released_year
FROM books
order by stock_quantity asc
limit 1;

SELECT min( stock_quantity )
from books;
-- 12 

SELECT title, released_year
from books
where stock_quantity = (SELECT min( stock_quantity ) from books);

-- 출간년도가 2017년도인 데이터를 가져오시오.
SELECT *
FROM books
WHERE released_year = 2017;

-- 출간년도가 2017년이 아닌 데이터를 가져오시오. 
SELECT *
FROM books
WHERE released_year != 2017;

-- 작가의 lname 이 'Harris'  가 아닌 데이터를 가져오되,
-- 책 제목과 페이지수만 가져오시오.
SELECT title, pages
FROM books
WHERE author_lname != 'Harris';

-- 책 제목에 W 가 포함된 책을 가져오시오.
SELECT *
FROM books
WHERE title  like  '%W%';

-- 책 제목에 W가 들어있지 않은 책을 가져오시오.
SELECT *
FROM books
WHERE title  not like  '%W%';

-- 책의 재고가 100개 이상인 데이터를 가져오되, 
-- 책 제목과 재고만 보여주세요.
SELECT title, stock_quantity
from books
WHERE stock_quantity >= 100;

-- 출간년도가 2000년 이상인 책에 대해서,
-- 최신책으로 정렬해서
-- 제목과 년도를 보여주세요.
SELECT title, released_year
from books
WHERE released_year >=  2000
ORDER BY released_year desc;

-- 신규 데이터 추가
INSERT INTO books
(title, author_fname, author_lname, released_year, pages)
values
('좋은책', '길동', '홍', 2024, 214);

SELECT *
from books;

-- 재고가 null 인 데이터를 가져오시오. 
SELECT *
FROM books
WHERE stock_quantity  is null;

-- 재고가 null 이 아닌 데이터를 가져오시오. 
SELECT *
FROM books
WHERE stock_quantity  is not null;

-- 출간년도가 1990년에서 2015년 사이의 책 데이터를 가져오시오.
SELECT *
from books
WHERE  released_year >= 1990  and released_year <= 2015;

SELECT *
from books
where released_year BETWEEN 1990 and 2015;

-- 책 재고가 100보다 크거나, 30보다 작은 데이터만 가져오세요.
SELECT *
from books
WHERE stock_quantity > 100  or stock_quantity < 30 ;

-- 년도별 stock_quantity 의 평균값이 70보다 큰 데이터를 가져와서
-- 년도와 평균값을 보여주세요.
SELECT  released_year  , avg( stock_quantity ) as avg
from books
group by released_year HAVING avg > 70; 

-- 위의 결과에서 avg 가 큰것부터 나오도록 정렬하세요.
SELECT  released_year  , avg( stock_quantity ) as avg
from books
group by released_year HAVING avg > 70
ORDER by avg desc;

-- 출간년도가 2000년 이상인 데이터에서,
-- 년도별 재고수량의 평균값이 70보다 큰 데이터의
-- 년도와 평균값을 보여주되, 
-- 평균값이 높은 것부터 정렬해서 보여주세요.
SELECT released_year , avg( stock_quantity ) as stock_avg
from books
WHERE released_year >= 2000
GROUP BY released_year HAVING stock_avg > 70
ORDER BY stock_avg desc;


-- 실습문제
SELECT  plan_name , count( * ) as cnt
from subscriptions
group by plan_name;

SELECT status , sum( price ) as total_price
from subscriptions
order by status;

SELECT plan_name, avg( price ) as avg_price
from subscriptions
group by plan_name;


SELECT plan_name ,count( * ) as cnt
from subscriptions
group by plan_name HAVING  cnt >= 2;

SELECT  status , sum( price ) as total_price
from subscriptions
group by status HAVING total_price >= 30000;

SELECT plan_name, count(*) as cnt
from subscriptions
WHERE start_date > '2023-06-01'
group by plan_name HAVING cnt >= 2
order by cnt desc;


SELECT plan_name , sum(price) as total_price
from subscriptions
WHERE status = 'Active'
group by plan_name HAVING total_price >= 20000
order by total_price desc;


-- 데이터를 가공할때, 2가지 상황에 대해서 처리하는 함수 : if()
-- books 테이블에서, 페이지 수가 300페이지 이상인 책은 '긴책' 이라고 하고
-- 그렇지 않으면, '짧은책' 이라고 하자. 컬럼 이름은 book_type 으로 한다.

SELECT * ,   if( pages >= 300 , '긴책', '짧은책'   ) as book_type
FROM books;

-- 출간년도가 2000년 이상인 책들은, '최신책' 이라고 하고
-- 그렇지 않으면 '예전책' 이라고 새로운 컬럼을 만드세요. 컬럼명은 type
SELECT * , if( released_year >= 2000 ,  "최신책" ,  "예전책"  ) as type
from books;

-- 3개 이상으로 분류하는 경우
-- stock_quantity 가 0이상이고 50이하면,  * 
-- stock_quantity 가 51 이상이고 100이하면, **
-- stock_quantity 가 100이상이면,  *** 
-- 새로운 컬럼 stock 을 만들어서 보여주세요.
select * , 
	CASE 
		when stock_quantity >=0 and stock_quantity <= 50 then '*'
		when stock_quantity >= 51 and stock_quantity <= 100 then '**'
		else '***'
	END	as stock
from books;

select * , 
	CASE 
		when stock_quantity between 0 and 50 then '*'
		when stock_quantity BETWEEN 50 and 100 then '**'
		else '***'
	END	as stock
from books;

-- stock_quantity 에 null 이 있으면,  0으로 나오게, 새로운 컬럼 stock_quantity2 
-- 를 만드세요.

-- null 이 있으면, 다른 값으로 셋팅하는 함수 : ifnull() 
SELECT *, IFNULL( stock_quantity, 0 ) 
from books;

SELECT *
from books
WHERE released_year < 1980;

SELECT *
from books
where author_lname = 'Eggers' or author_lname = 'Chabon' ;

SELECT *
FROM books
WHERE author_lname in ('Eggers', 'Chabon');

SELECT *
from books
WHERE author_lname = 'Lahiri'  and released_year > 2000  ;

SELECT *
from books
WHERE pages >= 100 and pages <= 200;

SELECT *
from books
where pages BETWEEN 100 and 200;


SELECT *
from books
WHERE author_lname  like 'c%'  or  author_lname like 's%'   ;



SELECT title, author_lname , 
	CASE 
		when title like '%stories%'  then 'Short Stories'
		when title = 'Just Kids' or title like '%heartbreaking%'   then 'Memoir' 
		else 'Novel'
	END as TYPE	
from books;


SELECT  title , author_lname , 
	if(  count(*) = 1,  concat( count(*), ' book' ) ,  concat( count(*) , ' books' ) ) as COUNT
from books
group by author_fname, author_lname;



SELECT  released_year as year  , count( * ) as '# books', avg(pages) as 'avg pages'
from books
group by released_year
order by released_year asc;


select * 
from orders;

-- 현재 날짜와 시간을 조회 : now()
SELECT now();

-- 현재 날짜만 조회 : curdate()
SELECT CURDATE(); 

-- 현재 시간만 조회 : curtime()
SELECT CURTIME(); 

-- orders 테이블에서, order_date 에서 년도만 가져오시오. year()
-- 월만 가져오는것 month()
SELECT * ,   year( order_date ) ,  MONTH( order_date ) ,
			day(	order_date ),  HOUR(order_date), MINUTE(order_date), SECOND(order_date)
from orders;

-- 두 날짜간의 차이 계산 : datediff()
-- 주문일과 배송일의 일수 계산
SELECT * , DATEDIFF(delivery_date, order_date) as diff
from orders;

-- 시간 차이 계산 : timediff()
SELECT * , TIMEDIFF('2023-10-05 15:00:00', order_date) as diff
from orders;






































































































