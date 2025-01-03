INSERT INTO Users (user_email, user_password, user_name) VALUES
('kimmin92@naver.com', '$2a$10$xxxxxxxxxxx1', '김민준'),
('parkji12@gmail.com', '$2a$10$xxxxxxxxxxx2', '박지우'),
('leeyun89@naver.com', '$2a$10$xxxxxxxxxxx3', '이윤서'),  -- 주문 안 함
('choihs@gmail.com', '$2a$10$xxxxxxxxxxx4', '최현서'),  -- 10번 주문
('kimsy.dev@naver.com', '$2a$10$xxxxxxxxxxx5', '김서연'),
('junghm@kakao.com', '$2a$10$xxxxxxxxxxx6', '정하민'),  -- 주문 안 함
('yang.dh@gmail.com', '$2a$10$xxxxxxxxxxx7', '양도현'),
('songjh@naver.com', '$2a$10$xxxxxxxxxxx8', '송지현'),
('kangdm@gmail.com', '$2a$10$xxxxxxxxxxx9', '강도민'),
('parksb1@kakao.com', '$2a$10$xxxxxxxxxx10', '박수빈'),
('leejw.dev@gmail.com', '$2a$10$xxxxxxxxxx11', '이지원'),
('kimms93@naver.com', '$2a$10$xxxxxxxxxx12', '김민서'),
('choijy@gmail.com', '$2a$10$xxxxxxxxxx13', '최진영'),  -- 주문 안 함
('parkje90@kakao.com', '$2a$10$xxxxxxxxxx14', '박지은'),
('yoosy@naver.com', '$2a$10$xxxxxxxxxx15', '유서연'),
('leedh12@gmail.com', '$2a$10$xxxxxxxxxx16', '이동현'),
('kimdw@kakao.com', '$2a$10$xxxxxxxxxx17', '김다원'),
('hwangjh@naver.com', '$2a$10$xxxxxxxxxx18', '황재현'),
('kimyj88@gmail.com', '$2a$10$xxxxxxxxxx19', '김유진'),
('parkcy@kakao.com', '$2a$10$xxxxxxxxxx20', '박찬영'),
('leejs.dev@naver.com', '$2a$10$xxxxxxxxxx21', '이준서'),
('seomg@gmail.com', '$2a$10$xxxxxxxxxx22', '서민규'),
('choibk@kakao.com', '$2a$10$xxxxxxxxxx23', '최보경'),
('shinje@naver.com', '$2a$10$xxxxxxxxxx24', '신지은'),
('kimgh12@gmail.com', '$2a$10$xxxxxxxxxx25', '김규현'),
('parksy90@kakao.com', '$2a$10$xxxxxxxxxx26', '박수연'),
('leejh93@naver.com', '$2a$10$xxxxxxxxxx27', '이지호'),
('jungjw@gmail.com', '$2a$10$xxxxxxxxxx28', '정재우'),
('kangms@kakao.com', '$2a$10$xxxxxxxxxx29', '강민수'),
('ohsy.dev@naver.com', '$2a$10$xxxxxxxxxx30', '오서영');

