class User(object):
    def __init__(self, obj):  # id, userId, action, bookId, value, date
        self.id = obj["_id"]
        self.getField(obj, "userId")
        self.getField(obj, "action")
        self.getField(obj, "bookId")
        self.getField(obj, "date")
        self.getField(obj, "value")

        
    def getField(self, obj, field):
        if field in obj:
            setattr(self, field, obj[field])






    # def __init__(self, my_dict):

    #     for key in my_dict:
    #         setattr(self, key, my_dict[key])


# if __name__ == "__main__":

#     my_dict = {"Name": "Geeks",
#                "Rank": "1223",
#                "Subject": "Python"}

#     result = User(my_dict)

#     # printing the result
#     print("After Converting Dictionary to Class : ")
#     print(result.Name, result.Rank, result.Subject)
#     print(type(result))
