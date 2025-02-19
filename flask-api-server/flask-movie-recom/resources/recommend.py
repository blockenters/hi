from flask_restful import Resource
from flask import request
from mysql_connection import get_connection
import pandas as pd 
from sklearn.metrics.pairwise import cosine_similarity

class RecommendResource(Resource) :
    
    def get(self):
        
        user_id = request.args.get('user_id')
        size = request.args.get('size')
        review_cnt = request.args.get('review_cnt')

        connection = get_connection()
        query = '''select movie_id, user_id, rating 
                    from review;'''
        review_df = pd.read_sql_query(query, connection)
        query = '''select id as movie_id, title
                    from movie;'''
        title_df = pd.read_sql_query(query, connection)


        # 1. 특정 사용자의 영화 평점 데이터를 가져온다.
        query = f'''select *
                    from review
                    where user_id = {user_id};'''
        user_rating = pd.read_sql_query(query, connection)

        print(user_rating)

        # 2. 이 사람의 평점 데이터가 없는 경우는, 인기영화를 추천해준다.
        if user_rating.shape[0] == 0 :
            # 최신영화리스트를 보여준다ㅏ.
            pass


        return


    def get_popular_movies(self) :
        pass


