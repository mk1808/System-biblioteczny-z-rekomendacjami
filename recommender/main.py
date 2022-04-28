from pymongo import MongoClient
from pprint import pprint



client = MongoClient("mongodb+srv://admin:admin@cluster0.l4evp.mongodb.net/mlibrary?retryWrites=true&w=majority")
db = client.mlibrary
serverStatusResult=db.command("serverStatus")
pprint(serverStatusResult)

