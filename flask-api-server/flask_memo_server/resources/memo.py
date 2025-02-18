# 1. Resource 라이브러리 임포트.
from flask_restful import Resource
from flask import request

memo_list = []

# 파이썬에서 상속은, 클래스명 괄호 안에, 상속할클래스 넣어준다.
class MemoResource(Resource) :
    
    def post(self) :
        # {
        #     "title": "점심약속",
        #     "datetime": "2025-02-20 11:40",
        #     "content": "친구랑 햄버거"
        # }

        data = request.get_json()
        
        memo_list.append( data )

        for memo in memo_list :
            print(memo)

        return {"result" : "success"}, 200

    def get(self) :

        page = request.args.get('page')
        size = request.args.get('size')

        ### 중요 : query param 은 문자열로 전부 처리하므로
        ###  숫자인 경우는 숫자로 바꿔줘야 한다.
        page = int(page)
        size = int(size)

        print(type(page))
        print(type(size))

        # size 가 현재 memo_list 의 갯수보다 큰지 작은지 체크 
        if len(memo_list) < size :
            items = memo_list[ page-1 : len(memo_list)  ] 
        else :
            items = memo_list[ page-1 :  size ]

        return { 'count' : len(items) , 'items' : items }



class MemoDetailResource(Resource):
    # 경로로 들어오는 path param은, 함수의 param으로 적는다.
    def get(self, memo_id) :
        
        index = memo_id - 1

        memo = memo_list[index]

        return memo, 200

    def put(self, memo_id) :
        
        index = memo_id - 1        
        
        data = request.get_json()

        del memo_list[index]
        memo_list.insert(index, data)

        # memo_list[index]['title'] = data['title']
        # memo_list[index]['datetime'] = data['datetime']
        # memo_list[index]['content'] = data['content']

        return {"result" : "success"}, 200

    def delete(self, memo_id) :
        
        index = memo_id - 1

        del memo_list[index]

        return {"result" : "success"}, 200


