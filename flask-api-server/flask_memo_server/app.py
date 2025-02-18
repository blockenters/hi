from flask import Flask
from flask_restful import Api

from resources.memo import MemoDetailResource, MemoResource

app = Flask(__name__)

api = Api(app)

# 경로(path) 와 Resource 를 연결하는 부분.
api.add_resource(MemoResource, '/memo')
api.add_resource(MemoDetailResource, '/memo/<int:memo_id>')

if __name__=='__main__' :
    app.run()



