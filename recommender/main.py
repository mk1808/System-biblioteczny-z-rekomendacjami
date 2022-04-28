from pymongo import MongoClient
from pprint import pprint
from user import User
from book import Book


client = MongoClient(
    "mongodb+srv://admin:admin@cluster0.l4evp.mongodb.net/mlibrary?retryWrites=true&w=majority"
)
db = client.mlibrary
serverStatusResult = db.command("serverStatus")
# pprint(serverStatusResult)

users = db["users"]

allUsers = users.find()
usersTab = []
for data in allUsers:
    usersTab.append(User(data))


books = db["books"]

allBooks = books.find()
booksTab = []
for data in allBooks:
    booksTab.append(Book(data))

# printing the result
# print("After Converting Dictionary to Class : ")
# print(user.userId, user.action, user.bookId)
# print(type(user))