INSERT INTO Products (product_name, product_price, stock_quantity) VALUES 
('Apple 맥북프로 13인치', 1690000, 85),
('Samsung Galaxy S24 Ultra', 1590000, 120),
('Apple iPad Air 5세대', 929000, 90),
('Apple 에어팟 프로 2세대', 359000, 150),
('Galaxy Watch 6 Classic', 498000, 75),
('LG 그램 16인치', 1890000, 65),
('Apple 아이폰 15 Pro', 1550000, 110),
('Samsung Galaxy Tab S9', 1159000, 70),
('Apple 매직 키보드', 189000, 95),
('Apple 매직 마우스', 119000, 100),
('Dell 34인치 커브드 모니터', 590000, 45),
('Bose QC 45 헤드폰', 429000, 60),
('Samsung 49인치 게이밍 모니터', 1590000, 40),
('Apple 맥 미니 M2', 850000, 55),
('Sony WH-1000XM5 헤드폰', 499000, 80),
('Logitech MX Master 3S', 149000, 130),
('Keychron K3 키보드', 159000, 85),
('Apple 에어팟 맥스', 769000, 60),
('Samsung 외장 SSD T7', 169000, 150),
('Microsoft Surface Pro 9', 1890000, 50),
('LG 4K 모니터 27인치', 549000, 70),
('Apple 매직 트랙패드', 205000, 90),
('Razer BlackWidow 키보드', 209000, 75),
('Logitech HD Pro 웹캠', 159000, 110),
('Anker 20000mAh 보조배터리', 89000, 200),
('Apple 맥세이프 충전기', 89000, 180),
('Sony WF-1000XM5 이어버드', 329000, 95),
('Samsung 27인치 모니터', 329000, 85),
('Apple 에어팟 3세대', 269000, 140),
('Microsoft Xbox 컨트롤러', 69000, 90),
('Dell 24인치 모니터', 259000, 80),
('Apple 펜슬 2세대', 189000, 120),
('Logitech G Pro X 마우스', 149000, 100),
('Samsung 무선 충전 패드', 59000, 150),
('JBL Flip 6 블루투스 스피커', 159000, 70),
('Apple 20W 충전어댑터', 25000, 250),
('Razer Kraken 헤드셋', 119000, 85),
('LG 블루투스 스피커', 129000, 65),
('Apple 가죽 케이스', 79000, 180),
('Samsung 무선 이어폰', 229000, 110),
('Microsoft Surface 키보드', 159000, 60),
('Apple 맥세이프 보조배터리', 149000, 130),
('LG 게이밍 모니터 32인치', 549000, 55),
('Logitech 무선 키보드', 119000, 95),
('Samsung 스마트 태그', 39000, 200),
('Apple 에어태그', 45000, 220),
('Microsoft Surface 펜', 139000, 75),
('Samsung 고속 충전기', 45000, 180),
('Anker 무선 충전 스탠드', 59000, 140),
('Apple 맥세이프 듀오 충전기', 189000, 90);

INSERT INTO Orders (user_id, order_status, order_date) VALUES
(4, 'ORDER', '2024-03-25 14:23:11'),
(4, 'ORDER', '2024-03-20 11:35:42'),
(4, 'ORDER', '2024-03-15 17:22:31'),
(4, 'ORDER', '2024-03-10 09:15:22'),
(4, 'ORDER', '2024-03-05 16:44:55'),
(4, 'ORDER', '2024-02-28 13:12:34'),
(4, 'ORDER', '2024-02-25 10:24:17'),
(4, 'ORDER', '2024-02-20 15:36:48'),
(4, 'ORDER', '2024-02-15 12:48:29'),
(4, 'ORDER', '2024-02-10 18:19:53'),
(2, 'ORDER', '2024-03-24 11:23:45'),
(2, 'ORDER', '2024-03-15 16:42:31'),
(2, 'ORDER', '2024-03-05 09:34:22'),
(2, 'ORDER', '2024-02-25 14:55:33'),
(2, 'ORDER', '2024-02-15 13:24:45'),
(5, 'ORDER', '2024-03-23 15:34:21'),
(5, 'ORDER', '2024-03-10 12:45:32'),
(5, 'ORDER', '2024-02-28 17:23:44'),
(5, 'ORDER', '2024-02-20 10:11:23'),
(1, 'ORDER', '2024-03-22 09:23:41'),
(1, 'ORDER', '2024-03-12 14:34:52'),
(1, 'ORDER', '2024-02-27 11:22:33'),
(7, 'ORDER', '2024-03-21 16:45:32'),
(7, 'ORDER', '2024-03-08 13:22:44'),
(7, 'ORDER', '2024-02-24 10:33:55'),
(8, 'ORDER', '2024-03-20 12:34:56'),
(8, 'ORDER', '2024-03-05 15:45:22'),
(9, 'ORDER', '2024-03-19 14:23:33'),
(9, 'ORDER', '2024-02-28 11:34:44'),
(10, 'ORDER', '2024-03-18 16:55:21'),
(10, 'ORDER', '2024-03-01 13:22:33'),
(11, 'ORDER', '2024-03-17 10:11:22'),
(11, 'ORDER', '2024-02-25 17:33:44'),
(12, 'ORDER', '2024-03-16 15:44:55'),
(12, 'ORDER', '2024-02-22 12:22:33'),
(14, 'ORDER', '2024-03-15 11:22:33'),
(14, 'ORDER', '2024-02-20 16:33:44'),
(15, 'ORDER', '2024-03-14 13:44:55'),
(15, 'ORDER', '2024-02-18 10:11:22'),
(16, 'ORDER', '2024-03-13 17:55:44'),
(16, 'ORDER', '2024-02-15 14:22:33'),
(17, 'ORDER', '2024-03-12 12:33:44'),
(17, 'ORDER', '2024-02-12 15:44:55'),
(18, 'ORDER', '2024-03-11 16:22:33'),
(18, 'ORDER', '2024-02-10 13:33:44'),
(19, 'ORDER', '2024-03-10 10:44:55'),
(19, 'ORDER', '2024-02-08 17:22:33'),
(20, 'ORDER', '2024-03-09 15:33:44'),
(20, 'ORDER', '2024-02-05 12:44:55'),
(21, 'ORDER', '2024-03-08 11:22:33'),
(22, 'ORDER', '2024-03-07 16:33:44'),
(23, 'ORDER', '2024-03-06 13:44:55'),
(24, 'ORDER', '2024-03-05 10:22:33'),
(25, 'ORDER', '2024-03-04 17:33:44'),
(26, 'ORDER', '2024-03-03 14:44:55'),
(27, 'ORDER', '2024-03-02 11:22:33'),
(28, 'ORDER', '2024-03-01 16:33:44'),
(29, 'ORDER', '2024-02-29 13:44:55'),
(30, 'ORDER', '2024-02-28 10:22:33'),
(2, 'COMPLETE', '2024-01-25 14:55:33'),
(4, 'COMPLETE', '2024-01-20 15:36:48'),
(5, 'COMPLETE', '2024-01-28 17:23:44'),
(7, 'COMPLETE', '2024-01-24 10:33:55'),
(9, 'CANCEL', '2024-01-28 11:34:44'),
(11, 'COMPLETE', '2024-01-25 17:33:44'),
(14, 'CANCEL', '2024-01-20 16:33:44'),
(16, 'COMPLETE', '2024-01-15 14:22:33'),
(18, 'COMPLETE', '2024-01-10 13:33:44'),
(20, 'CANCEL', '2024-01-05 12:44:55');

