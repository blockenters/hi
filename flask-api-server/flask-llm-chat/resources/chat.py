from flask_restful import Resource
from flask import request
from huggingface_hub import InferenceClient
from config import Config

class ChatResource(Resource):
    def post(self):
        # {
        #     "question": "베트남 여행지 좋은곳 3곳 알려줘."
        # }
        # 1. 클라언트로부터 데이터를 받아온다.
        data = request.get_json()
        question = data['question']

        # 2. 허깅페이스의 API를 호출해서 결과 받아온다.
        client = InferenceClient(
            provider="hf-inference",
            api_key= Config.HF_API_KEY
        )
        

        messages = [
            {
                "role": "user",
                "content": question
            },
            {
                "role" : "system",
                "content" : "당신은 한국사람입니다. 대답은 한글로해주세요. 친구에게 얘기하듯 대답해줘."
            }
        ]

        completion = client.chat.completions.create(
            model=Config.HF_MODEL, 
            messages=messages, 
            max_tokens=1024,
        )

        return {"status":"success", 
                "answer" : completion.choices[0].message.content}




