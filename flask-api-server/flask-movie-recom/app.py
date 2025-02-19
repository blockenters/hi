from flask import Flask
from flask_restful import Api

from resources.recommend import RecommendResource

app = Flask(__name__)
api = Api(app)

# 경로와 리소스를 연결한다.
api.add_resource( RecommendResource  , '/movie/recommend')

if __name__ == '__main__' :
    app.run()