INSERT INTO OrderItems (order_id, product_id, order_quantity) VALUES
(1, 1, 1),    
(1, 9, 1),    
(1, 10, 1),   
(2, 7, 1),    
(2, 26, 2),   
(3, 3, 1),   
(3, 32, 1),   
(4, 18, 1),   
(4, 36, 2),   
(5, 8, 1),     
(6, 15, 1),    
(7, 19, 2),    
(8, 4, 1),    
(9, 34, 3),    
(10, 27, 1),   
(11, 2, 1),    
(11, 5, 1),    
(12, 13, 1),   
(13, 40, 2),  
(14, 28, 1),   
(15, 45, 3),  
(16, 6, 1),    
(16, 16, 1),  
(17, 21, 1),   
(18, 38, 1),  
(19, 43, 1),  
(20, 14, 1),   
(20, 22, 1),   
(21, 29, 1),  
(22, 39, 2),   
(23, 17, 1),  
(23, 33, 1),   
(24, 24, 1),   
(25, 44, 2),   
(26, 11, 1),   
(27, 12, 1),  
(28, 20, 1),   
(29, 23, 1),   
(30, 25, 2),   
(31, 30, 2),  
(32, 31, 1),   
(33, 35, 2),   
(34, 37, 1),   
(35, 41, 1),   
(36, 42, 1),   
(37, 46, 2),   
(38, 47, 3),  
(39, 48, 2),   
(40, 49, 1),    
(41, 1, 1),   
(42, 7, 1),   
(43, 3, 1),    
(44, 5, 1),    
(45, 8, 1),    
(46, 15, 1),   
(47, 19, 2),   
(48, 4, 1),    
(49, 34, 3),   
(50, 27, 1),  
(51, 2, 1),    
(52, 6, 1),    
(53, 13, 1),   
(54, 21, 1),   
(55, 28, 1),   
(56, 14, 1),   
(57, 17, 1),   
(58, 11, 1),  
(59, 20, 1),  
(60, 23, 1);   
