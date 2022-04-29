from pymongo import MongoClient
from pprint import pprint
import time
from user import User
from book import Book
from recommendation import Recommendation
import pandas as pd
import random
import types


def connect_to_db():
    client = MongoClient(
        "mongodb+srv://admin:admin@cluster0.l4evp.mongodb.net/mlibrary?retryWrites=true&w=majority"
    )
    db = client.mlibrary
    server_status_result = db.command("serverStatus")
    # pprint(server_status_result)
    return db


def get_users(db):
    users = db["users"]

    all_users = users.find()
    users_tab = []
    for data in all_users:
        users_tab.append(User(data))

    return users_tab


def get_books(db):
    books = db["books"]

    all_books = books.find()
    books_tab = []
    for data in all_books:
        books_tab.append(Book(data))

    return books_tab


def get_prev_recommendations(db):
    recommendations = db["recommendations"]
    current_recommendations = recommendations.find({"deleted": {"$eq": None}}, {})
    recommendations_tab = []
    for data in current_recommendations:
        recommendations_tab.append(Recommendation(data))
    return recommendations_tab


def recommendation_to_dict(recom):
    rDict = {
        "_id": recom._id,
        "userId": recom.userId,
        "bookId": recom.bookId,
        "rating": recom.rating,
        "shouldNotRecommend": recom.shouldNotRecommend,
        "shouldNotRecommendType": recom.shouldNotRecommendType,
        "created": recom.created,
        "deleted": recom.deleted,
    }
    return rDict


def mark_deleted(prev_recommendations):
    for recom in prev_recommendations:
        recom.deleted = round(time.time() * 1000)
        id = recom["_id"]
        pprint(recom.deleted)
        rDict = recommendation_to_dict(recom)
        db["recommendations"].replace_one({"_id": {"$eq": id}}, rDict, upsert=True)


def mark_deleted_if_any(prev_recommendations):
    if len(prev_recommendations) > 0:
        mark_deleted(prev_recommendations)

def create_recom_dict(userId, bookId, created):
    rDict = {
        
        "userId": userId,
        "bookId": bookId,
        "created": created,
        "rating": None,
        "shouldNotRecommend": None,
        "shouldNotRecommendType": None
     
    }
    return rDict

def save_recom(db, users):
    users_df = pd.DataFrame(map(vars, users))
    grouped_users_df = users_df.groupby("userId")
    keys = grouped_users_df.groups.keys()
    books_size = len(books)
    num_of_recoms=2
    for key in keys:
        for x in range(num_of_recoms):
            random_number = random.randint(0, books_size-1)   
            user_book = books[random_number]
            rDict = create_recom_dict(key, user_book.id, round(time.time() * 1000))
            inserted = db["recommendations"].insert_one(rDict)


db = connect_to_db()
users = get_users(db)
books = get_books(db)
prev_recommendations = get_prev_recommendations(db)
mark_deleted_if_any(prev_recommendations)
save_recom(db, users)





# ids_tab = []
# for user in users:
#     ids_tab.append(user.userId)
# users_ids = list(dict.fromkeys(ids_tab))

#iterating over df
# for single in grouped_users_df:
#     single[1].reset_index() 
#     for index, row in single[1].iterrows():
#         b = row
