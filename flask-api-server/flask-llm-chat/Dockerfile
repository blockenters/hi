# Python 3.10 에서 테스해서 잘 돌아간다.
# 따라서, 도커소프트웨어에게 파이썬 3.10버전이고 aws의 람다와 같은 서버 준비하라고 한다.
FROM public.ecr.aws/lambda/python:3.10

# 라이브러리 설치를 위해서 requirements.txt 를 컨테이너에 복사.
COPY requirements.txt ./

# 설치위한 파일이 있으니까, 설치하도록 한다.
RUN pip install --no-cache-dir -r requirements.txt --target "${LAMBDA_TASK_ROOT}"

# 소스코드를 람다의 실행 경로로 복사 
COPY .  ${LAMBDA_TASK_ROOT}

# 람다에서 서버 동작시킨다.
CMD ["lambda_function.lambda_handler"]



