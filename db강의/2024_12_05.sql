-- 영화 리뷰 서비스 개발

-- 화면을 보고, 필요한 sql 문을 작성합니다.

-- 회원가입에 필요한 SQL 
use movie_db2;

INSERT INTO user (email, password, nickname, gender)
values ('abc@naver.com', '1234', '홍길동', 1);

-- 리뷰 작성 화면에 필요한 SQL
-- 영화 아이디, 유저 아이디, 유저가 준 별점, 유저가 작성한 내용을 프론트엔드
-- 개발자가 서버 개발자에게 보내준다.

INSERT INTO review (movieId, userId, rating, content)
values ( 1, 301, 5, '너무 재밌어요.' );

-- 유저가 어떤 영화의 즐겨찾기(하트모양)를 누르면 
-- 누가, 어떤영화를, 언제 즐겨찾기 했는지 저장해야 한다.
-- 따라서 테이블을 하나 만듭니다.
-- favorite
-- - id 
-- - user_id
-- - movie_id
-- - created_at 

-- 즐겨찾기 하는 SQL 
-- 가정 : 내(301)가  영화아이디 1번을 즐겨찾기 하는 경우
INSERT INTO favorite (user_id, movie_id)
values (301, 1);

-- 즐겨찾기 해제 하는 SQL 
-- 가정 : 내(301)가 영화아이디 1번을 즐겨찾기 해제 하는 경우
DELETE from favorite
where user_id = 301 and movie_id = 1;

-- 내 즐겨찾기 목록 가져오기 
SELECT m.id as movie_id, m.title , 
			count( r.id ) as cnt_review , 
			IFNULL( avg( r.rating ) , 0) avg_rating
from favorite f
join movie m 
on f.movie_id = m.id
left join review r 
on m.id = r.movieId
where user_id = 301
group by m.id;

-- 영화 상세페이지 화면에 사용할 sql 
-- 영화 아이디 2번에 대한 상세 정보를 가져온다.
SELECT m.* , 
		count(r.id) as cnt_review, 
		IFNULL( avg(r.rating) , 0) as avg_rating 
from movie m
left join review r
on m.id = r.movieId
where m.id = 2;


-- 특정 영화에 대한 리뷰 리스트 보여준 화면에 대한 SQL
-- 가정 : 영화 아이디 2번에 대한 리뷰 가져오기.

SELECT r.id as review_id, u.nickname, r.content, r.rating
from review r
join user u 
on r.userId = u.id
where movieId = 2
order by r.created_at desc;

-- 메인페이지 영화 리스트 화면의 SQL 개발
SELECT m.id as movie_id, m.title, 
		count( r.id ) as cnt_review , 
		ifnull( avg( r.rating) , 0 ) as avg_rating,
		if( f.id is null, 0 , 1 ) is_favorite
from movie m
left join review  r 
on m.id = r.movieId
left join favorite f
on m.id = f.movie_id  and f.user_id = 301
group by m.id
order by cnt_review desc
limit 0, 20;



-- 검색 화면 SQL
-- 가정 : 유저가 검색어로 big 이라고 입력하면, 제목에 big 가 포함된 영화 가져온다.
SELECT m.id as movie_id, m.title,  
			count(r.id) as cnt_review , 
			IFNULL( avg(r.rating) , 0)  as avg_rating
from movie m
left join review r
on m.id = r.movieId
where title like '%big%'
group by m.id;


-- 히스토리를 저정하기 위해서 
-- 가정 : 내(유저 아이디 301)가 검색한 검색어를 저장

INSERT INTO history (user_id, keyword)
values (301, 'er');

-- 검색 기록을 보여주기 위한 SQL
SELECT *
from history
where user_id = 301
order by created_at desc;

-- 히스토리 삭제 SQL
DELETE from history
where id = 2 and user_id = 301;


-- 내 정보 화면의 SQL 
-- 가정 : 내 아이디는 301 이다. 
SELECT id as user_id, email, nickname, gender
from user
where id = 301;

-- 가정 : 내아이디는 301이고, 내 리뷰 리스트 가져온다.
SELECT r.id as review_id, m.title, r.rating
from review r
join movie m
on r.movieId = m.id
where userId = 301;







