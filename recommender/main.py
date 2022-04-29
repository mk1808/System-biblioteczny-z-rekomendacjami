from pymongo import MongoClient
from pprint import pprint
import time
from user import User
from book import Book
from recommendation import Recommendation

def connect_to_db():
    client = MongoClient(
        "mongodb+srv://admin:admin@cluster0.l4evp.mongodb.net/mlibrary?retryWrites=true&w=majority"
    )
    db = client.mlibrary
    server_status_result = db.command("serverStatus")
    #pprint(server_status_result)
    return db;

def get_users(db):
    users = db["users"]

    all_users = users.find()
    users_tab = []
    for data in all_users:
        users_tab.append(User(data))

    return users_tab;

def get_books(db):
    books = db["books"]

    all_books = books.find()
    books_tab = []
    for data in all_books:
        books_tab.append(Book(data))

    return books_tab;

def get_prev_recommendations(db):
    recommendations = db["recommendations"]
    current_recommendations = recommendations.find({"deleted": {"$eq": None}}, {})
    recommendations_tab = []
    for data in current_recommendations:
        recommendations_tab.append(Recommendation(data))
    return recommendations_tab;

def mark_deleted(recommendations):
    a=0;

db = connect_to_db();
users = get_users(db);
books = get_books(db);
prev_recommendations = get_prev_recommendations(db);
db["recommendations"]
recom1={}
for recom in prev_recommendations:
    recom.deleted = round(time.time() * 1000)
    

    id = recom['_id']
    # db["recommendations"].replace_one({"id": {"$eq": id}}, recom, upsert=True) 
    pprint(recom.deleted)
    recom1 = recom
    
rDict =	{
  "_id": recom1._id,
  "userId": recom1.userId,
  "bookId": recom1.bookId,
  "rating": recom1.rating,
  "shouldNotRecommend": recom1.shouldNotRecommend,
  "shouldNotRecommendType": recom1.shouldNotRecommendType,
  "created": recom1.created,
  "deleted": recom1.deleted,
}
id1 = recom['_id']

db["recommendations"].replace_one({"_id": {"$eq": id1}}, rDict, upsert=True) 
    
    
    

def recommendation_to_dict(recom):
    a=0;



# printing the result
# print("After Converting Dictionary to Class : ")
# print(user.userId, user.action, user.bookId)
# print(type(user))
