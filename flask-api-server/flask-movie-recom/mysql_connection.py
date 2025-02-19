import mysql.connector
import os

from config import DevConfig, ProdConfig

def get_connection() :

    # 로컬에서 실행하는지, 서버에서 실행하는지 확인하여
    # 알맞은 Config 클래스를 가져와야 한다.
    if os.environ.get('FLASK_ENV') == 'prod' :
        config = ProdConfig
    else :
        config = DevConfig

    connection = mysql.connector.connect(
        host= config.DB_Host,
        port= config.DB_PORT,
        database= config.DATABASE,
        user= config.DB_USER,
        password= config.DB_PASSWORD
    )
    return connection



